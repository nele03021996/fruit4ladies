package com.example.fruit4ladiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vuzix.sdk.speechrecognitionservice.VuzixSpeechClient;

public class MainActivity extends AppCompatActivity {
    public final String LOG_TAG = "VoiceSample";
    public static final String EXTRA_MESSAGE = "com.example.fruit4ladiesapp.MESSAGE";

    VoiceCommandReceiver voiceCommandReceiver;
    private boolean recognizerActive = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView listening = (TextView)findViewById(R.id.listening);
        listening.setVisibility(TextView.INVISIBLE);

        // Create the voice command receiver class
        voiceCommandReceiver = new VoiceCommandReceiver(this);
        voiceCommandReceiver.TriggerRecognizerToListen(true);
    }

    public void onNextClick(View view){
        goToItemsActivity();
    }
    public void goToItemsActivity() {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra(EXTRA_MESSAGE, "test");
        startActivity(intent);
    }

    public String getMethodName() {
        return LOG_TAG + ":" + this.getClass().getSimpleName() + "." + new Throwable().getStackTrace()[1].getMethodName();
    }

    /**
     * Handler called when "Listen" button is clicked. Activates the speech recognizer identically to
     * saying "Hello Vuzix".  Also handles "Stop" button clicks to terminate the recognizer identically
     * to a time-out
     */
//    private void OnListenClick() {
//        Log.e(LOG_TAG, getMethodName());
//        // Trigger the speech recognizer to start/stop listening.  Listening has a time-out
//        // specified in the Vuzix Smart Glasses settings menu, so it may terminate without us
//        // requesting it.
//        //
//        // We want this to toggle to state opposite our current one.
//        recognizerActive = !recognizerActive;
//        // Manually calling this syncrhonizes our UI state to the recognizer state in case we're
//        // requesting the current state, in which we won't be notified of a change.
//        updateListenText();
//        // Request the new state
//        mVoiceCmdReceiver.TriggerRecognizerToListen(mRecognizerActive);
//    }
//
//    /**
//     * Update the button from "Listen" to "Stop" based on our cached state
//     */
//    private void updateListenButtonText() {
//        int newText = R.string.btn_text_listen;
//        if ( mRecognizerActive ) {
//            newText = R.string.btn_text_stop;
//        }
//        buttonListen.setText(newText);
//    }

}