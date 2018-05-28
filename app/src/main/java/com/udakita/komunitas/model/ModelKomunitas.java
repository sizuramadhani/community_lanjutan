package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelKomunitas{

	@SerializedName("DataKomunitas")
	private List<DataKomunitasItem> dataKomunitas;

	public void setDataKomunitas(List<DataKomunitasItem> dataKomunitas){
		this.dataKomunitas = dataKomunitas;
	}

	public List<DataKomunitasItem> getDataKomunitas(){
		return dataKomunitas;
	}
}