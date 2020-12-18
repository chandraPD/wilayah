package com.projectwilayah.projectwilayah.service;

import java.util.List;

import com.projectwilayah.projectwilayah.dto.KabupatenDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.KabupatenEntity;

public interface KabupatenService {

	StatusMessageDto<?> save(KabupatenDto dto);
	StatusMessageDto<?> update(KabupatenDto dto, Integer idKabupaten);
	StatusMessageDto<?> delete(Integer idKabupaten);
	
	List<KabupatenEntity> getAll();
}
