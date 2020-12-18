package com.projectwilayah.projectwilayah.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kecamatan_entity")
public class KecamatanEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "kode_kecamatan", unique = true, length = 25, nullable = false)
	private String kodeKecamatan;
	
	@Column(name = "nama_kecamatan", length = 50, nullable = false)
	private String namaKecamatan;
	
	@ManyToOne
	@JoinColumn(name = "kode_kabupaten", referencedColumnName = "kode_kabupaten")
	private KabupatenEntity kabupatenEntity;
	
	@ManyToOne
	@JoinColumn(name ="kode_provinsi", referencedColumnName = "kode_provinsi")
	private ProvinsiEntity provinsiEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKodeKecamatan() {
		return kodeKecamatan;
	}

	public void setKodeKecamatan(String kodeKecamatan) {
		this.kodeKecamatan = kodeKecamatan;
	}

	public String getNamaKecamatan() {
		return namaKecamatan;
	}

	public void setNamaKecamatan(String namaKecamatan) {
		this.namaKecamatan = namaKecamatan;
	}

	public KabupatenEntity getKabupatenEntity() {
		return kabupatenEntity;
	}

	public void setKabupatenEntity(KabupatenEntity kabupatenEntity) {
		this.kabupatenEntity = kabupatenEntity;
	}

	public ProvinsiEntity getProvinsiEntity() {
		return provinsiEntity;
	}

	public void setProvinsiEntity(ProvinsiEntity provinsiEntity) {
		this.provinsiEntity = provinsiEntity;
	}

	public KecamatanEntity(Integer id, String kodeKecamatan, String namaKecamatan, KabupatenEntity kabupatenEntity,
			ProvinsiEntity provinsiEntity) {
		super();
		this.id = id;
		this.kodeKecamatan = kodeKecamatan;
		this.namaKecamatan = namaKecamatan;
		this.kabupatenEntity = kabupatenEntity;
		this.provinsiEntity = provinsiEntity;
	}

	public KecamatanEntity() {
		super();
	}

}
