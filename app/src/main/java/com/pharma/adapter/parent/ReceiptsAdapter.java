package com.pharma.adapter.parent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.pharma.R;
import com.pharma.model.parent.EventsModel;
import com.pharma.model.parent.RecieptWiseDataModel;
import com.pharma.ui.parent.PaymentReceiptActivity;
import com.pharma.utils.AppKeys;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ReceiptsAdapter extends RecyclerView.Adapter<ReceiptsAdapter.ViewHolder> {

    private Context context;
    List<RecieptWiseDataModel> list;

    public ReceiptsAdapter(Context context, List<RecieptWiseDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_receipts,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RecieptWiseDataModel model=list.get(i);
        viewHolder.tvReceiptNo.setText(model.getRecNo());
        viewHolder.tvPaymentDate.setText(model.getDate());
        viewHolder.tvPayMode.setText(model.getPaymode());
        viewHolder.tvAmountPaid.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(model.getAmount()));
        viewHolder.rlDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                String json = gson.toJson(model.getCurrent_payments());
                Intent intent=new Intent(context, PaymentReceiptActivity.class);
                intent.putExtra(AppKeys.CURRENT_PAYMENTS,json);
                intent.putExtra(AppKeys.ISSUE_DATE,model.getDate());
                intent.putExtra(AppKeys.RECEIPT_NO,model.getRecNo());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // private final ImageView imgViewCover;
        private final TextView tvReceiptNo,tvPaymentDate,tvPayMode,tvAmountPaid,tvDownload;
        private RelativeLayout rlDownload;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReceiptNo=(TextView) itemView.findViewById(R.id.tvReceiptNo);
            tvPaymentDate=(TextView) itemView.findViewById(R.id.tvPaymentDate);
            tvPayMode=(TextView) itemView.findViewById(R.id.tvPayMode);
            tvAmountPaid=(TextView) itemView.findViewById(R.id.tvAmountPaid);
            tvDownload=(TextView) itemView.findViewById(R.id.tvDownload);
            rlDownload=(RelativeLayout) itemView.findViewById(R.id.rlDownload);
        }
    }
}