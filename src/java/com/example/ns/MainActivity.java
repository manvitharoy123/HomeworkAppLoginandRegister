package com.example.ns;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button buttonSignup;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView textViewSignin;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.firebaseAuth = instance;
        if (instance.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        this.editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        this.editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        this.textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        this.buttonSignup = (Button) findViewById(R.id.buttonSignup);
        this.progressDialog = new ProgressDialog(this);
        this.buttonSignup.setOnClickListener(this);
        this.textViewSignin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = this.editTextEmail.getText().toString().trim();
        String password = this.editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", 1).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", 1).show();
        }
        this.progressDialog.setMessage("Logging Please Wait...");
        this.progressDialog.show();
        this.firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    MainActivity.this.finish();
                    MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), ProfileActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Registration Error", 1).show();
                }
                MainActivity.this.progressDialog.dismiss();
            }
        });
    }

    public void onClick(View view) {
        if (view == this.buttonSignup) {
            registerUser();
        }
        if (view == this.textViewSignin) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
