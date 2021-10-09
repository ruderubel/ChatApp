package com.rrinc.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText email,password;
    Button btn_login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.bt_login);

        btn_login.setOnClickListener(v -> {
            String txt_email = Objects.requireNonNull(email.getText()).toString();
            String txt_password = Objects.requireNonNull(password.getText()).toString();

            if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                Toast.makeText(LoginActivity.this,"Fill up all field",Toast.LENGTH_SHORT).show();
            }else {

                auth.signInWithEmailAndPassword(txt_email,txt_password)
                        .addOnCompleteListener(task -> {
                            if(task.isSuccessful()){

                                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}