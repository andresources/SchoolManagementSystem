package com.pharma.ui.parent;

import static com.pharma.utils.AppKeys.CURRENT_PAYMENTS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.pharma.BuildConfig;
import com.pharma.CreatePDFActivity;
import com.pharma.MyApp;
import com.pharma.R;
import com.pharma.fragment.patient.StudentHomeFragment;
import com.pharma.model.parent.CompusDataModel;
import com.pharma.model.parent.CurrentPaymentsModel;
import com.pharma.model.parent.ExamsResultsModel;
import com.pharma.model.parent.StuDetailsModel;
import com.pharma.model.parent.UserDataModel;
import com.pharma.utils.AppKeys;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class PaymentReceiptActivity extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;
    LinearLayout llParent;
    Button btn;
    Bitmap bitmap;
    ImageView ivBackArrow,iv;
    TextView tvBranch,tvCompAddress,tvBranchMobile,tvIssueDate,tvReceiptNum,tvAcademic,tvStudentName,tvClass,tvFatherName,tvFMob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);
        String str=getIntent().getExtras().getString(CURRENT_PAYMENTS);
        setCurrentPayments(str);
        LinearLayout llBack = (LinearLayout)findViewById(R.id.llBack);
        llParent= (LinearLayout)findViewById(R.id.llParent);
        ivBackArrow= (ImageView)findViewById(R.id.ivBackArrow);
        iv= (ImageView)findViewById(R.id.iv);
        btn= (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivBackArrow.setVisibility(View.GONE);
                bitmap = LoadBitmap(llParent, llParent.getWidth(), llParent.getHeight());
                createPdf();
            }
        });
        tvBranch = (TextView)findViewById(R.id.tvBranch);
        tvCompAddress = (TextView)findViewById(R.id.tvCompAddress);
        tvBranchMobile = (TextView)findViewById(R.id.tvBranchMobile);
        tvIssueDate = (TextView)findViewById(R.id.tvIssueDate);

        tvReceiptNum = (TextView)findViewById(R.id.tvReceiptNum);
        tvAcademic = (TextView)findViewById(R.id.tvAcademic);
        tvStudentName = (TextView)findViewById(R.id.tvStudentName);
        tvClass = (TextView)findViewById(R.id.tvClass);
        tvFatherName = (TextView)findViewById(R.id.tvFatherName);
        tvFMob = (TextView)findViewById(R.id.tvFMob);

        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setUserProfile();
    }
    List<CurrentPaymentsModel> list=new ArrayList<>();
    TableLayout tl;
    private void setCurrentPayments(String str){
        tl=(TableLayout)findViewById(R.id.tl);
        final LayoutInflater  inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            JSONArray jsonArray = new JSONArray(str);
            for(int i=0;i<jsonArray.length();i++){
                View view = inflater.inflate(R.layout.row_current_payments,null);
                TextView tvSNo=(TextView)view.findViewById(R.id.tvSNo);
                TextView tvDesc=(TextView)view.findViewById(R.id.tvDesc);
                TextView tvFeeAmount=(TextView)view.findViewById(R.id.tvFeeAmount);
                tvSNo.setText(""+(i+1));
                tvDesc.setText(jsonArray.getJSONObject(i).getString("Feehead"));
                tvFeeAmount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(jsonArray.getJSONObject(i).getInt("catgAmt")));
               tl.addView(view);

            }
            //Toast.makeText(getApplicationContext(), "->"+list.size(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){ Toast.makeText(getApplicationContext(), "->"+e.getMessage(), Toast.LENGTH_SHORT).show();}
    }
    UserDataModel user;
    private void setUserProfile() {
        MyApp.app().appComponent().inject(PaymentReceiptActivity.this);
        String str=sharedPreferences.getString(AppKeys.USER_NAME, "default");
        String str_cmp=sharedPreferences.getString(AppKeys.CAMP_DATA, "default");
        String st=sharedPreferences.getString(AppKeys.STD_DETAILS, "default");
        Gson gson = new Gson();
         user=gson.fromJson(str, UserDataModel.class);
        CompusDataModel comp=gson.fromJson(str_cmp, CompusDataModel.class);
        StuDetailsModel std=gson.fromJson(st, StuDetailsModel.class);

        tvBranch.setText("Branch : "+comp.getName());
        tvCompAddress.setText(comp.getAddr());
        tvBranchMobile.setText("Mobile : "+comp.getMob());
        tvIssueDate.setText(getIntent().getStringExtra(AppKeys.ISSUE_DATE));
        tvReceiptNum.setText(getIntent().getStringExtra(AppKeys.RECEIPT_NO));

        tvAcademic.setText(user.getAcadYear()+"");
        tvStudentName.setText(user.getNameF()+" "+user.getNameL());
        tvClass.setText(std.getClsName()+"/"+std.getSecName());
        tvFatherName.setText(user.getFathName());
        tvFMob.setText(user.getMobF());
        Glide.with(this)
                .load("https://erp.tapasyaedu.com:9443/t/imgImgDisp.action?imgFolder="+user.getImgFolder()+"&refType=stu_&ref="+user.getSno())
                .into(iv);
    }
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
        file1 = new File(dir1, user.getNameF()+" "+user.getNameL()+".pdf");
        try {
            document.writeTo(new FileOutputStream(file1));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        document.close();
        Toast.makeText(this, "successfully pdf created", Toast.LENGTH_SHORT).show();
        ivBackArrow.setVisibility(View.VISIBLE);
        sharePDF();
    }
    File file1;
    private void sharePDF(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Uri imageUri = FileProvider.getUriForFile(PaymentReceiptActivity.this, BuildConfig.APPLICATION_ID, file1);

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