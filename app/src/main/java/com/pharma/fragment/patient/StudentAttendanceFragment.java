package com.pharma.fragment.patient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pharma.R;
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.model.parent.AttendanceListModel;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.MonthlyAttendanceModel;
import com.pharma.model.parent.YearWiseListModel;
import com.pharma.model.parent.YearWiseModel;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.AttendanceViewModel;
import com.pharma.view_model.parent.EventsViewModel;
import com.pharma.view_model.parent.SharedViewModel;
import com.seglib.SegmentedButtonGroup;

import java.util.Calendar;
import java.util.List;

public class StudentAttendanceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentAttendanceFragment() {
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
    public static StudentAttendanceFragment newInstance(String param1, String param2) {
        StudentAttendanceFragment fragment = new StudentAttendanceFragment();
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
    SegmentedButtonGroup segButton;
    LinearLayout llBack;
    SharedViewModel sharedViewModelInstance=null;
    public static int mYear=2022,mMonth=11;
    private static StudentAttendanceFragment instance = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_attendance, container, false);
        instance =this;
         yearWiseStuAtt=null;
         montwiseStuAtt=null;
        mYear= Calendar.getInstance().get(Calendar.YEAR);
        mMonth= Calendar.getInstance().get(Calendar.MONTH);
        segButton = (SegmentedButtonGroup)view.findViewById(R.id.segButton);
        segButton.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(final int position) {
                //Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
                addFragment(position);
            }
        });
        getAttendance(mMonth,mYear);

        return view;
    }
    AttendanceViewModel attendanceViewModel;
    Dialog pd;
    public static YearWiseListModel yearWiseStuAtt=null;
    public static List<MonthlyAttendanceModel> montwiseStuAtt=null;
    private void getAttendance(int mMonth,int mYear){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        attendanceViewModel = ViewModelProviders.of(getActivity()).get(AttendanceViewModel.class);
        attendanceViewModel.getAttendanceResponseLiveData((mMonth+1)+"",mYear+"").observe(getActivity(), new Observer<AttendanceListModel>() {
            @Override
            public void onChanged(AttendanceListModel attendanceResponse) {
                pd.dismiss();
                if(attendanceResponse!=null) {
                    if(attendanceResponse.getCode()==200) {
                        /* End 200*/
                        if(attendanceResponse.getYearWise()!=null){
                            yearWiseStuAtt = attendanceResponse.getYearWise();
                        }
                        if(attendanceResponse.getStuAttRespMonthArray()!=null) {
                            if (attendanceResponse.getStuAttRespMonthArray().size() > 0) {
                                montwiseStuAtt = attendanceResponse.getStuAttRespMonthArray();
                            }
                        }
                        addFragment(0);
                        /* End 200*/
                    }else{
                        Toast.makeText(getActivity(), "Oop! something went worng.Try again. Error code : ", Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }
        });
    }
    private void addFragment(int pos){
        Fragment fragment=null;
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (pos){
            case 0:fragment = new AttendanceFragment();
                transaction.replace(R.id.fragment_container, fragment);
                break;
            /*case 1:fragment = new HolidaysFragment();
                transaction.replace(R.id.fragment_container, fragment);
                break;*/
            case 1:fragment = new YearAttendanceFragment();
                transaction.replace(R.id.fragment_container, fragment);
                break;
        }
        transaction.commit();
    }
    public static StudentAttendanceFragment getInstance() {
        return instance;
    }
    public void setSegButtonPosition1() {
        segButton.setPosition(0,true);
    }
}
