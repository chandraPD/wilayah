package com.projectwilayah.projectwilayah.service;

import java.util.List;

import com.projectwilayah.projectwilayah.dto.DesaDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.DesaEntity;

public interface DesaService {
	
	
	StatusMessageDto<?> save(DesaDto dto);
	StatusMessageDto<?> update(DesaDto dto, Integer idDesa);
	StatusMessageDto<?> delete(Integer idDesa);
	StatusMessageDto<?> getById(Integer idDesa);
	StatusMessageDto<?> getByKode(String kodeDesa);
	List<DesaEntity> getAll();
}
