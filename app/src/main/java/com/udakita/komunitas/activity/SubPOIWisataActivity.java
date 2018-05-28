package com.udakita.komunitas.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.udakita.komunitas.R;
import com.udakita.komunitas.adapter.ListAdapterSubPOI;
import com.udakita.komunitas.helper.MyFunction;
import com.udakita.komunitas.model.DataKomunitasItem;
import com.udakita.komunitas.model.ModelKomunitas;
import com.udakita.komunitas.network.MyRetrofitClient;
import com.udakita.komunitas.network.RestApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubPOIWisataActivity extends MyFunction {

    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.namaObjek)
    TextView namaObjek;
    @BindView(R.id.buttonBerita)
    ImageView buttonBerita;
    @BindView(R.id.buttonMap)
    ImageView buttonMap;

    //init adapter
    ListAdapterSubPOI adapter;
    List<DataKomunitasItem> arraylist ;

    String catid = null;
    String id=null;
    String name=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_poiwisata);
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
        final ProgressDialog progress2 = new ProgressDialog(SubPOIWisataActivity.this);
        //tambahkan pesan
        progress2.setMessage("Loading...");
        progress2.show();

        //String url = getString(R.string.url_api);
        //init retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
//                .build();
        RestApi api = MyRetrofitClient.getInstaceRetrofit();


        Call<ModelKomunitas> call = api.getkomunitas(id);
        arraylist= new ArrayList<DataKomunitasItem>();

        call.enqueue(new Callback<ModelKomunitas>() {
            @Override
            public void onResponse(Call<ModelKomunitas> call, Response<ModelKomunitas> response) {
                progress2.dismiss();
                if (response.isSuccessful()) {
                    if ( response.body().getDataKomunitas()!=null){
                        int jlh = response.body().getDataKomunitas().size();
                        String[] idArr = new String[jlh];
                        String[] nameArr = new String[jlh];
                        String[] addressArr = new String[jlh];
                        String[] pictureArr = new String[jlh];
                        String[] markerIconArr = new String[jlh];
                        String[] idkomunitas = new String[jlh];
                        String[] idjenis = new String[jlh];
                        if (response.body().getDataKomunitas().size() > 0) {
                            for (int i = 0; i < response.body().getDataKomunitas().size(); i++) {
                                idArr[i] = String.valueOf(response.body().getDataKomunitas().get(i).getId());
                                nameArr[i] = response.body().getDataKomunitas().get(i).getNmKomunitas();
                                addressArr[i] = response.body().getDataKomunitas().get(i).getAlamat();
                                pictureArr[i] = response.body().getDataKomunitas().get(i).getGambar();
                                idkomunitas[i] = response.body().getDataKomunitas().get(i).getIdKomunitas();
                                idjenis[i] = response.body().getDataKomunitas().get(i).getIdJenis();
                                markerIconArr[i]=response.body().getDataKomunitas().get(i).getMarker();
                                DataKomunitasItem pg = new DataKomunitasItem(idArr[i], nameArr[i], addressArr[i], pictureArr[i],markerIconArr[i],idkomunitas[i],idjenis[i]);
                                //HeroHelper.pesan(ctx,nameArr[i]);
                                arraylist.add(pg);
                            }
                        }
                    }
                    else {
                        mytoast("tidak ada data komunitas");
//                        HeroHelper.pesan(ctx,"Tidak ada data wisata");
                    }

                } else {
  mytoast("tidak ada data komunitas");
//                    HeroHelper.pesan(ctx,"Tidak ada data wisata");
                }
                // Pass results to ListViewAdapter Class
                adapter = new ListAdapterSubPOI(SubPOIWisataActivity.this, arraylist, id);
                Log.d("testaja",arraylist.toString());
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
            public void onFailure(Call<ModelKomunitas> call, Throwable t) {
                Log.d("Signal Gagal ", " Gagal Response Parsing Data Json dari URL");
            }
        });



    }

    @OnClick({R.id.buttonBerita, R.id.buttonMap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonBerita:
//                Intent in = new Intent(SubPOIWisataActivity.this, BeritaAreaActivity.class);
//                in.putExtra("id",id);
//                in.putExtra("name",name);
//                startActivity(in);
                break;
            case R.id.buttonMap:
                //HeroHelper.pesan(ctx,catid+"-"+id);
                Intent intent = new Intent(SubPOIWisataActivity.this, MapSubKomunitasActivity.class);
                // Pass all data population
                intent.putExtra("id",id);
                intent.putExtra("catid",catid);
                intent.putExtra("name",name);
                startActivity(intent);
                break;
        }
    }
}
