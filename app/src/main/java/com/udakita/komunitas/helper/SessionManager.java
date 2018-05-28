package com.udakita.komunitas.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;

import com.udakita.komunitas.MainActivity;
import com.udakita.komunitas.activity.LoginActivity;


public class SessionManager extends MyFunction {
    @VisibleForTesting

    /*variable sharepreference*/
    SharedPreferences pref;

    public SharedPreferences.Editor editor;
    public SessionManager sessionManager;

    /*mode share preference*/
    int mode = 0;

    /*nama dari share preference*/
    private static final String pref_name = "crudpref";

    /*kunci share preference*/
    private static final String is_login = "islogin";
    public static final String kunci_email = "keyemail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(getApplicationContext());
   //     setTheme(R.style.MyAppTheme);

    }
    public SessionManager(){

    }

    /*construktor*/
    public SessionManager(Context context) {
        /*mengakses class ini*/
        c = context;
        /*share preference dari class ini*/ /*(nama, mode)*/
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    /*methode membuat session*/
    public void createSession(String email){
        /*login value menjadi true*/
        editor.putBoolean(is_login, true);
        /*memasukkan email ke dalam variable kunci email*/
        editor.putString(kunci_email, email);
        editor.commit();
    }
    public void setIdUser(String iduser) {
        editor.putBoolean(is_login, true);
        editor.putString("iduser", iduser);
        editor.commit();
    }

    public String getIdUser() {
        return pref.getString("iduser", "");
    }


    public void checkLogin(){
        /*jika is_login = false*/
        if (!this.islogin()){
            /*pergi ke loginactivity*/
            Intent i = new Intent(c, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        }else {
            /*jika true, pergi ke mainactivity*/
            Intent i = new Intent(c, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        }
    }

    /*set is_login menjadi false*/
    public boolean islogin() {
        return pref.getBoolean(is_login, false);
    }





    public void logout(){

        /*hapus semua data dan kunci*/
        editor.clear();
        editor.commit();

        //gmail logout


        /*pergi ke loginactivity*/
        Intent i = new Intent(c, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(i);
    }

     @Override
    public void onStop() {
        super.onStop();
       // hideProgressDialog();
    }



    public void setPhone(String phone){
        editor.putString("phone", phone);
        editor.commit();
    }
    public String getPhone(){
        return pref.getString("phone", "");
    }
    public void setIduser(String id_user){
        editor.putString("id_user", id_user);
        editor.commit();
    }

    public void setPohotUrl(String photo_url){
        editor.putString("photo_url", photo_url);
        editor.commit();
    }
    public String getPohotUrl(){
        return pref.getString("photo_url", "");
    }

    public void setGender(String gender){
        editor.putString("gender", gender);
        editor.commit();
    }
    public String getGender(){
        return pref.getString("gender", "");
    }

    public void setBirthDate(String birthdate){
        editor.putString("birthdate", birthdate);
        editor.commit();
    }
    public String getBirthDate(){
        return pref.getString("birthdate", "");
    }



    public void setNotifikasi(String status){
        editor.putString("notif", status);
        editor.commit();
    }
    public String getNotifikasi(){
        return pref.getString("notif", "");
    }

    public void setRadius(String status){
        editor.putString("radius", status);
        editor.commit();
    }
    public String getRadius(){
        return pref.getString("radius", "");
    }

    public void setTraffic(String status){
        editor.putString("traffic", status);
        editor.commit();
    }
    public String getTraffic(){
        return pref.getString("traffic", "");
    }



}
