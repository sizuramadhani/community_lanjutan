package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;


public class DataKomunitasItem{

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("id_komunitas")
	private String idKomunitas;

	@SerializedName("id_kota")
	private String idKota;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("id_prov")
	private String idProv;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_jenis")
	private String idJenis;

	@SerializedName("lon_komunitas")
	private String lonKomunitas;

	@SerializedName("marker")
	private String marker;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("id")
	private String id;

	@SerializedName("lat_komunitas")
	private String latKomunitas;

	@SerializedName("nm_komunitas")
	private String nmKomunitas;

	@SerializedName("alamat")
	private String alamat;

	public DataKomunitasItem(String s, String s1, String s2, String s3, String s4, String idkomunita, String idjeni) {

		id=s;
		nmKomunitas=s1;
		alamat=s2;
		gambar =s3;
		marker=s4;
		idKomunitas=idkomunita;
		idJenis=idjeni;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setIdKomunitas(String idKomunitas){
		this.idKomunitas = idKomunitas;
	}

	public String getIdKomunitas(){
		return idKomunitas;
	}

	public void setIdKota(String idKota){
		this.idKota = idKota;
	}

	public String getIdKota(){
		return idKota;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	public void setIdProv(String idProv){
		this.idProv = idProv;
	}

	public String getIdProv(){
		return idProv;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setIdJenis(String idJenis){
		this.idJenis = idJenis;
	}

	public String getIdJenis(){
		return idJenis;
	}

	public void setLonKomunitas(String lonKomunitas){
		this.lonKomunitas = lonKomunitas;
	}

	public String getLonKomunitas(){
		return lonKomunitas;
	}

	public void setMarker(String marker){
		this.marker = marker;
	}

	public String getMarker(){
		return marker;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setLatKomunitas(String latKomunitas){
		this.latKomunitas = latKomunitas;
	}

	public String getLatKomunitas(){
		return latKomunitas;
	}

	public void setNmKomunitas(String nmKomunitas){
		this.nmKomunitas = nmKomunitas;
	}

	public String getNmKomunitas(){
		return nmKomunitas;
	}
}