package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogInActivity extends AppCompatActivity {
    EditText edtUserNameSignUp,edtPasswordSignUp,edtUserNameLogin,edtPasswordLogin;
    Button btnSignUp,btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtPasswordLogin=findViewById(R.id.edtPasswordLogin);
        edtPasswordSignUp=findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogin=findViewById(R.id.edtUserNameLogin);
        edtUserNameSignUp=findViewById(R.id.edtUserNameSignUp);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser=new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLogInActivity.this,appUser.get("username")+" is signed up successfully", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                        else {
                            FancyToast.makeText(SignUpLogInActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null&&e==null){
                            FancyToast.makeText(SignUpLogInActivity.this,user.get("username")+" is logged in successfully", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                        }else{
                            FancyToast.makeText(SignUpLogInActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });

            }
        });

    }
}
