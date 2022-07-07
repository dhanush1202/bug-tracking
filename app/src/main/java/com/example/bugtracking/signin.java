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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signin extends AppCompatActivity {
    EditText name,mail,pno,pwd,cpwd;
    TextView log;
    Button b1;
    FirebaseAuth a;

    public void req(String id,String pwd){
        a.createUserWithEmailAndPassword(id,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signin.this, "success", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(signin.this,MainActivity.class));
                }
                else{
                    Toast.makeText(signin.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        b1=findViewById(R.id.button2);
        name=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        pno=findViewById(R.id.pno);
        pwd=findViewById(R.id.pwd);
        cpwd=findViewById(R.id.cpwd);
        log=findViewById(R.id.log);
        a=FirebaseAuth.getInstance();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin.this,MainActivity.class));
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na=name.getText().toString();
                String ma=mail.getText().toString();
                String p=pno.getText().toString();
                String pw=pwd.getText().toString();
                String cpw=cpwd.getText().toString();
                if(!(TextUtils.isEmpty(na)||TextUtils.isEmpty(ma)||TextUtils.isEmpty(p)||TextUtils.isEmpty(pw)||TextUtils.isEmpty(cpw))){
                    if( pw.equals(cpw)) {
                        req(ma, pw);
                        HashMap<String, String> h = new HashMap<>();
                        h.put("name", na);
                        h.put("email", ma);
                        h.put("phno", p);
                        h.put("password", pw);
                        FirebaseDatabase.getInstance().getReference().child("vendor").push().setValue(h);
                    }
                    else{
                        Toast.makeText(signin.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(signin.this, "enter all details", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}