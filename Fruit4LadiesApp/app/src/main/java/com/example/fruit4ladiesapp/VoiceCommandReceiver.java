package com.example.fruit4ladiesapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.vuzix.sdk.speechrecognitionservice.VuzixSpeechClient;

import java.util.List;

public class VoiceCommandReceiver extends BroadcastReceiver {
    private MainActivity mMainActivity;
    private VuzixSpeechClient sc;


    public VoiceCommandReceiver(MainActivity iActivity)
    {
        mMainActivity = iActivity;
        mMainActivity.registerReceiver(this, new IntentFilter(VuzixSpeechClient.ACTION_VOICE_COMMAND));
        Log.d(mMainActivity.LOG_TAG, "Connecting to Vuzix Speech SDK");

        try {
            sc = new VuzixSpeechClient(iActivity);
            // Delete specific phrases. This is useful if there are some that sound similar to yours, but
            // you want to keep the majority of them intact
            //sc.deletePhrase("go home");
            //sc.deletePhrase("go back");

            // Delete every phrase in the dictionary! (Available in SDK version 1.3 and newer)
            //
            // Note! When developing applications on the Vuzix Blade and Vuzix M400, deleting all
            // phrases in the dictionary removes the wake-up word(s) and voice-off words. The M300
            // cannot change the wake-up word, so "hello vuzix" is unaffected by the deletePhrase call.
            sc.deletePhrase("*");

            // For Blade and M400, the wake-word can be modified. This call has no impact on the M300,
            // which ignores added/deleted wake words.
            //
            // Please always keep the wake word "hello vuzix" to prevent confusion. You can add additional
            // wake words specific to your application by imitating the code below
            try {
                sc.insertWakeWordPhrase("hello vuzix");      // Add-back the default phrase for consistency (Blade and M400 only)
                sc.insertWakeWordPhrase("hello sample app"); // Add application specific wake-up phrase (Blade and M400 only)
            } catch (NoSuchMethodError e) {
                Log.i(mMainActivity.LOG_TAG, "Setting wake words is not supported. It is introduced in M300 v1.6.6, Blade v2.6, and M400 v1.0.0");
            }

            try {
                // For all platforms, the voice-off phrase can be modified
                sc.insertVoiceOffPhrase("voice off");      // Add-back the default phrase for consistency
                sc.insertVoiceOffPhrase("privacy please"); // Add application specific stop listening phrase
            } catch (NoSuchMethodError e) {
                Log.i(mMainActivity.LOG_TAG, "Setting voice off is not supported. It is introduced in M300 v1.6.6, Blade v2.6, and M400 v1.0.0");
            }

            // See what we've done
            Log.i(mMainActivity.LOG_TAG, sc.dump());

            // The recognizer may not yet be enabled in Settings. We can enable this directly
            VuzixSpeechClient.EnableRecognizer(mMainActivity, true);
        } catch(NoClassDefFoundError e) {
            // We get this exception if the SDK stubs against which we compiled cannot be resolved
            // at runtime. This occurs if the code is not being run on a Vuzix device supporting the voice
            // SDK
            Toast.makeText(iActivity, R.string.only_on_vuzix, Toast.LENGTH_LONG).show();
            Log.e(mMainActivity.LOG_TAG, iActivity.getResources().getString(R.string.only_on_vuzix) );
            Log.e(mMainActivity.LOG_TAG, e.getMessage());
            e.printStackTrace();
            iActivity.finish();
        } catch (Exception e) {
            Log.e(mMainActivity.LOG_TAG, "Error setting custom vocabulary: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void resetCommands() {
        for (Commands command: Commands.values()) {
            sc.deletePhrase(command.getCommand());
        }
    }

    public void registerCommands(List<Commands> commands) {
        for (Commands command: commands) {
            sc.insertPhrase(command.getCommand());
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(mMainActivity.LOG_TAG, mMainActivity.getMethodName());

        if (intent.getAction().equals(VuzixSpeechClient.ACTION_VOICE_COMMAND)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (extras.containsKey(VuzixSpeechClient.PHRASE_STRING_EXTRA)) {
                    String phrase = intent.getStringExtra(VuzixSpeechClient.PHRASE_STRING_EXTRA);
//                    Log.e(mMainActivity.LOG_TAG, mMainActivity.getMethodName() + " \"" + phrase + "\"");
//                    Log.e(mMainActivity.LOG_TAG, Commands.getAllCommands() + "");

                    if (Commands.getAllCommands().contains(phrase)) {
                        mMainActivity.navigate();
                    }
//
//
//                    if (phrase.equals(Commands.MATCH_START.getCommand())) {
//                        mMainActivity.loadFragment(new PackagingFragment());
//                    } else if (phrase.equals(Commands.MATCH_NEXT.getCommand())) {
//                        mMainActivity.loadFragment(new StartFragment());
//                    }
                    else {
                        Log.e(mMainActivity.LOG_TAG, "Phrase not handled");
                    }
                }
            }
        }
        else {
            Log.e(mMainActivity.LOG_TAG, "Other Intent not handled " + intent.getAction() );
        }
    }

}
