package com.projectwilayah.projectwilayah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectwilayah.projectwilayah.dto.KabupatenDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.KabupatenEntity;
import com.projectwilayah.projectwilayah.entity.ProvinsiEntity;
import com.projectwilayah.projectwilayah.repository.KabupatenRepository;
import com.projectwilayah.projectwilayah.repository.ProvinsiRepository;

@Service
@Transactional
public class KabupatenServiceImpl implements KabupatenService {

	@Autowired
	private KabupatenRepository kabupatenRepository;

	@Autowired
	private ProvinsiRepository provinsiRepository;

	@Override
	public StatusMessageDto<?> save(KabupatenDto dto) {
		// TODO Auto-generated method stub
		StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();

		KabupatenEntity kabupatenEntity = convertToKabupatenEntity(dto);
		
		String checkNama = kabupatenRepository.findByNama(dto.getNamaKabupaten());
		String kodeKabupaten = kabupatenEntity.getProvinsiEntity().getKodeProvinsi()+"."+dto.getKodeKabupaten();
		String checkKode = kabupatenRepository.findByKode(kodeKabupaten);
		if (checkNama == null && checkKode == null) {

			kabupatenRepository.save(kabupatenEntity);
//			check jika berhasil
			if (kabupatenEntity.getId() != null) {
				result.setMessage("Data Kabupaten Berhasil diinputkan");
				result.setStatus(HttpStatus.OK.value());
				result.setData(kabupatenEntity);
			} else {
				result.setMessage("Data Kabupaten Gagal diinputkan");
				result.setStatus(HttpStatus.BAD_GATEWAY.value());
				result.setData(null);
			}

		} else {
			result.setStatus(HttpStatus.BAD_GATEWAY.value());
			if (checkKode != null) {
				result.setMessage("Gagal, Kode Kabupaten " + kodeKabupaten + " sudah ada");
			} else if (checkNama != null) {
				result.setMessage("Gagal, Nama Kabupaten " + dto.getNamaKabupaten() + " sudah ada");
			}

			result.setData(null);
		}

		return result;
	}

	@Override
	public StatusMessageDto<?> update(KabupatenDto dto, Integer idKabupaten) {
		// TODO Auto-generated method stub
		
		StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
		KabupatenEntity kabupatenEntity = kabupatenRepository.findById(idKabupaten).get();	
		String checkNama = kabupatenRepository.findByNama(dto.getNamaKabupaten());
		String kodeKabupaten = kabupatenEntity.getProvinsiEntity().getKodeProvinsi()+"."+dto.getKodeKabupaten();
		String checkKode = kabupatenRepository.findByKode(kodeKabupaten);
		if (checkNama == null && checkKode == null) {
			
//			check jika berhasil
			kabupatenEntity.setKodeKabupaten(kodeKabupaten);
			kabupatenEntity.setNamaKabupaten(dto.getNamaKabupaten());
			kabupatenRepository.save(kabupatenEntity);
			result.setMessage("Data Kabupaten Berhasil diperbaharui");
			result.setStatus(HttpStatus.OK.value());
			result.setData(kabupatenEntity);
		} else {
			result.setStatus(HttpStatus.BAD_GATEWAY.value());
			if (checkKode != null) {
				result.setMessage("Gagal, Kode Kabupaten " + kodeKabupaten + " sudah ada");
			} else if (checkNama != null) {
				result.setMessage("Gagal, Nama Kabupaten " + dto.getNamaKabupaten() + " sudah ada");
			}

			result.setData(null);
		}

		return result;
	}

	@Override
	public StatusMessageDto<?> delete(Integer idKabupaten) {
		// TODO Auto-generated method stub
		Integer idKabupatenVal = kabupatenRepository.getById(idKabupaten);
		StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
		if (idKabupatenVal != null) {
			KabupatenEntity kabupatenEntity = kabupatenRepository.findById(idKabupaten).get();
			result.setStatus(200);
			result.setMessage("Data Berhasil di Delete");
			result.setData(kabupatenEntity);
//			provinsiRepository.delete(provinsiEntity);
		} else {
			result.setStatus(200);
			result.setMessage("Id " + idKabupaten + " tidak ditemukan");
			result.setData(null);
		}
		return result;
	}

//	Method

	public KabupatenEntity convertToKabupatenEntity(KabupatenDto dto) {
		KabupatenEntity kabupatenEntity = new KabupatenEntity();
		ProvinsiEntity provinsiEntity = provinsiRepository.findByKodeProvinsiIgnoreCase(dto.getKodeProvinsi());
		kabupatenEntity.setKodeKabupaten(dto.getKodeProvinsi()+"."+dto.getKodeKabupaten());
		kabupatenEntity.setNamaKabupaten(dto.getNamaKabupaten());
		kabupatenEntity.setProvinsiEntity(provinsiEntity);
		return kabupatenEntity;
	}

	@Override
	public List<KabupatenEntity> getAll() {
		// TODO Auto-generated method stub
		List<KabupatenEntity> kabupatenEntities = kabupatenRepository.findAll();
		return kabupatenEntities;
	}

	@Override
	public StatusMessageDto<?> getById(Integer idKabupaten) {
		// TODO Auto-generated method stub
		KabupatenEntity kabupatenEntity = kabupatenRepository.findById(idKabupaten).get();
		StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
		result.setStatus(200);
		result.setMessage("Data ditemukan");
		result.setData(kabupatenEntity);
		return result;
	}

	@Override
	public StatusMessageDto<?> getByKode(String kodeKabupaten) {
		// TODO Auto-generated method stub
		KabupatenEntity kabupatenEntity = kabupatenRepository.findByKodeKabupatenIgnoreCase(kodeKabupaten);
		StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
		result.setStatus(200);
		result.setMessage("Data ditemukan");
		result.setData(kabupatenEntity);
		return result;
	}

}
