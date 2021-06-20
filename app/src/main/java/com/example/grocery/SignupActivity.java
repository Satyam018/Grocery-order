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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText names,emails,passwrods;
    Button signup;
    TextView gosinin;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        names=(EditText)findViewById(R.id.name);
        emails=(EditText)findViewById(R.id.emailid);
        passwrods=(EditText)findViewById(R.id.password);
        signup=(Button)findViewById(R.id.singup);
        gosinin=(TextView)findViewById(R.id.gosigninpage);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkdata();
            }
        });
        gosinin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }
    private void checkdata(){

        String name=names.getText().toString();
        String email=emails.getText().toString();
        String password=passwrods.getText().toString();
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            Toast.makeText(SignupActivity.this,"All fields are necessary",Toast.LENGTH_LONG).show();
        }else if (password.length()<7){
            Toast.makeText(SignupActivity.this,"Put a Stronger password",Toast.LENGTH_LONG).show();
        }
        else {
            pd=new ProgressDialog(SignupActivity.this);
            pd.setTitle("Signing up");
            pd.setMessage("Please Wait");
            pd.show();

            enterdata(name,password,email);
        }
    }
    private void enterdata(String name,String password,String email){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                String userid=firebaseUser.getUid();
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users").child(userid);
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("name",name);
                hashMap.put("email",email);
                hashMap.put("password",password);
                hashMap.put("id",userid);
                reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        pd.dismiss();
                        startActivity(new Intent(SignupActivity.this,MainActivity2.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        pd.dismiss();
                       Toast.makeText(SignupActivity.this,"Unable to create",Toast.LENGTH_LONG).show();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                pd.dismiss();
                Toast.makeText(SignupActivity.this,"Unable to register",Toast.LENGTH_LONG).show();
            }
        });
    }
}