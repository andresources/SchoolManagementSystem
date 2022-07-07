package com.pharma.adapter.parent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.pharma.R;
import com.pharma.model.parent.RecieptWiseDataModel;
import com.pharma.model.parent.TransactionsModel;
import com.pharma.ui.parent.PaymentReceiptActivity;
import com.pharma.utils.AppKeys;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    private Context context;
    List<TransactionsModel> list;

    public TransactionsAdapter(Context context, List<TransactionsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_transactions,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TransactionsModel model=list.get(i);
        viewHolder.tvReceiptNo.setText(model.getRecNo());
        viewHolder.tvPaymentDate.setText(model.getDate());
        viewHolder.tvTransNo.setText(model.getTransNo());

        viewHolder.tvAmountPaid.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(Float.parseFloat(model.getAmount())));
        if(model.getTxnStatus().equals("active")){
            viewHolder.tvAmountPaidStatus.setBackground(context.getDrawable(R.drawable.bg_present));
            viewHolder.tvAmountPaidStatus.setText("Paid");
            viewHolder.tvAmountPaid.setTextColor(Color.parseColor("#4CAF50"));
        }else{
            viewHolder.tvAmountPaidStatus.setText("Failed");
            viewHolder.tvAmountPaidStatus.setBackground(context.getDrawable(R.drawable.bg_absent));
            viewHolder.tvAmountPaid.setTextColor(Color.parseColor("#F44336"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // private final ImageView imgViewCover;
        private final TextView tvReceiptNo,tvPaymentDate,tvTransNo,tvAmountPaid,tvAmountPaidStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReceiptNo=(TextView) itemView.findViewById(R.id.tvReceiptNo);
            tvPaymentDate=(TextView) itemView.findViewById(R.id.tvPaymentDate);
            tvTransNo=(TextView) itemView.findViewById(R.id.tvTransNo);
            tvAmountPaid=(TextView) itemView.findViewById(R.id.tvAmountPaid);
            tvAmountPaidStatus=(TextView) itemView.findViewById(R.id.tvAmountPaidStatus);

        }
    }
}