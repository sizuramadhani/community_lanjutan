package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelBerita{

	@SerializedName("berita")
	private List<DataBerita> berita;

	@SerializedName("status")
	private boolean status;

	public void setBerita(List<DataBerita> berita){
		this.berita = berita;
	}

	public List<DataBerita> getBerita(){
		return berita;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}