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

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText logInEmail,logInPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        logInEmail = findViewById(R.id.logInEmail);
        logInPassword = findViewById(R.id.logInPassword);
    }

    public void logIn(View view){
        String email = logInEmail.getText().toString().trim();
        String password = logInPassword.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            logInEmail.setError("Input a valid email");
            logInEmail.requestFocus();
        }
        if(password.length() == 0){
            logInPassword.setError("Password is requested");
            logInPassword.requestFocus();
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            Intent toTraining = new Intent(MainActivity.this,TrainingActivity.class);
                            startActivity(toTraining);
                        }else {
                            Toast.makeText(getApplicationContext(),"Email or Password are not match",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
