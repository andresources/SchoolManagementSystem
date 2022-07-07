package com.pharma;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestCalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cal);
        List<EventDay> events = new ArrayList<>();
        for(int i=1;i<22;i++) {
            Calendar date1 = Calendar.getInstance();
            date1.set(Calendar.DATE, i);
            date1.set(Calendar.MONTH, 5);
            date1.set(Calendar.YEAR, 2022);
            if(i==3||i==7||i==11) {
                events.add(new EventDay(date1, R.drawable.ab_logo, Color.parseColor("#EF5350")));
            }else if(i==10){
                events.add(new EventDay(date1, R.drawable.hl_logo, Color.parseColor("#42A5F5")));
            }else{
                events.add(new EventDay(date1, R.drawable.pr_logo, Color.parseColor("#66BB6A")));
            }
        }
        Calendar date2 = Calendar.getInstance();
        date2.set(Calendar.DATE,22);
        date2.set(Calendar.MONTH,5);
        date2.set(Calendar.YEAR,2022);
        events.add(new EventDay(date2, R.drawable.ab_logo, Color.parseColor("#EF5350")));

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setEvents(events);
        calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                Calendar date1 =calendarView.getCurrentPageDate();
                int m = date1.get(Calendar.MONTH);
                int y = date1.get(Calendar.YEAR);
                Toast.makeText(getApplicationContext(), ""+(m+1)+"/"+y, Toast.LENGTH_SHORT).show();
            }
        });
    }
}