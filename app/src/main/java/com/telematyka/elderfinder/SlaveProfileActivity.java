package com.telematyka.elderfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.telematyka.objects.SlaveProfile;
import android.net.Uri;

public class SlaveProfileActivity extends AppCompatActivity {
    SlaveProfile slaveProfile;
    TextView mMasterNameView;
    TextView mSlaveNameView;
    TextView mTelephoneNumberView;
    TextView mBirthDateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slave_profile);
        Intent intent = getIntent();
        Gson gson = new Gson();
        slaveProfile =  gson.fromJson(intent.getSerializableExtra("profile").toString(),SlaveProfile.class);
        setTextViews();
        setTexts();

    }

    private void setTexts(){
        mMasterNameView.setText(slaveProfile.getMasterNameSurname());
        mBirthDateView.setText(slaveProfile.getDateofBirth());
        mTelephoneNumberView.setText(slaveProfile.getTelephoneNumber());
        mSlaveNameView.setText(slaveProfile.getNameSurname());
    }
    private void setTextViews(){
        mMasterNameView =(TextView) findViewById(R.id.masterNameText);
        mSlaveNameView = (TextView) findViewById(R.id.nameSurname);
        mBirthDateView = (TextView) findViewById(R.id.birth_date);
        mTelephoneNumberView = (TextView) findViewById(R.id.telephoneNumber);

    }
    public void callMaster(View w){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+slaveProfile.getTelephoneNumber()));
        startActivity(callIntent);
    }

}
