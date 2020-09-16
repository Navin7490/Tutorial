package com.example.tutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashboardCourse_Adapter extends RecyclerView.Adapter<DashboardCourse_Adapter.Viewholder> {
    private Context context;
    private ArrayList<DashboardCorse_Modal>product;

    public DashboardCourse_Adapter(Context context, ArrayList<DashboardCorse_Modal> product) {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public DashboardCourse_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View viewlist=layoutInflater.inflate(R.layout.dashboard,parent,false);
        return new Viewholder(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardCourse_Adapter.Viewholder holder, int position) {
        holder.tvcourse.setText(product.get(position).getDascouNmae());
        holder.tvdescription.setText(product.get(position).getDashcouDescription());
        holder.tvprice.setText(product.get(position).getDashcouPrice());
        holder.tvacademy.setText(product.get(position).getDashcouAcademy());


    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvcourse,tvdescription,tvprice,tvacademy;
        ImageView edit;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvcourse=itemView.findViewById(R.id.Tv_mycc);
            tvdescription=itemView.findViewById(R.id.Tv_mydd);
            tvprice=itemView.findViewById(R.id.Tv_mypp);
            tvacademy=itemView.findViewById(R.id.Tv_myAA);
            edit=itemView.findViewById(R.id.Im_EditCo);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context.getApplicationContext(),ViewDetail_Activity.class);
                    context.startActivity(intent);
                }
            });


        }
    }
}
