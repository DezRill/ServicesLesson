package com.dezrill.serviceslesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.dezrill.serviceslesson.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private TextHandleViewModel handleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleViewModel=new TextHandleViewModel();

        ActivityMainBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setData(handleViewModel.getDataModel());
    }

    public void onClickStart(View view) {
        handleViewModel.StartService();
    }
}