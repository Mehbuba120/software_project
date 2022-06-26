package com.example.emergencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity {


    public static final String TAG = "TAG";
    EditText pName, pdate,ptime,phrt,pemail,pcomment,psys, pdis;
    Button save;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Intent data=getIntent();
        String editname=data.getStringExtra("editname");
        String editdate=data.getStringExtra("editdate");
        String edittime=data.getStringExtra("edittime");
        String editheart=data.getStringExtra("editheart");
        String editemail=data.getStringExtra("editemail");
        String editcomment=data.getStringExtra("editcomment");
        String editsys=data.getStringExtra("editsys");
        String editdis=data.getStringExtra("editdis");



        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        user=fAuth.getCurrentUser();

        pName=findViewById(R.id.updatename);
        pdate=findViewById(R.id.updateDis);
        ptime=findViewById(R.id.updatSub);
        phrt=findViewById(R.id.updatphn);
        pemail=findViewById(R.id.updatmail);
        pcomment=findViewById(R.id.updateStat);
        psys=findViewById(R.id.updatHos);
        save=findViewById(R.id.save);
        pdis=findViewById(R.id.updatdis);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pName.getText().toString().isEmpty() || pdate.getText().toString().isEmpty() || ptime.getText().toString().isEmpty() || phrt.getText().toString().isEmpty() ||pemail.getText().toString().isEmpty()|| pdis.getText().toString().isEmpty()||pcomment.getText().toString().isEmpty()||psys.getText().toString().isEmpty()){
                    Toast.makeText(UpdateProfile.this,"One or many fields are empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                String email=pemail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("admin").document(user.getUid());
                        Map<String,Object> edited= new HashMap<>();
                        edited.put("email",email);
                        edited.put("fname",pName.getText().toString());
                        edited.put("fdate",pdate.getText().toString());
                        edited.put("ftime",ptime.getText().toString());
                        edited.put("fheart",phrt.getText().toString());
                        edited.put("comment",pcomment.getText().toString());
                        edited.put("syspress",psys.getText().toString());
                        edited.put("dispress",pdis.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateProfile.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(UpdateProfile.this, AdminProfiel.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        //Toast.makeText(UpdateProfile.this,"Email is changed",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateProfile.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        pName.setText(editname);
        pdate.setText(editdate);
        ptime.setText(edittime);
        phrt.setText(editheart);
        pemail.setText(editemail);
        pcomment.setText(editcomment);
        psys.setText(editsys);
        pdis.setText(editdis);


        Log.d(TAG,"onCreate: " + editname + " " + editdate + " " + edittime + " " + editheart + " " + editemail +" " + editcomment+" "+editdis);


    }
}