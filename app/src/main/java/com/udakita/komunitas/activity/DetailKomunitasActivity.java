package com.udakita.komunitas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udakita.komunitas.R;

public class DetailKomunitasActivity extends AppCompatActivity {

    // Deklarasi
    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis;
    WebView wvKontenBerita;
    private String url_berita;
    Button btngabung,btnmap;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkomunitas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        // Jalankan method tampil detail berita
        fab = (FloatingActionButton) findViewById(R.id.fab);


        // Inisialisasi
        btngabung = (Button) findViewById(R.id.btnbergabung);
        btnmap = (Button) findViewById(R.id.btnmaps);
        ivGambarBerita = (ImageView) findViewById(R.id.ivGambarBerita);
        tvTglTerbit = (TextView) findViewById(R.id.tvTglTerbit);
        tvPenulis = (TextView) findViewById(R.id.tvPenulis);
        wvKontenBerita = (WebView) findViewById(R.id.wvKontenBerita);

        // Jalankan method tampil detail berita
        showDetailBerita(fab);

    }

    private void showDetailBerita(FloatingActionButton fab1) {
        // Tangkap data dari intent
        String judul_berita = getIntent().getStringExtra("JDL_BERITA");
        String tanggal_berita = getIntent().getStringExtra("TGL_BERITA");
        String penulis_berita = getIntent().getStringExtra("PNS_BERITA");
        String isi_berita = getIntent().getStringExtra("ISI_BERITA");
        String foto_berita = getIntent().getStringExtra("FTO_BERITA");
         url_berita = getIntent().getStringExtra("URL_BERITA");

        // Set judul actionbar / toolbar
        getSupportActionBar().setTitle(judul_berita);
       fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailKomunitasActivity.this,KomunitasActivity.class);
                i.putExtra("url",url_berita);
                startActivity(i);
            }
        });
        // Set ke widget
        tvPenulis.setText("Oleh : " + penulis_berita);
        tvTglTerbit.setText(tanggal_berita);
        // Untuk gambar berita
        Picasso.with(this).load(foto_berita).into(ivGambarBerita);
        // Set isi berita sebagai html ke WebView
        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
        wvKontenBerita.loadData(isi_berita, "text/html; charset=utf-8", "UTF-8");
    }
}
