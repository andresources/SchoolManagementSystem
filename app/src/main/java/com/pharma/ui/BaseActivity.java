package com.pharma.ui;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pharma.R;
import com.pharma.fragment.patient.StudentHomeFragment;
import com.pharma.utils.FragmentName;

public class BaseActivity extends AppCompatActivity {
    protected void startNewActivity(Class<?> cls){
        startActivity(new Intent(getApplicationContext(),cls));
    }
    protected void startNewActivityWithFinish(Class<?> cls){
        startActivity(new Intent(getApplicationContext(),cls));
        finish();
    }
}
