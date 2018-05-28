package com.udakita.komunitas.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.udakita.komunitas.R;
import com.udakita.komunitas.helper.MyFunction;
import com.udakita.komunitas.helper.SessionManager;
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

public class MapSubKomunitasActivity extends MyFunction implements OnMapReadyCallback {

    private GoogleMap mMap;
    @BindView(R.id.namaObjek)
    TextView namaObjek;
    @BindView(R.id.buttonBerita)
    ImageView buttonBerita;
    @BindView(R.id.buttonMap)
    ImageView buttonMap;
    CameraUpdate cu;
    //init marker , untuk fit zoom all marker
    private List<Marker> markersList = new ArrayList<Marker>();

    String catid = null;
    String id = null;
    String name=null;
    String key = "sdf";

    ArrayList<String> placeList = new ArrayList<String>();
    ArrayList<String> idList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_sub_komunitas);
        ButterKnife.bind(this);
        //back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_arrow_left);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        catid = intent.getStringExtra("catid");
        id = intent.getStringExtra("id");
        name=intent.getStringExtra("name");

        //set Text
        namaObjek.setText(name);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Enable/Show Traffic
        SessionManager sesi= new SessionManager();
//        if (sesi.getTraffic().equals("Yes")){
//            mMap.setTrafficEnabled(true);
//        }

        //iktifkan settingan zoom contol
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //init progess dialog
        final ProgressDialog progress = new ProgressDialog(MapSubKomunitasActivity.this);
        //tambahkan pesan
        progress.setMessage("Loading...");
        progress.show();

        //  String url = getString(R.string.url_api);
        //init retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
//                .build();
        //init api service
        RestApi api = MyRetrofitClient.getInstaceRetrofit();

        //get response base url route
        Call<ModelKomunitas> call = api.getkomunitas( id);
        call.enqueue(new Callback<ModelKomunitas>() {
            @Override
            public void onResponse(Call<ModelKomunitas> call, Response<ModelKomunitas> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getDataKomunitas()!=null){
                            for (int i = 0; i < response.body().getDataKomunitas().size(); i++) {
                                Double lat = Double.valueOf(response.body().getDataKomunitas().get(i).getLatKomunitas());
                                Double lon = Double.valueOf(response.body().getDataKomunitas().get(i).getLonKomunitas());
                                String placeName = response.body().getDataKomunitas().get(i).getNmKomunitas();
                                LatLng koordinat = new LatLng(lat, lon);
                                String id=response.body().getDataKomunitas().get(i).getId();
                                String idsubkom=response.body().getDataKomunitas().get(i).getIdKomunitas();
                                Marker mapMarker = mMap.addMarker(new MarkerOptions()
                                        .position(koordinat)
                                        .title(placeName)
                                );

                                //add to list, untuk membuat fix bounds, sehingga semua marker terlihat
                                markersList.add(i, mapMarker);

                                placeList.add(placeName);
                                idList.add(idsubkom);
                            }
                        }
                        else {
                            mytoast("Tidak ada data wisata");
//                            HeroHelper.pesan(ctx,"Tidak ada data wisata");
                        }

                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }

                }else {
                    mytoast("Tidak ada data komunitas");
//                    HeroHelper.pesan(ctx,"Tidak ada data wisata");
                }

                //kalau ada marker
                //fit zoom all markers
                if (markersList.size() > 0) {
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (Marker m : markersList) {
                        builder.include(m.getPosition());
                    }
                    /**initialize the padding for map boundary*/
                    int padding = 90;
                    /**create the bounds from latlngBuilder to set into map camera*/
                    LatLngBounds bounds = builder.build();
                    /**create the camera with bounds and padding to set into map*/
                    cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                    /**call the map call back to know map is loaded or not*/
                    mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                        @Override
                        public void onMapLoaded() {
                            /**set animated zoom camera into map*/
                            mMap.animateCamera(cu);

                        }
                    });
                }


                //Event Klik infoWindows
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {

                        LatLng latLon = marker.getPosition();

                        //Cycle through places array
                        for (Marker place : markersList) {
                            if (latLon.equals(place.getPosition())) {
                                // Send single item click data to SingleItemView Class
                                Intent intent = new Intent(MapSubKomunitasActivity.this, DetailPOIActivity.class);
                                // Pass all data population
                                // Pass all data population
                                int indexArrayList = placeList.indexOf(place.getTitle());
                                int dataid = placeList.indexOf(place.getId());
                                intent.putExtra("id",idList.get(indexArrayList));
                                startActivity(intent);
                            }

                        }
                    }
                });




            }

            @Override
            public void onFailure(Call<ModelKomunitas> call, Throwable t) {

            }
        });
    }
    @OnClick({R.id.buttonBerita, R.id.buttonMap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonBerita:
//                Intent in = new Intent(MapSubPOIWisataActivity.this, BeritaAreaActivity.class);
//                in.putExtra("id",id);
//                in.putExtra("name",name);
//                startActivity(in);
                break;
            case R.id.buttonMap:
                //HeroHelper.pesan(ctx,catid+"-"+id);
                Intent intent = new Intent(MapSubKomunitasActivity.this, SubPOIWisataActivity.class);
                // Pass all data population
                intent.putExtra("id",id);
                intent.putExtra("catid",catid);
                intent.putExtra("name",name);
                startActivity(intent);
                break;
        }
    }
}
