package com.example.tutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCourseSubject_Adapter extends RecyclerView.Adapter<MyCourseSubject_Adapter.viewholder> {
     public   Context context;
     ArrayList<MyCourseSubject_Modal> product;

    public MyCourseSubject_Adapter(Context context, ArrayList<MyCourseSubject_Modal> product) {
        this.context = context;
        this.product = product;

    }

    @NonNull
    @Override
    public MyCourseSubject_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewlist = layoutInflater.inflate(R.layout.mycourse_subject_listitem, parent, false);
        return new viewholder(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCourseSubject_Adapter.viewholder holder, int position) {
        holder.tvsubid.setText(product.get(position).getSubid());
        holder.tvcousub.setText(product.get(position).getSubcourse());
        holder.tvsubject.setText(product.get(position).getSubjectname());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvsubid,tvsubject,tvcousub;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvsubid=itemView.findViewById(R.id.Tv_cousubId);
            tvsubject = itemView.findViewById(R.id.Tv_MycourseSubjectList);
            tvcousub=itemView.findViewById(R.id.Tv_cousub);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String subid=tvsubid.getText().toString();
                    String coursename=tvcousub.getText().toString();
                    String subject=tvsubject.getText().toString();
                    Intent intent=new Intent(context,MyCourser_Sub_LessionActivity.class);
                    intent.putExtra("subjectid",subid);

                    intent.putExtra("course_name",coursename);
                    intent.putExtra("subject_name",subject);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }
    }
}
