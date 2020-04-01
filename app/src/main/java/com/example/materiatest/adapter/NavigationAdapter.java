package com.example.materiatest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.materiatest.R;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder> implements View.OnClickListener {

    private List<String> strings;
    private OnAdapterClickLis clickLis;

    public NavigationAdapter(OnAdapterClickLis clickLis, List<String> strings){
        this.clickLis = clickLis;
        this.strings = strings;
    }

    @NonNull
    @Override
    public NavigationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_navigation,
                viewGroup,false);
        return new NavigationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationHolder navigationHolder, int position) {
        navigationHolder.tv_Name.setText(strings.get(position));
        navigationHolder.tv_Name.setTag(position);


        navigationHolder.tv_Name.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class NavigationHolder extends RecyclerView.ViewHolder {

        TextView tv_Name;

        public NavigationHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_Name);
        }
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
       if(clickLis!=null){
           clickLis.onAdapterClick(v,position);
       }
    }

   public interface OnAdapterClickLis{
        void onAdapterClick(View view,int position);
    }
}
