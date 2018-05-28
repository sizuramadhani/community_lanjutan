package com.udakita.komunitas.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udakita.komunitas.R;
import com.udakita.komunitas.helper.DirectionMapsV2;
import com.udakita.komunitas.helper.MyFunction;
import com.udakita.komunitas.model.ModelSubKomunitas;
import com.udakita.komunitas.model.ResponseRoute;
import com.udakita.komunitas.model.ResponseWeather;
import com.udakita.komunitas.network.MyRetrofitClient;
import com.udakita.komunitas.network.RestApi;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailPOIActivity extends MyFunction {

    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.picturePOI)
    ImageView picturePOI;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    //@BindView(R.id.txtFare)
    //TextView txtFare;
    @BindView(R.id.textDescription)
    TextView textDescription;
    @BindView(R.id.extraPicture)
    ImageView extraPicture;


    @BindView(R.id.textjarak)
    TextView textjarak;
    @BindView(R.id.textwaktu)
    TextView textwaktu;
    @BindView(R.id.texttiba)
    TextView texttiba;


    @BindView(R.id.share)
    TextView share;
    @BindView(R.id.navigate)
    TextView navigate;
    @BindView(R.id.relButton)
    RelativeLayout relButton;


    @BindView(R.id.iconWeather)
    ImageView iconWeather;
    @BindView(R.id.textWeather)
    TextView textWeather;

    //@BindView(R.id.textTemp)
    //TextView textTemp;

    @BindView(R.id.textDate)
    TextView textDate;

    String id = null;
    String key = "sdf";

    String lat, lon; //untuk variable global langitude dan latitute
    String lat_akhir, lon_akhir; //untuk variable global langitude dan latitute

    DirectionMapsV2 direction;
    @BindView(R.id.textLastModified)
    TextView textLastModified;
    @BindView(R.id.textLink)
    TextView textLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_poi);
        ButterKnife.bind(this);

        //back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_arrow_left);
        getSupportActionBar().setDisplayUseLogoEnabled(true);



        //Mark My Currect Location
        lokasiKu();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

      //  String url = getString(R.string.url_api);
        //init retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
//                .build();
//        //init api service
//        ApiService api = retrofit.create(ApiService.class);
        RestApi api = MyRetrofitClient.getInstaceRetrofit();
       // id="1";
        //get response base url route
        Call<ModelSubKomunitas> call = api.getsubkomunitas(id);
        call.enqueue(new Callback<ModelSubKomunitas>() {
            @Override
            public void onResponse(Call<ModelSubKomunitas> call, final Response<ModelSubKomunitas> response) {
                if (response.isSuccessful()) {
                    if (response.body()!=null){
                        //HeroHelper.pesan(ctx,response.body().getName());
                        //progessbar
                        //set color progress bar  --- blue
                        progressBar1.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                        progressBar1.setVisibility(View.VISIBLE);
                        Picasso.with(DetailPOIActivity.this).load(response.body().getGambar())
                                .into(picturePOI, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {
                                        if (progressBar1 != null) {
                                            progressBar1.setVisibility(View.GONE); //gone progessbar ketika sudah complete
                                        }

                                    }

                                    @Override
                                    public void onError() {

                                    }
                                });

                        txtName.setText(response.body().getNmSubkomunitas());
                        txtAddress.setText(response.body().getAlamat());
                        //Costum Font
                        Typeface myCostumFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
                        textDescription.setTypeface(myCostumFont);
                        //costum font
                        textDescription.setText(response.body().getKeterangan()+" ("+response.body().getSurveyor()+")");
                        //txtFare.setText(response.body().getFare());
                        lat_akhir = response.body().getLatSubkomunitas();
                        lon_akhir = response.body().getLonSubkomunitas();
                        textLastModified.setText("Diperbaharui: "+response.body().getTanggalupdate());
                        textLink.setText("Link: "+response.body().getUrl());

                        //Klik Url Sumber
                        if (!response.body().getUrl().equals("")){
                            textLink.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getUrl()));
                                    startActivity(intent);
                                }
                            });
                        }

                        //Set Title Bar
                        if (!response.body().getNmkomunitas().equals("")) {
                            setTitle("Detail "+response.body().getNmkomunitas());
                            //setTitle("Detail "+response.body().getNmkomunitas());
                            String test =response.body().getNmkomunitas();

                        }

                        //extra picture
