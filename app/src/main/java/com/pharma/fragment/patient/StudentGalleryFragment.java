package com.pharma.fragment.patient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.pharma.R;
import com.pharma.adapter.parent.EventsAdapter;
import com.pharma.adapter.parent.GalleryAdapter;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.GalleryListModel;
import com.pharma.model.parent.GalleryModel;
import com.pharma.ui.parent.GalleryActivity;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.EventsViewModel;
import com.pharma.view_model.parent.GalleryViewModel;

import java.util.ArrayList;
import java.util.List;

public class StudentGalleryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentGalleryFragment() {
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
    public static StudentGalleryFragment newInstance(String param1, String param2) {
        StudentGalleryFragment fragment = new StudentGalleryFragment();
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
        View view=inflater.inflate(R.layout.fragment_student_gallery, container, false);
        progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        //rv.setHasFixedSize(true);
        //loadData();
        getGallery();
        return view;
    }
    GalleryViewModel galleryViewModel;
    Dialog pd;
    private void getGallery(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        galleryViewModel = ViewModelProviders.of(getActivity()).get(GalleryViewModel.class);
        galleryViewModel.getGalleryResponseLiveData().observe(getActivity(), new Observer<GalleryListModel>() {
            @Override
            public void onChanged(GalleryListModel eventsResponse) {
                if(eventsResponse!=null) {
                    pd.dismiss();
                    if(eventsResponse.getCode()==200) {
                        if(eventsResponse.getGallery()!=null){
                            if(eventsResponse.getGallery().size()>0){
                                enableDisaleViews(true);
                                GalleryAdapter adapter = new GalleryAdapter(getActivity(), eventsResponse.getGallery(),StudentGalleryFragment.this);
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
    List<GalleryModel> lists=new ArrayList<>();
    private void enableDisaleViews(boolean data){
        if(data){
            rv.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
        }else{
            rv.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }

    }

    public void displayImageAlert(String des,String close){
        DashboardAlertDialog dia=new DashboardAlertDialog(getActivity(),R.layout.dialog_gallery_alert);
        dia.setCancelable(false);
        dia.show();
        //Toast.makeText(getActivity(),""+close,Toast.LENGTH_LONG).show();
        if(close.equals("yes")){
            dia.findViewById(R.id.ivClose).setVisibility(View.VISIBLE);
        }else{
            dia.findViewById(R.id.ivClose).setVisibility(View.GONE);
        }
        dia.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia.dismiss();
            }
        });
        ImageView ivAlert=dia.findViewById(R.id.ivAlert);
        Glide.with(getActivity())
                .load(des)
                .into(ivAlert);

    }
    private class DashboardAlertDialog extends Dialog  {
        public DashboardAlertDialog(@NonNull Context context, int rid) {
            super(context);
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            setContentView(rid);
        }
        @Override
        public void show() {
            super.show();
        }

        @Override
        public void hide() {
            super.hide();
        }

        @Override
        public void dismiss() {
            super.dismiss();
        }

    }
}