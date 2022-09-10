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
    VoiceCommandReceiver voiceCommandReceiver;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voiceCommandReceiver = new VoiceCommandReceiver(this);
        voiceCommandReceiver.registerCommands(Arrays.asList(Commands.MATCH_START));

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

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

        navController.navigate(StartFragmentDirections.actionStartFragment2ToPackagingFragment2());

// OLD
//        FragmentTransaction ft = supportedFragmentManager.beginTransaction();
//        // Replace the contents of the container with the new fragment
//        ft.replace(R.id.nav_host_fragment, fragment);
//        // or ft.add(R.id.your_placeholder, new FooFragment());
//        ft.commit();
    }
}