//                        if (!response.body().getGambarextra().equals("")) {
//                            Picasso.with(DetailPOIActivity.this).load(response.body().getGambarextra()).into(extraPicture);
//                        }

                        //set text jarak,waktu,tiba
                        //init progess dialog
                        final ProgressDialog progressRoute = new ProgressDialog(DetailPOIActivity.this);
                        //tambahkan pesan
                        progressRoute.setMessage("Loading...");
                        progressRoute.show();

                        String url = "https://maps.googleapis.com/";
                        RestApi api = MyRetrofitClient.getInstaceRetrofit();

                        String origin = lat + "," + lon;
                        String dest = lat_akhir + "," + lon_akhir;
                        //get response base url route
                        Call<ResponseRoute> callRoute = api.getRoute(origin, dest);
                        callRoute.enqueue(new Callback<ResponseRoute>() {
                            @Override
                            public void onResponse(Call<ResponseRoute> call, Response<ResponseRoute> response) {
                                if (response.isSuccessful()) {
                                    //get response json array
                                    ArrayList<ResponseRoute.Kedua> i = response.body().getRoute();
                                    //jika tidak dapat route-nya
                                    if (i.size() == 0) {
                                        //informasikan undefine untuk jarak,waktu dan tiba
                                        //masukkan ke form input jarak
                                        textjarak.setText("undefined");
                                        //masukkan ke form input waktu
                                        textwaktu.setText("undefined");
                                        //masukkan ke form tiba
                                        texttiba.setText("undefined");

                                        //dialog sebelum melakukan logout
                                        AlertDialog.Builder alert = new AlertDialog.Builder(DetailPOIActivity.this);
                                        alert.setMessage("Jalur tidak ditemukan, silahkan cari jalur lain");
                                        //opsi Tidak
                                        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).show();

                                        //Clear Polyline Before
                                        //direction.clearPolyline();
                                    } else {
                                        //get response json object 'overview_polyline'
                                        // 0 karena array object-nya route hanya 1 array-nya
                                        ResponseRoute.Kedua.Poly a = i.get(0).getPoly();
                                        //get distance from API google
                                        ArrayList<ResponseRoute.Kedua.Jarak> b = i.get(0).getLegs();
                                        ResponseRoute.Kedua.Jarak.Distances jarak = b.get(0).getDistance();
                                        String distance = jarak.getText();
                                        //masukkan ke form input jarak
                                        textjarak.setText(distance);

                                        //get duration from API google
                                        ArrayList<ResponseRoute.Kedua.Jarak> c = i.get(0).getLegs();
                                        ResponseRoute.Kedua.Jarak.Durations durasi = c.get(0).getDuration();
                                        String duration = durasi.getText();
                                        //masukkan ke form input waktu
                                        textwaktu.setText(duration);

                                        //menentukan tiba
                                        Calendar cc = Calendar.getInstance();
                                        //add duration value dengan datetime
                                        //untuk mendapatkan waktu tiba
                                        Double durvaltemp = durasi.getValue();
                                        int durval = Integer.valueOf(durvaltemp.intValue());
                                        cc.add(Calendar.SECOND, durval);
                                        int mDate = cc.get(Calendar.DATE);
                                        int mMount = cc.get(Calendar.MONTH) + 1;
                                        int mYear = cc.get(Calendar.YEAR);
                                        int mHour = cc.get(Calendar.HOUR_OF_DAY);
                                        int mMinute = cc.get(Calendar.MINUTE);
                                        int mSecond = cc.get(Calendar.SECOND);
                                        texttiba.setText(String.valueOf(String.format("%02d/%02d/%02d  %02d:%02d:%02d", mDate, mMount, mYear, mHour, mMinute, mSecond)));
                                    }


                                }
                                progressRoute.dismiss();//mematikan progress, karena sudah ke tahap response

                            }

                            @Override
                            public void onFailure(Call<ResponseRoute> call, Throwable t) {
                                Log.d("Signal Gagal ", " Gagal Response Parsing Data Json dari URL");
                            }
                        });


                        //Manage Weather
                        String urlWeather = getString(R.string.url_api_waether);
                        String q = "select * from weather.forecast where woeid in (SELECT woeid FROM geo.places WHERE text=\"(" + lat_akhir + "," + lon_akhir + ")\") AND u='c'";
                        String format = "json";

                        //init retrofit
                        Retrofit retrofit2 = new Retrofit.Builder()
                                .baseUrl(urlWeather).addConverterFactory(GsonConverterFactory.create())
                                .build();
                        //init api service
                        RestApi api2 = MyRetrofitClient.getInstaceRetrofit();

                        //get response base url route
                        Call<ResponseWeather> call2 = api2.getWeather(q, format);
                        call2.enqueue(new Callback<ResponseWeather>() {
                            @Override
                            public void onResponse(Call<ResponseWeather> call2, Response<ResponseWeather> response2) {
                                if (response2.isSuccessful()) {

                                    ResponseWeather.Query qrs = response2.body().getQuery();
                                    ResponseWeather.Results rst = qrs.getResults();
                                    ResponseWeather.Channel chl = rst.getChannel();
                                    ResponseWeather.Item itm = chl.getItem();
                                    ResponseWeather.Condition con = itm.getCondition();
                                    String date = con.getDate();
                                    String temp = con.getTemp();
                                    String text = con.getText();
                                    String code =con.getCode();

                                    textDate.setText(date);
                                    //textTemp.setText(temp);
                                    textWeather.setText(temp + "°C, " + text);
                                    //HeroHelper.pesan(ctx,date+"=="+temp+"=="+text);

                                    //load icon weather
                                    //HeroHelper.pesan(ctx,getString(R.string.cdn_url)+"cuaca/"+code+".png");
                                    Picasso.with(DetailPOIActivity.this).load(getString(R.string.cdn_url)+"cuaca/"+code+".png").into(iconWeather);

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseWeather> call, Throwable t) {
                                Log.d("Signal Gagal ", " Gagal Response Parsing Data Json dari URL");

                            }
                        });

                        //End Manage Weather

                    }
                }
            }

            @Override
            public void onFailure(Call<ModelSubKomunitas> call, Throwable t) {
                Log.d("Signal Gagal ", " Gagal Response Parsing Data Json dari URL");
            }
        });


    }

    private void lokasiKu() {
        //apabila ada masalah dalam menemukan lokasi dibeberapa device, misall xiomy
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 120
            ); //120 adalah sebagai signal untuk dicek di methode onRequestPermissionsResult
        }
        if (gpsTracker.canGetLocation()) { //jika aktive GPS artinya bisa dapat get lokasi
            //get value coordinate
            lat = String.valueOf(gpsTracker.getLatitude());
            lon = String.valueOf(gpsTracker.getLongitude());
        } else {
        mytoast("gps is not activated");
        }
    }

    @OnClick({R.id.share, R.id.navigate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share:
                shareLocSocialMedia();
                break;
            case R.id.navigate:
                redirectNavigation();
                break;
        }
    }

    private void shareLocSocialMedia() {
        String alamat= convertNameAddress(Double.valueOf(lat_akhir), Double.valueOf(lon_akhir));
        //String uri = "http://maps.google.com/maps?q=" +alamat;
        String uri = "http://maps.google.com/maps?q=" +lat_akhir+","+lon_akhir;
        //String uri = "http://maps.google.com/maps?saddr=" + lat + "," + lon + "&daddr=" + lat_akhir + "," + lon_akhir;
        String ShareSub = "Ini Posisinya";

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, ShareSub);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, uri+"  ("+alamat+")");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    //Riderect to Maps Google Navigation
    private void redirectNavigation() {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + lat + "," + lon + "&daddr=" + lat_akhir + "," + lon_akhir + ""));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }
}
