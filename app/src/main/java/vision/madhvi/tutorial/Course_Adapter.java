package vision.madhvi.tutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import vision.madhvi.tutorial.R;

import java.util.ArrayList;

public class Course_Adapter extends RecyclerView.Adapter<Course_Adapter.Viewholder> {
    private Context context;
    private ArrayList<Course_Modal> product;

    public Course_Adapter(Context context, ArrayList<Course_Modal> product) {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public Course_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewlist = layoutInflater.inflate(R.layout.home_courses_listitem, parent, false);
        return new Viewholder(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull Course_Adapter.Viewholder holder, int position) {
        holder.tvcoursId.setText(product.get(position).getCourseId());
        holder.tvcoursename.setText(product.get(position).getCoursename());
        holder.tvcoursedescription.setText(product.get(position).getCoursedescription());
        Glide.with(context).load(product.get(position).getCourseimage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvcoursId, tvcoursename, tvcoursedescription;
        ImageView imageView;
        String courseid, coursename, description, courseprice, courseGroupId;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvcoursId = itemView.findViewById(R.id.Tv_Home_courseId);
            tvcoursename = itemView.findViewById(R.id.Tv_CourseName);
            tvcoursedescription = itemView.findViewById(R.id.Tv_CourseDescription);
            imageView = itemView.findViewById(R.id.Im_Course);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //for (int i=-1;i<getAdapterPosition();i++){
                    courseid = tvcoursId.getText().toString();
                    coursename = tvcoursename.getText().toString();
                    description = tvcoursedescription.getText().toString();

                    courseprice = product.get(getPosition()).getCoursePrice();
                    courseGroupId = product.get(getPosition()).getCourseGroupId();
                    String coursGroupName=product.get(getPosition()).getCourseGroupname();
                    Intent intent = new Intent(context, ViewDetail_Activity.class);
                    intent.putExtra("courseid",courseid);
                    intent.putExtra("coursename", coursename);
                    intent.putExtra("description", description);
                    intent.putExtra("price", courseprice);
                    intent.putExtra("coursegroupid", courseGroupId);
                    intent.putExtra("coursegroupname", coursGroupName);



                    context.startActivity(intent);


                    //}
                    // intent.putExtra("courseid",courseid);
                    // intent.putExtra("description",description);

                }
            });
        }
    }
}
