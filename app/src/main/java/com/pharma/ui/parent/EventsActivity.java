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
import com.pharma.model.parent.EventsModel;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {
    ProgressBar progress_circular;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_events);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(EventsActivity.this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        loadData();
    }
    List<EventsModel> lists=new ArrayList<>();
    private void loadData(){
        EventsModel e1=new EventsModel();
        e1.setTitle("Book Fair");
        e1.setNdate("12 Jan,2022 9:00AM");
        e1.setDescription("This is description");
        //e1.setImg_url("https://www.sakshi.com/sites/default/files/styles/section_big_image/public/article_images/2022/05/16/online%20games.jpg");
        lists.add(e1);
        EventsAdapter adapter = new EventsAdapter(EventsActivity.this, lists);
        rv.setAdapter(adapter);
    }
}