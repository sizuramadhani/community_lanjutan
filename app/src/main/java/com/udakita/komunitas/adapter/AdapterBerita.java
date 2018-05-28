package com.udakita.komunitas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udakita.komunitas.R;
import com.udakita.komunitas.activity.DetailKomunitasActivity;
import com.udakita.komunitas.model.DataBerita;

import java.util.List;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder>  {
    // Buat Global variable untuk manampung context
    Context context;
    List<DataBerita> berita;
    public AdapterBerita(Context context, List<DataBerita> data_berita) {
        // Inisialisasi
        this.context = context;
        this.berita = data_berita;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.komunitas_data, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.tvJudul.setText(berita.get(position).getJudulBerita());
        holder.tvTglTerbit.setText(berita.get(position).getTanggalPosting());
        holder.tvPenulis.setText(berita.get(position).getPenulis());

        // Dapatkan url gambar
        final String urlGambarBerita =  berita.get(position).getFoto();
//        final String urlGambarBerita =  API_URL+"images/"+berita.get(position).getFoto();

        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(berita.get(position).getFoto()).into(holder.ivGambarBerita);
//        Picasso.with(context).load(API_URL+"images/"+berita.get(position).getFoto()).into(holder.ivGambarBerita);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent varIntent = new Intent(context, DetailKomunitasActivity.class);
                // sisipkan data ke intent
                varIntent.putExtra("JDL_BERITA", berita.get(position).getJudulBerita());
                varIntent.putExtra("TGL_BERITA", berita.get(position).getTanggalPosting());
                varIntent.putExtra("PNS_BERITA", berita.get(position).getPenulis());
                varIntent.putExtra("FTO_BERITA", urlGambarBerita);
                varIntent.putExtra("ISI_BERITA", berita.get(position).getIsiBerita());
                varIntent.putExtra("URL_BERITA", berita.get(position).getUrl());

                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
                context.startActivity(varIntent);
            }
        });
    }
    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget
        ImageView ivGambarBerita;
        TextView tvJudul, tvTglTerbit, tvPenulis;
        public MyViewHolder(View itemView) {
            super(itemView);
            // inisialisasi widget
            ivGambarBerita = (ImageView) itemView.findViewById(R.id.ivPosterBerita);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudulBerita);
            tvTglTerbit = (TextView) itemView.findViewById(R.id.tvTglTerbit);
            tvPenulis = (TextView) itemView.findViewById(R.id.tvPenulis);
        }
    }
}
