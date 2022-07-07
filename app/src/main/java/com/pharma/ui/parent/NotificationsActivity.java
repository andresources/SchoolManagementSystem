package com.pharma.ui.parent;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.adapter.parent.NotificationAdapter;
import com.pharma.model.parent.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    ProgressBar progress_circular;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_notifications);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(NotificationsActivity.this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        loadData();
    }
    List<NotificationModel> lists=new ArrayList<>();
    private void loadData(){
        NotificationModel n1=new NotificationModel();
        n1.setTitle("Fee Alert");
        n1.setNdate("12 Jan,2022 9:00AM");
        n1.setDescription("This is description..");
        NotificationModel n2=new NotificationModel();
        n2.setTitle("Exams Alert");
        n2.setNdate("13 Feb,2022 9:00AM");
        n2.setDescription("This is description..");
        NotificationModel n3=new NotificationModel();
        n3.setTitle("Exams Alert");
        n3.setNdate("13 Feb,2022 9:00AM");
        n3.setDescription("This is description..");
        lists.add(n1);
        lists.add(n2);
        lists.add(n3);
        NotificationAdapter adapter = new NotificationAdapter(NotificationsActivity.this, lists);
        rv.setAdapter(adapter);
    }
}