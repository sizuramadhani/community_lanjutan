package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelProvinsi{

	@SerializedName("DataProvinsi")
	private List<DataProvinsi> dataProvinsi;

	public void setDataProvinsi(List<DataProvinsi> dataProvinsi){
		this.dataProvinsi = dataProvinsi;
	}

	public List<DataProvinsi> getDataProvinsi(){
		return dataProvinsi;
	}
}