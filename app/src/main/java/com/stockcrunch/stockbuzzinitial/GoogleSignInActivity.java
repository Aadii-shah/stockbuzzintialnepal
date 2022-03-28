package com.stockcrunch.stockbuzzinitial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shobhitpuri.custombuttons.GoogleSignInButton;


public class GoogleSignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private final int RC_SIGN_IN = 134;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso;
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("333091359097-ukjuaknb70fq5docu5kcjvog9je5b51s.apps.googleusercontent.com")
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInButton signInButton = findViewById(R.id.googleSignIn);
        signInButton.setOnClickListener(view -> signIn());

        TextView termsService = findViewById(R.id.termsService);
        TextView privacyPolicy = findViewById(R.id.privacyPolicy);
        termsService.setOnClickListener(v -> gotoUrl("https://docs.google.com/document/d/1ZsLzM31kmOu6kENDjfku3-OpxYF1l0vEq2f__s_W3TM/edit?usp=sharing"));
        privacyPolicy.setOnClickListener(v -> gotoUrl("https://docs.google.com/document/d/1ZsLzM31kmOu6kENDjfku3-OpxYF1l0vEq2f__s_W3TM/edit?usp=sharing"));

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data).addOnSuccessListener(account -> firebaseAuthWithGoogle(account.getIdToken()));

        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        ////Log.v("cred1", store);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // To dismiss the dialog
                        Intent i = new Intent(GoogleSignInActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();


                    } else {
                        //signInAgain();
                        Toast.makeText(GoogleSignInActivity.this, "NN", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
    }



}