package com.pharma.utils;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.pharma.R;
import com.pharma.fragment.patient.StudentELearningFragment;
import com.pharma.fragment.patient.StudentOnlineClassesFragment;
import com.pharma.fragment.patient.StudentAttendanceFragment;
import com.pharma.fragment.patient.StudentEventsFragment;
import com.pharma.fragment.patient.StudentExamsFragment;
import com.pharma.fragment.patient.StudentGalleryFragment;
import com.pharma.fragment.patient.StudentHomeFragment;
import com.pharma.fragment.patient.StudentHomeWorkFragment;
import com.pharma.fragment.patient.StudentNotificationFragment;
import com.pharma.fragment.patient.StudentPaymentsFragment;
import com.pharma.fragment.patient.StudentPaymentsOldFragment;
import com.pharma.fragment.patient.StudentProfileFragment;
import com.pharma.fragment.patient.StudentTimeTableFragment;

public class ScreenNav {
    public static void addFragment(FragmentName screen, Context cnt){
        Fragment fragment=null;
        AppCompatActivity activity = (AppCompatActivity)cnt;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = fragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        );
        switch (screen){
            case HOME:
                fragment = new StudentHomeFragment();
                break;
            case PROFILE:
                fragment = new StudentProfileFragment();
                break;
            case PAYMENTS:
                fragment = new StudentPaymentsFragment();
                break;
            case NOTIICATIONS:
                fragment = new StudentNotificationFragment();
                break;
            case EVENTS:
                fragment = new StudentEventsFragment();
                break;
            case EXAMS:
                fragment = new StudentExamsFragment();
                break;
            case GALLERY:
                fragment = new StudentGalleryFragment();
                break;
            case TIMETABLE:
                fragment = new StudentTimeTableFragment();
                break;
            case HOMEWORK:
                fragment = new StudentHomeWorkFragment();
                break;
            case ATTENDANCE:
                fragment = new StudentAttendanceFragment();
                break;
            case ONLINECLASSES:
                fragment = new StudentOnlineClassesFragment();
                break;
            case ELEARNING:
                fragment = new StudentELearningFragment();
                break;
        }
        transaction.replace(R.id.fragment_parent_container, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
