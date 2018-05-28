package com.udakita.komunitas.model;

import com.google.gson.annotations.SerializedName;

public class DataBerita {

	@SerializedName("penulis")
	private String penulis;

	@SerializedName("foto")
	private String foto;

	@SerializedName("sumber")
	private String sumber;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("judul_berita")
	private String judulBerita;

	@SerializedName("tanggal_posting")
	private String tanggalPosting;

	@SerializedName("isi_berita")
	private String isiBerita;

	public void setPenulis(String penulis){
		this.penulis = penulis;
	}

	public String getPenulis(){
		return penulis;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setSumber(String sumber){
		this.sumber = sumber;
	}

	public String getSumber(){
		return sumber;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setJudulBerita(String judulBerita){
		this.judulBerita = judulBerita;
	}

	public String getJudulBerita(){
		return judulBerita;
	}

	public void setTanggalPosting(String tanggalPosting){
		this.tanggalPosting = tanggalPosting;
	}

	public String getTanggalPosting(){
		return tanggalPosting;
	}

	public void setIsiBerita(String isiBerita){
		this.isiBerita = isiBerita;
	}

	public String getIsiBerita(){
		return isiBerita;
	}
}