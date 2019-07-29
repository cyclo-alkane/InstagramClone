 package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

 public class SignUp extends AppCompatActivity implements View.OnClickListener {
     private EditText edtName,edtPunch,edtKick;
     private Button submit,btnGetAllData,btnNextActivity;
     private TextView getData;
     private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName=findViewById(R.id.edtName);
        edtKick=findViewById(R.id.edtKick);
        edtPunch=findViewById(R.id.edtPunch);
        submit=findViewById(R.id.submit);
        getData=findViewById(R.id.getData);
        btnGetAllData=findViewById(R.id.btnGetAllData);
        btnNextActivity = findViewById(R.id.btnNextActivity);
        submit.setOnClickListener(SignUp.this) ;
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("dmGBHv7ZTA", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object!=null && e == null) {
                            getData.setText("Name: " + object.get("name")+"\n"+ "Punch: " + object.get("punch") + "\n" + "Kick: "+ object.get("kick"));
//                            FancyToast.makeText(SignUp.this, parseQuery + "", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                    }
                });
            }
        });
        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers ="";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.whereGreaterThan("kick",300);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size()>0){
                                for(ParseObject kickBoxer:objects){
                                    allKickBoxers=allKickBoxers +"Name:"+ kickBoxer.get("name") + "\n";
                                }

                                FancyToast.makeText(SignUp.this,allKickBoxers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }
                            else{
                                FancyToast.makeText(SignUp.this,"failure",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                            }
                        }
                    }
                });
            }
        });

        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

     @Override
     public void onClick(View v) {

        try {
            final ParseObject kickboxer = new ParseObject("KickBoxer");
            kickboxer.put("name", edtName.getText().toString());
            kickboxer.put("punch", edtPunch.getText().toString());
            kickboxer.put("kick", Integer.parseInt(edtKick.getText().toString()));
            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickboxer.get("name") + "is saved successfully to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        //Toast.makeText(SignUp.this,kickboxer.get("name")+" is saved successfully to server",Toast.LENGTH_LONG).show();
                    } else {
                   //     FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

//                    Toast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }
            });
        }catch(Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
     }
 }
