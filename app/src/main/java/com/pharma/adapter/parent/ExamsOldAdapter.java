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
import com.pharma.ui.parent.ExamsDetailsActivity;

import java.util.List;

public class ExamsOldAdapter extends RecyclerView.Adapter<ExamsOldAdapter.ViewHolder> {

    private Context context;
    List<ExamsModel> list;

    public ExamsOldAdapter(Context context, List<ExamsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_exams_old,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ExamsModel model=list.get(i);
        viewHolder.tvTerm.setText(model.getTerm());
        viewHolder.tvTermDate.setText(model.getTermDate());
        viewHolder.tvTermClass.setText(model.getTermclass());
        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ExamsDetailsActivity.class));
            }
        });
        if(model.getPercentage()>0){
            viewHolder.tvPercentage.setVisibility(View.VISIBLE);
            viewHolder.tvGrade.setVisibility(View.VISIBLE);
            viewHolder.tvPercentage.setText(model.getPercentage()+"%");
            viewHolder.tvGrade.setText(model.getGrade());
            if(model.getGrade().equals("Grade - A")){
                viewHolder.tvPercentage.setTextColor(context.getResources().getColor(R.color.green_400));
                viewHolder.tvGrade.setBackground(context.getResources().getDrawable(R.drawable.bg_grade_a));
            }else if(model.getGrade().equals("Grade - B")){
                viewHolder.tvPercentage.setTextColor(context.getResources().getColor(R.color.orange_400));
                viewHolder.tvGrade.setBackground(context.getResources().getDrawable(R.drawable.bg_grade_b));
            }else {
                viewHolder.tvPercentage.setTextColor(context.getResources().getColor(R.color.red_400));
                viewHolder.tvGrade.setBackground(context.getResources().getDrawable(R.drawable.bg_grade_c));
            }
        }else{
            viewHolder.tvPercentage.setVisibility(View.GONE);
            viewHolder.tvGrade.setVisibility(View.GONE);
        }
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
        private final TextView tvTerm,tvTermDate,tvTermClass,tvPercentage,tvGrade;
        RelativeLayout rl;
        //private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //iv=(ImageView) itemView.findViewById(R.id.iv);
            tvTerm=(TextView) itemView.findViewById(R.id.tvTerm);
            tvTermDate=(TextView) itemView.findViewById(R.id.tvTermDate);
            tvTermClass=(TextView) itemView.findViewById(R.id.tvTermClass);
            tvPercentage=(TextView) itemView.findViewById(R.id.tvPercentage);
            tvGrade=(TextView) itemView.findViewById(R.id.tvGrade);
            rl=(RelativeLayout) itemView.findViewById(R.id.rl);
        }
    }
}