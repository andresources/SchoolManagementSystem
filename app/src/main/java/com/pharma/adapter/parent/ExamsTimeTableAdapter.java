package com.pharma.adapter.parent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.pharma.R;
import com.pharma.model.parent.ELearningModel;
import com.pharma.model.parent.ExamsSubjectsTableModel;
import com.pharma.ui.parent.ExamsDetailsActivity;

import java.util.List;

public class ExamsTimeTableAdapter extends RecyclerView.Adapter<ExamsTimeTableAdapter.ViewHolder> {

    private Context context;
    List<ExamsSubjectsTableModel>  list;
    String exam_name="",percentage="0",grade;

    public ExamsTimeTableAdapter(Context context, List<ExamsSubjectsTableModel>  list,String exam_name,String percentage,String grade) {
        this.context = context;
        this.list = list;
        this.exam_name =exam_name;
        this.grade = grade;
        this.percentage =percentage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_exams_timetable,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ExamsSubjectsTableModel model=list.get(i);
        viewHolder.tvSubject.setText(model.getSubjName());
        viewHolder.tvMax.setText(model.getMax()+"");
        viewHolder.tvMax.setVisibility(View.GONE);
        viewHolder.tvMin.setText(model.getMin()+"");
        viewHolder.tvMin.setVisibility(View.GONE);
        viewHolder.tvDate.setText(model.getDate());
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ExamsDetailsActivity.class);
                Gson gson = new Gson();
                String data = gson.toJson(list);
                intent.putExtra("exams_data",data);
                intent.putExtra("exams_percentage",percentage);
                intent.putExtra("exams_grade",grade);
                intent.putExtra("exam_name",exam_name);

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
        private final TextView tvSubject,tvMax,tvMin,tvDate;
        LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSubject=(TextView) itemView.findViewById(R.id.tvSubject);
            tvMax=(TextView) itemView.findViewById(R.id.tvMax);
            tvMin=(TextView) itemView.findViewById(R.id.tvMin);
            tvDate=(TextView) itemView.findViewById(R.id.tvDate);
            ll=(LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}