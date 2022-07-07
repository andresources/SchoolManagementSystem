package com.pharma.fragment.patient;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pharma.R;
import com.pharma.adapter.parent.LeavesAdapter;
import com.pharma.adapter.parent.PresentAbsentAdapter;
import com.pharma.model.parent.PresentAbsentModel;
import com.pharma.model.parent.YearWiseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YearAttendanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YearAttendanceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YearAttendanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YearAttendanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static YearAttendanceFragment newInstance(String param1, String param2) {
        YearAttendanceFragment fragment = new YearAttendanceFragment();
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
    TextView tvAcademicYear;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_year_attendance, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        tvAcademicYear= (TextView) view.findViewById(R.id.tvAcademicYear);
        prepareData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);

        //Toast.makeText(getActivity(), "Size : "+StudentAttendanceFragment.yearWiseStuAtt.getYearWiseStuAtt().size(), Toast.LENGTH_SHORT).show();
        PresentAbsentAdapter adapter = new PresentAbsentAdapter(getActivity(), lists);
        rv.setAdapter(adapter);
        return view;
    }
    List<PresentAbsentModel> lists=new ArrayList<>();
    private void prepareData(){
        tvAcademicYear.setText("Year of "+StudentAttendanceFragment.yearWiseStuAtt.getAcadyear());
        //Log.i("Test",StudentAttendanceFragment.yearWiseStuAtt.getYearWiseStuAtt().toArray().toString());
        for(YearWiseModel y:StudentAttendanceFragment.yearWiseStuAtt.getYearWiseStuAtt()){
            PresentAbsentModel model=new PresentAbsentModel();
            model.setMonth(getMonthName(y.getMonth()));
            model.setPresent(y.getPresent());
            model.setAbsent(y.getAbsent());
            model.setMont1(y.getMonth());
            model.setTotal((byte)y.getTotal());
            model.setYear1(Integer.parseInt(y.getYear()));
            lists.add(model);
        }
    }
    private String getMonthName(int month){
        String _month="";
        switch (month) {
            case 1:
                _month= "January";
                break;
            case 2:
                _month= "February";
                break;
            case 3:
                _month= "March";
                break;
            case 4:
                _month= "April";
                break;
            case 5:
                _month= "May";
                break;
            case 6:
                _month= "June";
                break;
            case 7:
                _month= "July";
                break;
            case 8:
                _month= "August";
                break;
            case 9:
                _month= "September";
                break;
            case 10:
                _month= "October";
                break;
            case 11:
                _month= "November";
                break;
            case 12:
                _month= "December";
                break;
        }
        return _month;
    }
}