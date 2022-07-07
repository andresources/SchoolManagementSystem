package com.pharma.fragment.patient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.adapter.parent.HomeWorkAdapter;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.HomeWorkListModel;
import com.pharma.model.parent.HomeWorkModel;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.EventsViewModel;
import com.pharma.view_model.parent.HomeWorkViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentHomeWorkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentHomeWorkFragment() {
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
    public static StudentHomeWorkFragment newInstance(String param1, String param2) {
        StudentHomeWorkFragment fragment = new StudentHomeWorkFragment();
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
    List<HomeWorkModel> lists=new ArrayList<>();
    RecyclerView rv;
    TextView tvNoData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_home_work, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        getHomeWorks();
        return view;
    }
    HomeWorkViewModel eventsViewModel;

    Dialog pd;
    private void getHomeWorks(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        eventsViewModel = ViewModelProviders.of(getActivity()).get(HomeWorkViewModel.class);
        eventsViewModel.getHomeWorkResponseLiveData().observe(getActivity(), new Observer<HomeWorkListModel>() {
            @Override
            public void onChanged(HomeWorkListModel homeWorks) {

                if(homeWorks!=null) {
                    pd.dismiss();
                    if(homeWorks.getCode()==200) {
                        if(homeWorks.getHomeWorkList()!=null){
                            if(homeWorks.getHomeWorkList().size()>0){
                                tvNoData.setVisibility(View.GONE);
                                rv.setVisibility(View.VISIBLE);
                                HomeWorkAdapter adapter = new HomeWorkAdapter(getActivity(), homeWorks.getHomeWorkList());
                                rv.setAdapter(adapter);
                            }else{
                                //No Records
                                tvNoData.setVisibility(View.VISIBLE);
                                rv.setVisibility(View.GONE);
                            }
                        }else{
                            //No Records
                            tvNoData.setVisibility(View.VISIBLE);
                            rv.setVisibility(View.GONE);
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
    private void prepareData(){
        HomeWorkModel h1=new HomeWorkModel();
        h1.setAssign_date("01 Nov,2022");
        h1.setDescription("This is description");
        h1.setSubject("Mathematics");
        h1.setTopic("This is Subject");
        h1.setLast_submission_date("05 Nov,2022");

        HomeWorkModel h2=new HomeWorkModel();
        h2.setAssign_date("01 Nov,2022");
        h2.setDescription("This is description");
        h2.setSubject("Mathematics");
        h2.setTopic("This is Subject");
        h2.setLast_submission_date("05 Nov,2022");

        HomeWorkModel h3=new HomeWorkModel();
        h3.setAssign_date("01 Nov,2022");
        h3.setDescription("This is description");
        h3.setSubject("Mathematics");
        h3.setTopic("This is Subject");
        h3.setLast_submission_date("05 Nov,2022");


    }
}