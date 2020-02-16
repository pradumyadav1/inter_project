package com.example.postrecy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.http.Url;

public class HorizantalAdpter extends RecyclerView.Adapter<HorizantalAdpter.HorizantalViewHoler> {
    Context context;
    List<Photo> text;
    public HorizantalAdpter(Context context,List<Photo> text) {
        this.context=context;
        this.text=text;
    }

    @NonNull
    @Override
    public HorizantalViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.horizantal_layout,parent,false);
        HorizantalViewHoler horizantalViewHoler =new HorizantalViewHoler(view);
        return horizantalViewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull HorizantalViewHoler holder, int position) {
        if(text.get(position).getUrlS().toLowerCase().contains(".jpg")) {
            Glide.with(context).load(text.get(position).getUrlS()).into(holder.imageView);
        }
           else{


            holder.webView.loadData(text.get(position).getUrlS(),"text/html","utf-8");
       }
        Toast.makeText(context, ""+text.get(position).getUrlS(), Toast.LENGTH_LONG).show();

    }

    @Override
    public int getItemCount() {
        return text.size();
    }

    public class HorizantalViewHoler extends RecyclerView.ViewHolder{
              ImageView imageView;
              WebView webView;

        public HorizantalViewHoler(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.horizantalImage);
            webView=itemView.findViewById(R.id.videovew);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());

        }
    }

}
