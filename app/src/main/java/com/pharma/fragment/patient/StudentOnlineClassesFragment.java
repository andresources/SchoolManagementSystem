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
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.adapter.parent.OnlineClassesAdapter;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.OnlineClassesListModel;
import com.pharma.model.parent.OnlineClassesModel;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.EventsViewModel;
import com.pharma.view_model.parent.OnlineClassesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentOnlineClassesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentOnlineClassesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentOnlineClassesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnlineClassesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentOnlineClassesFragment newInstance(String param1, String param2) {
        StudentOnlineClassesFragment fragment = new StudentOnlineClassesFragment();
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
        View view=inflater.inflate(R.layout.fragment_online_classes, container, false);
        progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        getOnlineClasses();
        return view;
    }
    List<OnlineClassesModel> lists=new ArrayList<>();
    Dialog pd;
    private void getOnlineClasses(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        OnlineClassesViewModel onlineClassesViewModel = ViewModelProviders.of(getActivity()).get(OnlineClassesViewModel.class);
        onlineClassesViewModel.getOnlineClassesResponseLiveData().observe(getActivity(), new Observer<OnlineClassesListModel>() {
            @Override
            public void onChanged(OnlineClassesListModel onlineClassesResponse) {
                if(onlineClassesResponse!=null) {
                    pd.dismiss();
                    if(onlineClassesResponse.getCode()==200) {
                        if(onlineClassesResponse.getStuOlClsData()!=null){
                            if(onlineClassesResponse.getStuOlClsData().size()>0){
                                enableDisaleViews(true);
                                OnlineClassesAdapter adapter = new OnlineClassesAdapter(getActivity(), onlineClassesResponse.getStuOlClsData());
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