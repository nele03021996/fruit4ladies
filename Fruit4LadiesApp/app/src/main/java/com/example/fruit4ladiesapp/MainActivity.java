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
    public static final String EXTRA_MESSAGE = "com.example.fruit4ladiesapp.MESSAGE";

    VoiceCommandReceiver voiceCommandReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the voice command receiver class
        voiceCommandReceiver = new VoiceCommandReceiver(this);
        voiceCommandReceiver.registerCommands(Arrays.asList(Commands.MATCH_START));
    }

    public String getMethodName() {
        return LOG_TAG + ":" + this.getClass().getSimpleName() + "." + new Throwable().getStackTrace()[1].getMethodName();
    }

    public void start(View view) {
        loadFragment(new PackagingFragment());
    }

    public void nextItem(View view) {
        loadFragment(new ItemFragment());
    }

    void loadFragment(Fruit4LadiesFragment fragment) {
        voiceCommandReceiver.resetCommands();
        voiceCommandReceiver.registerCommands(fragment.getCommands());

        FragmentManager supportedFragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment =
                (NavHostFragment) supportedFragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

// OLD
//        FragmentTransaction ft = supportedFragmentManager.beginTransaction();
//        // Replace the contents of the container with the new fragment
//        ft.replace(R.id.nav_host_fragment, fragment);
//        // or ft.add(R.id.your_placeholder, new FooFragment());
//        ft.commit();
    }
}