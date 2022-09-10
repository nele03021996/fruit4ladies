package com.example.fruit4ladiesapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public final String LOG_TAG = "VoiceSample";
    NavController navController;
    NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VoiceCommandReceiver voiceCommandReceiver = new VoiceCommandReceiver(this);
        voiceCommandReceiver.registerCommands(Arrays.asList(Commands.MATCH_START));
        ((Fruit4LadiesApplication)getApplicationContext()).setVoiceCommandReceiver(voiceCommandReceiver);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
    }

    public String getMethodName() {
        return LOG_TAG + ":" + this.getClass().getSimpleName() + "." + new Throwable().getStackTrace()[1].getMethodName();
    }

    public void start(View view) {
        loadFragment();
    }

    public void nextItem(View view) {
        loadFragment();
    }

    void loadFragment() {
        ((Fruit4LadiesApplication)getApplicationContext()).getVoiceCommandReceiver().resetCommands();
        Fruit4LadiesFragment fragment = (Fruit4LadiesFragment)navHostFragment.getChildFragmentManager().getFragments().get(0);
        navController.navigate(fragment.getAction());

// OLD
//        FragmentTransaction ft = supportedFragmentManager.beginTransaction();
//        // Replace the contents of the container with the new fragment
//        ft.replace(R.id.nav_host_fragment, fragment);
//        // or ft.add(R.id.your_placeholder, new FooFragment());
//        ft.commit();
    }
}