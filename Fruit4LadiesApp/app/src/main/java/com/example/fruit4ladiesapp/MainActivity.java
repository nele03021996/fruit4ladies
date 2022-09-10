package com.example.fruit4ladiesapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public final String LOG_TAG = "VoiceSample";
    public static final String EXTRA_MESSAGE = "com.example.fruit4ladiesapp.MESSAGE";

    VoiceCommandReceiver voiceCommandReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the voice command receiver class
        voiceCommandReceiver = new VoiceCommandReceiver(this);
    }

    public String getMethodName() {
        return LOG_TAG + ":" + this.getClass().getSimpleName() + "." + new Throwable().getStackTrace()[1].getMethodName();
    }
}