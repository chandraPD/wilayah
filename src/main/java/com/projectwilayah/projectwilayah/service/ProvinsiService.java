package com.projectwilayah.projectwilayah.service;

import java.util.List;

import com.projectwilayah.projectwilayah.dto.ProvinsiDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.ProvinsiEntity;

public interface ProvinsiService {
	StatusMessageDto<?> save(ProvinsiDto dto);
	StatusMessageDto<?> update(ProvinsiDto dto, Integer idProvinsi);
	StatusMessageDto<?> delete(Integer idProvinsi);
	StatusMessageDto<?> getById(Integer idProvinsi);
	StatusMessageDto<?> getByKode(String kodeProvinsi);

	List<ProvinsiEntity> getAll();
}
