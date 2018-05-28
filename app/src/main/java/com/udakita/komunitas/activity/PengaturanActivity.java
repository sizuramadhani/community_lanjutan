package com.udakita.komunitas.activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.udakita.komunitas.R;
import com.udakita.komunitas.helper.MyFunction;
import com.udakita.komunitas.helper.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PengaturanActivity extends MyFunction {

    /*@BindView(R.id.notifSwitch)
    Switch notifSwitch;*/
    @BindView(R.id.radiusSeekBar)
    SeekBar radiusSeekBar;
    @BindView(R.id.infoRadius)
    TextView infoRadius;
    @BindView(R.id.trafficSwitch)
    Switch trafficSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_arrow_left);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //ON OFF Traffic
        final SessionManager sesi = new SessionManager();
        if (sesi.getTraffic().equals("Yes")) {
            trafficSwitch.setChecked(true);
        } else {
            trafficSwitch.setChecked(false);
        }

        //Switch Action
        trafficSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String statusTraffic;
                if (isChecked == true) {
                    statusTraffic = trafficSwitch.getTextOn().toString();
                    //save notif status to session
                    sesi.setTraffic(statusTraffic);
                } else {
                    statusTraffic = trafficSwitch.getTextOff().toString();
                    //save notif status to session
                    sesi.setTraffic(statusTraffic);
                }

            }
        });



        /*
            Switch ON/OF
         */
        /*
        //set switch ON/OFF from session
        if (sesi.getNotifikasi().equals("Yes")) {
            //HeroHelper.pesan(ctx,"Y: "+sesi.getNotifikasi());
            notifSwitch.setChecked(true);
        } else {
            //HeroHelper.pesan(ctx,"N: "+sesi.getNotifikasi());
            notifSwitch.setChecked(false);
        }

        //Switch Action
        notifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Log.v("Switch State=", ""+isChecked);
                String statusNotifSwitch;
                if (isChecked == true) {
                    statusNotifSwitch = notifSwitch.getTextOn().toString();
                    //save notif status to session
                    sesi.setNotifikasi(statusNotifSwitch);
                    //HeroHelper.pesan(ctx, statusNotifSwitch);
                    //HeroHelper.pesan(ctx, "Sesi Notif adalah  :" + sesi.getNotifikasi());
                } else {
                    statusNotifSwitch = notifSwitch.getTextOff().toString();
                    //save notif status to session
                    sesi.setNotifikasi(statusNotifSwitch);
                    //HeroHelper.pesan(ctx, statusNotifSwitch);
                    //HeroHelper.pesan(ctx, "Sesi Notif adalah  :" + sesi.getNotifikasi());
                }

            }
        });
        */


        /*
            Radius
         */
        //show info radius
        infoRadius.setText(sesi.getRadius());

        //set latest progress progress
        radiusSeekBar.setProgress(Integer.parseInt(sesi.getRadius()));

        //slider radius
        radiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                //directly show progress value
                infoRadius.setText(String.valueOf(progressChangedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //HeroHelper.pesan(ctx, String.valueOf(progressChangedValue));
                //save in session
                sesi.setRadius(String.valueOf(progressChangedValue));
            }
        });

        /*
            tombol back kembali ke sebelumya/parent
            - contoh set di AndroidManifest : android:parentActivityName=".Activity.MainActivity"
         */
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}
