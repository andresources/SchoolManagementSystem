package com.pharma.ui.parent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;

import com.pharma.R;
import com.pharma.ui.BaseActivity;

public class PaymentsActivity extends BaseActivity {
    TextView tvPayNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_payments_old);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        tvPayNow= (TextView)findViewById(R.id.tvPayNow);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(PaymentReceiptActivity.class);
            }
        });
        AppCompatSpinner sp = (AppCompatSpinner) findViewById(R.id.sp);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ary, R.layout.spinner_item);
        sp.setAdapter(adapter);
    }
}