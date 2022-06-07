package com.severianfw.training_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.severianfw.training_mobile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String RESULT_KEY = "RESULT";

    private ActivityMainBinding binding;
    private String result;
//    private MainViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState != null) {
            result = savedInstanceState.getString(RESULT_KEY);
            binding.tvResult.setText(savedInstanceState.getString(RESULT_KEY));
        }

//        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        displayResult();

        binding.btnAdd.setOnClickListener(view -> {
            int num1 = Integer.parseInt(binding.etNum1.getText().toString());
            int num2 = Integer.parseInt(binding.etNum2.getText().toString());

//            [onSavedInstance]
            int resultNum = num2 + num1;
            result = Integer.toString(resultNum);

            binding.tvResult.setText(result);

//            [viewModel]
//            mViewModel.sum(num1, num2);
//            displayResult();
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(RESULT_KEY, result);
        super.onSaveInstanceState(outState);
    }

//    void displayResult() {
//        binding.tvResult.setText(mViewModel.result);
//    }
}