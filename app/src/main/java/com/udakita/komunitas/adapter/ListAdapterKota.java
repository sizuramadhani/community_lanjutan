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
import com.udakita.komunitas.activity.SubPOIWisataActivity;
import com.udakita.komunitas.model.DataKota;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by leonardo.siagian on 5/29/2017.
 */

public class ListAdapterKota extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<DataKota> subpoilist = null;
    private ArrayList<DataKota> arraylist;
    private String id;

    public ListAdapterKota(Context context, List<DataKota> subpoilist, String id) {
        mContext = context;
        this.subpoilist = subpoilist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<DataKota>();
        this.arraylist.addAll(subpoilist);
        this.id=id;
    }

    public class ViewHolder {
        TextView id;
        TextView name;
        TextView address;
        ImageView picture;

    }

    @Override
    public int getCount() {
        return subpoilist.size();
    }

    @Override
    public DataKota getItem(int position) {
        return subpoilist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ListAdapterKota.ViewHolder holder;
        if (view == null) {
            holder = new ListAdapterKota.ViewHolder();
            view = inflater.inflate(R.layout.listview_subpoi_item, null);
            // Locate the TextViews in listview_item.xml
            holder.id = (TextView) view.findViewById(R.id.idPOI);
            holder.name = (TextView) view.findViewById(R.id.namaPOI);
            holder.address = (TextView) view.findViewById(R.id.addressPOI);
            holder.picture = (ImageView) view.findViewById(R.id.logoPOI);
            view.setTag(holder);
        } else {
            holder = (ListAdapterKota.ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.id.setText(id);
        holder.name.setText(subpoilist.get(position).getNama());


        /*
            Referensi : https://github.com/codepath/android_guides/wiki/Displaying-Images-with-the-Picasso-Library
         */
        //progessbar
//        final ProgressBar mProgresBar = (ProgressBar) view.findViewById(R.id.progressBar1);
//        //set color progress bar  --- blue
//        mProgresBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
//        mProgresBar.setVisibility(View.VISIBLE);
//        Picasso.with(mContext).load(subpoilist.get(position).getPicture())
//                .into(holder.picture, new Callback() {
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
                Intent intent = new Intent(mContext, SubPOIWisataActivity.class);
                // Pass all data population
                intent.putExtra("id",subpoilist.get(position).getId());
                intent.putExtra("name",subpoilist.get(position).getNama());
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
        subpoilist.clear();
        if (charText.length() == 0) {
            subpoilist.addAll(arraylist);
        }
        else
        {
            for (DataKota wp : arraylist)
            {
                if (wp.getNama().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    subpoilist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
