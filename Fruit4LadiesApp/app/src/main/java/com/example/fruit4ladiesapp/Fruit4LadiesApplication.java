package com.example.fruit4ladiesapp;

import android.app.Application;

public class Fruit4LadiesApplication extends Application {

    private VoiceCommandReceiver voiceCommandReceiver;

    public void setVoiceCommandReceiver(VoiceCommandReceiver voiceCommandReceiver) {
        this.voiceCommandReceiver = voiceCommandReceiver;
    }

    public VoiceCommandReceiver getVoiceCommandReceiver() {
        return voiceCommandReceiver;
    }
}
