package com.example.fruit4ladiesapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public final String LOG_TAG = "VoiceSample";

    VoiceCommandReceiver voiceCommandReceiver;
    private OrderViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the voice command receiver class
        voiceCommandReceiver = new VoiceCommandReceiver(this);
        voiceCommandReceiver.registerCommands(Arrays.asList(Commands.MATCH_START));

        viewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        viewModel.setCurrentStep(PackagingStep.START);
        viewModel.setCurrentOrder(new Order());
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

    public void navigate() {
        viewModel.getCurrentOrder();

        switch (viewModel.getCurrentStep().getValue()) {
            case START:
                viewModel.setCurrentStep(PackagingStep.PACKAGING);
                viewModel.setCurrentItem(null);
                loadFragment(new PackagingFragment());
                break;
            case PACKAGING:
                viewModel.setCurrentStep(PackagingStep.ITEMS);
                viewModel.setCurrentItem(getNextOrderItem());
                loadFragment(new ItemFragment());
                break;
            case ITEMS:
                viewModel.setCurrentItem(getNextOrderItem());
                if (viewModel.getCurrentItem().getValue() != null) {
                    viewModel.setCurrentStep(PackagingStep.ITEMS);
                    loadFragment(new ItemFragment());
                } else {
//    viewModel.setCurrentStep(PackagingStep.MESSAGE);
//    loadFragment(new MessageFragment());

                    viewModel.setCurrentStep(PackagingStep.START);
                    loadFragment(new StartFragment());
                }

            default:
                break;
        }

    }

    public OrderItem getNextOrderItem() {
        Order currentOrder = viewModel.getCurrentOrder().getValue();
        OrderItem orderItem = viewModel.getCurrentItem().getValue();
        if (orderItem == null) {
            return currentOrder.items.get(0);
        }

        int idx = currentOrder.items.indexOf(orderItem);
        if (idx < 0 || idx + 1 == currentOrder.items.size()) return null;
        return currentOrder.items.get(idx + 1);
    }
}