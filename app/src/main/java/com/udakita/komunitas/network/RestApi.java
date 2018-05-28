package com.udakita.komunitas.network;

import com.udakita.komunitas.model.ModelBerita;
import com.udakita.komunitas.model.ModelKomunitas;
import com.udakita.komunitas.model.ModelKota;
import com.udakita.komunitas.model.ModelProvinsi;
import com.udakita.komunitas.model.ModelSubKomunitas;
import com.udakita.komunitas.model.ModelUser;
import com.udakita.komunitas.model.ResponseRoute;
import com.udakita.komunitas.model.ResponseWeather;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Blackswan on 9/12/2017.
 */

public interface RestApi {


    @FormUrlEncoded
    @POST("registeruser.php/")
    Call<ModelUser> registerUser(
            @Field("vsnama") String strnama,
            @Field("vsalamat") String stralamat,
            @Field("vsnotelp") String strnotelp,
            @Field("vsjenkel") String strjenkel,
            @Field("vsusername") String strusername,
            @Field("vspassword") String strpassword,
            @Field("vslevel") String strlevel,
            @Field("vstanggalgabung") String strtanggalbergabung
    );

    @FormUrlEncoded
    @POST("loginuser.php/")
    Call<ModelUser> loginUser(
            @Field("edtusername") String strusername,
            @Field("edtpassword") String strpassword,
            @Field("vslevel") String strlevel
    );

//    @FormUrlEncoded
//    @POST("getdatamakanan.php/")
//    Call<ModelMakanan> getdatamakanan(
//            @Field("vsiduser") String striduser,
//            @Field("vsidkastrkategorimakanan") String strkartmakaan
//    );
//
//    @FormUrlEncoded
//    @POST("deletedatamakanan.php/")
//    Call<ModelUser> deletedata(
//            @Field("vsidmakanan") String stridmakanan
//    );
//
//
//    @GET("ambildataCarikategorimakanan.php/")
//    public Call<ModelKategoriMakanan> getDataCarikategoriMakanan();
//
//    @Multipart
//    @POST("uploadmakanan1.php")
//    Call<ServerResponse> uploadFile(@Part MultipartBody.Part file, @Part("image") RequestBody name);


    //@TIPEMETHOD("API_END_POINT")
    @GET("tampil_berita.php")
    Call<ModelBerita> request_show_all_berita();
    // <ModelData> nama_method()


    @GET("kategoriprovinsi.php")
    Call<ModelProvinsi> getProvinsi(
//            @Query("catid") String catid,
//            @Query("key") String key
    );
    @FormUrlEncoded
    @POST("kategorikota.php")
    Call<ModelKota> getKota(
            @Field("idprov") String idprov
    );

    @FormUrlEncoded
    @POST("listkomunitas.php")
    Call<ModelKomunitas> getkomunitas(
            @Field("idkota") String idkota
            );
    @FormUrlEncoded
    @POST("dataperkomunitas.php")
    Call<ModelSubKomunitas> getsubkomunitas(
            @Field("idkategorikomunitas") String id
    );

    @GET("maps/api/directions/json")
    Call<ResponseRoute> getRoute(
            @Query("origin") String origin,
            @Query("destination") String tujuan
    );

    @GET("v1/public/yql")
    Call<ResponseWeather> getWeather(
            @Query("q") String q,
            @Query("format") String format
    );

}
