package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Model> DataArrayList; //kit add kan ke adapter
    private ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        //addData();
        addDataOnline();
    }

    void addData() {
        //offline, isi data offline dulu
        DataArrayList = new ArrayList<>();
        Model data1 = new Model();
        data1.setOriginal_title("Rogue");
        data1.setPoster_path("https://image.tmdb.org/t/p/w185/uOw5JD8IlD546feZ6oxbIjvN66P.jpg");
        data1.setAdult(false);
        data1.setOverview("Battle-hardened O’Hara leads a lively mercenary team of soldiers on a daring mission: rescue hostages from their captors in remote Africa. But as the mission goes awry and the team is stranded, O’Hara’s squad must face a bloody, brutal encounter with a gang of rebels.");
        data1.setVote_count(100);
        data1.setRelease_date("2020-08-20");
        DataArrayList.add(data1);


        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //get data online


    }

    void addDataOnline(){
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=232d11bd2b63919c51d8d5382aa319c9")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setOriginal_title(jsonObject.getString("original_title"));
                                modelku.setRelease_date(jsonObject.getString("release_date"));
                                modelku.setOverview(jsonObject.getString("overview"));
                                modelku.setPoster_path("https://image.tmdb.org/t/p/w185/"+jsonObject.getString("poster_path"));
                                modelku.setAdult(jsonObject.getBoolean("adult"));
                                modelku.setVote_count(jsonObject.getInt("vote_count"));
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {

                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}