package com.udakita.komunitas.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.udakita.komunitas.R;
import com.udakita.komunitas.adapter.ListAdapterProvinsiKomunitas;
import com.udakita.komunitas.helper.MyFunction;
import com.udakita.komunitas.helper.SessionManager;
import com.udakita.komunitas.model.DataProvinsi;
import com.udakita.komunitas.model.ModelProvinsi;
import com.udakita.komunitas.network.MyRetrofitClient;
import com.udakita.komunitas.network.RestApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarProvinsiKomunitasActivity extends MyFunction {

    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.listview)
    ListView listview;

    //init adapter
    ListAdapterProvinsiKomunitas adapter;
    ArrayList<DataProvinsi> arraylist = new ArrayList<DataProvinsi>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsikomunitas);
        ButterKnife.bind(this);
        SessionManager sesi = new SessionManager();
        //create session Radius "0" if empty
//        if (sesi.getRadius() == "") {
//            sesi.setRadius("20"); //default
//        }
//
//        //create session Notif "NO" if empty
//        if (sesi.getNotifikasi() == "") {
//            sesi.setNotifikasi("Yes"); //default
//        }
        //back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_arrow_left);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //init progess dialog
        final ProgressDialog progress = new ProgressDialog(this);
        //tambahkan pesan
        progress.setMessage("Loading...");
        progress.show();

        RestApi api = MyRetrofitClient.getInstaceRetrofit();

        //query string
        final String catid = "2";
        String key = "sdf";
        //get response base url route
        Call<ModelProvinsi> call = api.getProvinsi();
        call.enqueue(new Callback<ModelProvinsi>() {
            @Override
            public void onResponse(Call<ModelProvinsi> call, Response<ModelProvinsi> response) {
                progress.dismiss();
                if (response.isSuccessful()){
                    if (response.body().getDataProvinsi()!=null){
                        int jlh=response.body().getDataProvinsi().size();
                        String[] idArr = new String[jlh];
                        String[] nameArr = new String[jlh];
                        String[] provinceNameArr = new String[jlh];
                        String[] logoArr = new String[jlh];
                        if (response.body().getDataProvinsi().size()>0){
                            for (int i = 0; i < response.body().getDataProvinsi().size(); i++){
                                idArr[i]= String.valueOf(response.body().getDataProvinsi().get(i).getIdProv());
                                nameArr[i] = response.body().getDataProvinsi().get(i).getNama();
                           //     provinceNameArr[i] = response.body().getDataProvinsi().get(i).getNama();
                            //    logoArr[i] = response.body().getDataProvinsi().get(i).getLogo();
                                DataProvinsi pg = new DataProvinsi(idArr[i],nameArr[i]);
                                //HeroHelper.pesan(ctx,nameArr[i]);
                                arraylist.add(pg);
                            }
                        }
                    }
                    else {
                        mytoast("Tidak ada data daerah wisata");
            //            HeroHelper.pesan(ctx,"Tidak ada data daerah wisata");
                    }
                }
                else {
              mytoast("Tidak ada data daerah wisata");
//                    HeroHelper.pesan(ctx,"Tidak ada data daerah wisata");
                }


                // Pass results to ListViewAdapter Class
                //1:kuliner , 2:wisata , 3: penginapan
                adapter = new ListAdapterProvinsiKomunitas(DaftarProvinsiKomunitasActivity.this, arraylist,catid);

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
            public void onFailure(Call<ModelProvinsi> call, Throwable t) {
                Log.d("Signal Gagal ", " Gagal Response Parsing Data Json dari URL");
            }
        });



    }

    //untuk memanggil file menu_toolbar (menu_toolbar.toolbar.xml) yang dibuatkan di folder res - menu_toolbar
    //solusi agar sekali panggil saja : http://stackoverflow.com/questions/3270206/same-option-menu-in-all-activities-in-android
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //memanggil menu_toolbar.xml
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //action klik dari item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get id dari item menu
        //semua id yang ada di resource (res) termasuk folder menu adalah 'int'
        int id = item.getItemId();
        if (id == R.id.logout) {

            //dialog sebelum melakukan logout
            AlertDialog.Builder alert = new AlertDialog.Builder(DaftarProvinsiKomunitasActivity.this);
            alert.setMessage("Apakah anda yakin mau keluar ?");

            //opsi Ya
            final SessionManager sesi = new SessionManager();
            alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //clear session
                    sesi.logout();
                    //langsung pindah ke halaman login
                    startActivity(new Intent(DaftarProvinsiKomunitasActivity.this, LoginActivity.class));
                    finish();
                }
            });
            //opsi Tidak
            alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();


        } else if (id == R.id.pengaturan) {
            startActivity(new Intent(DaftarProvinsiKomunitasActivity.this, PengaturanActivity.class));

        }else if(id==R.id.profil){
    //        startActivity(new Intent(DaftarProvinsiKomunitasActivity.this,ProfilActivity.class));
        }else if (id==R.id.bantuan){
      //      startActivity(new Intent(DaftarProvinsiKomunitasActivity.this,BantuanActivity.class));
        }else if (id==R.id.tentang){
        //    startActivity(new Intent(DaftarProvinsiKomunitasActivity.this,TentangActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
