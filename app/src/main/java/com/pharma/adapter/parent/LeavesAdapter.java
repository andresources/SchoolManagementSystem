package com.pharma.adapter.parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.model.parent.LeaveModel;
import com.pharma.model.parent.TimeTableModel;

import java.util.List;

public class LeavesAdapter extends RecyclerView.Adapter<LeavesAdapter.ViewHolder> {

    private Context context;
    List<LeaveModel> list;

    public LeavesAdapter(Context context, List<LeaveModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_leaves,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LeaveModel model=list.get(i);
        viewHolder.tvLeave.setText(model.getLeave());
        viewHolder.tvDate.setText(model.getLdate());
        viewHolder.tvDayOfWeek.setText(model.getDayOfWeek());
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
        private final TextView tvLeave,tvDate,tvDayOfWeek;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvLeave=(TextView) itemView.findViewById(R.id.tvLeave);
            tvDate=(TextView) itemView.findViewById(R.id.tvDate);
            tvDayOfWeek=(TextView) itemView.findViewById(R.id.tvDayOfWeek);
        }
    }
}