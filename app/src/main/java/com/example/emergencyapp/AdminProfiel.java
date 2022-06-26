package com.example.emergencyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AdminProfiel extends AppCompatActivity {
    Button logout;
    TextView mName, mdate, mtime, mheart, memail,mcomment,msyspress, mdispress;
    Button update;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String adminID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profiel);


        logout=findViewById(R.id.logout);

        mName=findViewById(R.id.profilename);
        mdate=findViewById(R.id.profiledis);
        mtime=findViewById(R.id.profilesub);
        mheart=findViewById(R.id.profilephn);
        memail=findViewById(R.id.profilemail);
        mcomment=findViewById(R.id.profilestatus);
        msyspress=findViewById(R.id.profilehos);
        mdispress= findViewById(R.id.disprofile);

        update=findViewById(R.id.update);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        adminID= fAuth.getCurrentUser().getUid();

        DocumentReference documentReference= fStore.collection("admin").document(adminID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                //if document exists
                mName.setText(documentSnapshot.getString("fName"));
                mdate.setText(documentSnapshot.getString("fdate"));
                mtime.setText(documentSnapshot.getString("ftime"));
                mheart.setText(documentSnapshot.getString("fheart"));
                memail.setText(documentSnapshot.getString("email"));
                mcomment.setText(documentSnapshot.getString("comment"));
                msyspress.setText(documentSnapshot.getString("syspress"));
                mdispress.setText(documentSnapshot.getString("dispress"));

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminProfiel.this, UpdateProfile.class);
                intent.putExtra("editname",mName.getText().toString());
                intent.putExtra("editdate",mdate.getText().toString());
                intent.putExtra("edittime",mtime.getText().toString());
                intent.putExtra("editheart",mheart.getText().toString());
                intent.putExtra("editemail",memail.getText().toString());
                intent.putExtra("editcomment",mcomment.getText().toString());
                intent.putExtra("editsys",msyspress.getText().toString());
                intent.putExtra("editdis",mdispress.getText().toString());

                startActivity(intent);
            }
        });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(AdminProfiel.this,Admin_Login.class);
                startActivity(intent);
                finish();
            }
        });
    }


}