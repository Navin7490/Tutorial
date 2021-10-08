package vision.madhvi.tutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import vision.madhvi.tutorial.model.Group_Modal;
import vision.madhvi.tutorial.R;

public class Group_Adapter extends ArrayAdapter<Group_Modal> {


    public Group_Adapter(@NonNull Context context, ArrayList<Group_Modal>grouplist) {
        super(context, 0,grouplist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
    private View initView(int position, View convertView, ViewGroup parent){

        if (convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.course_group_list,parent,false);
        }

        TextView tvgid=convertView.findViewById(R.id.Tv_groupid);
        TextView tvgname=convertView.findViewById(R.id.Tv_group);

  Group_Modal groupModal=getItem(position);

  if (groupModal!=null) {

      tvgid.setText(groupModal.getGroupid());
      tvgname.setText(groupModal.getGroupname());

  }

  return convertView;



    }
}
