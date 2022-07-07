package com.pharma.atom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.atom.atompaynetzsdk.AtomAES;
import com.atom.atompaynetzsdk.PayActivity;
import com.atom.ots.enc.AtomEncryption;
import com.pharma.AtomPaymentActivity;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ATOMPaymentService {
    public static void payNow(Activity cntxt, String amount,String txnid,String mdate){
        Intent newPayIntent=new Intent(cntxt, PayActivity.class);
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
        newPayIntent.putExtra("amt",amount);

        newPayIntent.putExtra("txnid","ATOM-"+System.currentTimeMillis() );
        //newPayIntent.putExtra("date", "29/05/2022 18:31:00");
        newPayIntent.putExtra("date", mdate);
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
        cntxt.startActivityForResult(newPayIntent,1);
    }
    public static String encodeBase64(String encode)
    {
        String decode=null;
        try {
            decode=  Base64.encode(encode.getBytes());
            Base64.decode("C05AED44531A61C12454AC83E2D48EA16A780A0A0F35FF24075A80FAAACAE3E430557E0FA2968E35C4FF2ECB37402443C1BE16094B2E6D7E93B0934FB16F45324F406580A3A54B497148FA3CD939812FB92E16A72B82E46911E81B92444F8BD48BBC014C7955D062881BFDEA1C557761065918731626EBF1B9630DD5A7B9E2412E76E0AEA10E08E22993B62FD81D76A491FD96D69851E6B029118965822982AB1809978FF0EE4F773F858AA7C56B7755D2025F4C0B316C41B9EFB46A30B7B6C3E4D00656DB72164C7899C2724C365D62D04EFCF4BBB111E3D41276D7717976044EC38DA8177FDC432B5BFC8260E3DBBA84CB3568DCAF50A6E07256FDAEDD071A942DCC5AD4A9638791DC222A7DCD3F22F10DE70E254869F9AD35E59E68F54AC8982FF6DDB5B611C3A9D55FBB39A5B2BD");
        } catch (Exception e) {
            System.out.println("Unable to decode : "+ e);
        }
        return  decode.trim();
    }
}
