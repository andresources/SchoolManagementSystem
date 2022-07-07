package com.pharma.ui.parent;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.adapter.parent.OnlineClassesAdapter;
import com.pharma.model.parent.EventsModel;
import com.pharma.model.parent.OnlineClassesModel;

import java.util.ArrayList;
import java.util.List;

public class OnlineClassesActivity extends AppCompatActivity {
    ProgressBar progress_circular;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_classes);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OnlineClassesActivity.this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        loadData();
    }
    List<OnlineClassesModel> lists=new ArrayList<>();
    private void loadData(){
        OnlineClassesModel m1=new OnlineClassesModel();
        m1.setDay(11);
        m1.setMonth("JAN");
        m1.setSubject("English");
        m1.setDayOfWeek("Sunday");
        m1.setTime("9:00AM");
        lists.add(m1);

        OnlineClassesModel m2=new OnlineClassesModel();
        m2.setDay(23);
        m2.setMonth("FEB");
        m2.setSubject("Telugu");
        m2.setDayOfWeek("Monday");
        m2.setTime("11:00AM");
        lists.add(m2);
        lists.add(m2);
        lists.add(m2);
        lists.add(m2);
        lists.add(m2);
        lists.add(m2);
        lists.add(m2);
        lists.add(m2);
        lists.add(m2);
        lists.add(m2);
        OnlineClassesAdapter adapter = new OnlineClassesAdapter(OnlineClassesActivity.this, lists);
        rv.setAdapter(adapter);
    }
}