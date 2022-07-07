package com.pharma.ui.parent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.adapter.parent.ExamsOldAdapter;
import com.pharma.model.parent.ExamsModel;
import com.pharma.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ExamsActivity extends BaseActivity {
    ProgressBar progress_circular;
    RecyclerView rv;
    AppCompatSpinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_exams);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        rv = (RecyclerView) findViewById(R.id.rv);
        sp = (AppCompatSpinner) findViewById(R.id.sp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ary, R.layout.spinner_item);
        sp.setAdapter(adapter);
        prepareData();
    }
    List<ExamsModel> list=new ArrayList<>();
    private void prepareData(){
        ExamsModel m1=new ExamsModel();
        m1.setTerm("First Term");
        m1.setTermDate("2022-01-01");
        m1.setTermclass("First Term for Class-1");
        m1.setGrade("Grade - A");
        m1.setPercentage(90);
        list.add(m1);

        ExamsModel m2=new ExamsModel();
        m2.setTerm("Mid Term");
        m2.setTermDate("2022-03-01");
        m2.setTermclass("Mid Term for Class-1");
        m2.setGrade("Grade - B");
        m2.setPercentage(74);
        list.add(m2);

        ExamsModel m3=new ExamsModel();
        m3.setTerm("Final Term");
        m3.setTermDate("2022-03-01");
        m3.setTermclass("Mid Term for Class-1");
        m3.setGrade("Grade - C");
        m3.setPercentage(45);
        list.add(m3);

        ExamsModel m4=new ExamsModel();
        m4.setTerm("English Exam");
        m4.setTermDate("2022-01-01");
        m4.setTermclass("First Term for Class-1");
        m4.setGrade("");
        m4.setPercentage(0);
        list.add(m4);

        ExamsOldAdapter adapter=new ExamsOldAdapter(this,list);
        rv.setAdapter(adapter);
    }
}