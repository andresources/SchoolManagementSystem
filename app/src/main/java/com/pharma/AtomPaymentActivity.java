package com.pharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.atom.atompaynetzsdk.PayActivity;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
public class AtomPaymentActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atom_payment);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(AtomPaymentActivity.this,"test",Toast.LENGTH_SHORT).show();
                Intent newPayIntent=new Intent(AtomPaymentActivity.this, PayActivity.class);
                newPayIntent.putExtra("isLive", false);
                newPayIntent.putExtra("txnscamt", "0");
                newPayIntent.putExtra("merchantId", "9132");
                newPayIntent.putExtra("loginid", "9132");
                newPayIntent.putExtra("password", "Test@123");
                newPayIntent.putExtra("prodid", "NSE");
                newPayIntent.putExtra("txncurr", "INR");
                newPayIntent.putExtra("clientcode", encodeBase64("TkFWSU4="));
                newPayIntent.putExtra("custacc", "100000036600");
                newPayIntent.putExtra("channelid", "INT");
                newPayIntent.putExtra("amt","100.00");
                newPayIntent.putExtra("txnid", "M169");
                newPayIntent.putExtra("date", "29/05/2022 18:31:00");
                newPayIntent.putExtra("signature_request", "KEY123657234");
                newPayIntent.putExtra("signature_response", "KEYRESP123657234");
                newPayIntent.putExtra("enc_request", "A4476C2062FFA58980DC8F79EB6A799E");
                newPayIntent.putExtra("salt_request", "A4476C2062FFA58980DC8F79EB6A799E");
                newPayIntent.putExtra("salt_response", "75AEF0FA1B94B3C10D4F5B268F757F11");
                newPayIntent.putExtra("enc_response", "75AEF0FA1B94B3C10D4F5B268F757F11");
                newPayIntent.putExtra("discriminator","NB");//NB,All
                newPayIntent.putExtra("ru","https://paynetzuat.atomtech.in/paynetz/epi/fts");
                newPayIntent.putExtra("customerName", "LMN PQR");
                newPayIntent.putExtra("customerEmailID", "Test@gmail.com");
                newPayIntent.putExtra("customerMobileNo", "9888870996");
                newPayIntent.putExtra("billingAddress", "Pune");
                newPayIntent.putExtra("optionalUdf9", "OPTIONAL DATA 2");
                newPayIntent.putExtra("ttype", "NBFundTransfer");
                startActivityForResult(newPayIntent,1);
            }
        });
    }

    public String encodeBase64(String encode)
    {
        String decode=null;
        try {
            decode=  Base64.encode(encode.getBytes());
        } catch (Exception e) {
            System.out.println("Unable to decode : "+ e);
        }
        return  decode.trim();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("resultCode = "+resultCode);
        if(data != null){
            ArrayList arr = data.getStringArrayListExtra("response"); //have all response data
            HashMap<String, String> hm = new HashMap<String, String>();

            for(int i=0; i<arr.size(); i++)
            {
                String s[] = (String[]) arr.get(i);
                hm.put(s[0], s[1]);
            }

            System.out.println("*********** Response ***************");
            for (String key : hm.keySet()) {
                String value = hm.get(key);
                System.out.println("Key = " + key + " value = " + value);
            }

            if(resultCode == 1){
                Toast.makeText(AtomPaymentActivity.this,"Transaction Successful! \n" + Arrays.toString((String[]) arr.get(20)) + "\n " + Arrays.toString((String[]) arr.get(14)), Toast.LENGTH_LONG).show();
            }
            else if(resultCode == 2){
                Toast.makeText(AtomPaymentActivity.this,"Transaction Cancelled! \n"  + Arrays.toString((String[]) arr.get(20)) + "\n" + Arrays.toString((String[]) arr.get(14)), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(AtomPaymentActivity.this,"Transaction Failed! \n"  + Arrays.toString((String[]) arr.get(20)) + "\n" + Arrays.toString((String[]) arr.get(14)), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(AtomPaymentActivity.this,"Transaction Cancelled!", Toast.LENGTH_LONG).show();
        }

    }//onActivityResult

}