package com.projectwilayah.projectwilayah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "desa_entity")
public class DesaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "kode_desa", unique = true, length = 50, nullable = false)
	private String kodeDesa;
	
	@Column(name = "nama_desa", length = 50, nullable = false)
	private String namaDesa;
	
	@ManyToOne
	@JoinColumn(name = "kode_kabupaten", referencedColumnName = "kode_kabupaten")
	private KabupatenEntity kabupatenEntity;
	
	@ManyToOne
	@JoinColumn(name = "kode_kecamatan", referencedColumnName = "kode_kecamatan")
	private KecamatanEntity kecamatanEntity;
	
	@ManyToOne
	@JoinColumn(name = "kode_provinsi", referencedColumnName = "kode_provinsi")
	private ProvinsiEntity provinsiEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKodeDesa() {
		return kodeDesa;
	}

	public void setKodeDesa(String kodeDesa) {
		this.kodeDesa = kodeDesa;
	}

	public String getNamaDesa() {
		return namaDesa;
	}

	public void setNamaDesa(String namaDesa) {
		this.namaDesa = namaDesa;
	}

	public KabupatenEntity getKabupatenEntity() {
		return kabupatenEntity;
	}

	public void setKabupatenEntity(KabupatenEntity kabupatenEntity) {
		this.kabupatenEntity = kabupatenEntity;
	}

	public KecamatanEntity getKecamatanEntity() {
		return kecamatanEntity;
	}

	public void setKecamatanEntity(KecamatanEntity kecamatanEntity) {
		this.kecamatanEntity = kecamatanEntity;
	}

	public ProvinsiEntity getProvinsiEntity() {
		return provinsiEntity;
	}

	public void setProvinsiEntity(ProvinsiEntity provinsiEntity) {
		this.provinsiEntity = provinsiEntity;
	}

	public DesaEntity(Integer id, String kodeDesa, String namaDesa, KabupatenEntity kabupatenEntity,
			KecamatanEntity kecamatanEntity, ProvinsiEntity provinsiEntity) {
		super();
		this.id = id;
		this.kodeDesa = kodeDesa;
		this.namaDesa = namaDesa;
		this.kabupatenEntity = kabupatenEntity;
		this.kecamatanEntity = kecamatanEntity;
		this.provinsiEntity = provinsiEntity;
	}

	public DesaEntity() {
		super();
	}

	

	
	
}
