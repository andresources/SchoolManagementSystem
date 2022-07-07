package com.pharma.ui.parent;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.pharma.R;
import com.pharma.adapter.parent.GalleryAdapter;
import com.pharma.model.parent.GalleryModel;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    ProgressBar progress_circular;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_gallery);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        rv = (RecyclerView) findViewById(R.id.rv);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        //rv.setHasFixedSize(true);
        loadData();
    }
    List<GalleryModel> lists=new ArrayList<>();
    private void loadData(){
        GalleryModel m1=new GalleryModel();
        m1.setUrl("https://www.sakshi.com/sites/default/files/styles/cinema_medium/public/article_images/2022/05/16/Adani-Group.jpg");
        lists.add(m1);
        GalleryModel m2=new GalleryModel();
        m2.setUrl("https://www.sakshi.com/web-stories/article_images/2022/05/16/01_1652643950.jpg");

        GalleryModel m3=new GalleryModel();
        m3.setUrl("https://www.sakshi.com/web-stories/article_images/2022/05/16/01_1652643809.jpg");

        GalleryModel m4=new GalleryModel();
        m4.setUrl("https://www.sakshi.com/sites/default/files/styles/cinema_medium/public/article_images/2022/05/16/Express-Highway.jpg");

        GalleryModel m5=new GalleryModel();
        m5.setUrl("https://www.sakshi.com/sites/default/files/styles/storypage_main/public/article_images/2022/05/16/symo.jpg");


        // lists.add(m2);
        lists.add(m2);
        lists.add(m3);
        lists.add(m4);
        lists.add(m5);
        lists.add(m3);
        lists.add(m1);
        lists.add(m5);
        lists.add(m3);
        lists.add(m1);
//        GalleryAdapter adapter = new GalleryAdapter(GalleryActivity.this, lists);
        //rv.setAdapter(adapter);
    }
}