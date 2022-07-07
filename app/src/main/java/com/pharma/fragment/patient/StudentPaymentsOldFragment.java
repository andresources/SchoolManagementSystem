package com.pharma.fragment.patient;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.pharma.R;
import com.pharma.adapter.parent.ReceiptsAdapter;
import com.pharma.atom.ATOMPaymentService;
import com.pharma.fragment.BaseFragment;
import com.pharma.model.parent.PaymentsListModel;
import com.pharma.ui.parent.PaymentTransactionDetailsActivity;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.PaymentsViewModel;

import java.text.NumberFormat;
import java.util.Locale;

public class StudentPaymentsOldFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentPaymentsOldFragment() {
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
    public static StudentPaymentsOldFragment newInstance(String param1, String param2) {
        StudentPaymentsOldFragment fragment = new StudentPaymentsOldFragment();
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
    TextView tvTotalAmount,tvConcession,tvPaidAmount,tvPendingAmount,tvPayNow,tvTransHistory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_payments_old, container, false);
         tvPayNow= (TextView)view.findViewById(R.id.tvPayNow);
        tvTransHistory= (TextView)view.findViewById(R.id.tvTransHistory);
        rv= (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        tvTotalAmount= (TextView)view.findViewById(R.id.tvTotalAmount);
        tvConcession= (TextView)view.findViewById(R.id.tvConcession);
        tvPaidAmount= (TextView)view.findViewById(R.id.tvPaidAmount);
        tvPendingAmount= (TextView)view.findViewById(R.id.tvPendingAmount);

        tvPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAmount();
            }
        });
        tvTransHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PaymentTransactionDetailsActivity.class);
                startActivity(intent);
            }
        });
        getPayments();
        AppCompatSpinner sp = (AppCompatSpinner) view.findViewById(R.id.sp);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.acc_ary, R.layout.spinner_item);
        sp.setAdapter(adapter);
        return view;
    }

    PaymentsViewModel paymentsViewModel;
    Dialog pd;
    RecyclerView rv;
    private void getPayments(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        paymentsViewModel = ViewModelProviders.of(getActivity()).get(PaymentsViewModel.class);
        paymentsViewModel.getPaymentsResponseLiveData().observe(getActivity(), new Observer<PaymentsListModel>() {
            @Override
            public void onChanged(PaymentsListModel paymentsResponse) {
                if(paymentsResponse!=null) {
                    pd.dismiss();
                    if(paymentsResponse.getCode()==200) {
                        if(paymentsResponse.getFeeData()!=null){

                            tvTotalAmount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(paymentsResponse.getFeeData().getFee()));
                            tvConcession.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(paymentsResponse.getFeeData().getConcs()));
                            tvPaidAmount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(paymentsResponse.getFeeData().getPaid()));
                            tvPendingAmount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(paymentsResponse.getFeeData().getBalance()));
                            if(paymentsResponse.getFeeData().getBalance()==0){
                               /* tvPayNow.setBackgroundColor(getResources().getColor(R.color.gray));
                                tvPayNow.setClickable(false);*/
                                tvPayNow.setBackgroundColor(getResources().getColor(R.color.bg_color));
                                tvPayNow.setClickable(true);
                            }else{
                                tvPayNow.setBackgroundColor(getResources().getColor(R.color.bg_color));
                                tvPayNow.setClickable(true);
                            }
                        }
                        if(paymentsResponse.getRecieptWiseData()!=null){
                            if(paymentsResponse.getRecieptWiseData().size()>0){
                                ReceiptsAdapter adapter=new ReceiptsAdapter(getActivity(),paymentsResponse.getRecieptWiseData());
                                rv.setAdapter(adapter);
                                //Toast.makeText(getActivity(), ""+paymentsResponse.getRecieptWiseData().size(), Toast.LENGTH_SHORT).show();
                                /*EventsAdapter adapter = new EventsAdapter(getActivity(), eventsResponse.getStuOlClsData());
                                rv.setAdapter(adapter);*/
                            }else{
                                //No Records
                            }
                        }else{
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
    float amt;
    TextInputEditText etAmount;
    private void getAmount(){
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_payment_edittext, null);
        AlertDialog alertDialog = new AlertDialog.Builder(requireContext()).create();
        alertDialog.setTitle("Payments");
        alertDialog.setCancelable(false);
        alertDialog.setView(view);
        alertDialog.setMessage("Please enter your payable amount.");
        etAmount= (TextInputEditText) view.findViewById(R.id.etAmount);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(etAmount.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter amount,", Toast.LENGTH_SHORT).show();
                }
                amt=Float.parseFloat(etAmount.getText().toString());
                alertDialog.dismiss();
                ATOMPaymentService.payNow(getActivity(),amt+"","M233","09/06/2022 19:31:00");
            }
        });


        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }


}