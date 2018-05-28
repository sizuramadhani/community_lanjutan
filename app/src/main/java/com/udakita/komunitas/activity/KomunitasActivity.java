package com.udakita.komunitas.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class KomunitasActivity extends AppCompatActivity {

    // Deklarasi Widget
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komunitas);
        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilBerita();
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
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
                        AdapterBerita adapter = new AdapterBerita(KomunitasActivity.this, data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(KomunitasActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
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
