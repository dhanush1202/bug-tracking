package com.example.bugtracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class bugregis extends AppCompatActivity {
    EditText nam,source,description;
    Button submit;
    FloatingActionButton fab;
    FirebaseFirestore f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugregis);
        nam=findViewById(R.id.name1);
        source=findViewById(R.id.source);
        description=findViewById(R.id.description);
        submit=findViewById(R.id.submit);
        fab=findViewById(R.id.fab);
        f=FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nm=nam.getText().toString(),so=source.getText().toString(),de=description.getText().toString();
                if(TextUtils.isEmpty(nm)||TextUtils.isEmpty(so)||TextUtils.isEmpty(de)){
                    Toast.makeText(bugregis.this, "enter valid details", Toast.LENGTH_SHORT).show();
                }
                else{
                HashMap<String ,String > hash=new HashMap<>();
                hash.put("Name",nm);
                hash.put("Source",so);
                hash.put("Description",de);
                hash.put("Status","pending");
                f.collection("user").add(hash).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(bugregis.this, "Added successfully", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(bugregis.this,lists.class));
                    }
                });
            }}
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bugregis.this,lists.class));
                Toast.makeText(bugregis.this, "working", Toast.LENGTH_SHORT).show();

            }
        });

    }


}