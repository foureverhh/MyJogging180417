package com.nackademin.foureverhh.myjogging180417;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText signUpUserName,signUpEmail,signUpPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_avtivity);

        mAuth = FirebaseAuth.getInstance();
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);
    }

    public void signUp(View view){
        String email = signUpEmail.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpEmail.setError("Input a valid email");
            signUpEmail.requestFocus();
        }
        if(password.length() == 0){
            signUpPassword.setError("Password is requested");
            signUpEmail.requestFocus();
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            Intent toTraining = new Intent(SignUpActivity.this,TrainingActivity.class);
                            startActivity(toTraining);
                        }else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),"You have already registered",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
