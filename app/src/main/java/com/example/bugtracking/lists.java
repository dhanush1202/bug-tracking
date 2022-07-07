package com.example.bugtracking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class lists extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<user> arrayList;
myAdapter myadapter;
FirebaseFirestore db;
FloatingActionButton fb;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();
        recyclerView=findViewById(R.id.recyc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();
        arrayList =new ArrayList<user>();
        myadapter=new myAdapter(lists.this,arrayList);
        recyclerView.setAdapter(myadapter);
        eventlistner();
        fb=findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(lists.this,bugregis.class));
            }
        });

    }

    private void eventlistner() {
        db.collection("user").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            if(error!=null){
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Log.e("firestore error",error.getMessage());
                return;
            }
                assert value != null;
                for(DocumentChange dc: value.getDocumentChanges()){
                if(dc.getType() == DocumentChange.Type.ADDED){
                    arrayList.add(dc.getDocument().toObject(user.class));
                }
                myadapter.notifyDataSetChanged();

                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
            }
        });
    }
}