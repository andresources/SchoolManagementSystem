package com.pharma.adapter.parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.fragment.patient.AttendanceFragment;
import com.pharma.fragment.patient.StudentAttendanceFragment;
import com.pharma.model.parent.LeaveModel;
import com.pharma.model.parent.PresentAbsentModel;
import com.pharma.ui.parent.AttendanceActivity;

import java.util.List;


public class PresentAbsentAdapter extends RecyclerView.Adapter<PresentAbsentAdapter.ViewHolder> {

    private Context context;
    List<PresentAbsentModel> list;

    public PresentAbsentAdapter(Context context, List<PresentAbsentModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_present_absent,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PresentAbsentModel model=list.get(i);
        if(i==0){
            viewHolder.line.setVisibility(View.GONE);
        }else{
            viewHolder.line.setVisibility(View.VISIBLE);
        }
        viewHolder.tvMonth.setText(model.getMonth());
        viewHolder.tvPresent.setText(model.getPresent()<10?"0"+model.getPresent():model.getPresent()+"");
        viewHolder.tvAbsent.setText(model.getAbsent()<10?"0"+model.getAbsent():model.getAbsent()+"");
        viewHolder.tvTotal.setText(model.getTotal()<10?"0"+model.getTotal():model.getTotal()+"");
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //((AttendanceActivity) context).setSegButtonPosition1();
                StudentAttendanceFragment.getInstance().setSegButtonPosition1();
                StudentAttendanceFragment.mYear = model.getYear1();
                StudentAttendanceFragment.mMonth = model.getMont1()-1;
                //Toast.makeText(context, AttendanceActivity.mYear +" : "+AttendanceActivity.mMonth, Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new AttendanceFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });
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
        private final TextView tvMonth,tvPresent,tvAbsent,tvTotal;
        private final LinearLayout ll;
        private final View line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvMonth=(TextView) itemView.findViewById(R.id.tvMonth);
            tvPresent=(TextView) itemView.findViewById(R.id.tvPresent);
            tvAbsent=(TextView) itemView.findViewById(R.id.tvAbsent);
            tvTotal=(TextView) itemView.findViewById(R.id.tvTotal);
            ll=(LinearLayout) itemView.findViewById(R.id.ll);
            line=(View) itemView.findViewById(R.id.line);
        }
    }
}