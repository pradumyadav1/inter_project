package com.example.postrecy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VerticalAdpter extends RecyclerView.Adapter<VerticalAdpter.VerticalListViewHolder> {
    Context context;
    List<Photo> imageurl;
    String[] page;
    public VerticalAdpter(Context context,List<Photo> imageurl,String[] page){
        this.context=context;
        this.imageurl=imageurl;
        this.page=page;
    }
    @NonNull
    @Override
    public VerticalListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.vertical_layout,parent,false);
        VerticalListViewHolder verticalListViewHolder = new VerticalListViewHolder(view);
        return verticalListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalListViewHolder holder, int position) {
        String data=page[position];
        holder.textView.setText(data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setAdapter(new HorizantalAdpter(context,imageurl));

    }

    @Override
    public int getItemCount() {
        return page.length;
    }

    public class VerticalListViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        RecyclerView recyclerView;
        public VerticalListViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.vertiacalText);
            recyclerView=itemView.findViewById(R.id.horizanterrecy);

        }
    }
}
