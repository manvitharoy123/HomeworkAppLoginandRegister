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

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView textViewSignup;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseAuth instance = FirebaseAuth.getInstance();
        this.firebaseAuth = instance;
        if (instance.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        this.editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        this.editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        this.buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        this.textViewSignup = (TextView) findViewById(R.id.textViewSignUp);
        this.progressDialog = new ProgressDialog(this);
        this.buttonSignIn.setOnClickListener(this);
        this.textViewSignup.setOnClickListener(this);
    }

    private void userLogin() {
        String email = this.editTextEmail.getText().toString().trim();
        String password = this.editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", 1).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", 1).show();
        } else {
            this.progressDialog.setMessage("Registering Please Wait...");
            this.progressDialog.show();
            this.firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    LoginActivity.this.progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        LoginActivity.this.finish();
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this.getApplicationContext(), ProfileActivity.class));
                    }
                }
            });
        }
    }

    public void onClick(View view) {
        if (view == this.buttonSignIn) {
            userLogin();
        }
        if (view == this.textViewSignup) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
