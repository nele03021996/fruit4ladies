package com.example.fruit4ladiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Items extends AppCompatActivity {

    VoiceCommandReceiver voiceCommandReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        setContentView(R.layout.activity_items);

        // Create the voice command receiver class
        //voiceCommandReceiver = new VoiceCommandReceiver(this);
        //voiceCommandReceiver.TriggerRecognizerToListen(true);
    }

    public void onBackClick(View view){
        goBack();
    }

    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}