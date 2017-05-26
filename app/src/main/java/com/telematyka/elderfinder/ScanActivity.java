package com.telematyka.elderfinder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.zxing.Result;
import com.telematyka.objects.SlaveProfile;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mScannerView = new ZXingScannerView(ScanActivity.this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(ScanActivity.this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.w("result",result.getText());
        HttpRequest getProfile = new HttpRequest(result.getText());
        getProfile.execute();



    }

    private class HttpRequest extends AsyncTask <Void,Void,SlaveProfile>{

        String slaveid;

        HttpRequest(String slaveid){
            this.slaveid = slaveid;
        }

        @Override
        protected SlaveProfile doInBackground(Void... voids) {
            try{
                final String url = "http://elderfind-evmatsle.rhcloud.com/slave/find/" + slaveid;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                SlaveProfile slaveProfile = restTemplate.getForObject(url, SlaveProfile.class);
                return slaveProfile;
            }catch (Exception e){
                Log.e("error:",e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(SlaveProfile slaveProfile){
            Gson gson = new Gson();
            Intent slaveProfileIntent = new Intent(getApplicationContext(),SlaveProfileActivity.class);
            slaveProfileIntent.putExtra("profile",gson.toJson(slaveProfile));
            startActivity(slaveProfileIntent);

            finish();
        }
    }
}
