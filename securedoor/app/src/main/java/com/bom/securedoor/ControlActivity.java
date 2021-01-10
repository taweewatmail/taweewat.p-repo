package com.bom.securedoor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControlActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Intent intentSignOut, intentPIN, intentProfile;
    private FirebaseUser currentUser;
    private static boolean active = true;
    private DatabaseReference refChangeState, refChangeStatePut;
    private static final String TAG = "";
    private String changeStateValue;
    private FirebaseDatabase chkDatabase, chkDatabasePut;
    private DatabaseReference mRootRef;
    private String uidAcc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contrl);
        final Button buttonSignOut = findViewById(R.id.signOutButton);
        final Button buttonProfile = findViewById(R.id.profButton);
        final Button buttonControl = findViewById(R.id.controlButton);
        intentSignOut = new Intent(this, MainActivity.class);
        intentProfile = new Intent(this, AccountActivity.class);
        intentPIN = new Intent(this, PINActivity.class);
        mAuth = FirebaseAuth.getInstance();
        chkDatabase = FirebaseDatabase.getInstance();
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.getInstance()
                        .signOut();
                startActivity(intentSignOut);
            }
        });
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intentProfile);
            }
        });
        buttonControl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(changeStateValue.equals("0")){
                    Toast.makeText(ControlActivity.this, "Open the door",
                            Toast.LENGTH_SHORT).show();
                    refChangeState.setValue("1");
                    onStart();
                }else if(changeStateValue.equals("1")){
                    Toast.makeText(ControlActivity.this, "Close the door",
                            Toast.LENGTH_SHORT).show();
                    refChangeState.setValue("0");
                    onStart();
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            uidAcc = user.getUid();
            checkStateCont();
        }
    }
    private void checkStateCont(){
        refChangeState = chkDatabase.getReference(uidAcc+"/changeCont");
        refChangeState.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    changeStateValue = dataSnapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
