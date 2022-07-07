package com.pharma.adapter.parent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.model.parent.ExamListModel;
import com.pharma.model.parent.ExamSingleDataModel;
import com.pharma.model.parent.ExamsModel;
import com.pharma.ui.parent.ExamsDetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.ViewHolder> {

    private Context context;
    List<ExamSingleDataModel> list,filtered;

    public ExamsAdapter(Context context, List<ExamSingleDataModel> list) {
        this.context = context;
        this.list = list;
        filtered = new ArrayList<>();
        filtered.clear();
        this.filtered.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_exams,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ExamSingleDataModel model=filtered.get(i);
        viewHolder.tvTerm.setText(model.getExamDetails().getExamName());
        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context.startActivity(new Intent(context, ExamsDetailsActivity.class));
            }
        });
        viewHolder.rvSection.setHasFixedSize(true);
        viewHolder.rvSection.setNestedScrollingEnabled(false);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(context);
        viewHolder.rvSection.setLayoutManager(gridLayoutManager);
        if(model.getExamDetails().getEttData()!=null) {
            if(model.getExamDetails().getEttData().size()>0) {
                ExamsTimeTableAdapter e = new ExamsTimeTableAdapter(context, model.getExamDetails().getEttData(),model.getExamDetails().getExamName(),model.getExamDetails().getPerc(),model.getExamDetails().getGrade());
                viewHolder.rvSection.setAdapter(e);
            }else {
                Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(context, "NoData", Toast.LENGTH_SHORT).show();
        }

            viewHolder.tvPercentage.setVisibility(View.VISIBLE);
            viewHolder.tvGrade.setVisibility(View.VISIBLE);
            viewHolder.tvPercentage.setText(model.getExamDetails().getPerc()+"%");
            viewHolder.tvGrade.setText("Grade "+model.getExamDetails().getGrade());
            if(model.getExamDetails().getGrade().equalsIgnoreCase("A")) {
                viewHolder.tvPercentage.setTextColor(context.getResources().getColor(R.color.grade_a));
                viewHolder.tvGrade.setBackground(context.getResources().getDrawable(R.drawable.bg_grade_a));
            }else if(model.getExamDetails().getGrade().equalsIgnoreCase("B")) {
                viewHolder.tvPercentage.setTextColor(context.getResources().getColor(R.color.grade_b));
                viewHolder.tvGrade.setBackground(context.getResources().getDrawable(R.drawable.bg_grade_b));
            }else if(model.getExamDetails().getGrade().equalsIgnoreCase("C")){
                viewHolder.tvPercentage.setTextColor(context.getResources().getColor(R.color.grade_c));
                viewHolder.tvGrade.setBackground(context.getResources().getDrawable(R.drawable.bg_grade_c));
            }else if(model.getExamDetails().getGrade().equalsIgnoreCase("D")){
                viewHolder.tvPercentage.setTextColor(context.getResources().getColor(R.color.grade_d));
                viewHolder.tvGrade.setBackground(context.getResources().getDrawable(R.drawable.bg_grade_d));
            }else{
                viewHolder.tvPercentage.setTextColor(context.getResources().getColor(R.color.grade_e));
                viewHolder.tvGrade.setBackground(context.getResources().getDrawable(R.drawable.bg_grade_e));
            }
        /*if(model.getPercentage()>0){
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
        }*/
        /*Glide.with(context)
                .load(model.getImg_url())
                .into(viewHolder.iv);*/
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // private final ImageView imgViewCover;
        private final TextView tvTerm,tvPercentage,tvGrade;
        RelativeLayout rl;
        private final RecyclerView rvSection;
        //private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //iv=(ImageView) itemView.findViewById(R.id.iv);
            rvSection=(RecyclerView) itemView.findViewById(R.id.rv);
            tvTerm=(TextView) itemView.findViewById(R.id.tvTerm);
            tvPercentage=(TextView) itemView.findViewById(R.id.tvPercentage);
            tvGrade=(TextView) itemView.findViewById(R.id.tvGrade);
            rl=(RelativeLayout) itemView.findViewById(R.id.rl);
        }
    }
    public void filtered(String str){
        filtered.clear();
        if(str.equals("All")){
            filtered.addAll(list);
        }else{
            for(ExamSingleDataModel e:list){
                if(e.getExamDetails().getExamName().equals(str)) {
                    filtered.add(e);
                }
            }
        }
        //Toast.makeText(context, filtered.get(0).getExamDetails().getExamName(), Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
    }
}
