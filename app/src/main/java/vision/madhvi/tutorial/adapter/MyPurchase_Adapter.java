package vision.madhvi.tutorial.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vision.madhvi.tutorial.model.MyPurchaseCourse_Modal;
import vision.madhvi.tutorial.R;
import vision.madhvi.tutorial.ui.activity.MyCourseSubject_Activity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyPurchase_Adapter  extends RecyclerView.Adapter<MyPurchase_Adapter.viewholder> {
    public MyPurchase_Adapter(Context context, ArrayList<MyPurchaseCourse_Modal> product) {
        this.context = context;
        this.product = product;
    }

    private Context context;
    private ArrayList<MyPurchaseCourse_Modal>product;

    @NonNull
    @Override
    public MyPurchase_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View vielist=layoutInflater.inflate(R.layout.my_corses_list_item,parent,false);
        return new viewholder(vielist);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPurchase_Adapter.viewholder holder, int position) {
        holder.tvoid.setText(product.get(position).getPurchaseId());
        holder.tvcoursename.setText(product.get(position).getCoursename());
        holder.tvcoursedescr.setText(product.get(position).getCoursedetail());
        Picasso.get().load(product.get(position).getImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvoid,tvcoursename,tvcoursedescr;
        ImageView imageView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvoid=itemView.findViewById(R.id.Tv_MyPurchaseCourseId);
            tvcoursename=itemView.findViewById(R.id.Tv_My_Purchasecourse);
            tvcoursedescr=itemView.findViewById(R.id.Tv_MY_CourseDescription);
            imageView=itemView.findViewById(R.id.Im_My_PCourse);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String couserid=tvoid.getText().toString();
                    Intent intent=new Intent(context.getApplicationContext(), MyCourseSubject_Activity.class);
                    intent.putExtra("couserid",couserid);
                    context.startActivity(intent);
                }
            });


        }
    }
}
