package com.projectwilayah.projectwilayah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectwilayah.projectwilayah.dto.ProvinsiDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.ProvinsiEntity;
import com.projectwilayah.projectwilayah.repository.ProvinsiRepository;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {

	@Autowired
	private ProvinsiRepository provinsiRepository;

	@Override
	public StatusMessageDto<?> save(ProvinsiDto dto) {
		// TODO Auto-generated method stub

		StatusMessageDto<ProvinsiEntity> result = new StatusMessageDto<>();

		String checkNama = provinsiRepository.findByNama(dto.getNamaProvinsi());
		String checkKode = provinsiRepository.findByKode(dto.getKodeProvinsi());
		if (checkNama == null && checkKode == null) {
			ProvinsiEntity provinsiEntity = convertToProvinsiEntity(dto);
			provinsiRepository.save(provinsiEntity);
//			check jika berhasil
			if (provinsiEntity.getId() != null) {
				result.setMessage("Data Provinsi Berhasil diinputkan");
				result.setStatus(HttpStatus.OK.value());
				result.setData(provinsiEntity);
			} else {
				result.setMessage("Data Provinsi Gagal diinputkan");
				result.setStatus(HttpStatus.BAD_GATEWAY.value());
				result.setData(null);
			}
		} else {
			result.setStatus(HttpStatus.BAD_GATEWAY.value());
			if (checkKode != null) {
				result.setMessage("Gagal, Kode Provinsi " + dto.getKodeProvinsi() + " sudah ada");
			} else if (checkNama != null) {
				result.setMessage("Gagal, Nama Provinsi " + dto.getNamaProvinsi() + " sudah ada");
			}

			result.setData(null);
		}
		return result;
	}

	@Override
	public StatusMessageDto<?> update(ProvinsiDto dto, Integer idProvinsi) {
		// TODO Auto-generated method stub
		ProvinsiEntity provinsiEntity = provinsiRepository.findById(idProvinsi).get();
		StatusMessageDto<ProvinsiEntity> result = new StatusMessageDto<>();
		String checkNama = provinsiRepository.findByNama(dto.getNamaProvinsi());
		String checkKode = provinsiRepository.findByKode(dto.getKodeProvinsi());
		if (checkNama == null && checkKode == null) {
			provinsiEntity.setKodeProvinsi(dto.getKodeProvinsi());
			provinsiEntity.setNamaProvinsi(dto.getNamaProvinsi());
			provinsiRepository.save(provinsiEntity);
			result.setStatus(200);
			result.setMessage("Berhasil");
			result.setData(provinsiEntity);
		} else {
			result.setStatus(400);
			if (checkKode != null) {
				result.setMessage("Gagal, Kode Provinsi " + dto.getKodeProvinsi() + " sudah ada");
			} else if (checkNama != null) {
				result.setMessage("Gagal, Nama Provinsi " + dto.getNamaProvinsi() + " sudah ada");
			}

			result.setData(null);
		}

		return result;
	}

	@Override
	public StatusMessageDto<?> delete(Integer idProvinsi) {
		// TODO Auto-generated method stub

//		Jika ketemu maka return true untuk mengeset status = Berhasil, jika tidak false
		Integer idProvinsiVal = provinsiRepository.getById(idProvinsi);
		StatusMessageDto<ProvinsiEntity> result = new StatusMessageDto<>();
		if (idProvinsiVal != null) {
			ProvinsiEntity provinsiEntity = provinsiRepository.findById(idProvinsi).get();
			result.setStatus(200);
			result.setMessage("Data Berhasil di Delete");
			result.setData(provinsiEntity);
//			provinsiRepository.delete(provinsiEntity);
		} else {
			result.setStatus(200);
			result.setMessage("Id " + idProvinsi + " tidak ditemukan");
			result.setData(null);
		}
		return result;
	}

//	Method

	public ProvinsiEntity convertToProvinsiEntity(ProvinsiDto dto) {
		ProvinsiEntity provinsiEntity = new ProvinsiEntity();
		provinsiEntity.setKodeProvinsi(dto.getKodeProvinsi());
		provinsiEntity.setNamaProvinsi(dto.getNamaProvinsi());
		return provinsiEntity;
	}

}
