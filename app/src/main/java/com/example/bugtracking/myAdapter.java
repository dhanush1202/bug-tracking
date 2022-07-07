package com.example.bugtracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.net.ssl.SSLEngineResult;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {
    Context context;
    ArrayList<user> userArrayList;

    public myAdapter(Context context, ArrayList<user> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public myAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.myViewHolder holder, int position) {
    user u=userArrayList.get(position);
    holder.Name.setText(u.Name);
    holder.Status.setText(u.Status);

    holder.Description.setText(u.Description);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Status,Description;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.Name);
            Status =itemView.findViewById(R.id.Status);
            Description=itemView.findViewById(R.id.Description);

        }
    }
}
