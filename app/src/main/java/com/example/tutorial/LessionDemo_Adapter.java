package com.example.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LessionDemo_Adapter extends RecyclerView.Adapter<LessionDemo_Adapter.viewholder> {
    private Context context;

    public LessionDemo_Adapter(Context context, ArrayList<LessonDemo_Modal> product) {
        this.context = context;
        this.product = product;
    }

    private ArrayList<LessonDemo_Modal>product;
    @NonNull
    @Override
    public LessionDemo_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View viewlist=layoutInflater.inflate(R.layout.demo_video_list,parent,false);
        return new viewholder(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull LessionDemo_Adapter.viewholder holder, int position) {
        holder.tvdemotitle.setText(product.get(position).getDemovideotitle());
        holder.tvdemovideourl.setText(product.get(position).getDemovideourl());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvdemotitle,tvdemovideourl;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvdemotitle=itemView.findViewById(R.id.Tv_lessonItemDemodetail);
            tvdemovideourl=itemView.findViewById(R.id.Tv_VideoUrlDemo);
        }
    }
}
