package com.pharma.fragment.patient;

import android.animation.Animator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
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
import com.pharma.adapter.parent.NotificationAdapter;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.NotificationListModel;
import com.pharma.model.parent.NotificationModel;
import com.pharma.ui.parent.NotificationsActivity;
import com.pharma.utils.CustomProgressDialog;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.EventsViewModel;
import com.pharma.view_model.parent.NotificationViewModel;
import java.util.ArrayList;
import java.util.List;

public class StudentNotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentNotificationFragment() {
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
    public static StudentNotificationFragment newInstance(String param1, String param2) {
        StudentNotificationFragment fragment = new StudentNotificationFragment();
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
        View view=inflater.inflate(R.layout.fragment_student_notifications, container, false);
        progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        getNotifications();
        return view;
    }
    Dialog pd;
    NotificationViewModel notificationViewModel;
    private void getNotifications(){
        pd= new MyProgressDialog(getActivity());
        pd.show();

        notificationViewModel = ViewModelProviders.of(getActivity()).get(NotificationViewModel.class);
        notificationViewModel.getNotificationResponseLiveData().observe(getActivity(), new Observer<NotificationListModel>() {
            @Override
            public void onChanged(NotificationListModel notificationsResponse) {

                if(notificationsResponse!=null) {
                    pd.dismiss();
                    if(notificationsResponse.getCode()==200) {
                        if(notificationsResponse.getStuNotices()!=null){
                            if(notificationsResponse.getStuNotices().size()>0){
                                tvNoData.setVisibility(View.GONE);
                                rv.setVisibility(View.VISIBLE);
                                NotificationAdapter adapter = new NotificationAdapter(getActivity(), notificationsResponse.getStuNotices());
                                rv.setAdapter(adapter);
                            }else{
                                tvNoData.setVisibility(View.VISIBLE);
                                rv.setVisibility(View.GONE);
                                //No Records
                            }
                        }else{
                            tvNoData.setVisibility(View.VISIBLE);
                            rv.setVisibility(View.GONE);
                            //No Records
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
    List<NotificationModel> lists=new ArrayList<>();
    private void loadData(){
        NotificationModel n1=new NotificationModel();
        n1.setTitle("Fee Alert");
        n1.setNdate("12 Jan,2022 9:00AM");
        n1.setDescription("This is description..");
        NotificationModel n2=new NotificationModel();
        n2.setTitle("Exams Alert");
        n2.setNdate("13 Feb,2022 9:00AM");
        n2.setDescription("This is description..");
        NotificationModel n3=new NotificationModel();
        n3.setTitle("Exams Alert");
        n3.setNdate("13 Feb,2022 9:00AM");
        n3.setDescription("This is description..");
        lists.add(n1);
        lists.add(n2);
        lists.add(n3);
        NotificationAdapter adapter = new NotificationAdapter(getActivity(), lists);
        rv.setAdapter(adapter);
    }
}