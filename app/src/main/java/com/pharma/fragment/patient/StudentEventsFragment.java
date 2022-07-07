package com.pharma.fragment.patient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.MyApp;
import com.pharma.R;
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.EventsModel;
import com.pharma.response.LoginResponse;
import com.pharma.ui.LoginActivity;
import com.pharma.ui.parent.EventsActivity;
import com.pharma.ui.parent.StudentDashboardActivity;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.LoginViewModel;
import com.pharma.view_model.parent.EventsViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentEventsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentEventsFragment() {
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
    public static StudentEventsFragment newInstance(String param1, String param2) {
        StudentEventsFragment fragment = new StudentEventsFragment();
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
    ProgressBar progress_circular;
    RecyclerView rv;
    TextView tvNoData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_events, container, false);
        progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        tvNoData=(TextView)view.findViewById(R.id.tvNoData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        //loadData();
        getEvents();
        return view;
    }
    EventsViewModel eventsViewModel;
    Dialog pd;
    private void getEvents(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        eventsViewModel = ViewModelProviders.of(getActivity()).get(EventsViewModel.class);
        eventsViewModel.getEventsResponseLiveData().observe(getActivity(), new Observer<EventsListModel>() {
            @Override
            public void onChanged(EventsListModel eventsResponse) {
                if(eventsResponse!=null) {
                    pd.dismiss();
                    if(eventsResponse.getCode()==200) {
                        if(eventsResponse.getStuOlClsData()!=null){
                           if(eventsResponse.getStuOlClsData().size()>0){
                               enableDisaleViews(true);
                               EventsAdapter adapter = new EventsAdapter(getActivity(), eventsResponse.getStuOlClsData());
                               rv.setAdapter(adapter);
                           }else{
                               //No Records
                               enableDisaleViews(false);
                           }
                        }else{
                            //No Records
                            enableDisaleViews(false);
                        }
                    }else{
                        Toast.makeText(getActivity(), "Oop! something went worng.Try again. Error code : ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    pd.dismiss();
                }
            }
        });
    }
    private void enableDisaleViews(boolean data){
        if(data){
            rv.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
        }else{
            rv.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }

    }
}