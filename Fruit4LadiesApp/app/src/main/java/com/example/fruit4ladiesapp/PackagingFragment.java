package com.example.fruit4ladiesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PackagingFragment extends Fruit4LadiesFragment {
    private OrderViewModel viewModel;

    public PackagingFragment() {
        // Required empty public constructor
        new Gson();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        TextView textView = (TextView) view.findViewById(R.id.taskView);
        textView.setText("Task ID: "+viewModel.getCurrentOrder().getValue().id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_packaging, container, false);
    }

    public List<Commands> getCommands() {
        return Arrays.asList(Commands.MATCH_NEXT);
    }
}