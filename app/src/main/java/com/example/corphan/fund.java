package com.example.corphan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class fund extends AppCompatActivity {
    EditText amount,desc,name,upiid;
    Button pay;

    final int UPI_PAYMENT=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund);

        initializemethod();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amounttxt=amount.getText().toString();
                String desctxt=desc.getText().toString();
                String nametxt=name.getText().toString();
                String upitxt=upiid.getText().toString();
                payUsingupi(amounttxt,desctxt,nametxt,upitxt);

            }
        });
    }

    private void payUsingupi(String amounttxt, String desctxt, String nametxt, String upitxt) {
        Uri uri=Uri.parse("upi://pay").buildUpon().appendQueryParameter("pa",upitxt).appendQueryParameter("pn",nametxt).appendQueryParameter("tn",desctxt).appendQueryParameter("am",amounttxt).appendQueryParameter("cu","INR").build();
        Intent upi_payment=new Intent(Intent.ACTION_VIEW);
        upi_payment.setData(uri);
        Intent chooser=Intent.createChooser(upi_payment,"Pay with");
        if(null!=chooser.resolveActivity(getPackageManager())){
            startActivityForResult(chooser,UPI_PAYMENT);
        }
        else{
            Toast.makeText(this, "No Upi App Found", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializemethod() {
        pay=(Button) findViewById(R.id.button);
        name=(EditText) findViewById(R.id.name);
        amount=(EditText) findViewById(R.id.amount);
        desc=(EditText) findViewById(R.id.desc);
        upiid=(EditText) findViewById(R.id.upi_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode || (resultCode == 11))) {
                    if (data != null) {
                        String txt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult:" + txt);
                        ArrayList<String> dataLst = new ArrayList<>();
                        dataLst.add("Nothing");
                        upipaymentdataoperation(dataLst);
                    } else {
                        Log.d("UPI", "onActivityResult:" + "Return data is null");
                        ArrayList<String> dataLst = new ArrayList<>();
                        dataLst.add("Nothing");
                        upipaymentdataoperation(dataLst);
                    }
                } else {
                    Log.d("UPI", "onActivityResult:" + "Return data is null");
                    ArrayList<String> dataLst = new ArrayList<>();
                    dataLst.add("Nothing");
                    upipaymentdataoperation(dataLst);
                }
                break;
        }
    }

    private void upipaymentdataoperation(ArrayList<String> data) {
        if(isConnetionAvaliable(fund.this)){
            String str=data.get(0);
        Log.d("UPIPAY","upipaymentoperation:"+str);
        String paymentCancel="";
        if(str==null)str="discard";
        String status="";
        String approvalref="";
        String response[]=str.split("&");
        for(int i=0;i<response.length;i++) {
            String equalStr[] = response[i].split("=");
            if (equalStr.length >= 2) {
                if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                    status = equalStr[1].toLowerCase();
                } else if (equalStr[0].toLowerCase().equals("approval Ref".toLowerCase()) || equalStr[0].toLowerCase().equals(("txnRef".toLowerCase()))) {
                    approvalref = equalStr[1];
                }
            }
            else
            {
                paymentCancel = "Payement cancel by user";
            }
        }
                if (status.equals("Success")) {
                    Toast.makeText(this, "Transaction Success", Toast.LENGTH_SHORT).show();
                    Log.d("UPI", "responsestr:" + approvalref);
                } else if ("Payment cancel by user".equals(paymentCancel)) {
                    Toast.makeText(this, "Payment cancel by user", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Transaction failed", Toast.LENGTH_SHORT).show();
                }
            }
            else{
            Toast.makeText(this, "Internet connection is not available", Toast.LENGTH_SHORT).show();
            }
        }


    private boolean isConnetionAvaliable(Context context) {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
            NetworkInfo networkinfo=connectivityManager.getActiveNetworkInfo();
            if(networkinfo!=null && networkinfo.isConnected()&&networkinfo.isConnectedOrConnecting() && networkinfo.isAvailable()){
                return true;
            }
    }
    return false;
}
}