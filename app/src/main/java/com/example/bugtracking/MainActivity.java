package com.example.bugtracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText id,pswd;
    Button but;
    TextView sign;
    FirebaseAuth au;
    public void res(String id,String pswd){
        au.signInWithEmailAndPassword(id,pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,bugregis.class));
                    Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=findViewById(R.id.id);
        pswd=findViewById(R.id.pswd);
        but=findViewById(R.id.login);
        sign=findViewById(R.id.sign);
        au=FirebaseAuth.getInstance();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,signin.class);
                startActivity(i);
            }
        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=id.getText().toString();
                String b=pswd.getText().toString();
                if(TextUtils.isEmpty(a)|| TextUtils.isEmpty(b)){
                    Toast.makeText(MainActivity.this, "enter valid details", Toast.LENGTH_SHORT).show();
                }
                else{
                    res(a,b);
//                    Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}