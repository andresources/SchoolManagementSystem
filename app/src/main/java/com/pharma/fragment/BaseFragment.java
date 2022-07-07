package com.pharma.fragment;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.pharma.ui.parent.PaymentReceiptActivity;

public class BaseFragment extends Fragment {
     protected void startNewActivity(Class<?> clss){
         getActivity().startActivity(new Intent(getActivity(),clss));
     }
    //startNewActivity(PaymentReceiptActivity .class);
}
