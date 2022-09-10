package com.example.fruit4ladiesapp;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import java.util.List;

public abstract class Fruit4LadiesFragment extends Fragment {
    public abstract List<Commands> getCommands();
    public abstract NavDirections getAction();
}
