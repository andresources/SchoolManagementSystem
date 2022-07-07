package com.pharma.ui.parent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pharma.R;
import com.pharma.adapter.parent.ReceiptsAdapter;
import com.pharma.adapter.parent.TransactionsAdapter;
import com.pharma.model.parent.PaymentsListModel;
import com.pharma.model.parent.TransactionsListModel;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.PaymentsViewModel;
import com.pharma.view_model.parent.TransactionViewModel;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentTransactionDetailsActivity extends AppCompatActivity {
    RecyclerView rv;
    LinearLayout llBack;
    TextView tvNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_transaction_details);
        rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        llBack=(LinearLayout)findViewById(R.id.llBack);
        tvNoData=(TextView)findViewById(R.id.tvNoData);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getPaymentDetails();
    }
    TransactionViewModel paymentsViewModel;
    Dialog pd;
    private void getPaymentDetails(){
        pd= new MyProgressDialog(this);
        pd.show();
        paymentsViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        paymentsViewModel.getTransactionResponseLiveData().observe(this, new Observer<TransactionsListModel>() {
            @Override
            public void onChanged(TransactionsListModel paymentsResponse) {
                if(paymentsResponse!=null) {
                    pd.dismiss();
                    if(paymentsResponse.getCode()==200) {
                        if(paymentsResponse.getTransactions()!=null&&paymentsResponse.getTransactions().size()>0) {
                            enableDisaleViews(true);
                            TransactionsAdapter ta = new TransactionsAdapter(getApplicationContext(), paymentsResponse.getTransactions());
                            rv.setAdapter(ta);
                        }else{
                            enableDisaleViews(false);
                        }
                        //Toast.makeText(getApplicationContext(), ""+paymentsResponse.getTransactions().size(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Oop! something went worng.Try again. Error code : ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    enableDisaleViews(false);
                    pd.dismiss();
                }
            }
        });
    }
    private void enableDisaleViews(boolean data){
        if(data){
            rv.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
        }else{
            rv.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }

    }
}