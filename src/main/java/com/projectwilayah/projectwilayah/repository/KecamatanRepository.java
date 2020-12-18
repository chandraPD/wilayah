package com.projectwilayah.projectwilayah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.projectwilayah.projectwilayah.entity.KecamatanEntity;

@Repository
public interface KecamatanRepository extends JpaRepository<KecamatanEntity, Integer>{
	
	@Query(value = "select id from kecamatan_entity where id = ?", nativeQuery = true)
	Integer getById(Integer id);
	
	@Query(value = "select kode_kecamatan from kecamatan_entity where kode_kecamatan = ?", nativeQuery = true)
	String findByKode(String kodeKecamatan);
	
	@Query(value = "select nama_kecamatan from kecamatan_entity where nama_kecamatan = ?", nativeQuery = true)
	String findByNama(String namaKecamatan);
	
	KecamatanEntity findByKodeKecamatanIgnoreCase(String kodeKecamatan);

}
