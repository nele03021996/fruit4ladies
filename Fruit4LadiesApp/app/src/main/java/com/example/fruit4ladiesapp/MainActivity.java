package com.example.fruit4ladiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vuzix.sdk.speechrecognitionservice.VuzixSpeechClient;

public class MainActivity extends AppCompatActivity {
    public final String LOG_TAG = "VoiceSample";
    public final String CUSTOM_SDK_INTENT = "com.vuzix.sample.vuzix_voicecontrolwithsdk.CustomIntent";
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private boolean recognizerActive = false;

    VoiceCommandReceiver voiceCommandReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}