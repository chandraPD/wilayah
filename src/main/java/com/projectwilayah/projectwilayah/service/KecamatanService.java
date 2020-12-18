package com.projectwilayah.projectwilayah.service;

import java.util.List;

import com.projectwilayah.projectwilayah.dto.KecamatanDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.KecamatanEntity;

public interface KecamatanService {
	
	StatusMessageDto<?> save(KecamatanDto dto);
	StatusMessageDto<?> update(KecamatanDto dto, Integer idKecamatan);
	StatusMessageDto<?> delete(Integer idKecamatan);
	
	List<KecamatanEntity> getAll();
}
