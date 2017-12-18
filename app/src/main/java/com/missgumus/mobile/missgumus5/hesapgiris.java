package com.missgumus.mobile.missgumus5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class hesapgiris extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText emailText;
    EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesapgiris);

        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        emailText=(EditText) findViewById(R.id.emailText);
        passwordText=(EditText) findViewById(R.id.passwordText);

    }

    public void signUp(View view) {

        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Kullanıcı Oluşturuldu",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),HesabimActivity.class);
                            startActivity(intent);
                        }

                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if (e != null) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void signIn(View view){

        mAuth.signInWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            String email=emailText.getText().toString();
                            String sifre=passwordText.getText().toString();
                            Intent intent;
                            if(email.equals("deryaderux@gmail.com")&&sifre.equals("123456"))
                            {
                                Toast.makeText(getApplicationContext(),"Admin girişi yapıldı",Toast.LENGTH_SHORT).show();
                                intent=new Intent(getApplicationContext(),AdminActivity.class);
                            }
                            else
                            {
                                intent = new Intent(getApplicationContext(),HesabimActivity.class);
                            }

                            startActivity(intent);
                        }

                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mAuthListener !=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }
}
