package vision.madhvi.tutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorial.R;

import java.util.ArrayList;

public class MySub_Lession_Adapter extends RecyclerView.Adapter<MySub_Lession_Adapter.viewholder> {
     Context context;
    ArrayList<MySub_Lession_Modal>product;

    public MySub_Lession_Adapter(Context context, ArrayList<MySub_Lession_Modal> product) {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public MySub_Lession_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View vielist=layoutInflater.inflate(R.layout.mysub_lession_list,parent,false);

        return new viewholder(vielist);
    }

    @Override
    public void onBindViewHolder(@NonNull MySub_Lession_Adapter.viewholder holder, int position) {

        holder.tvid.setText(product.get(position).getLessionid());
        holder.tvlessiotitle.setText(product.get(position).getLessiontitle());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView tvid,tvlessiotitle;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvid=itemView.findViewById(R.id.Tv_MySub_Lession_id);
            tvlessiotitle=itemView.findViewById(R.id.Tv_MySub_Lession_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String lessionid=tvid.getText().toString();
                    Intent intent=new Intent(context.getApplicationContext(),ViewVideoBySubject_Activity.class);
                    intent.putExtra("lessionid",lessionid);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);
                }
            });
        }
    }
}
