package com.pharma.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.callib.CompactCalendarView;
import com.callib.domain.Event;
import com.pharma.R;
import com.pharma.model.parent.AbsentLeaveModel;
import com.seglib.SegmentedButtonGroup;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity1 extends AppCompatActivity {
    Button btnPrevious,btnNext;
    CompactCalendarView compactcalendar_view;
    TextView tvTitle,tvAbsentCnt,tvHolidaysCnt;
    List<AbsentLeaveModel> list=new ArrayList<>();
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat _month = new SimpleDateFormat("MM", Locale.getDefault());
    private SimpleDateFormat _year = new SimpleDateFormat("yyyy", Locale.getDefault());
    boolean bg_pre_next=true;
    SegmentedButtonGroup segButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        segButton = (SegmentedButtonGroup)findViewById(R.id.segButton);
        segButton.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(final int position) {
                //Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        });
        //segButton.setPosition(1,true);
        tvTitle= (TextView)findViewById(R.id.tvTitle);
        tvAbsentCnt= (TextView)findViewById(R.id.tvAbsentCnt);
        tvHolidaysCnt= (TextView)findViewById(R.id.tvHolidaysCnt);
        compactcalendar_view = (CompactCalendarView)findViewById(R.id.compactcalendar_view);
        tvTitle.setText(dateFormatForMonth.format(compactcalendar_view.getFirstDayOfCurrentMonth()));
        //tvTitle.setText(_month.format(compactcalendar_view.getFirstDayOfCurrentMonth())+"-"+_year.format(compactcalendar_view.getFirstDayOfCurrentMonth()));
        btnPrevious = (Button)findViewById(R.id.btnPrevious);
        btnNext = (Button)findViewById(R.id.btnNext);

        compactcalendar_view.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                tvTitle.setText(dateFormatForMonth.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                //Toast.makeText(getApplicationContext(), _month.format(compactcalendar_view.getFirstDayOfCurrentMonth())+"-"+_year.format(compactcalendar_view.getFirstDayOfCurrentMonth()), Toast.LENGTH_SHORT).show();
                setAbsentPresentCount(Integer.parseInt(_month.format(compactcalendar_view.getFirstDayOfCurrentMonth())),Integer.parseInt(_year.format(compactcalendar_view.getFirstDayOfCurrentMonth())));
                tvTitle.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                if(bg_pre_next) {
                    tvTitle.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
                }else{
                    tvTitle.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
                }
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        try{
            prepareDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
            for(AbsentLeaveModel m:list) {
                String inputDateInString= m.getLdate();
                Date parsedDate = dateFormat.parse(inputDateInString);
                Timestamp timestamp = new Timestamp(parsedDate.getTime());
                if(m.getLeave_type()==1) {
                    compactcalendar_view.addEvent(new Event(getResources().getColor(R.color.red_100), timestamp.getTime()));
                }else {
                    compactcalendar_view.addEvent(new Event(getResources().getColor(R.color.green_100), timestamp.getTime()));
                }
            }
            setAbsentPresentCount(Integer.parseInt(_month.format(compactcalendar_view.getFirstDayOfCurrentMonth())),Integer.parseInt(_year.format(compactcalendar_view.getFirstDayOfCurrentMonth())));
        }catch (Exception e){}
    }
    private void prepareDate(){
        list.clear();

        AbsentLeaveModel m3=new AbsentLeaveModel();
        m3.setMonth(5);
        m3.setYear(2022);
        m3.setLeave_type(1);//Absent
        m3.setLdate("01/06/2022");
        list.add(m3);


        AbsentLeaveModel m5=new AbsentLeaveModel();
        m5.setMonth(6);
        m5.setYear(2022);
        m5.setLeave_type(2);//Absent
        m5.setLdate("24/06/2022");
        list.add(m5);
        /*AbsentLeaveModel m6=new AbsentLeaveModel();
        m6.setMonth(7);
        m6.setYear(2022);
        m6.setLeave_type(2);//Absent
        m6.setLdate("10/07/2022");
        list.add(m6);*/
    }
    private void setAbsentPresentCount(int month,int year){
        int absent_cnt=0,holidays_cnt=0;
        for(AbsentLeaveModel m:list) {
            if(m.getYear()==year&&m.getMonth()==month){
                if(m.getLeave_type()==1){
                    absent_cnt = absent_cnt+1;
                }else if(m.getLeave_type()==2){
                    holidays_cnt = holidays_cnt+1;
                }
            }
        }
        tvAbsentCnt.setText(absent_cnt<10?"0"+absent_cnt:absent_cnt+"");
        tvAbsentCnt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoomin));
        tvHolidaysCnt.setText(holidays_cnt<10?"0"+holidays_cnt:holidays_cnt+"");
        tvHolidaysCnt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoomin));
    }
}