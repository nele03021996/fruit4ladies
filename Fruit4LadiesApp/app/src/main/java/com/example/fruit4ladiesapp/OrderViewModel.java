package com.example.fruit4ladiesapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {
    private final MutableLiveData<Order> currentOrder = new MutableLiveData<Order>();
    private final MutableLiveData<PackagingStep> currentStep = new MutableLiveData<PackagingStep>();
    private final MutableLiveData<OrderItem> currentItem = new MutableLiveData<OrderItem>();

    public void setCurrentOrder(Order order) {
        currentOrder.setValue(order);
    }
    public LiveData<Order> getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentStep (PackagingStep step) {
        currentStep.setValue(step);
    }

    public LiveData<PackagingStep> getCurrentStep() {
        return currentStep;
    }

    public void setCurrentItem (OrderItem item) {
        currentItem.setValue(item);
    }

    public LiveData<OrderItem> getCurrentItem() {
        return currentItem;
    }
}
