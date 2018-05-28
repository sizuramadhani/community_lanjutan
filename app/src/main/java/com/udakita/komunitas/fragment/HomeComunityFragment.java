package com.udakita.komunitas.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udakita.komunitas.R;
import com.udakita.komunitas.adapter.AdapterBerita;
import com.udakita.komunitas.model.DataBerita;
import com.udakita.komunitas.model.ModelBerita;
import com.udakita.komunitas.network.MyRetrofitClient;
import com.udakita.komunitas.network.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeComunityFragment extends Fragment {

    // Deklarasi Widget
    private RecyclerView recyclerView;

    public HomeComunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home_comunity, container, false);
        // Inisialisasi Widget
        recyclerView = (RecyclerView) v.findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Eksekusi method
        tampilBerita();
        Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
        return v;
    }


    private void tampilBerita() {
        RestApi api = MyRetrofitClient.getInstaceRetrofit();
        // Siapkan request
        Call<ModelBerita> beritaCall = api.request_show_all_berita();
        // Kirim request
        beritaCall.enqueue(new Callback<ModelBerita>() {
            @Override
            public void onResponse(Call<ModelBerita> call, Response<ModelBerita> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<DataBerita> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterBerita adapter = new AdapterBerita(getContext(), data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(getContext(), "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelBerita> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}
