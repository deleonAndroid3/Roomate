package com.training.android.roomate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AuthenticationActivity extends AppCompatActivity {


    private EditText metPhoneNum;
    private EditText metVerify;
    private Button mbtnSignin;
    private Button mbtnVerify;
    private Button mbtnResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


    }
}
