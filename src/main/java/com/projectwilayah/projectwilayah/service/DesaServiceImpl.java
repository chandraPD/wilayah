package com.projectwilayah.projectwilayah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.projectwilayah.projectwilayah.dto.DesaDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.DesaEntity;
import com.projectwilayah.projectwilayah.entity.KabupatenEntity;
import com.projectwilayah.projectwilayah.entity.KecamatanEntity;
import com.projectwilayah.projectwilayah.entity.ProvinsiEntity;
import com.projectwilayah.projectwilayah.repository.DesaRepository;
import com.projectwilayah.projectwilayah.repository.KecamatanRepository;


@Service
public class DesaServiceImpl implements DesaService {

	@Autowired
	private KecamatanRepository kecamatanRepository;

	@Autowired
	private DesaRepository desaRepository;

	@Override
	public StatusMessageDto<?> save(DesaDto dto) {
		// TODO Auto-generated method stub
		StatusMessageDto<DesaEntity> result = new StatusMessageDto<>();

		DesaEntity desaEntity = convertToDesaEntity(dto);

		String checkNama = desaRepository.findByNama(dto.getNamaDesa());
		String kodeDesa = desaEntity.getKecamatanEntity().getKodeKecamatan() + "." + dto.getKodeKecamatan();
		String checkKode = desaRepository.findByKode(kodeDesa);

		if (checkNama == null && checkKode == null) {

			desaRepository.save(desaEntity);
//			check jika berhasil
			if (desaEntity.getId() != null) {
				result.setMessage("Data Desa Berhasil diinputkan");
				result.setStatus(HttpStatus.OK.value());
				result.setData(desaEntity);
			} else {
				result.setMessage("Data Desa Gagal diinputkan");
				result.setStatus(HttpStatus.BAD_GATEWAY.value());
				result.setData(null);
			}

		} else {
			result.setStatus(HttpStatus.BAD_GATEWAY.value());
			if (checkKode != null) {
				result.setMessage("Gagal, Kode Desa " + kodeDesa + " sudah ada");
			} else if (checkNama != null) {
				result.setMessage("Gagal, Nama Desa " + dto.getNamaDesa() + " sudah ada");
			}

			result.setData(null);
		}

		return result;
	}

	@Override
	public StatusMessageDto<?> update(DesaDto dto, Integer idDesa) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		StatusMessageDto<DesaEntity> result = new StatusMessageDto<>();
		DesaEntity desaEntity = desaRepository.findById(idDesa).get();
		String checkNama = desaRepository.findByNama(dto.getNamaDesa());
		String kodeDesa = desaEntity.getKecamatanEntity().getKodeKecamatan() + "." + dto.getKodeDesa();
		String checkKode = desaRepository.findByKode(kodeDesa);
		if (checkNama == null && checkKode == null) {

//			check jika berhasil
			desaEntity.setKodeDesa(kodeDesa);
			desaEntity.setNamaDesa(dto.getNamaDesa());
			desaRepository.save(desaEntity);
			result.setMessage("Data Desa Berhasil diperbaharui");
			result.setStatus(HttpStatus.OK.value());
			result.setData(desaEntity);
		} else {
			result.setStatus(HttpStatus.BAD_GATEWAY.value());
			if (checkKode != null) {
				result.setMessage("Gagal, Kode Desa " + kodeDesa + " sudah ada");
			} else if (checkNama != null) {
				result.setMessage("Gagal, Nama Desa " + dto.getNamaDesa() + " sudah ada");
			}

			result.setData(null);
		}

		return result;
	}

	@Override
	public StatusMessageDto<?> delete(Integer idDesa) {
		// TODO Auto-generated method stub
		Integer idDesaVal = desaRepository.getById(idDesa);
		StatusMessageDto<DesaEntity> result = new StatusMessageDto<>();
		if (idDesaVal != null) {
			DesaEntity desaEntity = desaRepository.findById(idDesa).get();
			result.setStatus(200);
			result.setMessage("Data Berhasil di Delete");
			result.setData(desaEntity);
//					provinsiRepository.delete(provinsiEntity);
		} else {
			result.setStatus(200);
			result.setMessage("Id " + idDesa + " tidak ditemukan");
			result.setData(null);
		}
		return result;
	}

	@Override
	public List<DesaEntity> getAll() {
		// TODO Auto-generated method stub
		List<DesaEntity> desaEntities = desaRepository.findAll();
		return desaEntities;
	}

//	Method
	public DesaEntity convertToDesaEntity(DesaDto dto) {
		DesaEntity desaEntity = new DesaEntity();
		KecamatanEntity kecamatanEntity = kecamatanRepository.findByKodeKecamatanIgnoreCase(dto.getKodeKecamatan());
		KabupatenEntity kabupatenEntity = kecamatanEntity.getKabupatenEntity();
		ProvinsiEntity provinsiEntity = kabupatenEntity.getProvinsiEntity();

		String kodeKabupaten = kabupatenEntity.getKodeKabupaten();
		String kodeDesa = kodeKabupaten + "." + dto.getKodeDesa();

		desaEntity.setKodeDesa(kodeDesa);
		desaEntity.setNamaDesa(dto.getNamaDesa());
		desaEntity.setKecamatanEntity(kecamatanEntity);
		desaEntity.setKabupatenEntity(kabupatenEntity);
		desaEntity.setProvinsiEntity(provinsiEntity);
		return desaEntity;
	}

	@Override
	public StatusMessageDto<?> getById(Integer idDesa) {
		// TODO Auto-generated method stub
		DesaEntity desaEntity = desaRepository.findById(idDesa).get();
		StatusMessageDto<DesaEntity> result = new StatusMessageDto<>();
		result.setStatus(200);
		result.setMessage("Data ditemukan");
		result.setData(desaEntity);
		return result;
	}

	@Override
	public StatusMessageDto<?> getByKode(String kodeDesa) {
		// TODO Auto-generated method stub
		DesaEntity desaEntity = desaRepository.findByKodeDesaIgnoreCase(kodeDesa);
		StatusMessageDto<DesaEntity> result = new StatusMessageDto<>();
		result.setStatus(200);
		result.setMessage("Data ditemukan");
		result.setData(desaEntity);
		return result;
	}
	
	

}
