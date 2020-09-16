package com.example.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vision_Adapter extends RecyclerView.Adapter<Vision_Adapter.Viewholder> {
    private Context context;
    private ArrayList<Vision_Modal>product;

    public Vision_Adapter(Context context, ArrayList<Vision_Modal> product) {
        this.context = context;
        this.product = product;
    }


    @NonNull
    @Override
    public Vision_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View viewlist=layoutInflater.inflate(R.layout.home_vision_listitem,parent,false);

        return new Viewholder(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull Vision_Adapter.Viewholder holder, int position) {
        holder.tvvtitle.setText(product.get(position).getVisiontitle());
        holder.tvvdescription.setText(product.get(position).getVisiondescription());
        Glide.with(context).load(product.get(position).getVisionimage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView tvvtitle,tvvdescription;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.Im_Home_visionlist);
            tvvtitle=itemView.findViewById(R.id.Tv_Home_visionTitle);
            tvvdescription=itemView.findViewById(R.id.Tv_Home_visionDescription);
        }
    }
}
