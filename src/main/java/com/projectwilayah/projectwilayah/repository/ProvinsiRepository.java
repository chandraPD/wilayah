package com.projectwilayah.projectwilayah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectwilayah.projectwilayah.entity.ProvinsiEntity;

@Repository
public interface ProvinsiRepository extends JpaRepository<ProvinsiEntity, Integer> {
	
	@Query(value = "select id from provinsi_entity where id = ?", nativeQuery = true)
	Integer getById(Integer id);
	
	@Query(value = "select kode_provinsi from provinsi_entity where kode_provinsi = ?", nativeQuery = true)
	String findByKode(String kodeProvinsi);
	
	@Query(value = "select nama_provinsi from provinsi_entity where nama_provinsi = ?", nativeQuery = true)
	String findByNama(String namaProvinsi);
	
//	@Query(value = "select * from provinsi_entity where kode_provinsi = ?", nativeQuery = true)
	ProvinsiEntity findByKodeProvinsiIgnoreCase(String kodeProvinsi);


	
}
