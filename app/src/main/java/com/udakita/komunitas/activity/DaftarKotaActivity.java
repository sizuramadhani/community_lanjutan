package com.udakita.komunitas.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.udakita.komunitas.R;
import com.udakita.komunitas.adapter.ListAdapterKota;
import com.udakita.komunitas.helper.MyFunction;
import com.udakita.komunitas.model.DataKota;
import com.udakita.komunitas.model.ModelKota;
import com.udakita.komunitas.network.MyRetrofitClient;
import com.udakita.komunitas.network.RestApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarKotaActivity extends MyFunction {

    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.namaObjek)
    TextView namaObjek;

    //init adapter
    ListAdapterKota adapter;
    ArrayList<DataKota> arraylist = new ArrayList<DataKota>();

    String catid = null;
    String id=null;
    String name=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotakomunitas);
        ButterKnife.bind(this);
        //back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_arrow_left);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Intent intent = getIntent();
        catid = intent.getStringExtra("catid");
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");

        //set text
        namaObjek.setText(name);

        String key = "sdf";

        //init progess dialog
        final ProgressDialog progress2 = new ProgressDialog(this);
        //tambahkan pesan
        progress2.setMessage("Loading...");
        progress2.show();
        RestApi api = MyRetrofitClient.getInstaceRetrofit();

        //get response base url route
        Call<ModelKota> call = api.getKota( id);
        call.enqueue(new Callback<ModelKota>() {
            @Override
            public void onResponse(Call<ModelKota> call, Response<ModelKota> response) {
                progress2.dismiss();
                if (response.isSuccessful()) {
                    if ( response.body().getDataKota()!=null){
                        int jlh = response.body().getDataKota().size();
                        String[] idArr = new String[jlh];
                        String[] nameArr = new String[jlh];
                        String[] addressArr = new String[jlh];
                        String[] pictureArr = new String[jlh];
                        String[] markerIconArr = new String[jlh];
                        if (response.body().getDataKota().size() > 0) {
                            for (int i = 0; i < response.body().getDataKota().size(); i++) {
                                idArr[i] = String.valueOf(response.body().getDataKota().get(i).getId());
                                nameArr[i] = response.body().getDataKota().get(i).getNama();
//                                addressArr[i] = response.body().getDataKota().get(i).getAddress();
//                                pictureArr[i] = response.body().getDataKota().get(i).getPicture();
//                                markerIconArr[i]=response.body().getDataKota().get(i).getMarker();
                                DataKota pg = new DataKota(idArr[i], nameArr[i]);
                                //HeroHelper.pesan(ctx,nameArr[i]);
                                arraylist.add(pg);
                            }
                        }
                    }
                    else {
  mytoast("tidak ada data kota");
//                        HeroHelper.pesan(ctx,"Tidak ada data wisata");
                    }

                } else {
                    mytoast("tidak ada data kota");
//                    HeroHelper.pesan(ctx,"Tidak ada data wisata");
                }

                // Pass results to ListViewAdapter Class
                adapter = new ListAdapterKota(DaftarKotaActivity.this, arraylist, id);

                // Binds the Adapter to the ListView
                listview.setAdapter(adapter);

                search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.filter(newText);
                        return false;
                    }
                });
            }

            @Override
            public void onFailure(Call<ModelKota> call, Throwable t) {
                Log.d("Signal Gagal ", " Gagal Response Parsing Data Json dari URL");
            }
        });



    }

}
