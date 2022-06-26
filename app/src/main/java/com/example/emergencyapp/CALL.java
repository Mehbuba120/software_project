package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CALL extends AppCompatActivity {
    private TextView name, sys, dis,  hrt, time, date;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_a_l_l);

        myDialog = new Dialog(this);

        name = findViewById(R.id.textView8);
        sys = findViewById(R.id.textView9);
        hrt = findViewById(R.id.textView10);
        dis= findViewById(R.id.textView10dis);
        time=findViewById(R.id.textView8time);
        date=findViewById(R.id.textView8date);

        name.setText(getIntent().getStringExtra("fName"));
        sys.setText(getIntent().getStringExtra("syspress"));
        hrt.setText(getIntent().getStringExtra("fheart"));
        dis.setText(getIntent().getStringExtra("dispress"));
        time.setText(getIntent().getStringExtra("ftime"));
        date.setText(getIntent().getStringExtra("fdate"));

    }



    //PopUp
   /* public void showPopup(View v) {
        TextView closs;
        myDialog.setContentView(R.layout.terms_policies);
        closs = myDialog.findViewById(R.id.cross);
        closs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();


    }*/
}