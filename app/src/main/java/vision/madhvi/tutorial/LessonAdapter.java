package vision.madhvi.tutorial;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import vision.madhvi.tutorial.R;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.lessonViewholder> {
    private Context context;
     ArrayList<LessonModal>lessonitem;
    String videolink;
    public LessonAdapter(Context context, ArrayList<LessonModal> lessonitem) {
        this.context = context;
        this.lessonitem = lessonitem;
    }

    @NonNull
    @Override
    public LessonAdapter.lessonViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listitem=layoutInflater.inflate(R.layout.lesson_item,parent,false);
        return new lessonViewholder(listitem);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.lessonViewholder holder, int position) {
        holder.tvvideolink.setText(lessonitem.get(position).getTvlink());
        holder.videoTitle.setText(lessonitem.get(position).getLessonName());
//        try {
//            String link= lessonitem.get(position).getLessonImageurl();
//            MediaController mediaController=new MediaController(context);
//            mediaController.setAnchorView(holder.imageView);
//
//            Uri video=Uri.parse(link);
//            holder.imageView.setMediaController(mediaController);
//            holder.imageView.setVideoURI(video);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


     //    Glide.with(context).load(lessonitem.get(position).getLessonImageurl()).into(holder.imageView);

        //holder.tvvideoUrl.setText(lessonitem.get(position).getVideourl());
        //Glide.with(context).load(lessonitem.get(position).getLessonImageurl()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return lessonitem.size();
    }

    public class lessonViewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView  videoTitle,tvvideolink;
        public lessonViewholder(@NonNull View itemView) {
            super(itemView);
           // imageView=itemView.findViewById(R.id.Im_lessonItem);
            //tvlessonitemname=itemView.findViewById(R.id.Tv_lessonItemdetail);
            videoTitle=itemView.findViewById(R.id.Tv_lessonItemdetail);
            imageView=itemView.findViewById(R.id.Im_lessonItem);
            tvvideolink=itemView.findViewById(R.id.Tv_VideoUrl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videolink=tvvideolink.getText().toString();
                    Intent intent=new Intent(context.getApplicationContext(),View_Video_Activity.class);
                    intent.putExtra("videourl",  videolink);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
