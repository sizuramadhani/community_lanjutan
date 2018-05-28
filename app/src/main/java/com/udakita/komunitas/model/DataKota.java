package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;


public class DataKota {

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_jenis")
	private String idJenis;

	@SerializedName("id")
	private String id;

	@SerializedName("id_prov")
	private String idProv;

	public DataKota(String s, String s1) {

		this.id=s;
		this.nama=s1;
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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIdProv(String idProv){
		this.idProv = idProv;
	}

	public String getIdProv(){
		return idProv;
	}
}