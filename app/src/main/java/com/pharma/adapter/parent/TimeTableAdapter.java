package com.pharma.adapter.parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pharma.R;
import com.pharma.model.parent.Article;
import com.pharma.model.parent.TimeTableModel;

import java.util.ArrayList;
import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHolder> {

    private Context context;
    List<TimeTableModel> list;

    public TimeTableAdapter(Context context, List<TimeTableModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_time_table,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TimeTableModel model=list.get(i);
        viewHolder.tvSubject.setText(model.getSubject());
        viewHolder.tvTimings.setText(model.getTimings());
        viewHolder.tvFacality.setText(model.getFacality());
        viewHolder.tvPeriod.setText(model.getPeriod());
        if(i%5==0) {
            viewHolder.ll.setBackground(context.getResources().getDrawable(R.drawable.bg_online_1));
        }else if(i%5==1){
            viewHolder.ll.setBackground(context.getResources().getDrawable(R.drawable.bg_online_2));
        }else if(i%5==2){
            viewHolder.ll.setBackground(context.getResources().getDrawable(R.drawable.bg_online_3));
        }else if(i%5==3){
            viewHolder.ll.setBackground(context.getResources().getDrawable(R.drawable.bg_online_4));
        }else{
            viewHolder.ll.setBackground(context.getResources().getDrawable(R.drawable.bg_online_5));
        }
        /*Glide.with(context)
                .load(article.getUrlToImage())
                .into(viewHolder.imgViewCover);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       // private final ImageView imgViewCover;
        private final TextView tvSubject,tvTimings,tvFacality,tvPeriod;
        private final LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvSubject=(TextView) itemView.findViewById(R.id.tvSubject);
            ll=(LinearLayout) itemView.findViewById(R.id.ll);
            tvTimings=(TextView) itemView.findViewById(R.id.tvTimings);
            tvFacality=(TextView) itemView.findViewById(R.id.tvFacality);
            tvPeriod=(TextView) itemView.findViewById(R.id.tvPeriod);
        }
    }
}