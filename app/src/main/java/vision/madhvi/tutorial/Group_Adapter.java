package vision.madhvi.tutorial;

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

public class Group_Adapter extends RecyclerView.Adapter<Group_Adapter.ViewHolderGroup> {
     Context context;
     ArrayList<Group_Modal>product;

    public Group_Adapter(Context context, ArrayList<Group_Modal> product) {
        this.context = context;
        this.product = product;
    }

    @NonNull
    @Override
    public Group_Adapter.ViewHolderGroup onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context.getApplicationContext());
        View viewlist=layoutInflater.inflate(R.layout.group_list_item,parent,false);
        return new ViewHolderGroup(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull Group_Adapter.ViewHolderGroup holder, int position) {
        holder.tvgroupname.setText(product.get(position).getGroupname());
        holder.tvgroupid.setText(product.get(position).getGroupid());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolderGroup extends RecyclerView.ViewHolder {
        TextView tvgroupname,tvgroupid;
        public ViewHolderGroup(@NonNull View itemView) {
            super(itemView);
            tvgroupname=itemView.findViewById(R.id.Tv_GroupName);
            tvgroupid=itemView.findViewById(R.id.Tv_GroupId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id =tvgroupid.getText().toString();
                    String groupname=tvgroupname.getText().toString();
                    Toast.makeText(context, ""+id+" "+groupname, Toast.LENGTH_SHORT).show();
                   // Intent intent=new Intent(context,Course_Fragment.class);
                   // intent.putExtra("groupid",id);
                   // intent.putExtra("groupname",groupname);
                   // context.startActivity(intent);

                }
            });

        }
    }
}
