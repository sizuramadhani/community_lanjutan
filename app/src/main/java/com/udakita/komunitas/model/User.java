package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;


public class User{

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("kota_nama_kota")
	private String kotaNamaKota;

	@SerializedName("level")
	private String level;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("jenkel")
	private String jenkel;

	@SerializedName("url")
	private String url;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("username")
	private String username;

	@SerializedName("tanggal_bergabung")
	private String tanggalBergabung;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setKotaNamaKota(String kotaNamaKota){
		this.kotaNamaKota = kotaNamaKota;
	}

	public String getKotaNamaKota(){
		return kotaNamaKota;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setNoTelp(String noTelp){
		this.noTelp = noTelp;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setJenkel(String jenkel){
		this.jenkel = jenkel;
	}

	public String getJenkel(){
		return jenkel;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setTanggalBergabung(String tanggalBergabung){
		this.tanggalBergabung = tanggalBergabung;
	}

	public String getTanggalBergabung(){
		return tanggalBergabung;
	}
}