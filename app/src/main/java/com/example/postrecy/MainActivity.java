package com.example.postrecy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    RelativeLayout relativeLayout;
    String[] page={"1 Remark","2 Remark","3 Remark","4 Remark","5 Remark","6 Remark","7 Remark","8 Remark","9 Remark","10 Remark"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout=findViewById(R.id.layout);
        recyclerView = findViewById(R.id.vertical);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

      //getting data
        getData();
    }




    void getData(){
        String URL_API="/services/rest/?method=flickr.photos.getRecent&per_page=20&page=";
        String URL_API1="&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api =retrofit.create(Api.class);
        Call<Pojo> call=api.setPhot(URL_API+"2"+URL_API1);
        call.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                Pojo  result = response.body();
                //adapter attach
                recyclerView.setAdapter(new VerticalAdpter(MainActivity.this ,result.getPhotos().getPhoto(),page));
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                //snackbar when no connection
               Snackbar.make(relativeLayout,"No internet connection.",Snackbar.LENGTH_INDEFINITE)
                        .setAction("retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "retrying", Toast.LENGTH_SHORT).show();
                                getData();
                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorAccent))
                        .show();
            }
        });
    }

}
