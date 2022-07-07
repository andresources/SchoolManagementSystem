package com.pharma.fragment.patient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pharma.R;
import com.pharma.adapter.parent.ExamsAdapter;
import com.pharma.adapter.parent.ExamsOldAdapter;
import com.pharma.model.parent.ExamListModel;
import com.pharma.model.parent.ExamSingleDataModel;
import com.pharma.model.parent.ExamsModel;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.ExamsViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentExamsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentExamsFragment() {
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
    public static StudentExamsFragment newInstance(String param1, String param2) {
        StudentExamsFragment fragment = new StudentExamsFragment();
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
    AppCompatSpinner sp;
    TextView tvNoData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_exams, container, false);
        progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        tvNoData= (TextView) view.findViewById(R.id.tvNoData);
        sp = (AppCompatSpinner) view.findViewById(R.id.sp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        getExams();
        return view;
    }
    ExamsViewModel eventsViewModel;
    Dialog pd;
    private void getExams(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        eventsViewModel = ViewModelProviders.of(getActivity()).get(ExamsViewModel.class);
        eventsViewModel.getEventsResponseLiveData().observe(getActivity(), new Observer<ExamListModel>() {
            @Override
            public void onChanged(ExamListModel eventsResponse) {
                if(eventsResponse!=null) {
                    pd.dismiss();
                    if(eventsResponse.getCode()==200) {
                        if(eventsResponse.getExamFinalDetails()!=null){
                            if(eventsResponse.getExamFinalDetails().size()>0){
                                String[] str=new String[eventsResponse.getExamFinalDetails().size()+1];
                                str[0] = "All";
                                int i=1;
                                for(ExamSingleDataModel e:eventsResponse.getExamFinalDetails()){
                                   str[i]= e.getExamDetails().getExamName();
                                   i++;
                                }
                                ExamsAdapter adapter1 = new ExamsAdapter(getActivity(), eventsResponse.getExamFinalDetails());
                                rv.setAdapter(adapter1);
                                enableDisaleViews(true);
                                //ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(getActivity(),str, R.layout.spinner_item);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, str);
                                sp.setAdapter(adapter);
                                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        adapter1.filtered(str[i]);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                                //Toast.makeText(getActivity(), ""+eventsResponse.getExamFinalDetails().size(), Toast.LENGTH_SHORT).show();

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
    List<ExamsModel> list=new ArrayList<>();
    private void prepareData(){
        ExamsModel m1=new ExamsModel();
        m1.setTerm("First Term");
        m1.setTermDate("2022-01-01");
        m1.setTermclass("First Term for Class-1");
        m1.setGrade("Grade - A");
        m1.setPercentage(90);
        list.add(m1);

        ExamsModel m2=new ExamsModel();
        m2.setTerm("Mid Term");
        m2.setTermDate("2022-03-01");
        m2.setTermclass("Mid Term for Class-1");
        m2.setGrade("Grade - B");
        m2.setPercentage(74);
        list.add(m2);

        ExamsModel m3=new ExamsModel();
        m3.setTerm("Final Term");
        m3.setTermDate("2022-03-01");
        m3.setTermclass("Mid Term for Class-1");
        m3.setGrade("Grade - C");
        m3.setPercentage(45);
        list.add(m3);

        ExamsModel m4=new ExamsModel();
        m4.setTerm("English Exam");
        m4.setTermDate("2022-01-01");
        m4.setTermclass("First Term for Class-1");
        m4.setGrade("");
        m4.setPercentage(0);
        list.add(m4);

        ExamsOldAdapter adapter=new ExamsOldAdapter(getActivity(),list);
        rv.setAdapter(adapter);
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