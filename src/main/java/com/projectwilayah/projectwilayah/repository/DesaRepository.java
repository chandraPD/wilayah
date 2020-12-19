package com.projectwilayah.projectwilayah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectwilayah.projectwilayah.entity.DesaEntity;

@Repository
public interface DesaRepository extends JpaRepository<DesaEntity, Integer>{
	
	@Query(value = "select id from desa_entity where id = ?", nativeQuery = true)
	Integer getById(Integer id);
	
	@Query(value = "select kode_desa from desa_entity where kode_desa = ?", nativeQuery = true)
	String findByKode(String kodeDesa);
	
	@Query(value = "select nama_desa from desa_entity where nama_desa = ?", nativeQuery = true)
	String findByNama(String namaDesa);
	
	DesaEntity findByKodeDesaIgnoreCase(String kodeDesa);

}
