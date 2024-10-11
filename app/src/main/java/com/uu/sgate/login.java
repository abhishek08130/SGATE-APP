package com.uu.sgate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login extends AppCompatActivity {
    LinearLayout btn ,googles,chppl,fcebk;
    FirebaseAuth mAuth;
    TextInputEditText psd,eml;
    TextView fgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //hooks
        btn=findViewById(R.id.button);
        googles=findViewById(R.id.ggl);
        chppl=findViewById(R.id.Appl);
        fcebk=findViewById(R.id.facebook);
        psd=findViewById(R.id.password);
        eml=findViewById(R.id.emails);
        fgt=findViewById(R.id.forgot);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(view -> {
            loginUser();
        });

        fgt.setOnClickListener(view -> {
            Intent intent = new Intent(login.this,forgot.class);
            startActivity(intent);
        });
    }
    private void loginUser(){
        String email = Objects.requireNonNull(eml.getText()).toString();
        String password = Objects.requireNonNull(psd.getText()).toString();

        if (TextUtils.isEmpty(email)){
            eml.setError("Email cannot be empty");
            eml.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            psd.setError("Password cannot be empty");
            psd.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(login.this, "Welcome to SGATE", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, dashboard.class));
                        finish();
                    }else{
                        Toast.makeText(login.this, "Log in Error: Check Your Username and Password ", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

}

