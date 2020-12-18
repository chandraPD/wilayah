package com.projectwilayah.projectwilayah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectwilayah.projectwilayah.entity.KabupatenEntity;

@Repository
public interface KabupatenRepository extends JpaRepository<KabupatenEntity, Integer>{
	
	@Query(value = "select id from kabupaten_entity where id = ?", nativeQuery = true)
	Integer getById(Integer id);
	
	@Query(value = "select kode_kabupaten from kabupaten_entity where kode_kabupaten = ?", nativeQuery = true)
	String findByKode(String kodeKabupaten);
	
	@Query(value = "select nama_kabupaten from kabupaten_entity where nama_kabupaten = ?", nativeQuery = true)
	String findByNama(String namaKabupaten);
	

	KabupatenEntity findByKodeKabupatenIgnoreCase(String kodeKabupaten);
}
