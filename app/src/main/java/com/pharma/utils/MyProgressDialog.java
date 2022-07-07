package com.pharma.utils;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.pharma.R;

public class MyProgressDialog extends Dialog  {
    Context cnt;
    public MyProgressDialog(@NonNull Context context) {
        super(context);
        cnt= context;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.dialog1);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }


}
