package vision.madhvi.tutorial.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vision.madhvi.tutorial.R;
import vision.madhvi.tutorial.ui.activity.ViewVideo_FreeActivity;
import vision.madhvi.tutorial.model.FreeTutorial_Modal;

public class FreeTutorial_Adapter extends RecyclerView.Adapter<FreeTutorial_Adapter.ViewholderFreeTutorial> {
    private Context context;
    private ArrayList<FreeTutorial_Modal> product;

    public FreeTutorial_Adapter(Context context, ArrayList<FreeTutorial_Modal> product) {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public FreeTutorial_Adapter.ViewholderFreeTutorial onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View viewlist=layoutInflater.inflate(R.layout.freedemovideo_list_item,parent,false);
        return new ViewholderFreeTutorial(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull FreeTutorial_Adapter.ViewholderFreeTutorial holder, int position) {
        holder.tvftId.setText(product.get(position).getFtutorialid());
        holder.tvftname.setText(product.get(position).getFtutorialname());
        holder.tvftdescription.setText(product.get(position).getFtutorialdescription());
        holder.tvftpath.setText(product.get(position).getFtutorialpath());
        holder.tvftlessionid.setText(product.get(position).getFtutoriallessionid());
        holder.tvftorderid.setText(product.get(position).getFtutorialorderid());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewholderFreeTutorial extends RecyclerView.ViewHolder {
        TextView tvftId, tvftname, tvftdescription,tvftpath,tvftlessionid,tvftorderid;
        String path;
        public ViewholderFreeTutorial(@NonNull View itemView) {
            super(itemView);
            tvftId=itemView.findViewById(R.id.Tv_FTId);
            tvftname=itemView.findViewById(R.id.Tv_FTName);
            tvftdescription=itemView.findViewById(R.id.Tv_FTDescription);
            tvftpath=itemView.findViewById(R.id.Tv_FTPath);
            tvftlessionid=itemView.findViewById(R.id.Tv_FTLessionId);
            tvftorderid=itemView.findViewById(R.id.Tv_FTOrderId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    path=tvftpath.getText().toString();

                    Intent intent=new Intent(context, ViewVideo_FreeActivity.class);
                    intent.putExtra("freevideopath",path);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });


        }
    }
}
