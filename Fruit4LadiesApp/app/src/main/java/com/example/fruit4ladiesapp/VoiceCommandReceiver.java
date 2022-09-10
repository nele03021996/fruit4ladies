package com.example.fruit4ladiesapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.vuzix.sdk.speechrecognitionservice.VuzixSpeechClient;

public class VoiceCommandReceiver extends BroadcastReceiver {
    private MainActivity mMainActivity;
    final String MATCH_EDIT_TEXT = "edit_text_pressed"; // 17 char

    public VoiceCommandReceiver(MainActivity iActivity)
    {
        mMainActivity = iActivity;
        mMainActivity.registerReceiver(this, new IntentFilter(VuzixSpeechClient.ACTION_VOICE_COMMAND));
        Log.d(mMainActivity.LOG_TAG, "Connecting to Vuzix Speech SDK");

        try {
            // Create a VuzixSpeechClient from the SDK
            VuzixSpeechClient sc = new VuzixSpeechClient(iActivity);
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

            // Now add any new strings.  If you put a substitution in the second argument, you will be passed that string instead of the full string
            sc.insertKeycodePhrase("Alfa", KeyEvent.KEYCODE_A );
            sc.insertKeycodePhrase("Bravo", KeyEvent.KEYCODE_B);
            sc.insertKeycodePhrase("Charlie", KeyEvent.KEYCODE_C);
            sc.insertKeycodePhrase("Delta", KeyEvent.KEYCODE_D);
            sc.insertKeycodePhrase("Echo", KeyEvent.KEYCODE_E);
            sc.insertKeycodePhrase("Foxtrot", KeyEvent.KEYCODE_F);
            sc.insertKeycodePhrase("Golf", KeyEvent.KEYCODE_G);
            sc.insertKeycodePhrase("Hotel", KeyEvent.KEYCODE_H);
            sc.insertKeycodePhrase("India", KeyEvent.KEYCODE_I);
            sc.insertKeycodePhrase("Juliett", KeyEvent.KEYCODE_J);
            sc.insertKeycodePhrase("Kilo", KeyEvent.KEYCODE_K);
            sc.insertKeycodePhrase("Lima", KeyEvent.KEYCODE_L);
            sc.insertKeycodePhrase("Mike", KeyEvent.KEYCODE_M);
            sc.insertKeycodePhrase("November", KeyEvent.KEYCODE_N);
            sc.insertKeycodePhrase("Oscar", KeyEvent.KEYCODE_O);
            sc.insertKeycodePhrase("Papa", KeyEvent.KEYCODE_P);
            sc.insertKeycodePhrase("Quebec", KeyEvent.KEYCODE_Q);
            sc.insertKeycodePhrase("Romeo", KeyEvent.KEYCODE_R);
            sc.insertKeycodePhrase("Sierra", KeyEvent.KEYCODE_S);
            sc.insertKeycodePhrase("Tango", KeyEvent.KEYCODE_T);
            sc.insertKeycodePhrase("Uniform", KeyEvent.KEYCODE_U);
            sc.insertKeycodePhrase("Victor", KeyEvent.KEYCODE_V);
            sc.insertKeycodePhrase("Whiskey", KeyEvent.KEYCODE_W);
            sc.insertKeycodePhrase("X-Ray", KeyEvent.KEYCODE_X);
            sc.insertKeycodePhrase("Yankee", KeyEvent.KEYCODE_Y);
            sc.insertKeycodePhrase("Zulu", KeyEvent.KEYCODE_Z);
            // Misc
            sc.insertKeycodePhrase("Space", KeyEvent.KEYCODE_SPACE);
            sc.insertKeycodePhrase("shift", KeyEvent.KEYCODE_SHIFT_LEFT);
            sc.insertKeycodePhrase("caps lock", KeyEvent.KEYCODE_CAPS_LOCK);
            sc.insertKeycodePhrase("at sign", KeyEvent.KEYCODE_AT);
            sc.insertKeycodePhrase("period", KeyEvent.KEYCODE_PERIOD);
            sc.insertKeycodePhrase("erase", KeyEvent.KEYCODE_DEL);
            sc.insertKeycodePhrase("enter", KeyEvent.KEYCODE_ENTER);



            sc.insertPhrase("Edit Text", MATCH_EDIT_TEXT);


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

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
