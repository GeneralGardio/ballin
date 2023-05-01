package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_Login extends AppCompatActivity {

    Button btnLogin;
    TextView txtcriarconta;
    EditText txtemail,txtpass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtcriarconta = findViewById(R.id.txtCriarConta);
        txtemail = findViewById(R.id.txtEmailLogin);
        txtpass = findViewById(R.id.txtPassLogin);


        btnLogin.setOnClickListener(v -> {
            if (txtemail.getText().toString().equals("") || txtpass.getText().toString().equals(""))
            {
                Toast.makeText(activity_Login.this, "Please fill all the spaces!", Toast.LENGTH_SHORT).show();
            }
            else if (!txtemail.getText().toString().matches(emailPattern))
            {
                if (!txtemail.getText().toString().matches(emailPattern2)) {
                    txtemail.setError("Insert your email correctly!");
                    txtemail.requestFocus();
                }
            } else if (txtpass.getText().toString().length() < 6)
            {
                txtpass.setError("Password must have 6 caracters");
                txtpass.requestFocus();
            }
            else
            if(txtpass.getText().toString().equals(txtconfpass.getText().toString())){
                verificaruser(txtemail.getText().toString(), txtpass.getText().toString());
            }
        });

        txtcriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, criarcontaActivity.class);
                startActivity(intent);
            }
        });

    }

    public void verificaruser(String email, String pass){
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, menuprincipal.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }
}
