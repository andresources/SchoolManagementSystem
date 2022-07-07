package com.pharma.adapter.parent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.model.parent.ExamsModel;
import com.pharma.model.parent.ExamsResultsModel;
import com.pharma.ui.parent.ExamsDetailsActivity;

import java.util.List;

public class ExamsDetailsAdapter extends RecyclerView.Adapter<ExamsDetailsAdapter.ViewHolder> {

    private Context context;
    List<ExamsResultsModel> list;

    public ExamsDetailsAdapter(Context context, List<ExamsResultsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_exams_results,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ExamsResultsModel model=list.get(i);
        viewHolder.tvSubject.setText(model.getSubject());
        viewHolder.tvMaxMarks.setText(model.getMax_marks());
        viewHolder.tvMinMarks.setText(model.getMin_marks());
        viewHolder.tvMarks.setText(model.getMarks());
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
        private final TextView tvSubject,tvMaxMarks,tvMarks,tvMinMarks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //iv=(ImageView) itemView.findViewById(R.id.iv);
            tvSubject=(TextView) itemView.findViewById(R.id.tvSubject);
            tvMaxMarks=(TextView) itemView.findViewById(R.id.tvMaxMarks);
            tvMinMarks=(TextView) itemView.findViewById(R.id.tvMinMarks);
            tvMarks=(TextView) itemView.findViewById(R.id.tvMarks);
        }
    }
}