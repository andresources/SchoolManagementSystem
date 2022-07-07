package com.pharma.fragment.patient;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.pharma.MyApp;
import com.pharma.R;
import com.pharma.fragment.BaseFragment;
import com.pharma.model.parent.AttendanceListModel;
import com.pharma.model.parent.CompusDataModel;
import com.pharma.model.parent.Employee;
import com.pharma.model.parent.HomeScreenDataListModel;
import com.pharma.model.parent.StuDetailsModel;
import com.pharma.model.parent.UserDataModel;
import com.pharma.ui.LoginActivity;
import com.pharma.ui.parent.AttendanceActivity;
import com.pharma.ui.parent.EventsActivity;
import com.pharma.ui.parent.ExamsActivity;
import com.pharma.ui.parent.GalleryActivity;
import com.pharma.ui.parent.HomeWorkActivity;
import com.pharma.ui.parent.NotificationsActivity;
import com.pharma.ui.parent.OnlineClassesActivity;
import com.pharma.ui.parent.PaymentsActivity;
import com.pharma.ui.parent.StudentProfileActivity;
import com.pharma.ui.parent.TimeTableActivity;
import com.pharma.utils.AppKeys;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.FragmentName;
import com.pharma.utils.MyProgressDialog;
import com.pharma.utils.ScreenNav;
import com.pharma.view_model.parent.AttendanceViewModel;
import com.pharma.view_model.parent.HomeScreenDataViewMOdel;

