package com.pharma.ui.parent;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.adapter.parent.HomeWorkAdapter;
import com.pharma.model.parent.HomeWorkModel;

import java.util.ArrayList;
import java.util.List;

public class HomeWorkActivity extends AppCompatActivity {
    List<HomeWorkModel> lists=new ArrayList<>();
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_home_work);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        prepareData();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        HomeWorkAdapter adapter = new HomeWorkAdapter(this, lists);
        rv.setAdapter(adapter);
    }
    private void prepareData(){
        HomeWorkModel h1=new HomeWorkModel();
        h1.setAssign_date("01 Nov,2022");
        h1.setDescription("This is description");
        h1.setSubject("Mathematics");
        h1.setTopic("This is Subject");
        h1.setLast_submission_date("05 Nov,2022");

        HomeWorkModel h2=new HomeWorkModel();
        h2.setAssign_date("01 Nov,2022");
        h2.setDescription("This is description");
        h2.setSubject("Mathematics");
        h2.setTopic("This is Subject");
        h2.setLast_submission_date("05 Nov,2022");

        HomeWorkModel h3=new HomeWorkModel();
        h3.setAssign_date("01 Nov,2022");
        h3.setDescription("This is description");
        h3.setSubject("Mathematics");
        h3.setTopic("This is Subject");
        h3.setLast_submission_date("05 Nov,2022");

        lists.add(h1);
        lists.add(h2);
        lists.add(h3);
    }
}