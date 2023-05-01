package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class activity_criarconta extends AppCompatActivity {

    Button btnCriarConta;
    TextView txtexisteconta;
    EditText txtutilizador, txtemail,txtconfpass,txtpass;
    FirebaseAuth auth;
   // String emailPattern = "[a-zA-Z0-9.-]+@[a-z]+\.+[a-z]+";
   // String emailPattern2 = "[a-zA-Z0-9.-]+@[a-z]+.+[a-z]+\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criarconta);

        txtutilizador = findViewById(R.id.txtUtilizador);
        txtemail = findViewById(R.id.txtEmailLogin);
        txtpass = findViewById(R.id.txtPassLogin);
        txtconfpass = findViewById(R.id.txtConfPass);
        btnCriarConta = findViewById(R.id.btnCriarConta);
        txtexisteconta = findViewById(R.id.txtExisteConta);

        btnCriarConta.setOnClickListener(v -> {
            if (txtutilizador.getText().toString().equals("") || txtemail.getText().toString().equals("") || txtconfpass.getText().toString().equals("") || txtpass.getText().toString().equals(""))
            {
                Toast.makeText(activity_criarconta.this, "Please fill all the spaces!", Toast.LENGTH_SHORT).show();
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

        public void verificaruser(String email,String pass){
            auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {
                        Toast.makeText(activity_criarconta.this, "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity_criarconta.this, menuprincipal.class);
                        startActivity(intent);

                    }).addOnFailureListener(e -> Toast.makeText(activity_criarconta.this, "Something went wrong", Toast.LENGTH_SHORT).show());

        txtexisteconta.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), activity_Login.class);
            startActivity(intent);
            finish();
        });

    }
}
}