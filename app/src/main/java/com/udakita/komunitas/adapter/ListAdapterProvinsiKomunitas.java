package com.udakita.komunitas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.udakita.komunitas.R;
import com.udakita.komunitas.activity.DaftarKotaActivity;
import com.udakita.komunitas.model.DataProvinsi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by leonardo.siagian on 5/29/2017.
 */

public class ListAdapterProvinsiKomunitas extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<DataProvinsi> daerahpoilist = null;
    private ArrayList<DataProvinsi> arraylist;
    private String catid;

    public ListAdapterProvinsiKomunitas(Context context, List<DataProvinsi> daerahpoilist, String catid) {
        mContext = context;
        this.daerahpoilist = daerahpoilist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<DataProvinsi>();
        this.arraylist.addAll(daerahpoilist);
        this.catid=catid;
    }

    public class ViewHolder {
        TextView id;
        TextView name;
        TextView provincename;
        ImageView logo;

    }

    @Override
    public int getCount() {
        return daerahpoilist.size();
    }

    @Override
    public DataProvinsi getItem(int position) {
        return daerahpoilist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ListAdapterProvinsiKomunitas.ViewHolder holder;
        if (view == null) {
            holder = new ListAdapterProvinsiKomunitas.ViewHolder();
            view = inflater.inflate(R.layout.listview_daerah_item, null);
            // Locate the TextViews in listview_item.xml
            holder.id = (TextView) view.findViewById(R.id.idDaerah);
            holder.name = (TextView) view.findViewById(R.id.namaDaerah);
            holder.provincename = (TextView) view.findViewById(R.id.provinsiDaerah);
            holder.logo = (ImageView) view.findViewById(R.id.logo);
            view.setTag(holder);
        } else {
            holder = (ListAdapterProvinsiKomunitas.ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.id.setText(daerahpoilist.get(position).getNama());
        holder.name.setText(daerahpoilist.get(position).getNama());
        holder.provincename.setText(daerahpoilist.get(position).getNama());


         /*
            Referensi : https://github.com/codepath/android_guides/wiki/Displaying-Images-with-the-Picasso-Library
         */
        //progessbar
//        final ProgressBar mProgresBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        //set color progress bar  --- blue
//        mProgresBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
//        mProgresBar.setVisibility(View.VISIBLE);
//        Picasso.with(mContext).load(daerahpoilist.get(position).getLogo())
//                .into(holder.logo, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        if (mProgresBar!=null){
//                            mProgresBar.setVisibility(View.GONE); //gone progessbar ketika sudah complete
//                        }
//
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });


        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                //1:kuliner , 2:wisata , 3: penginapan

                Intent intent = null;
                     intent = new Intent(mContext, DaftarKotaActivity.class);


                if (catid.equals("1")){
             //       intent = new Intent(mContext, SubPOIKulinerActivity.class);
                }
                else  if (catid.equals("2")){
               //     intent = new Intent(mContext, DaftarKotaActivity.class);
                }else {
                 //   intent = new Intent(mContext, SubPOIPenginapanActivity.class);
                }

                // Pass all data population
                intent.putExtra("catid",catid);
                intent.putExtra("name",daerahpoilist.get(position).getNama());
                intent.putExtra("id",daerahpoilist.get(position).getIdProv());
                // Start SingleItemView Class
                mContext.startActivity(intent);

                /*HeroHelper.pesan(mContext,"ID: "+daerahpoilist.get(position).getId()
                        +" Name: "+ daerahpoilist.get(position).getName()
                        +" ProvinsiName: "+daerahpoilist.get(position).getProvincename());*/

            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        daerahpoilist.clear();
        if (charText.length() == 0) {
            daerahpoilist.addAll(arraylist);
        }
        else
        {
            for (DataProvinsi wp : arraylist)
            {
                if (wp.getNama().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    daerahpoilist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
