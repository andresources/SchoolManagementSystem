package com.pharma.fragment.patient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.adapter.parent.TimeTableAdapter;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.TimeTableListModel;
import com.pharma.model.parent.TimeTableModel;
import com.pharma.ui.parent.TimeTableActivity;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.EventsViewModel;
import com.pharma.view_model.parent.TimeTableViewModel;
import com.seglib.SegmentedButtonGroup;

import java.util.ArrayList;
import java.util.List;

public class StudentTimeTableFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentTimeTableFragment() {
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
    public static StudentTimeTableFragment newInstance(String param1, String param2) {
        StudentTimeTableFragment fragment = new StudentTimeTableFragment();
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
    private RecyclerView my_recycler_view;
    private TextView tvNoData;
    private ProgressBar progress_circular_movie_article;
    private LinearLayoutManager layoutManager;
    private TimeTableAdapter adapter;
    private List<TimeTableModel> list=new ArrayList<>();
    private SegmentedButtonGroup seg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_time_table, container, false);
        initViews(view);
        getTimeTable();
        return view;
    }
    private void initViews(View view){
        progress_circular_movie_article = (ProgressBar) view.findViewById(R.id.progress_circular_movie_article);
        my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);
        seg= (SegmentedButtonGroup)view.findViewById(R.id.segButton);
        tvNoData= (TextView) view.findViewById(R.id.tvNoData);
        seg.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                filterData(position+1);
            }
        });
    }
    TimeTableViewModel timeTaleViewModel;
    Dialog pd;
    private void getTimeTable(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        timeTaleViewModel = ViewModelProviders.of(getActivity()).get(TimeTableViewModel.class);
        timeTaleViewModel.getTimeTableResponseLiveData().observe(getActivity(), new Observer<TimeTableListModel>() {
            @Override
            public void onChanged(TimeTableListModel timeTableResponse) {
                if(timeTableResponse!=null) {
                    pd.dismiss();
                    if(timeTableResponse.getCode()==200) {
                        if(timeTableResponse.getTimeTable()!=null){
                            if(timeTableResponse.getTimeTable().size()>0){
                                tvNoData.setVisibility(View.GONE);
                                my_recycler_view.setVisibility(View.VISIBLE);
                                list.clear();
                                list.addAll(timeTableResponse.getTimeTable());
                                filterData(1);
                            }else{
                                //No Records
                                tvNoData.setVisibility(View.VISIBLE);
                                my_recycler_view.setVisibility(View.GONE);
                            }
                        }else{
                            //No Records
                            tvNoData.setVisibility(View.VISIBLE);
                            my_recycler_view.setVisibility(View.GONE);
                        }
                    }else{
                        Toast.makeText(getActivity(), "Oop! something went worng.Try again. Error code : ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    tvNoData.setVisibility(View.VISIBLE);
                    my_recycler_view.setVisibility(View.GONE);
                    pd.dismiss();
                }
            }
        });
    }


    private void filterData(int day){
        List<TimeTableModel> fList=new ArrayList<>();
        fList.clear();
        for (TimeTableModel m:list){
            if(day==m.getDayOfWeek()){
                fList.add(m);
            }
        }
        if(fList.size()>0) {
            tvNoData.setVisibility(View.GONE);
            my_recycler_view.setVisibility(View.VISIBLE);
            adapter = new TimeTableAdapter(getActivity(), fList);
            my_recycler_view.setAdapter(adapter);
        }else{
            tvNoData.setVisibility(View.VISIBLE);
            my_recycler_view.setVisibility(View.GONE);
        }
    }
}