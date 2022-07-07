package com.pharma.ui.parent;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.pharma.R;
import com.pharma.fragment.patient.AttendanceFragment;
import com.pharma.fragment.patient.YearAttendanceFragment;
import com.pharma.ui.BaseActivity;
import com.pharma.view_model.parent.SharedViewModel;
import com.seglib.SegmentedButtonGroup;
import java.util.Calendar;

public class AttendanceActivity extends BaseActivity {
     SegmentedButtonGroup segButton;
    LinearLayout llBack;
    SharedViewModel sharedViewModelInstance=null;
    public static int mYear=2022,mMonth=11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_attendance);
        mYear= Calendar.getInstance().get(Calendar.YEAR);
        mMonth= Calendar.getInstance().get(Calendar.MONTH);
        Toast.makeText(getApplicationContext(), "->"+mMonth, Toast.LENGTH_SHORT).show();
        llBack = (LinearLayout)findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        segButton = (SegmentedButtonGroup)findViewById(R.id.segButton);
        segButton.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(final int position) {
                //Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
                addFragment(position);
            }
        });

        addFragment(0);
    }

    private void addFragment(int pos){
        Fragment fragment=null;
        FragmentManager fragmentManager = getSupportFragmentManager();
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

    public void setSegButtonPosition1() {
        segButton.setPosition(0,true);
    }
}