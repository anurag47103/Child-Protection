package com.learningandroid.childprotection;
// this is the adapter class used in our recycler view
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;
    public Adapter(List<ModelClass> userList){this.userList=userList;}



    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        int resource =userList.get(position).getImageview1();
        String name= userList.get(position).getNameText();
        String time = userList.get(position).getDurationText();

        holder.setData(resource,name,time);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    // view holder class
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageview1 ;
        private TextView nameText;
        private TextView durationText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview1 = itemView.findViewById(R.id.imageview1);
            nameText= itemView.findViewById(R.id.nameText);
            durationText=itemView.findViewById(R.id.durationText);
        }

        public void setData(int resource, String name, String time) {
            imageview1.setImageResource(resource);
            nameText.setText(name);
            durationText.setText(time+" hrs");
        }
    }
}
