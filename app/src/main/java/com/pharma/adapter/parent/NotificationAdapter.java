package com.pharma.adapter.parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.model.parent.HomeWorkModel;
import com.pharma.model.parent.NotificationModel;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    List<NotificationModel> list;

    public NotificationAdapter(Context context, List<NotificationModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_notifications,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NotificationModel model=list.get(i);
        viewHolder.tvTitle.setText(model.getTitle());
        viewHolder.tvDate.setText(model.getNdate());
        viewHolder.tvDes.setText(model.getDescription());
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
        private final TextView tvTitle,tvDate,tvDes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitle);
            tvDate=(TextView) itemView.findViewById(R.id.tvDate);
            tvDes=(TextView) itemView.findViewById(R.id.tvDes);
        }
    }
}