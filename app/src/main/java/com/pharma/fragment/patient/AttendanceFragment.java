package com.pharma.fragment.patient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.callib.CompactCalendarView;
import com.callib.domain.Event;
import com.pharma.MainActivity;
import com.pharma.R;
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.adapter.parent.LeavesAdapter;
import com.pharma.adapter.parent.MovieArticleAdapter;
import com.pharma.model.parent.AbsentLeaveModel;
import com.pharma.model.parent.AttendanceListModel;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.LeaveModel;
import com.pharma.model.parent.MonthlyAttendanceModel;
import com.pharma.ui.parent.AttendanceActivity;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.AttendanceViewModel;
import com.pharma.view_model.parent.EventsViewModel;
import com.pharma.view_model.parent.SharedViewModel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AttendanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendanceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AttendanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttendanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendanceFragment newInstance(String param1, String param2) {
        AttendanceFragment fragment = new AttendanceFragment();
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
    Button btnPrevious,btnNext;
    CompactCalendarView compactcalendar_view;
    TextView tvTitle,tvAbsentCnt,tvPresentCnt,tvHolidayCnt,tvTotalWorkingDays;
    ImageView ivShowHide;
    List<AbsentLeaveModel> list=new ArrayList<>();
    private SimpleDateFormat week_name = new SimpleDateFormat("EEEE", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat _month = new SimpleDateFormat("MM", Locale.getDefault());
    private SimpleDateFormat _year = new SimpleDateFormat("yyyy", Locale.getDefault());
    boolean bg_pre_next=true,is_show=true;
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_attendance, container, false);
        tvTitle= (TextView)view.findViewById(R.id.tvTitle);
        tvAbsentCnt= (TextView)view.findViewById(R.id.tvAbsentCnt);
        tvPresentCnt= (TextView)view.findViewById(R.id.tvPresentCnt);
        tvHolidayCnt= (TextView)view.findViewById(R.id.tvHolidayCnt);
        tvTotalWorkingDays= (TextView)view.findViewById(R.id.tvTotalWorkingDays);
        ivShowHide= (ImageView) view.findViewById(R.id.ivShowHide);

        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        getAttendance(StudentAttendanceFragment.mMonth,StudentAttendanceFragment.mYear);
        Calendar myCal = Calendar.getInstance();
        //Toast.makeText(getActivity(), AttendanceActivity.mYear+" -> "+AttendanceActivity.mMonth, Toast.LENGTH_SHORT).show();
        myCal.set(Calendar.YEAR, StudentAttendanceFragment.mYear);
        myCal.set(Calendar.MONTH, StudentAttendanceFragment.mMonth);//-1 means month start from 0
        myCal.set(Calendar.DAY_OF_MONTH, 1);
        Date theDate = myCal.getTime();
        compactcalendar_view = (CompactCalendarView)view.findViewById(R.id.compactcalendar_view);
        compactcalendar_view.setCurrentDate(theDate);
        ivShowHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (!is_show) {
                        //Toast.makeText(getActivity(),"Showing",Toast.LENGTH_LONG).show();
                        ivShowHide.setBackgroundResource(R.drawable.ic_up);
                        is_show = true;
                        compactcalendar_view.setVisibility(View.VISIBLE);
                        compactcalendar_view.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.in_animation));
                        //compactcalendar_view.showCalendarWithAnimation();
                    } else {
                        //Toast.makeText(getActivity(),"Hid",Toast.LENGTH_LONG).show();
                        ivShowHide.setBackgroundResource(R.drawable.ic_down);
                        is_show = false;
                        compactcalendar_view.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.out_animation));
                        compactcalendar_view.setVisibility(View.GONE);
                       // compactcalendar_view.hideCalendarWithAnimation();
                    }

            }
        });

        tvTitle.setText(dateFormatForMonth.format(compactcalendar_view.getFirstDayOfCurrentMonth()));
        //tvTitle.setText(_month.format(compactcalendar_view.getFirstDayOfCurrentMonth())+"-"+_year.format(compactcalendar_view.getFirstDayOfCurrentMonth()));
        btnPrevious = (Button)view.findViewById(R.id.btnPrevious);
        btnNext = (Button)view.findViewById(R.id.btnNext);

        compactcalendar_view.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                tvTitle.setText(dateFormatForMonth.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                int mm=Integer.parseInt(_month.format(compactcalendar_view.getFirstDayOfCurrentMonth()));
                int yy=Integer.parseInt(_year.format(compactcalendar_view.getFirstDayOfCurrentMonth()));
                getAttendance(mm-1,yy);
                //Toast.makeText(getApplicationContext(), _month.format(compactcalendar_view.getFirstDayOfCurrentMonth())+"-"+_year.format(compactcalendar_view.getFirstDayOfCurrentMonth()), Toast.LENGTH_SHORT).show();
               // setAbsentPresentCount(Integer.parseInt(_month.format(compactcalendar_view.getFirstDayOfCurrentMonth())),Integer.parseInt(_year.format(compactcalendar_view.getFirstDayOfCurrentMonth())));
                tvTitle.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                if(bg_pre_next) {
                    tvTitle.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down));
                }else{
                    tvTitle.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up));
                }
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAttendance
                bg_pre_next = false;
                compactcalendar_view.scrollLeft();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg_pre_next = true;
                compactcalendar_view.scrollRight();

            }
        });
        prepareDataCalender();
        //compactcalendar_view.invalidate();
        return view;
    }
    Dialog pd;
    AttendanceViewModel attendanceViewModel;
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

                        if(attendanceResponse.getStuAttRespMonthArray()!=null) {
                            if (attendanceResponse.getStuAttRespMonthArray().size() > 0) {
                                StudentAttendanceFragment.montwiseStuAtt = attendanceResponse.getStuAttRespMonthArray();
                                StudentAttendanceFragment.yearWiseStuAtt = attendanceResponse.getYearWise();
                                prepareDataCalender();
                                //setAbsentPresentCount(Integer.parseInt(_month.format(compactcalendar_view.getFirstDayOfCurrentMonth())),Integer.parseInt(_year.format(compactcalendar_view.getFirstDayOfCurrentMonth())));
                            }
                        }

                        /* End 200*/
                    }else{
                        Toast.makeText(getActivity(), "Oop! something went worng.Try again. Error code : ", Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }
        });
    }

    private void prepareDataCalender() {
        try{
            prepareDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if(list.size()>0) {

                for (AbsentLeaveModel m : list) {
                    String inputDateInString = m.getLdate();
                    Date parsedDate = dateFormat.parse(inputDateInString);
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());
                    if (m.getLeave_type() == 1) {
                        compactcalendar_view.addEvent(new Event(getResources().getColor(R.color.green_100), timestamp.getTime()));
                    } else if (m.getLeave_type() == 2) {
                        compactcalendar_view.addEvent(new Event(getResources().getColor(R.color.red_100), timestamp.getTime()));
                    } else if(m.getLeave_type() == 3){
                        compactcalendar_view.addEvent(new Event(getResources().getColor(R.color.blue_100), timestamp.getTime()));
                    }else{
                        compactcalendar_view.addEvent(new Event(getResources().getColor(R.color.silver), timestamp.getTime()));
                    }
                }

                setAbsentPresentCount(Integer.parseInt(_month.format(compactcalendar_view.getFirstDayOfCurrentMonth())), Integer.parseInt(_year.format(compactcalendar_view.getFirstDayOfCurrentMonth())));
            }
           /* Date parsedDate = dateFormat.parse("20/06/2022");
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            compactcalendar_view.addEvent(new Event(getResources().getColor(R.color.green_100), timestamp.getTime()));*/

        }catch (Exception e){
            //Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        compactcalendar_view.invalidate();
    }

    private void prepareDate(){
        list.clear();
        if(StudentAttendanceFragment.montwiseStuAtt.size()>0) {
            for (MonthlyAttendanceModel _month : StudentAttendanceFragment.montwiseStuAtt) {
                AbsentLeaveModel m6 = new AbsentLeaveModel();
                m6.setMonth(Integer.parseInt(_month.getMonth()));
                m6.setYear(Integer.parseInt(_month.getYear()));
                m6.setLeave_type(_month.getLeaveType());//Absent
                m6.setLeave(_month.getHolidayNote());
                m6.setLdate(_month.getDay() + "/" + _month.getMonth() + "/" + _month.getYear());
                list.add(m6);
            }
        }


    }
    List<LeaveModel> leaves=new ArrayList<>();
    private void setAbsentPresentCount(int month,int year){
        leaves.clear();
        int present_cnt=0,absent_cnt=0,holidays_cnt=0;
        for(AbsentLeaveModel m:list) {
            if(m.getYear()==year&&m.getMonth()==month){
                if(m.getLeave_type()==1){
                    present_cnt = present_cnt+1;
                }else if(m.getLeave_type()==2){
                    absent_cnt = absent_cnt+1;
                }else if(m.getLeave_type()==3){
                    holidays_cnt = holidays_cnt+1;
                    LeaveModel leave=new LeaveModel();
                    leave.setLeave(m.getLeave());
                    leave.setLdate(m.getLdate());
                    leave.setDayOfWeek(getDayName(m.getLdate()));
                    leaves.add(leave);
                }
            }
        }
        //int no_days=countWeekendDays(year,month);
        tvAbsentCnt.setText(absent_cnt<10?"0"+absent_cnt:absent_cnt+"");
        tvAbsentCnt.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.zoomin));
        tvPresentCnt.setText(present_cnt<10?"0"+present_cnt:present_cnt+"");
        tvPresentCnt.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.zoomin));
        tvHolidayCnt.setText(holidays_cnt<10?"0"+holidays_cnt:holidays_cnt+"");
        tvHolidayCnt.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.zoomin));
        //Toast.makeText(getActivity(), "Leaves : "+leaves.size(), Toast.LENGTH_SHORT).show();
        //int total_days=no_days-holidays_cnt;
        tvTotalWorkingDays.setText((absent_cnt+present_cnt)+"");
        LeavesAdapter adapter = new LeavesAdapter(getActivity(), leaves);
        rv.setAdapter(adapter);
    }
    public String getDayName(String date) {
        String strDayofWeek="";
        try {

            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            strDayofWeek = week_name.format(date1);
        }catch (Exception e){}
        return strDayofWeek;
    }
    public int countWeekendDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        // Note that month is 0-based in calendar, bizarrely.
        calendar.set(year, month - 1, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int count = 0;
        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(year, month - 1, day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY ) {
                count++;
                // Or do whatever you need to with the result.
            }
        }
        return daysInMonth-count;
    }
}