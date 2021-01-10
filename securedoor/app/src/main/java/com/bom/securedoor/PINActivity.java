package com.bom.securedoor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PINActivity extends AppCompatActivity {
    private static final String TAG = "";
    private EditText pinET;
    private Intent intentCont;
    private String pinValue;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String uidAcc;
    private FirebaseDatabase chkDatabase;
    private DatabaseReference mRootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pin);
        pinET = (EditText) findViewById(R.id.editTextPin);
        intentCont = new Intent(this, ControlActivity.class);
        mAuth = FirebaseAuth.getInstance();
        chkDatabase = FirebaseDatabase.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        pinET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /**if(Integer.parseInt(pinValue) == 000000){
                    startActivity(intentCont);
                }**/
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(pinET.getText().toString().length() == 6){
                    if(pinET.getText().toString().equals(pinValue.toString())){
                        Toast.makeText(PINActivity.this, "Checked PIN",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intentCont);
                    }
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
        checkPin();
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            uidAcc = user.getUid();
        }
    }
    private void checkPin(){
        DatabaseReference refPin = chkDatabase.getReference(uidAcc+"/pin");
        refPin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    pinValue = dataSnapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
