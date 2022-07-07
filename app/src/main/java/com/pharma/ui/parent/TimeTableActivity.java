package com.pharma.ui.parent;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.adapter.parent.TimeTableAdapter;
import com.pharma.model.parent.TimeTableModel;
import com.seglib.SegmentedButtonGroup;

import java.util.ArrayList;
import java.util.List;

public class TimeTableActivity extends AppCompatActivity {
    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular_movie_article;
    private LinearLayoutManager layoutManager;
    private TimeTableAdapter adapter;
    private List<TimeTableModel> list=new ArrayList<>();
    private SegmentedButtonGroup seg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_time_table);
        initViews();
        loadData();
    }
    private void initViews(){
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(TimeTableActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);
        seg= (SegmentedButtonGroup)findViewById(R.id.segButton);
        seg.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                filterData(position+1);
            }
        });
    }
    private void loadData(){
        TimeTableModel m1=new TimeTableModel();
        m1.setDayOfWeek(1);
        m1.setFacality("James Neesham");
        m1.setPeriod(1);
        m1.setTimings("9AM-9.45AM");
        m1.setSubject("Computer Science");
        list.add(m1);

        TimeTableModel m2=new TimeTableModel();
        m2.setDayOfWeek(1);
        m2.setFacality("Virat Singh");
        m2.setPeriod(1);
        m2.setTimings("9AM-9.45AM");
        m2.setSubject("English");
        list.add(m2);

        TimeTableModel m3=new TimeTableModel();
        m3.setDayOfWeek(2);
        m3.setFacality("Test3");
        m3.setPeriod(1);
        m3.setTimings("9AM-9.45AM");
        m3.setSubject("Science");
        list.add(m3);

        TimeTableModel m4=new TimeTableModel();
        m4.setDayOfWeek(3);
        m4.setFacality("Test4");
        m4.setPeriod(1);
        m4.setTimings("9AM-9.45AM");
        m4.setSubject("Social");
        list.add(m4);

        TimeTableModel m5=new TimeTableModel();
        m5.setDayOfWeek(4);
        m5.setFacality("Test5");
        m5.setPeriod(1);
        m5.setTimings("9AM-9.45AM");
        m5.setSubject("Telugu");
        list.add(m5);

        filterData(1);


    }

    private void filterData(int day){
        List<TimeTableModel> fList=new ArrayList<>();
        fList.clear();
        for (TimeTableModel m:list){
            if(day==m.getDayOfWeek()){
                fList.add(m);
            }
        }
        adapter = new TimeTableAdapter(TimeTableActivity.this, fList);
        my_recycler_view.setAdapter(adapter);
    }
}