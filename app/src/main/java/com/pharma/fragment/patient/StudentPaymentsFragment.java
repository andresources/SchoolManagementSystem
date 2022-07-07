package com.pharma.fragment.patient;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.pharma.R;
import com.pharma.atom.ATOMPaymentService;
import com.pharma.fragment.BaseFragment;
import com.pharma.model.parent.FeesDataModel;
import com.pharma.model.parent.MainFeeCatgModel;
import com.pharma.model.parent.PaymentHistoryModel;
import com.pharma.ui.parent.PaymentTransactionDetailsActivity;
import com.pharma.ui.parent.StudentDashboardActivity;
import com.pharma.utils.MyProgressDialog;
import com.pharma.view_model.parent.StudentPaymentsViewModel;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class StudentPaymentsFragment extends BaseFragment implements PaymentResultListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentPaymentsFragment() {
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
    LinearLayout llDupParent,llDup,llMain;
    TextView tvDupTitle,tvMainTitle,tvPaymentHistory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_payments, container, false);
        llDupParent=(LinearLayout)view.findViewById(R.id.llDupParent);
        llDup=(LinearLayout)view.findViewById(R.id.llDup);
        llMain=(LinearLayout)view.findViewById(R.id.llMain);
        tvDupTitle=(TextView) view.findViewById(R.id.tvDupTitle);
        tvPaymentHistory=(TextView) view.findViewById(R.id.tvPaymentHis);
        tvPaymentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),PaymentTransactionDetailsActivity.class));
            }
        });
        tvDupTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TransactionDialog dia=new TransactionDialog(getActivity());
                dia.show();
                dia.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dia.dismiss();
                    }
                });
                if(dumpFeesData!=null){
                    if(dumpFeesData.size()>0){
                        setDupDialog(dumpFeesData,dia.findViewById(R.id.tl),false,null);
                    }
                }*/
            }
        });
        tvMainTitle=(TextView) view.findViewById(R.id.tvMainTitle);
        tvMainTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TransactionDialog dia=new TransactionDialog(getActivity());
                dia.show();
                dia.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dia.dismiss();
                    }
                });
                if(mainFeesData!=null){
                    if(mainFeesData.size()>0){
                        setDupDialog(mainFeesData,dia.findViewById(R.id.tl),true,list);
                    }
                }*/
            }
        });
        getPayments();
        return view;
    }

    StudentPaymentsViewModel paymentsViewModel;
    Dialog pd;
    private void setDupDialog(List<FeesDataModel> dumpFeesData,TableLayout tl,boolean install_footer,List<MainFeeCatgModel> cat){
            final LayoutInflater  inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            try {
                float total=0;
                for(int i=0;i<dumpFeesData.size();i++){
                    View view = inflater.inflate(R.layout.row_current_payments,null);
                    TextView tvSNo=(TextView)view.findViewById(R.id.tvSNo);
                    TextView tvDesc=(TextView)view.findViewById(R.id.tvDesc);
                    TextView tvFeeAmount=(TextView)view.findViewById(R.id.tvFeeAmount);
                    tvSNo.setText(""+(i+1));
                    tvDesc.setText(dumpFeesData.get(i).getFeetypeName());
                    total=total+Float.parseFloat(dumpFeesData.get(i).getPaid());
                    tvFeeAmount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(Float.parseFloat(dumpFeesData.get(i).getPaid())));
                    tl.addView(view);
                }
                View view = inflater.inflate(R.layout.row_payments_total,null);
                TextView t=view.findViewById(R.id.tvTotalAmount);
                t.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(total));
                tl.addView(view);
                if(install_footer){
                    for (MainFeeCatgModel m:cat) {
                        View view1 = inflater.inflate(R.layout.row_installments,null);
                        TextView tvDate=view1.findViewById(R.id.tvDate);
                        tvDate.setText(m.getDueDate());
                        TextView tvInstallment=view1.findViewById(R.id.tvInstallment);
                        tvInstallment.setText(m.getInstallment());
                        TextView tvTotalAmount=view1.findViewById(R.id.tvTotalAmount);
                        tvTotalAmount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(Float.parseFloat(m.getDueAmt())));
                        tl.addView(view1);
                    }
                }
                //Toast.makeText(getApplicationContext(), "->"+list.size(), Toast.LENGTH_SHORT).show();
            }catch (Exception e){ Toast.makeText(getActivity(), "->"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

    }
    List<FeesDataModel> dumpFeesData,mainFeesData;
    List<MainFeeCatgModel> list;
    String razor_pay_id="";
    private void getPayments(){
        pd= new MyProgressDialog(getActivity());
        pd.show();
        paymentsViewModel = ViewModelProviders.of(getActivity()).get(StudentPaymentsViewModel.class);
        paymentsViewModel.getPaymentsResponseLiveData().observe(getActivity(), new Observer<PaymentHistoryModel>() {
            @Override
            public void onChanged(PaymentHistoryModel paymentsResponse) {
                pd.dismiss();
                if(paymentsResponse!=null) {
                    if(paymentsResponse.getOnlineTransKeys()!=null){
                       if(paymentsResponse.getOnlineTransKeys().size()>0) {
                           razor_pay_id = paymentsResponse.getOnlineTransKeys().get(0).getKey_id();
                       }
                    }
                    if (paymentsResponse.getDupFeeCatg() != null) {
                        if (paymentsResponse.getDupFeeCatg().size() > 0) {
                            dumpFeesData = paymentsResponse.getDupFeeDetails().getFeeData();
                            createDupDetails(paymentsResponse.getDupFeeCatg());

                        }
                    }
                    if (paymentsResponse.getMainFeeCatg() != null) {
                        mainFeesData = paymentsResponse.getMainFeeDetails().getFeeData();
                        list = paymentsResponse.getMainFeeCatg();
                        createMainDetails(paymentsResponse.getMainFeeCatg());
                    } else {
                        //Toast.makeText(getActivity(),"Else"+paymentsResponse.getSuccess(),Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
    private void createDupDetails(List<MainFeeCatgModel> data){
        llDupParent.setVisibility(View.VISIBLE);
        tvDupTitle.setText(data.get(0).getCatgName());
        final LayoutInflater  inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            for(int i=0;i<data.size();i++){
                final String am;
                View view = inflater.inflate(R.layout.row_student_payment1,null);
                TextView tvAmount=(TextView)view.findViewById(R.id.tvAmount);
                tvAmount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(Float.parseFloat(data.get(i).getDueAmt())));
                TextView tvDate=(TextView)view.findViewById(R.id.tvDate);
                tvDate.setText(data.get(i).getDueDate());
                TextView btnPayNow=(TextView)view.findViewById(R.id.btnPayNow);
                TextView tvDetails=(TextView)view.findViewById(R.id.tvDetails);
                if(Float.parseFloat(data.get(i).getDueAmt())<1.0f){
                    btnPayNow.setEnabled(false);
                    tvDetails.setVisibility(View.GONE);
                    btnPayNow.setBackgroundResource(R.drawable.bg_disable);
                }else{
                    btnPayNow.setEnabled(true);
                    tvDetails.setVisibility(View.VISIBLE);
                    btnPayNow.setBackgroundResource(R.drawable.bg_absent);
                }
                am = data.get(i).getDueAmt();

                tvDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransactionDialog dia=new TransactionDialog(getActivity());
                        dia.show();
                        dia.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dia.dismiss();
                            }
                        });
                        if(dumpFeesData!=null){
                            if(dumpFeesData.size()>0){
                                setDupDialog(dumpFeesData,dia.findViewById(R.id.tl),false,null);
                            }
                        }
                    }
                });

                btnPayNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getAmount(am);
                    }
                });
                llDup.addView(view);


            }
            //Toast.makeText(getApplicationContext(), "->"+list.size(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){ Toast.makeText(getActivity(), "->"+e.getMessage(), Toast.LENGTH_SHORT).show();}
    }
    private void createMainDetails(List<MainFeeCatgModel> data){

        tvMainTitle.setText(data.get(0).getCatgName());
        final LayoutInflater  inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {

            for(int i=0;i<data.size();i++){
                final String am;
                View view = inflater.inflate(R.layout.row_student_payments,null);
                TextView tvHeading=(TextView)view.findViewById(R.id.tvHeading);
                tvHeading.setText(data.get(i).getInstallment());
                TextView tvAmount=(TextView)view.findViewById(R.id.tvAmount);
                tvAmount.setText(NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(Float.parseFloat(data.get(i).getDueAmt())));
                TextView tvDate=(TextView)view.findViewById(R.id.tvDate);
                TextView tvDetails=(TextView)view.findViewById(R.id.tvDetails);
                tvDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransactionDialog dia=new TransactionDialog(getActivity());
                        dia.show();
                        dia.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dia.dismiss();
                            }
                        });
                        if(mainFeesData!=null){
                            if(mainFeesData.size()>0){
                                setDupDialog(mainFeesData,dia.findViewById(R.id.tl),true,list);
                            }
                        }
                    }
                });
                tvDate.setText(data.get(i).getDueDate());
                TextView btnPayNow=(TextView)view.findViewById(R.id.btnPayNow);
                if(Float.parseFloat(data.get(i).getDueAmt())<1.0f){
                    btnPayNow.setEnabled(false);
                    tvDetails.setVisibility(View.GONE);
                    btnPayNow.setBackgroundResource(R.drawable.bg_disable);
                }else{
                    btnPayNow.setEnabled(true);
                    tvDetails.setVisibility(View.VISIBLE);
                    btnPayNow.setBackgroundResource(R.drawable.bg_absent);
                }
                am = data.get(i).getDueAmt();
                btnPayNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getAmount(am);
                    }
                });
                llMain.addView(view);

            }
            //Toast.makeText(getApplicationContext(), "->"+list.size(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){ Toast.makeText(getActivity(), "->"+e.getMessage(), Toast.LENGTH_SHORT).show();}
    }
    float amt;
    TextInputEditText etAmount;
    private void getAmount(String amount){
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_payment_edittext, null);
        AlertDialog alertDialog = new AlertDialog.Builder(requireContext()).create();
        alertDialog.setTitle("Payments");
        alertDialog.setCancelable(false);
        alertDialog.setView(view);
        alertDialog.setMessage("Please enter your payable amount.");
        etAmount= (TextInputEditText) view.findViewById(R.id.etAmount);
        etAmount.setText(amount);
        etAmount.setEnabled(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(etAmount.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter amount,", Toast.LENGTH_SHORT).show();
                }
                amt=Float.parseFloat(etAmount.getText().toString());
                alertDialog.dismiss();
                if(razor_pay_id.length()>10) {
                    ((StudentDashboardActivity) getActivity()).razorPayment(etAmount.getText().toString(), razor_pay_id);
                }else{
                    Toast.makeText(getActivity(), "Razor PayID is not exist.Please contact admin.", Toast.LENGTH_SHORT).show();
                }
                //CallPaytmIntegration("1");
                //ATOMPaymentService.payNow(getActivity(),amt+"","M233","09/06/2022 19:31:00");
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

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getActivity(),"Success ->"+s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getActivity(),"Error ->"+s,Toast.LENGTH_LONG).show();
    }

    public class TransactionDialog extends Dialog  {
        public TransactionDialog(@NonNull Context context) {
            super(context);
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            setContentView(R.layout.dialog_transactions_details);
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

    public void CallPaytmIntegration(String txnAmount) {
        //Toast.makeText(getApplicationContext(), checkSumHash, Toast.LENGTH_SHORT).show();
        //int amount = Math.round(Float.parseFloat(txnAmount) * 100);
        int amount = Math.round(Float.parseFloat(txnAmount) * 100);
        // initialize Razorpay account.
        Checkout checkout = new Checkout();

        // set your id as below
        //checkout.setKeyID("rzp_test_KiHV4GDYU4VTaZ");
        checkout.setKeyID("rzp_live_M42iCtxbvVbp61");

        // set image
        checkout.setImage(R.drawable.ic_no_profile);
        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "Sambav");
            object.put("description", "Test");
            //object.put("theme.color", "");
            object.put("currency", "INR");//USD,INR
            object.put("amount", amount);
            object.put("image", "https://www.sambav.com/app/img/sambav.svg");

            checkout.open(getActivity(), object);
        } catch (JSONException e) {
            Log.i("razor_error",e.getMessage());
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}