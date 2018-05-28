package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;


public class ModelSubKomunitas{

	@SerializedName("lon_subkomunitas")
	private String lonSubkomunitas;

	@SerializedName("fare")
	private String fare;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("gambarextra")
	private String gambarextra;

	@SerializedName("surveyor")
	private String surveyor;

	@SerializedName("idkomunitas")
	private String idkomunitas;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("tanggalupdate")
	private String tanggalupdate;

	@SerializedName("url")
	private String url;

	@SerializedName("nm_subkomunitas")
	private String nmSubkomunitas;

	@SerializedName("nmkomunitas")
	private String nmkomunitas;

	@SerializedName("id_subkomunitas")
	private String idSubkomunitas;

	@SerializedName("lat_subkomunitas")
	private String latSubkomunitas;

	public void setLonSubkomunitas(String lonSubkomunitas){
		this.lonSubkomunitas = lonSubkomunitas;
	}

	public String getLonSubkomunitas(){
		return lonSubkomunitas;
	}

	public void setFare(String fare){
		this.fare = fare;
	}

	public String getFare(){
		return fare;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setGambarextra(String gambarextra){
		this.gambarextra = gambarextra;
	}

	public String getGambarextra(){
		return gambarextra;
	}

	public void setSurveyor(String surveyor){
		this.surveyor = surveyor;
	}

	public String getSurveyor(){
		return surveyor;
	}

	public void setIdkomunitas(String idkomunitas){
		this.idkomunitas = idkomunitas;
	}

	public String getIdkomunitas(){
		return idkomunitas;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	public void setTanggalupdate(String tanggalupdate){
		this.tanggalupdate = tanggalupdate;
	}

	public String getTanggalupdate(){
		return tanggalupdate;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setNmSubkomunitas(String nmSubkomunitas){
		this.nmSubkomunitas = nmSubkomunitas;
	}

	public String getNmSubkomunitas(){
		return nmSubkomunitas;
	}

	public void setNmkomunitas(String nmkomunitas){
		this.nmkomunitas = nmkomunitas;
	}

	public String getNmkomunitas(){
		return nmkomunitas;
	}

	public void setIdSubkomunitas(String idSubkomunitas){
		this.idSubkomunitas = idSubkomunitas;
	}

	public String getIdSubkomunitas(){
		return idSubkomunitas;
	}

	public void setLatSubkomunitas(String latSubkomunitas){
		this.latSubkomunitas = latSubkomunitas;
	}

	public String getLatSubkomunitas(){
		return latSubkomunitas;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
}