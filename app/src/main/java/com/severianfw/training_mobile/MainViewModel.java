package com.severianfw.training_mobile;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public String result;

    public void sum(int num1, int num2) {
        int resultNum = num2 + num1;
        result = Integer.toString(resultNum);
    }
}
