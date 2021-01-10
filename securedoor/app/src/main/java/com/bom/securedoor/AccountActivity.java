package com.bom.securedoor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {
    private static final String TAG = "";
    private TextView UsernameTextVie, UidTextVie;
    private Intent intentSignInAcc;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String nameAcc, emailAcc = "Your email", uidAcc = "Your UID", uidAccCreate, chkDbf="false", newPass, emailChgPass, passChgPass;
    private DatabaseReference mRootRef;
    private FirebaseDatabase chkDatabase;
    private EditText pinET,passET, newPassET;
    //String emailSh= "Email: " + emailAcc, uidSh= "Uid: " + uidAcc;**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account);
        mAuth = FirebaseAuth.getInstance();
        UsernameTextVie = (TextView) findViewById(R.id.textViewEmailAcc);
        UidTextVie = (TextView) findViewById(R.id.textViewUidAcc);
        final Button buttonSignInAcc = findViewById(R.id.signInButtonAcc);
        final Button buttonSavePassAcc = findViewById(R.id.savePassButtonAcc);
        intentSignInAcc =   new Intent(this, MainActivity.class);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        pinET = (EditText) findViewById(R.id.editTextEditPin);
        passET = (EditText) findViewById(R.id.editTextEditPass);
        newPassET = (EditText) findViewById(R.id.editTextNewPass);
        pinET.setVisibility(View.INVISIBLE);
        passET.setVisibility(View.INVISIBLE);
        newPassET.setVisibility(View.INVISIBLE);
        chkDatabase = FirebaseDatabase.getInstance();
        final Button editPinButton = findViewById(R.id.edPinButton);
        final Button editPassButton = findViewById(R.id.edPassButton);
        buttonSavePassAcc.setVisibility(View.INVISIBLE);
        buttonSignInAcc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intentSignInAcc);
            }
        });
        editPinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pinET.setVisibility(View.VISIBLE);
            }
        });
        editPassButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                passET.setVisibility(View.VISIBLE);
                newPassET.setVisibility(View.VISIBLE);
                buttonSavePassAcc.setVisibility(View.VISIBLE);

            }
        });
        buttonSavePassAcc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                passChgPass = passET.getText().toString();
                newPass = newPassET.getText().toString();
                updatePassword();
                passET.setVisibility(View.INVISIBLE);
                newPassET.setVisibility(View.INVISIBLE);
                buttonSavePassAcc.setVisibility(View.INVISIBLE);
            }
        });
        pinET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(pinET.getText().toString().length() == 6){
                    DatabaseReference refPin = chkDatabase.getReference(uidAccCreate+"/pin");
                    refPin.setValue(pinET.getText().toString());
                    pinET.setVisibility(View.INVISIBLE);
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
        createProf();
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intentAcc = getIntent();
            nameAcc = user.getDisplayName();
            emailAcc = "Email: " + user.getEmail();
            Uri photoUrlAcc = user.getPhotoUrl();
            boolean emailVerifiedAcc = user.isEmailVerified();
            uidAcc = "UID: " + user.getUid();
            uidAccCreate = user.getUid();
            UsernameTextVie.setText(emailAcc);
            UidTextVie.setText(uidAcc);
            emailChgPass = user.getEmail();
        }
    }
    private void createProf(){
        DatabaseReference refPin = chkDatabase.getReference(uidAccCreate);
        refPin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                }else{
                    DatabaseReference mUsersRef = mRootRef.child(uidAccCreate);
                    mUsersRef.child("pin").setValue("000000");
                    mUsersRef.child("changeCont").setValue("close");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void updatePassword(){
        AuthCredential credential = EmailAuthProvider.getCredential(emailChgPass, passChgPass);
        currentUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            currentUser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Password updated");
                                    } else {
                                        Log.d(TAG, "Error password not updated");
                                    }
                                }
                            });
                        } else {
                            Log.d(TAG, "Error auth failed");
                        }
                    }
                });
    }
}