import java.text.NumberFormat;
import java.util.Locale;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentHomeFragment extends BaseFragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    //Your Activity will implement this interface
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int message);
    }

    public StudentHomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentHomeFragment newInstance(String param1, String param2) {
        StudentHomeFragment fragment = new StudentHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    RelativeLayout rlProfile;
    ImageView iv;
    LinearLayout llAttendance,llFee;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Employee emp;
    CardView cvLogout,cvELearning,cvPayments,cvExams,cvGallery,cvProfile,cvAttendance,cvHomeWork,cvTimeTable,cvNotifications,cvEvents,cvOnlineClasses;
    TextView tvName,tvClass,tvAttPercentage,tvTotalFee,tvFeeMsg,tvBranchName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_student_home, container, false);
        rlProfile = (RelativeLayout) view.findViewById(R.id.rlProfile);
        llAttendance = (LinearLayout) view.findViewById(R.id.llAttendance);
        llFee = (LinearLayout) view.findViewById(R.id.llFee);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvClass = (TextView) view.findViewById(R.id.tvClass);
        tvAttPercentage= (TextView) view.findViewById(R.id.tvAttPercentage);
        tvTotalFee= (TextView) view.findViewById(R.id.tvTotalFee);
        tvFeeMsg= (TextView) view.findViewById(R.id.tvFeeMsg);
        tvBranchName= (TextView) view.findViewById(R.id.tvBranchName);
        iv=(ImageView) view.findViewById(R.id.iv);
        cvProfile = (CardView) view.findViewById(R.id.cvProfile);
        cvAttendance= (CardView) view.findViewById(R.id.cvAttendance);
        cvHomeWork= (CardView) view.findViewById(R.id.cvHomeWork);
        cvTimeTable= (CardView) view.findViewById(R.id.cvTimeTable);
        cvNotifications= (CardView) view.findViewById(R.id.cvNotifications);
        cvEvents= (CardView) view.findViewById(R.id.cvEvents);
        cvOnlineClasses= (CardView) view.findViewById(R.id.cvOnlineClasses);
        cvGallery= (CardView) view.findViewById(R.id.cvGallery);
        cvExams= (CardView) view.findViewById(R.id.cvExams);
        cvPayments= (CardView) view.findViewById(R.id.cvPayments);
        cvELearning= (CardView) view.findViewById(R.id.cvELearning);
        cvLogout= (CardView) view.findViewById(R.id.cvLogout);
        cvProfile.setOnClickListener(this);
        cvAttendance.setOnClickListener(this);
        cvHomeWork.setOnClickListener(this);
        cvTimeTable.setOnClickListener(this);
        cvNotifications.setOnClickListener(this);
        cvEvents.setOnClickListener(this);
        cvOnlineClasses.setOnClickListener(this);
        cvGallery.setOnClickListener(this);
        cvExams.setOnClickListener(this);
        cvPayments.setOnClickListener(this);
        cvELearning.setOnClickListener(this);
        cvLogout.setOnClickListener(this);

        rlProfile.setOnClickListener(this);
        llAttendance.setOnClickListener(this);
        llFee.setOnClickListener(this);
        setUserProfile();
        getHomeScreenDetails();
        return view;
    }

    private void setUserProfile() {
        MyApp.app().appComponent().inject(StudentHomeFragment.this);
        String str=sharedPreferences.getString(AppKeys.USER_NAME, "default");
        String st=sharedPreferences.getString(AppKeys.STD_DETAILS, "default");
        String str_cmp=sharedPreferences.getString(AppKeys.CAMP_DATA, "default");

        Gson gson = new Gson();
        UserDataModel user=gson.fromJson(str, UserDataModel.class);
        StuDetailsModel std=gson.fromJson(st, StuDetailsModel.class);
        CompusDataModel comp=gson.fromJson(str_cmp, CompusDataModel.class);
        tvName.setText(user.getNameF()+" "+user.getNameL());
        tvClass.setText(std.getClsName()+"/ "+std.getSecName()+"/RNO: "+user.getRollNo());
        tvBranchName.setText("Branch : "+comp.getName());
        Glide.with(getActivity())
                .load("https://erp.tapasyaedu.com:9443/t/imgImgDisp.action?imgFolder="+user.getImgFolder()+"&refType=stu_&ref="+user.getSno())
                .into(iv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvProfile:
                ScreenNav.addFragment(FragmentName.PROFILE,getActivity());
                //startNewActivity(StudentProfileActivity.class);
                break;
            case R.id.rlProfile:
                ScreenNav.addFragment(FragmentName.PROFILE,getActivity());
                //startNewActivity(StudentProfileActivity.class);
                break;
            case R.id.cvAttendance:
                ScreenNav.addFragment(FragmentName.ATTENDANCE,getActivity());
                //startNewActivity(AttendanceActivity.class);
                break;
            case R.id.llAttendance:
                ScreenNav.addFragment(FragmentName.ATTENDANCE,getActivity());
                //startNewActivity(AttendanceActivity.class);
                break;
            case R.id.cvHomeWork:
                ScreenNav.addFragment(FragmentName.HOMEWORK,getActivity());
                //startNewActivity(HomeWorkActivity.class);
                break;
            case R.id.cvTimeTable:
                ScreenNav.addFragment(FragmentName.TIMETABLE,getActivity());
                //startNewActivity(TimeTableActivity.class);
                break;
            case R.id.cvNotifications:
                ScreenNav.addFragment(FragmentName.NOTIICATIONS,getActivity());
                //startNewActivity(NotificationsActivity.class);
                break;
            case R.id.cvEvents:
                ScreenNav.addFragment(FragmentName.EVENTS,getActivity());
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                //startNewActivity(EventsActivity.class);
                break;
            case R.id.cvOnlineClasses:
                ScreenNav.addFragment(FragmentName.ONLINECLASSES,getActivity());
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                //startNewActivity(OnlineClassesActivity.class);
                break;
            case R.id.cvGallery:
                ScreenNav.addFragment(FragmentName.GALLERY,getActivity());
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                //startNewActivity(GalleryActivity.class);
                break;
            case R.id.cvExams:
                ScreenNav.addFragment(FragmentName.EXAMS,getActivity());
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                //startNewActivity(ExamsActivity.class);
                break;
            case R.id.cvPayments:
                ScreenNav.addFragment(FragmentName.PAYMENTS,getActivity());
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                //startNewActivity(PaymentsActivity.class);
                break;
            case R.id.llFee:
                ScreenNav.addFragment(FragmentName.PAYMENTS,getActivity());
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                //startNewActivity(PaymentsActivity.class);
                break;
            case R.id.cvELearning:
                ScreenNav.addFragment(FragmentName.ELEARNING,getActivity());
                //Toast.makeText(getApplicationContext(), "mmm", Toast.LENGTH_SHORT).show();
                //startNewActivity(PaymentsActivity.class);
                break;
            case R.id.cvLogout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(AppKeys.IS_ALREADY_LOGIN, "no");
                editor.putString(AppKeys.IS_DISPLAY_ALERT, "yes");
                editor.apply();
                getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();
                break;
        }
    }
    Dialog pd;
    HomeScreenDataViewMOdel attendanceViewModel;
    private void getHomeScreenDetails(){
        pd= new MyProgressDialog(getActivity());
        pd.setCancelable(false);
        pd.show();

        attendanceViewModel = ViewModelProviders.of(getActivity()).get(HomeScreenDataViewMOdel.class);
        attendanceViewModel.getHomeScreenDetailsResponseLiveData().observe(getActivity(), new Observer<HomeScreenDataListModel>() {
            @Override
            public void onChanged(HomeScreenDataListModel attendanceResponse) {
                pd.dismiss();
                if(attendanceResponse!=null) {
                    if(attendanceResponse.getCode()==200) {
                        /* Start 200*/
                        //Toast.makeText(getActivity(), "Hhh", Toast.LENGTH_SHORT).show();
                        tvAttPercentage.setText((int)attendanceResponse.getAttPerc()+"%");
                        tvTotalFee.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(Float.parseFloat(attendanceResponse.getHomeScrnData().getFee_due())));
                        tvFeeMsg.setText(attendanceResponse.getHomeScrnData().getDelay_msg());
                        if (mListener != null) {
                            mListener.onFragmentInteraction(attendanceResponse.getHomeScrnData().getCount());
                        }
                        if(sharedPreferences.getString(AppKeys.IS_DISPLAY_ALERT, "no").equals("yes")){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(AppKeys.IS_DISPLAY_ALERT, "no");
                            editor.apply();
                            if(attendanceResponse.getHomeScreenAlert().getDisplayAlertType()!=null){
                                if(attendanceResponse.getHomeScreenAlert().getDisplayAlertType().equals("text")){
                                    displayTextAlert(attendanceResponse.getHomeScreenAlert().getDisplayAlertTitle(),attendanceResponse.getHomeScreenAlert().getDisplayAlertDes(),attendanceResponse.getHomeScreenAlert().getDisplayAlert());
                                }else{
                                    displayImageAlert(attendanceResponse.getHomeScreenAlert().getImageUrl(),attendanceResponse.getHomeScreenAlert().getDisplayAlert());
                                }
                            }
                        }
                        /*if(attendanceResponse.getHomeScrnData().getCount()>0){
                            tvCnt.setVisibility(View.VISIBLE);
                            tvCnt.setText(attendanceResponse.getHomeScrnData().getCount()+"");
                        }else{
                            tvCnt.setVisibility(View.GONE);
                            tvCnt.setText(attendanceResponse.getHomeScrnData().getCount()+"");
                        }*/
                        /* End 200*/
                    }else{
                        Toast.makeText(getActivity(), "Oop! something went worng.Try again. Error code : ", Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }
        });
    }
    private void displayTextAlert(String title,String des,String close){
        DashboardAlertDialog dia=new DashboardAlertDialog(getActivity(),R.layout.dialog_text_home_alert);
        dia.setCancelable(false);
        dia.show();
        if(close.equals("yes")){
            dia.findViewById(R.id.ivClose).setVisibility(View.VISIBLE);
        }else{
            dia.findViewById(R.id.ivClose).setVisibility(View.GONE);
        }
        dia.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia.dismiss();
            }
        });
        TextView tvAlertTitle=dia.findViewById(R.id.tvAlertTitle);
        TextView tvAlertDes=dia.findViewById(R.id.tvAlertDes);
        if(title!=null) {
            tvAlertTitle.setText(title);
        }
        if(des!=null) {
            tvAlertDes.setText(des);
        }
    }
    private void displayImageAlert(String des,String close){
        DashboardAlertDialog dia=new DashboardAlertDialog(getActivity(),R.layout.dialog_image_home_alert);
        dia.setCancelable(false);
        dia.show();
        //Toast.makeText(getActivity(),""+close,Toast.LENGTH_LONG).show();
        if(close.equals("yes")){
            dia.findViewById(R.id.ivClose).setVisibility(View.VISIBLE);
        }else{
            dia.findViewById(R.id.ivClose).setVisibility(View.GONE);
        }
        dia.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia.dismiss();
            }
        });
        ImageView ivAlert=dia.findViewById(R.id.ivAlert);
        Glide.with(getActivity())
                .load(des)
                .into(ivAlert);

    }
    private class DashboardAlertDialog extends Dialog  {
        public DashboardAlertDialog(@NonNull Context context,int rid) {
            super(context);
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            setContentView(rid);
        }
        @Override
        public void show() {
            super.show();
        }

        @Override
        public void hide() {
            super.hide();
        }

        @Override
        public void dismiss() {
            super.dismiss();
        }

    }
}