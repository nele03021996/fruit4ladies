package com.example.fruit4ladiesapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

//import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public final String LOG_TAG = "VoiceSample";
    public static final String EXTRA_MESSAGE = "com.example.fruit4ladiesapp.MESSAGE";

    VoiceCommandReceiver voiceCommandReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject obj = null;

        try {
            obj = new JSONObject(loadJSONFromAsset(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Task task = gson.fromJson(String.valueOf(obj), Task.class);

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

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container_view, fragment);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("Task01.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}