package com.learningandroid.childprotection.adapter;
// this is the adapter class used in our recycler view
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learningandroid.childprotection.R;
import com.learningandroid.childprotection.model.statsItem;

import java.util.ArrayList;
import java.util.List;

public class statsAdapter extends RecyclerView.Adapter<statsAdapter.ViewHolder> implements Filterable {

    private final List<statsItem> userList;
    private List<statsItem> exampleListFull;

    public statsAdapter(List<statsItem> userList){
        this.userList=userList;
        exampleListFull = new ArrayList<>(userList);
    }

    @NonNull
    @Override
    public statsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull statsAdapter.ViewHolder holder, int position) {

        int resource =userList.get(position).getImageview1();
        String name= userList.get(position).getNameText();
        String time = userList.get(position).getDurationText().toString();

        holder.setData(resource,name,time);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<statsItem> filteredList = new ArrayList<>();
            if(charSequence.length()==0||charSequence==null){
                filteredList.addAll(exampleListFull);
            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(statsItem item: exampleListFull){
                    if(item.getNameText().toLowerCase().trim().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            userList.clear();
            userList.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };


    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageview1 ;
        private final TextView nameText;
        private final TextView durationText;

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
