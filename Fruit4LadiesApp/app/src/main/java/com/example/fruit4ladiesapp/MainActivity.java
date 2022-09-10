package com.example.fruit4ladiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vuzix.sdk.speechrecognitionservice.VuzixSpeechClient;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private boolean recognizerActive = false;
    VoiceCommandReceiver voiceCommandReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToItemsActivity(View view) {
        Intent intent = new Intent(this, Items.class);
        intent.putExtra(EXTRA_MESSAGE, "test");
        startActivity(intent);
    }
}