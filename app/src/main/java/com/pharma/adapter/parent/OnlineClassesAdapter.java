package com.pharma.adapter.parent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.pharma.model.parent.EventsModel;
import com.pharma.model.parent.OnlineClassesModel;

import java.util.List;

public class OnlineClassesAdapter extends RecyclerView.Adapter<OnlineClassesAdapter.ViewHolder> {

    private Context context;
    List<OnlineClassesModel> list;

    public OnlineClassesAdapter(Context context, List<OnlineClassesModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_online_classes,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        OnlineClassesModel model=list.get(i);
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
        viewHolder.tvDay.setText(model.getDay()>=10?""+model.getDay():"0"+model.getDay());
        viewHolder.tvMonth.setText(model.getMonth());
        viewHolder.tvSubject.setText(model.getSubject());
        viewHolder.tvDayOfWeek.setText(model.getDayOfWeek());
        viewHolder.tvTime.setText(model.getTime());

        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = model.getPath();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        /*Glide.with(context)
                .load(model.getImg_url())
                .into(viewHolder.iv);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // private final ImageView imgViewCover;
        private final TextView tvDay,tvMonth,tvSubject,tvDayOfWeek,tvTime;
        LinearLayout ll;
        //private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //iv=(ImageView) itemView.findViewById(R.id.iv);
            tvDay=(TextView) itemView.findViewById(R.id.tvDay);
            tvMonth=(TextView) itemView.findViewById(R.id.tvMonth);
            tvSubject=(TextView) itemView.findViewById(R.id.tvSubject);
            tvDayOfWeek=(TextView) itemView.findViewById(R.id.tvDayOfWeek);
            tvTime=(TextView) itemView.findViewById(R.id.tvTime);
            ll=(LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}