package com.pharma.ui.parent;

import static com.pharma.atom.ATOMPaymentService.encodeBase64;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.atom.atompaynetzsdk.AtomAES;
import com.atom.ots.enc.AtomEncryption;
import com.google.gson.JsonObject;
import com.pharma.MyApp;
import com.pharma.R;
import com.pharma.fragment.patient.AttendanceFragment;
import com.pharma.fragment.patient.StudentHomeFragment;
import com.pharma.fragment.patient.YearAttendanceFragment;
import com.pharma.model.parent.NotificationUpdateModel;
import com.pharma.model.parent.TransactionStatusModel;
import com.pharma.response.LoginResponse;
import com.pharma.ui.BaseActivity;
import com.pharma.ui.LoginActivity;
import com.pharma.utils.FragmentName;
import com.pharma.utils.MyProgressDialog;
import com.pharma.utils.ScreenNav;
import com.pharma.view_model.LoginViewModel;
import com.pharma.view_model.parent.UpdateNotificationCountViewModel;
import com.pharma.view_model.parent.UpdateTransactionStatusViewModel;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class StudentDashboardActivity extends BaseActivity implements View.OnClickListener, StudentHomeFragment.OnFragmentInteractionListener, PaymentResultListener {
    //CardView cvPayments,cvExams,cvGallery,cvProfile,cvAttendance,cvHomeWork,cvTimeTable,cvNotifications,cvEvents,cvOnlineClasses;
    FrameLayout fragment_parent_container;
    LinearLayout llHome,llProfile,llPayment,llNotification;
    TextView tvCnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        fragment_parent_container=(FrameLayout)findViewById(R.id.fragment_parent_container);
        llHome=(LinearLayout)findViewById(R.id.llHome);
        tvCnt=(TextView)findViewById(R.id.tvCnt);
        llProfile=(LinearLayout)findViewById(R.id.llProfile);
        llPayment=(LinearLayout)findViewById(R.id.llPayment);
        llNotification=(LinearLayout)findViewById(R.id.llNotification);
        ScreenNav.addFragment(FragmentName.HOME,this);
        llHome.setOnClickListener(this);
        llProfile.setOnClickListener(this);
        llPayment.setOnClickListener(this);
        llNotification.setOnClickListener(this);
        /*cvProfile = (CardView) findViewById(R.id.cvProfile);
        cvAttendance= (CardView) findViewById(R.id.cvAttendance);
        cvHomeWork= (CardView) findViewById(R.id.cvHomeWork);
        cvTimeTable= (CardView) findViewById(R.id.cvTimeTable);
        cvNotifications= (CardView) findViewById(R.id.cvNotifications);
        cvEvents= (CardView) findViewById(R.id.cvEvents);
        cvOnlineClasses= (CardView) findViewById(R.id.cvOnlineClasses);
        cvGallery= (CardView) findViewById(R.id.cvGallery);
        cvExams= (CardView) findViewById(R.id.cvExams);
        cvPayments= (CardView) findViewById(R.id.cvPayments);
        cvProfile.setOnClickListener(this);
        cvAttendance.setOnClickListener(this);
        cvHomeWork.setOnClickListener(this);
        cvTimeTable.setOnClickListener(this);
        cvNotifications.setOnClickListener(this);
        cvEvents.setOnClickListener(this);
        cvOnlineClasses.setOnClickListener(this);
        cvGallery.setOnClickListener(this);
        cvExams.setOnClickListener(this);
        cvPayments.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llHome:
                ScreenNav.addFragment(FragmentName.HOME, this);
                break;
            case R.id.llProfile:
                ScreenNav.addFragment(FragmentName.PROFILE, this);
                break;
            case R.id.llPayment:
                ScreenNav.addFragment(FragmentName.PAYMENTS, this);
                break;
            case R.id.llNotification:
                ScreenNav.addFragment(FragmentName.NOTIICATIONS, this);
                if(tvCnt.getVisibility()==View.VISIBLE){
                    updateNotificationCnt();
                    tvCnt.setVisibility(View.GONE);
                }
                break;
        }
       /* switch (view.getId()){
            case R.id.cvProfile:
                startNewActivity(StudentProfileActivity.class);
                break;
            case R.id.cvAttendance:
                startNewActivity(AttendanceActivity.class);
                break;
            case R.id.cvHomeWork:
                startNewActivity(HomeWorkActivity.class);
                break;
            case R.id.cvTimeTable:
                startNewActivity(TimeTableActivity.class);
                break;
            case R.id.cvNotifications:
                startNewActivity(NotificationsActivity.class);
                break;
            case R.id.cvEvents:
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                startNewActivity(EventsActivity.class);
                break;
            case R.id.cvOnlineClasses:
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                startNewActivity(OnlineClassesActivity.class);
                break;
            case R.id.cvGallery:
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                startNewActivity(GalleryActivity.class);
                break;
            case R.id.cvExams:
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                startNewActivity(ExamsActivity.class);
                break;
            case R.id.cvPayments:
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                startNewActivity(PaymentsActivity.class);
                break;
        }*/
    }
    String mer_txn="",merchant_id="",amt="",tdate="";
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            ArrayList arr = data.getStringArrayListExtra("response"); //have all response data
            HashMap<String, String> hm = new HashMap<String, String>();
            for(int i=0; i<arr.size(); i++)
            {
                String s[] = (String[]) arr.get(i);
                hm.put(s[0], s[1]);
            }
            for (String key : hm.keySet()) {
                String value = hm.get(key);
                System.out.println("Key = " + key + " value = " + value);
                if(key.equals("mer_txn")){
                    mer_txn = value;
                }
                if(key.equals("merchant_id")){
                    merchant_id = value;
                }
                if(key.equals("amt")){
                    amt = value;
                }
                if(key.equals("date")){
                    tdate = value;
                }

            }
            if(resultCode == 1){
                JsonObject obj=new JsonObject();
                obj.addProperty("tstatus","success");
                obj.addProperty("tid",mer_txn);
                obj.addProperty("tamount",amt);
                obj.addProperty("tdate",tdate);
                updateTransactionStatus("success",mer_txn,amt, tdate);
                Toast.makeText(getApplicationContext(),"Transaction Successful! \n" +mer_txn, Toast.LENGTH_LONG).show();
                String benc="merchantid="+merchant_id+"&merchanttxnid="+mer_txn+"&amt="+amt+"&tdate=2022-06-10";//tdate
                Log.i("benc",benc);
                Log.i("enc_data",AtomEncryption.encrypt(benc,"A4476C2062FFA58980DC8F79EB6A799E"));
                try {
                    Log.i("enc_data", AtomEncryption.decrypt("C05AED44531A61C12454AC83E2D48EA16A780A0A0F35FF24075A80FAAACAE3E430557E0FA2968E35C4FF2ECB37402443C1BE16094B2E6D7E93B0934FB16F45324F406580A3A54B497148FA3CD939812FB92E16A72B82E46911E81B92444F8BD48BBC014C7955D062881BFDEA1C557761065918731626EBF1B9630DD5A7B9E2412E76E0AEA10E08E22993B62FD81D76A491FD96D69851E6B029118965822982AB1809978FF0EE4F773F858AA7C56B7755D2025F4C0B316C41B9EFB46A30B7B6C3E4D00656DB72164C7899C2724C365D62D04EFCF4BBB111E3D41276D7717976044EC38DA8177FDC432B5BFC8260E3DBBA84CB3568DCAF50A6E07256FDAEDD071A942DCC5AD4A9638791DC222A7DCD3F22F10DE70E254869F9AD35E59E68F54AC8982FF6DDB5B611C3A9D55FBB39A5B2BD", "75AEF0FA1B94B3C10D4F5B268F757F11"));//C05AED44531A61C12454AC83E2D48EA16A780A0A0F35FF24075A80FAAACAE3E430557E0FA2968E35C4FF2ECB37402443C1BE16094B2E6D7E93B0934FB16F45324F406580A3A54B497148FA3CD939812FB92E16A72B82E46911E81B92444F8BD48BBC014C7955D062881BFDEA1C557761065918731626EBF1B9630DD5A7B9E2412E76E0AEA10E08E22993B62FD81D76A491FD96D69851E6B029118965822982AB1809978FF0EE4F773F858AA7C56B7755D2025F4C0B316C41B9EFB46A30B7B6C3E4D00656DB72164C7899C2724C365D62D04EFCF4BBB111E3D41276D7717976044EC38DA8177FDC432B5BFC8260E3DBBA84CB3568DCAF50A6E07256FDAEDD071A942DCC5AD4A9638791DC222A7DCD3F22F10DE70E254869F9AD35E59E68F54AC8982FF6DDB5B611C3A9D55FBB39A5B2BD
                }catch (Exception e){
                    Log.i("enc_data",e.getMessage());
                }
            }
            else if(resultCode == 2){
                JsonObject obj=new JsonObject();
                obj.addProperty("tstatus","cancelled");
                obj.addProperty("tid",mer_txn);
                obj.addProperty("tamount",amt);
                obj.addProperty("tdate",tdate);
                updateTransactionStatus("success",mer_txn,amt, tdate);
                Toast.makeText(getApplicationContext(),"Transaction Cancelled! \n" +mer_txn, Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(),"Transaction Cancelled! \n"  + Arrays.toString((String[]) arr.get(20)) + "\n" + Arrays.toString((String[]) arr.get(14)), Toast.LENGTH_LONG).show();
            }
            else{
                JsonObject obj=new JsonObject();
                obj.addProperty("tstatus","failed");
                obj.addProperty("tid",mer_txn);
                obj.addProperty("tamount",amt);
                obj.addProperty("tdate",tdate);
                updateTransactionStatus("success",mer_txn,amt, tdate);
                Toast.makeText(getApplicationContext(),"Transaction Failed! \n" +mer_txn, Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),"Transaction Failed! \n"  + Arrays.toString((String[]) arr.get(20)) + "\n" + Arrays.toString((String[]) arr.get(14)), Toast.LENGTH_LONG).show();
            }
        }
        else{
            JsonObject obj=new JsonObject();
            obj.addProperty("tstatus","cancelled");
            obj.addProperty("tid",mer_txn);
            obj.addProperty("tamount",amt);
            obj.addProperty("tdate",tdate);
            updateTransactionStatus("success",mer_txn,amt, tdate);
            Toast.makeText(getApplicationContext(),"Transaction Cancelled! \n" +mer_txn, Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(),"Transaction Cancelled!", Toast.LENGTH_LONG).show();
        }
    }//onActivityResult

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() ==1) {
            finish();
        }else{
            super.onBackPressed();
        }
    }
    String paid_amount="0";
    public  void razorPayment(String m,String pay_id){
        paid_amount = m;
        CallPaytmIntegration(m,pay_id);
        //Toast.makeText(StudentDashboardActivity.this, "", Toast.LENGTH_SHORT).show();
    }
    Dialog pd;
    UpdateTransactionStatusViewModel viewModel;
    private void updateTransactionStatus(String tstatus, String tid, String tamount, String tdate){
        pd= new MyProgressDialog(StudentDashboardActivity.this);
        pd.show();
        viewModel = ViewModelProviders.of(this).get(UpdateTransactionStatusViewModel.class);
        viewModel.getTransactionResponseLiveData( tstatus,  tid,  tamount,  tdate).observe(this, new Observer<TransactionStatusModel>() {
            @Override
            public void onChanged(TransactionStatusModel loginResponse) {
                if(loginResponse!=null) {
                    pd.dismiss();
                    if(loginResponse.getCode()==200) {
                        Toast.makeText(getApplicationContext(), "Updated successfully.", Toast.LENGTH_SHORT).show();
                        ScreenNav.addFragment(FragmentName.PAYMENTS, StudentDashboardActivity.this);
                    }else{
                        Toast.makeText(getApplicationContext(), "Oop! something went worng.Try again. Error code : "+loginResponse.getCode(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    pd.dismiss();
                }
            }
        });
    }

    UpdateNotificationCountViewModel vm;
    private void updateNotificationCnt(){
        vm = ViewModelProviders.of(this).get(UpdateNotificationCountViewModel.class);
        vm.updateNotificationCntViewModel().observe(this, new Observer<NotificationUpdateModel>() {
            @Override
            public void onChanged(NotificationUpdateModel loginResponse) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(int message) {
        if(message>0) {
            tvCnt.setText(message + "");
            tvCnt.setVisibility(View.VISIBLE);
        }else{
            tvCnt.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        updateTransactionStatus("success",s,paid_amount, formatter.format(date));
        Toast.makeText(getApplicationContext(),"Transaction Successful! \n" +mer_txn, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        updateTransactionStatus("failed","RAZOR",paid_amount, formatter.format(date));
        Toast.makeText(getApplicationContext(),"Transaction Failed! \n" +mer_txn, Toast.LENGTH_LONG).show();
    }
    public void CallPaytmIntegration(String txnAmount,String pay_id) {
        //Toast.makeText(getApplicationContext(), checkSumHash, Toast.LENGTH_SHORT).show();
        //int amount = Math.round(Float.parseFloat(txnAmount) * 100);
        int amount = Math.round(Float.parseFloat(txnAmount) * 100);
        // initialize Razorpay account.
        Checkout checkout = new Checkout();

        // set your id as below
        //checkout.setKeyID("rzp_test_KiHV4GDYU4VTaZ");
        checkout.setKeyID(pay_id);

        // set image
        checkout.setImage(R.drawable.tapasya_logo);
        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "Tapasya");
            object.put("description", "Fee");
            //object.put("theme.color", "");
            object.put("currency", "INR");//USD,INR
            object.put("amount", amount);
            JSONObject preFillObj = new JSONObject();
            preFillObj.put("contact","9999999999");
            preFillObj.put("email","info@tapasya.com");
            object.put("prefill", preFillObj);

            JSONObject readOnly = new JSONObject();
            readOnly.put("email",false);
            readOnly.put("contact",false);
            //object.put("image", "https://www.sambav.com/app/img/sambav.svg");

            checkout.open(StudentDashboardActivity.this, object);
        } catch (JSONException e) {
            Log.i("razor_error",e.getMessage());
            Toast.makeText(StudentDashboardActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}