package com.example.fruit4ladiesapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ItemFragment extends Fruit4LadiesFragment {
    private OrderViewModel viewModel;

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        TextView taskView = (TextView) view.findViewById(R.id.taskView);
        taskView.setText("Task ID: "+viewModel.getCurrentOrder().getValue().id);
        TextView amountView = (TextView) view.findViewById(R.id.fruitAmountView);
        amountView.setText(""+viewModel.getCurrentItem().getValue().getAmount());
        ImageView fruitView = (ImageView) view.findViewById(R.id.imageViewItems);
        if(viewModel.getCurrentItem().getValue().getName() == "orange"){
            fruitView.setImageResource(R.drawable.orange);
        }
        else{
            fruitView.setImageResource(R.drawable.apple);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    public List<Commands> getCommands() {
        return Arrays.asList(Commands.MATCH_NEXT);
    }
}