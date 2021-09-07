package com.example.teachme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class showdetails extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String taddress;
    String tem;
    String tmno2;
    String tid;
    String tland;
    String tmail;
    String tmid;
    String tname;
    String tschedule;
    String twno;
    String tmode="";
    String nan="Tutor Not Avalaible";
    TextView wno,mid,em1,address,land,mno2,schedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetails);
        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        wno=findViewById(R.id.wn);
        em1=findViewById(R.id.em);
        mid=findViewById(R.id.mid);
        address=findViewById(R.id.add);
        land=findViewById(R.id.land);
        mno2=findViewById(R.id.mno2);
        schedule=findViewById(R.id.schedule);
        taddress=getIntent().getStringExtra("taddress");
        tem=getIntent().getStringExtra("tem");
        tid=fauth.getCurrentUser().getUid();
        tland=getIntent().getStringExtra("tland");
        tmid=getIntent().getStringExtra("tmid");
        tname=getIntent().getStringExtra("tname");
        tmno2=getIntent().getStringExtra("tmno");
        tschedule=getIntent().getStringExtra("tschedule");
        twno=getIntent().getStringExtra("twno");
        tmode=getIntent().getStringExtra("tmode");


        DocumentReference documentReference2 = fstore.collection("users").document(tid);
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.getString("tmode").contentEquals("Online")) {
                    wno.setText(wno.getText()+twno);
                    mid.setText(mid.getText()+tmid);
                    em1.setText(em1.getText()+tem);

                    schedule.setText(nan);
                    mno2.setText(nan);
                    land.setText(nan);
                    address.setText(nan);
                }
                else if(value.getString("tmode").contentEquals("Offline"))
                {
                    schedule.setText(schedule.getText()+tschedule);
                    mno2.setText(mno2.getText()+tmno2);
                    land.setText(land.getText()+tland);
                    address.setText(address.getText()+taddress);

                    wno.setText(nan);
                    mid.setText(nan);
                    em1.setText(nan);
                }
                else if(value.getString("tmode").contentEquals("Both"))
                { wno.setText(wno.getText()+twno);
                    mid.setText(mid.getText()+tmid);
                    em1.setText(em1.getText()+tem);
                    schedule.setText(schedule.getText()+tschedule);
                    mno2.setText(mno2.getText()+tmno2);
                    land.setText(land.getText()+tland);
                    address.setText(address.getText()+taddress);
                }

            }
        });

    }
}