package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;


public class DataProvinsi {

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_prov")
	private String idProv;

	public DataProvinsi(String id, String name) {
		this.idProv=id;
		this.nama = name;
//		this.provincename = provincename;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setIdProv(String idProv){
		this.idProv = idProv;
	}

	public String getIdProv(){
		return idProv;
	}
}