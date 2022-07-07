package com.pharma.ui.parent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.pharma.BuildConfig;
import com.pharma.MyApp;
import com.pharma.R;
import com.pharma.adapter.parent.ExamsDetailsAdapter;
import com.pharma.fragment.patient.StudentHomeFragment;
import com.pharma.model.parent.ExamsResultsModel;
import com.pharma.model.parent.ExamsSubjectsTableModel;
import com.pharma.model.parent.UserDataModel;
import com.pharma.utils.AppKeys;
import com.pharma.utils.DateUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ExamsDetailsActivity extends AppCompatActivity {
    ProgressBar progress_circular;
    RecyclerView rv;
    AppCompatSpinner sp;
    TextView tvTerm,tvSName,tvPercentage,tvGrade,tvTotalMaxMarks,tvTotalMinMarks,tvTotalMarks;
    ImageView ivback;
    LinearLayout llParent;
    Button btn;
    int total=0,mintotal=0,maxtotal=0;
    @Inject
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams_details);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        ivback= (ImageView)findViewById(R.id.ivback);
        llParent = (LinearLayout)findViewById(R.id.llParent);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //subject_name
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        rv = (RecyclerView) findViewById(R.id.rv);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivback.setVisibility(View.GONE);
                bitmap = LoadBitmap(llParent, llParent.getWidth(), llParent.getHeight());
                createPdf();
            }
        });
        tvTerm = (TextView) findViewById(R.id.tvTerm);
        tvSName = (TextView) findViewById(R.id.tvSName);
        tvPercentage = (TextView) findViewById(R.id.tvPercentage);
        tvGrade= (TextView) findViewById(R.id.tvGrade);
        tvTotalMaxMarks= (TextView) findViewById(R.id.tvTotalMaxMarks);
        tvTotalMinMarks= (TextView) findViewById(R.id.tvTotalMinMarks);
        tvTotalMarks= (TextView) findViewById(R.id.tvTotalMarks);
        sp = (AppCompatSpinner) findViewById(R.id.sp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ary, R.layout.spinner_item);
        sp.setAdapter(adapter);
        tvTerm.setText(getIntent().getExtras().getString("exam_name"));
        tvPercentage.setText(getIntent().getExtras().getString("exams_percentage")+"%");
        tvGrade.setText("Grade "+getIntent().getExtras().getString("exams_grade"));

        setUserProfile();
        prepareData();
    }
    UserDataModel user;
    private void setUserProfile() {
        MyApp.app().appComponent().inject(ExamsDetailsActivity.this);
        String str=sharedPreferences.getString(AppKeys.USER_NAME, "default");
        Gson gson = new Gson();
         user=gson.fromJson(str, UserDataModel.class);
        tvSName.setText(user.getNameF()+" "+user.getNameL());
    }
    List<ExamsResultsModel> list=new ArrayList<>();
    private void prepareData(){
        String str=getIntent().getExtras().getString("exams_data");
        try {
            JSONArray jsonArray = new JSONArray(str);
            for(int i=0;i<jsonArray.length();i++){
                ExamsResultsModel m1=new ExamsResultsModel();
                m1.setSubject(jsonArray.getJSONObject(i).getString("subjName"));
                m1.setMax_marks(jsonArray.getJSONObject(i).getString("max"));
                m1.setMin_marks(jsonArray.getJSONObject(i).getString("min"));
                m1.setMarks(jsonArray.getJSONObject(i).getString("marks"));
                if(DateUtils.isNumeric(jsonArray.getJSONObject(i).getString("max"))){
                    maxtotal =maxtotal+Integer.parseInt(jsonArray.getJSONObject(i).getString("max"));
                }
                if(DateUtils.isNumeric(jsonArray.getJSONObject(i).getString("min"))){
                    mintotal =mintotal+Integer.parseInt(jsonArray.getJSONObject(i).getString("min"));
                }
                if(DateUtils.isNumeric(jsonArray.getJSONObject(i).getString("marks"))){
                    total =total+Integer.parseInt(jsonArray.getJSONObject(i).getString("marks"));
                }
                tvTotalMaxMarks.setText(""+maxtotal);
                tvTotalMinMarks.setText(""+mintotal);
                tvTotalMarks.setText(""+total);
                //marks
                list.add(m1);
            }
        }catch (Exception e){}

        ExamsDetailsAdapter adapter=new ExamsDetailsAdapter(this,list);
        rv.setAdapter(adapter);
    }
    Bitmap bitmap;
    private void createPdf() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;

        int convertHighet = (int) hight, convertWidth = (int) width;
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawPaint(paint);
        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        // write the document content
        File dir1 = new File(getFilesDir() + "/PDFF");
        dir1.mkdirs(); //create folders where write files
        file1 = new File(dir1, user.getNameF()+"_"+user.getNameL()+"_"+getIntent().getExtras().getString("exam_name")+"Results"+".pdf");
        try {
            document.writeTo(new FileOutputStream(file1));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        document.close();
        Toast.makeText(this, "successfully pdf created", Toast.LENGTH_SHORT).show();
        ivback.setVisibility(View.VISIBLE);
        sharePDF();
    }
    File file1;
    private void sharePDF(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Uri imageUri = FileProvider.getUriForFile(ExamsDetailsActivity.this, BuildConfig.APPLICATION_ID, file1);

        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.setType("image/*");
        startActivity(intent);
    }

    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }
}