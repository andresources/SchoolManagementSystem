package com.pharma.fragment.patient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pharma.R;
import com.pharma.adapter.parent.ELearningSectionAdapter;
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.model.parent.ELearningModel;
import com.pharma.model.parent.ELearningSectionModel;
import com.pharma.model.parent.ElearnLinkListModel;
import com.pharma.model.parent.ElearnLinkModel;
import com.pharma.model.parent.ElearningNewModel;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.EventsModel;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.ElearningViewModel;
import com.pharma.view_model.parent.EventsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentELearningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentELearningFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentELearningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ELearningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentELearningFragment newInstance(String param1, String param2) {
        StudentELearningFragment fragment = new StudentELearningFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view=inflater.inflate(R.layout.fragment_e_learning, container, false);
        progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        getElearning();
        return view;
    }
    ElearningViewModel eventsViewModel;
    Dialog pd;
    private void getElearning(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        eventsViewModel = ViewModelProviders.of(getActivity()).get(ElearningViewModel.class);
        eventsViewModel.getElearningResponseLiveData().observe(getActivity(), new Observer<ElearningNewModel>() {
            @Override
            public void onChanged(ElearningNewModel elearningResponse) {
                if(elearningResponse!=null) {
                    pd.dismiss();
                    if(elearningResponse.getCode()==200) {
                        if(elearningResponse.getElearnData()!=null){
                            if(elearningResponse.getElearnData().size()>0){
                                enableDisaleViews(true);
                                lists.clear();
                                //Toast.makeText(getActivity(), ""+eventsResponse.getElearnData().size(), Toast.LENGTH_SHORT).show();
                                for (ElearnLinkListModel m:elearningResponse.getElearnData())
                                {
                                    ELearningSectionModel es=new ELearningSectionModel();
                                    es.setSection_name(m.getClsRef());

                                    if(m.getElearnLink()!=null) {
                                        if (m.getElearnLink().size() > 0) {
                                            List<ELearningModel> list=new ArrayList<>();
                                            list.clear();
                                            for(ElearnLinkModel ee:m.getElearnLink()){
                                                ELearningModel e1=new ELearningModel();
                                                e1.setTitle(ee.getSubject());
                                                e1.setLogo(ee.getSubject().toLowerCase().replace(" ","").replace("-",""));
                                                e1.setLink(ee.getLink());
                                                list.add(e1);
                                            }
                                            es.setList(list);
                                           // Toast.makeText(getActivity(), ""+m.getElearnLink().size(), Toast.LENGTH_SHORT).show();
                                            //ElearnLinkModel
                                            /*List<ELearningModel> list=new ArrayList<>();
                                            list.clear();*/
                                        }
                                    }
                                    lists.add(es);
                                }
                                //Toast.makeText(getActivity(), ""+lists.size(), Toast.LENGTH_SHORT).show();
                                ELearningSectionAdapter adapter = new ELearningSectionAdapter(getActivity(), lists);
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
    List<ELearningSectionModel> lists=new ArrayList<>();
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