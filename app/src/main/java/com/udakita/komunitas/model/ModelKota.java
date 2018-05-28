package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ModelKota{

	@SerializedName("DataKota")
	private List<DataKota> dataKota;

	public void setDataKota(List<DataKota> dataKota){
		this.dataKota = dataKota;
	}

	public List<DataKota> getDataKota(){
		return dataKota;
	}
}