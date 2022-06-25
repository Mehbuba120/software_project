package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Admin_Reg extends AppCompatActivity {
    public static final String TAG = "TAG";
    public static final String TAG1 = "TAG";
    EditText mName, mtime, mDate, mSerial, mheartRate, memail, mDispress,mcomment,msyspress;
    Button mRegister;
    TextView login;
    FirebaseAuth fAuth;
    FirebaseFirestore  fStore;
    String adminID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__reg);
        mName=findViewById(R.id.adminname);
        mDate=findViewById(R.id.district);
        mtime=findViewById(R.id.Sub);
        mSerial=findViewById(R.id.ETpass);
        mheartRate=findViewById(R.id.ETphn);
        mDispress=findViewById(R.id.ETdis);
        login=findViewById(R.id.etLOG);
        mRegister=findViewById(R.id.adminReg);
        mcomment=findViewById(R.id.ETstatus);
        msyspress=findViewById(R.id.Hospital);
        memail=findViewById(R.id.ETmail);

        fAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() !=null){
            Intent intent=new Intent(Admin_Reg.this,AdminProfiel.class);
            startActivity(intent);
            finish();
        }

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dispress= mDispress.getText().toString().trim();
                String email= memail.getText().toString().trim();
                String serial= mSerial.getText().toString().trim();
                String name=mName.getText().toString();
                String date=mDate.getText().toString();
                String time=mtime.getText().toString();
                String heart=mheartRate.getText().toString();
                String comment=mcomment.getText().toString();
                String syspress=msyspress.getText().toString();
                if(TextUtils.isEmpty(name)){
                    mName.setError("Name is required.");
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

                fAuth.createUserWithEmailAndPassword(email,serial).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Admin_Reg.this,"Registration Complete.",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Admin_Reg.this,AdminProfiel.class);
                            adminID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("admin").document(adminID);
                            Map<String,Object> admin=new HashMap<>();
                            admin.put("fName",name);
                            admin.put("fdate",date);
                            admin.put("ftime",time);
                            admin.put("fheart",heart);
                            admin.put("dispress",dispress);
                            admin.put("comment",comment);
                            admin.put("syspress",syspress);
                            admin.put("email",email);
                            admin.put("serial",serial);

                            documentReference.set(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: Admin Profile is created for "+ adminID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG1,"onFailure: " + e.toString());
                                }
                            });

                            startActivity(intent);
                        }else{
                            Toast.makeText(Admin_Reg.this, "Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();


                        }

                    }
                });

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Admin_Reg.this,Admin_Login.class);
                startActivity(intent);
            }
        });



    }
}
