package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button signin;
    TextView signup;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.loginemail);
        password=(EditText)findViewById(R.id.passwordlogin);
        signin=(Button)findViewById(R.id.signinlogin);
        signup=(TextView)findViewById(R.id.singuplogin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkdata();
            }
        });
    }
    private void checkdata(){
        String passwords=password.getText().toString();
        String emails=email.getText().toString();
        if (TextUtils.isEmpty(emails)||TextUtils.isEmpty(passwords)){
            Toast.makeText(LoginActivity.this,"All Field are necessary",Toast.LENGTH_LONG).show();
        }else if (passwords.length()<7){
            Toast.makeText(LoginActivity.this,"wrong password",Toast.LENGTH_LONG).show();
        }else {
            checkuser(emails,passwords);
            pd=new ProgressDialog(LoginActivity.this);
            pd.setTitle("Signing in");
            pd.setMessage("Please Wait");
            pd.show();

        }
    }
    private void checkuser(String email,String password){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                String uid=firebaseUser.getUid();
                pd.dismiss();
                Intent i=new Intent(LoginActivity.this,MainActivity2.class);
                startActivity(i);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this,"Unable to login",Toast.LENGTH_LONG).show();
            }
        });

    }
}