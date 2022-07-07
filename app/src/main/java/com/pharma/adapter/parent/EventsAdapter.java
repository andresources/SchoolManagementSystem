package com.pharma.adapter.parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.pharma.R;
import com.pharma.model.parent.EventsModel;
import com.pharma.model.parent.NotificationModel;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private Context context;
    List<EventsModel> list;

    public EventsAdapter(Context context, List<EventsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_events,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EventsModel model=list.get(i);
        viewHolder.tvTitle.setText(model.getTitle());
        viewHolder.tvDate.setText(model.getNdate());
        viewHolder.tvDes.setText(model.getDescription());
        Glide.with(context)
                .load(model.getImg_url())
                .into(viewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // private final ImageView imgViewCover;
        private final TextView tvTitle,tvDate,tvDes;
        private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=(ImageView) itemView.findViewById(R.id.iv);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitle);
            tvDate=(TextView) itemView.findViewById(R.id.tvDate);
            tvDes=(TextView) itemView.findViewById(R.id.tvDes);
        }
    }
}