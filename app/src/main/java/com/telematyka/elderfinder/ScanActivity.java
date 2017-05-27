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
import com.telematyka.objects.StatusResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    private class HttpRequest extends AsyncTask <Void,Void,String>{

        String slaveid;

        HttpRequest(String slaveid){
            this.slaveid = slaveid;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                Gson gson = new Gson();

                URL url = new URL("http://elderfind-evmatsle.rhcloud.com/slave/find/itamus1");
                final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setConnectTimeout(5000);
                conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
                conn.setDoOutput(false);
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                String result = "";
                InputStream in = new BufferedInputStream(conn.getInputStream());
                InputStreamReader inR = new InputStreamReader(in);
                BufferedReader buf = new BufferedReader(inR);
                String line;
                while ((line = buf.readLine()) != null) {
                    result = result + line;
                }
                SlaveProfile slaveProfile = gson.fromJson(result,SlaveProfile.class);
                in.close();
                conn.disconnect();
                Log.w("response:",result);

                /*
                final String url = "http://elderfind-evmatsle.rhcloud.com/slave/find/itamus1";//" + slaveid;
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());*/
              //  SlaveProfile slaveProfile = restTemplate.getForObject(url, SlaveProfile.class);
                return result;
            }catch (Exception e){
                Log.e("error:",e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String slaveProfile){
            Gson gson = new Gson();
            Intent slaveProfileIntent = new Intent(getApplicationContext(),SlaveProfileActivity.class);
            slaveProfileIntent.putExtra("profile",slaveProfile);//gson.toJson(slaveProfile));
            startActivity(slaveProfileIntent);
            mScannerView.stopCamera();
            finish();
        }
    }
}
