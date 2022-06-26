package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_Login extends AppCompatActivity {
    EditText memail, mSerial;
    Button login;
    TextView add;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login);

        memail=findViewById(R.id.AdminLoginMail);
        mSerial=findViewById(R.id.adminLoginPass);
        login=findViewById(R.id.adminLoginButton);
        add=findViewById(R.id.adminSignup);

        fAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= memail.getText().toString().trim();
                String serial= mSerial.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(serial)){
                    mSerial.setError("Serial_Number is required.");
                    return;
                }
                if(serial.length() <5){
                    mSerial.setError("Serial_Number must be >= 5");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,serial).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Admin_Login.this,"Logged In Successfully.",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Admin_Login.this,AdminProfiel.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Admin_Login.this, "Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_Login.this,Admin_Reg.class);
                startActivity(intent);
            }
        });
    }

}