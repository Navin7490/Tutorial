package vision.madhvi.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vision.madhvi.tutorial.R;

import java.util.ArrayList;

public class SubjectDetail_Adapter extends RecyclerView.Adapter<SubjectDetail_Adapter.Viewholder> {

    private Context context;
    private ArrayList<SubjectDeatail_Modal>product;
    public SubjectDetail_Adapter(Context context, ArrayList<SubjectDeatail_Modal> product) {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public SubjectDetail_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View viewlist=layoutInflater.inflate(R.layout.subject_list,parent,false);
        return new Viewholder(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectDetail_Adapter.Viewholder holder, int position) {
        holder.tvsubject.setText(product.get(position).getSubname());
        holder.tvdescription.setText(product.get(position).getSubdescription());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvsubject,tvdescription;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvsubject=itemView.findViewById(R.id.Tv_Subject);
            tvdescription=itemView.findViewById(R.id.Tv_Description);
        }
    }
}
