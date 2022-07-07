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
import com.pharma.model.parent.LeaveModel;
import java.util.List;

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.ViewHolder> {

    private Context context;
    List<HomeWorkModel> list;

    public HomeWorkAdapter(Context context, List<HomeWorkModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_home_work,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeWorkModel model=list.get(i);
        viewHolder.tvSubject.setText(model.getSubject());
        viewHolder.tvTopic.setText(model.getTopic());
        viewHolder.tvTopic.setVisibility(View.GONE);
        viewHolder.tvDescription.setText(model.getDescription());
        viewHolder.tvAssignDate.setText(model.getAssign_date());
        viewHolder.tvLastSubmissionDate.setText(model.getLast_submission_date());
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
        private final TextView tvSubject,tvTopic,tvDescription,tvAssignDate,tvLastSubmissionDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvSubject=(TextView) itemView.findViewById(R.id.tvSubject);
            tvTopic=(TextView) itemView.findViewById(R.id.tvTopic);
            tvDescription=(TextView) itemView.findViewById(R.id.tvDescription);
            tvAssignDate=(TextView) itemView.findViewById(R.id.tvAssignDate);
            tvLastSubmissionDate=(TextView) itemView.findViewById(R.id.tvLastSubmissionDate);
        }
    }
}