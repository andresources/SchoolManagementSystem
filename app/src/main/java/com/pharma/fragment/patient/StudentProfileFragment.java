package com.pharma.fragment.patient;

import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T;
import static com.pharma.utils.DateUtils.SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_24_HOURS;
import static com.pharma.utils.DateUtils.SIMPLE_TIME_FORMAT_AM_PM;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.pharma.MyApp;
import com.pharma.R;
import com.pharma.fragment.BaseFragment;
import com.pharma.model.parent.CompusDataModel;
import com.pharma.model.parent.StuDetailsModel;
import com.pharma.model.parent.UserDataModel;
import com.pharma.utils.AppKeys;
import com.pharma.utils.DateUtils;

import javax.inject.Inject;

public class StudentProfileFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HolidaysFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentProfileFragment newInstance(String param1, String param2) {
        StudentProfileFragment fragment = new StudentProfileFragment();
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
    TextView tvName,tvClass,tvBranchName;
    ImageView iv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_profile, container, false);
        tvName=(TextView)view.findViewById(R.id.tvName);
        tvClass=(TextView)view.findViewById(R.id.tvClass);
        tvBranchName=(TextView)view.findViewById(R.id.tvBranchName);
        etName=(TextInputEditText)view.findViewById(R.id.etName);
        etFName=(TextInputEditText)view.findViewById(R.id.etFName);
        iv=(ImageView) view.findViewById(R.id.iv);

        etAcademicYear=(TextInputEditText)view.findViewById(R.id.etAcademicYear);
        etRollNo=(TextInputEditText)view.findViewById(R.id.etRollNo);
        etSection=(TextInputEditText)view.findViewById(R.id.etSection);
        etDOJ=(TextInputEditText)view.findViewById(R.id.etDOJ);
        etHostel=(TextInputEditText)view.findViewById(R.id.etHostel);
        etContactNumber=(TextInputEditText)view.findViewById(R.id.etContactNumber);
        etGender=(TextInputEditText)view.findViewById(R.id.etGender);
        etMotherTongue=(TextInputEditText)view.findViewById(R.id.etMotherTongue);

        etDOB=(TextInputEditText)view.findViewById(R.id.etDOB);
        etNationality=(TextInputEditText)view.findViewById(R.id.etNationality);

        etEmail=(TextInputEditText)view.findViewById(R.id.etEmail);
        etOldTC=(TextInputEditText)view.findViewById(R.id.etOldTC);
        etAddresses=(TextInputEditText)view.findViewById(R.id.etAddresses);

        setUserProfile();
        return view;
    }
    @Inject
    SharedPreferences sharedPreferences;
    TextInputEditText etAddresses,etEmail,etOldTC,etDOB,etNationality,etGender,etMotherTongue,etName,etFName,etAcademicYear,etRollNo,etSection,etDOJ,etHostel,etContactNumber;
    private void setUserProfile() {
        MyApp.app().appComponent().inject(StudentProfileFragment.this);
        String str=sharedPreferences.getString(AppKeys.USER_NAME, "default");
        String str1=sharedPreferences.getString(AppKeys.STD_DETAILS, "default");
        String str_cmp=sharedPreferences.getString(AppKeys.CAMP_DATA, "default");
        Gson gson = new Gson();
        UserDataModel user=gson.fromJson(str, UserDataModel.class);
        StuDetailsModel std=gson.fromJson(str1, StuDetailsModel.class);
        CompusDataModel comp=gson.fromJson(str_cmp, CompusDataModel.class);
        tvName.setText(user.getNameF()+" "+user.getNameL());
        tvClass.setText(std.getClsName()+"/ "+std.getSecName()+"/ Roll No:"+user.getRollNo());
        etName.setText(user.getNameF()+" "+user.getNameL());
        tvBranchName.setText("Branch : "+comp.getName());
        etFName.setText(user.getFathName());
        Glide.with(getActivity())
                .load("https://erp.tapasyaedu.com:9443/t/imgImgDisp.action?imgFolder="+user.getImgFolder()+"&refType=stu_&ref="+user.getSno())
                .into(iv);
        etAcademicYear.setText(std.getAcadYearName()+"");
        etRollNo.setText(user.getRollNo());
        etMotherTongue.setText(user.getMotherTongue());
        etOldTC.setText(user.getOldTcNo());

        etSection.setText(std.getSecName());
        try {
            String d[]=user.getDateJ().substring(0,user.getDateJ().indexOf(".")).split("T");
            etDOJ.setText(DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T,SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24,d[0]));
        }catch (Exception e){
            etDOJ.setText(user.getDateJ());
        }

        if(user.getHstl()==0){
            etHostel.setText("No");
        }else {
            etHostel.setText("Yes`");
        }
        //etHostel.setText(user.getHstl()+"");
        etContactNumber.setText(user.getMobF());
        if(user.getGender()==1){
            etGender.setText("Male");
        }else{
            etGender.setText("Female");
        }
        try {
            String d[]=user.getDob().substring(0,user.getDob().indexOf(".")).split("T");
            etDOB.setText(DateUtils.getDesiredDateFormat(SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T,SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24,d[0]));
        }catch (Exception e){
            etDOB.setText(user.getDob()+"");
        }

        etNationality.setText(user.getNation());
        etEmail.setText(user.getEmail());
        etAddresses.setText(user.getAddr());
    }
}