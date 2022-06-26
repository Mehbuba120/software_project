package com.example.emergencyapp;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {


    public Context context;
    ArrayList<user> userArrayList;
    ArrayList<user> userArrayListFull;
    public MyAdapter(Context context, ArrayList<user> userArrayList) {
        this.context = context;
        this.userArrayListFull = userArrayList;
        this.userArrayList= new ArrayList<>(userArrayListFull);
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        user user =userArrayList.get(position);

        holder.fName.setText(user.getfName());
        holder.fdate.setText(user.getfdate());
        holder.ftime.setText(user.getftime());
        holder.dispress.setText(user.getdispress());
        holder.fheart.setText(user.getfheart());
        holder.comment.setText(user.getcomment());
        holder.syspress.setText(user.getsyspress());
        holder.serial.setText(user.getserial());


        //new
        String ava=holder.comment.getText().toString().trim();
        if(ava.length()>9){
            holder.comment.setTextColor(Color.parseColor("#C82F24"));
        }
        else if (ava.length()==6){
            holder.comment.setTextColor(Color.parseColor("#0A5E0E"));
        }


//new

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,CALL.class);
                intent.putExtra("fName",user.getfName());
                intent.putExtra("fdate",user.getfdate());
                intent.putExtra("ftime",user.getftime());
                intent.putExtra("dispress",user.getdispress());
                intent.putExtra("fheart",user.getfheart());
                intent.putExtra("comment",user.getcomment());
                intent.putExtra("syspress",user.getsyspress());
                intent.putExtra("serial",user.getserial());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return userFilter;
    }
    private final Filter userFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
           ArrayList<user> filterdList = new ArrayList<>();
           if (constraint == null || constraint.length()==0){
               filterdList.addAll(userArrayListFull);
           }else{
               String filterPattern = constraint.toString().toLowerCase().trim();

               for(user user :userArrayListFull){
                   if(user.fName.toLowerCase().contains(filterPattern))
                       filterdList.add(user);
               }
           }
           FilterResults results =new FilterResults();
           results.values=filterdList;
           results.count=filterdList.size();
           return  results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            userArrayList.clear();
            userArrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();


        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fName, fdate,ftime,dispress,fheart,comment, syspress, serial;
        Button call;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fName=itemView.findViewById(R.id.username);
            fdate=itemView.findViewById(R.id.userdis);
            ftime=itemView.findViewById(R.id.usersub);
            dispress=itemView.findViewById(R.id.useremail);
            fheart=itemView.findViewById(R.id.usercontact);
            call=itemView.findViewById(R.id.callNow);
            comment=itemView.findViewById(R.id.userstatus);
            syspress=itemView.findViewById(R.id.userhos);
            serial=itemView.findViewById(R.id.userserial);
        }
    }
}

