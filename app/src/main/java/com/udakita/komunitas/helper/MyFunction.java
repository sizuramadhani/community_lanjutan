package com.udakita.komunitas.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyFunction extends AppCompatActivity {

    public Context c;
    protected GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = MyFunction.this;
        gpsTracker = new GPSTracker(c);

    }

    public void isGpsOn(){
        if (!gpsTracker.canGetLocation()) {
            showSettingGps(c); //aktivekan GPS
        }
    }

    public static void showSettingGps(final Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

        alertBuilder.setTitle("GPS Setting");
        alertBuilder
                .setMessage("GPS is not enabled. Do you want to go to settings location ON ?");

        alertBuilder.setPositiveButton("Setting",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                    }
                });
        alertBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
        alertBuilder.show();
    }



    public void myIntent(Class classtujuan) {
        startActivity(new Intent(c, classtujuan));
    }


    public void mytoast(String isipesan) {
        Toast.makeText(c, isipesan, Toast.LENGTH_SHORT).show();
    }

    public static String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String convertNameAddress(Double lat, Double lon) {
        /*
            Untuk convert dari koordinat jadi nama dengan menggunakan GEOCODER
         */
        String name = "Unknown Address";
        //init geocoder
        Geocoder geocoder = new Geocoder(c, Locale.getDefault());

        //get list address/nama alamat
        try {
            if (lat!=null && lon!=null){
                List<Address> listAddress = geocoder.getFromLocation(lat, lon, 1); //karena alamat yang akan didapat adalah 1 , karena lat dan lon cuma 1 diterima methode
                if (listAddress!=null){
                    if (listAddress.get(0)!=null){
                        if (listAddress.get(0).getAddressLine(0)!=null){
                            name = listAddress.get(0).getAddressLine(0); //0 maximum karena value parameter di listAddress 1, maka array-nya max 1 , array 0
                        }else {

                            name="Unknown Address";
                        }
                    }
                }else {

                    name="Unknown Address";
                }
            }else{
                name="Unknown Address";
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;

    }

    public static String timeConversion(int totalSeconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
        int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
        int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
        int hours = totalMinutes / MINUTES_IN_AN_HOUR;

        if (hours == 0) {
            return minutes + " minutes";
        } else {
            return hours + " hours " + minutes + " minutes ";
        }
        // return hours + " hours " + minutes + " minutes " + seconds +
        // " seconds";

    }

}
