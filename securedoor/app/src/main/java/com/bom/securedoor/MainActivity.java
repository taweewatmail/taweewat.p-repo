package com.bom.securedoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";
    private FirebaseAuth mAuth;
    protected String email="", password="", nameAcc, emailAcc, uidAcc,emailSignIn,passwordSignIn;
    private EditText UsernameET, PasswordET;
    private FirebaseUser user, currentUser;
    private Intent intentAcc, intentPIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        UsernameET = (EditText)findViewById(R.id.editTextUsername);
        PasswordET = (EditText)findViewById(R.id.editTextPass);
        intentAcc = new Intent(this, AccountActivity.class);
        intentPIN = new Intent(this, PINActivity.class);
        final Button signUpButton = findViewById(R.id.signUpButton);
        final Button signInButton = findViewById(R.id.signInButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                email = UsernameET.getText().toString();
                password = PasswordET.getText().toString();
                Toast.makeText(MainActivity.this, email,
                        Toast.LENGTH_SHORT).show();
                createUserWithEmailAndPassword();
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                emailSignIn = UsernameET.getText().toString();
                passwordSignIn = PasswordET.getText().toString();
                Toast.makeText(MainActivity.this, "Sign in",
                        Toast.LENGTH_SHORT).show();
                signInWithEmailAndPassword();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        updateUISignIn(currentUser);
    }
   private void createUserWithEmailAndPassword(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Create user:success");
                            user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Create user:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Sign up with email only.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void signInWithEmailAndPassword(){
        mAuth.signInWithEmailAndPassword(emailSignIn, passwordSignIn)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUISignIn(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Sign in failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUISignIn(null);
                        }
                    }
                });
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            nameAcc = user.getDisplayName();
            emailAcc = user.getEmail();
            Uri photoUrlAcc = user.getPhotoUrl();
            boolean emailVerifiedAcc = user.isEmailVerified();
            uidAcc = user.getUid();
            /**Toast.makeText(MainActivity.this, "Sign up success"+"\nEmail: "+emailAcc + "\nUid: " +uidAcc,
                    Toast.LENGTH_SHORT).show();**/
            startActivity(intentAcc);
        }
    }
    private void updateUISignIn(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(MainActivity.this, "Login success",
                    Toast.LENGTH_SHORT).show();
            startActivity(intentPIN);
        }
    }
}