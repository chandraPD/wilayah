package com.projectwilayah.projectwilayah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectwilayah.projectwilayah.dto.KecamatanDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.KabupatenEntity;
import com.projectwilayah.projectwilayah.entity.KecamatanEntity;
import com.projectwilayah.projectwilayah.entity.ProvinsiEntity;
import com.projectwilayah.projectwilayah.repository.KabupatenRepository;
import com.projectwilayah.projectwilayah.repository.KecamatanRepository;
import com.projectwilayah.projectwilayah.repository.ProvinsiRepository;

@Service
@Transactional
public class KecamatanServiceImpl implements KecamatanService {

	@Autowired
	private KecamatanRepository kecamatanRepository;

	@Autowired
	private KabupatenRepository kabupatenRepository;

	@Autowired
	private ProvinsiRepository provinsiRepository;

	@Override
	public StatusMessageDto<?> save(KecamatanDto dto) {
		// TODO Auto-generated method stub
		StatusMessageDto<KecamatanEntity> result = new StatusMessageDto<>();

		KecamatanEntity kecamatanEntity = convertToKecamatanEntity(dto);

		String checkNama = kecamatanRepository.findByNama(dto.getNamaKecamatan());
		String kodeKecamatan = kecamatanEntity.getKabupatenEntity().getKodeKabupaten() + "." + dto.getKodeKabupaten();
		String checkKode = kecamatanRepository.findByKode(kodeKecamatan);

		if (checkNama == null && checkKode == null) {

			kecamatanRepository.save(kecamatanEntity);
//			check jika berhasil
			if (kecamatanEntity.getId() != null) {
				result.setMessage("Data Kecamatan Berhasil diinputkan");
				result.setStatus(HttpStatus.OK.value());
				result.setData(kecamatanEntity);
			} else {
				result.setMessage("Data Kecamatan Gagal diinputkan");
				result.setStatus(HttpStatus.BAD_GATEWAY.value());
				result.setData(null);
			}

		} else {
			result.setStatus(HttpStatus.BAD_GATEWAY.value());
			if (checkKode != null) {
				result.setMessage("Gagal, Kode Kecamatan " + kodeKecamatan + " sudah ada");
			} else if (checkNama != null) {
				result.setMessage("Gagal, Nama Kecamatan " + dto.getNamaKecamatan() + " sudah ada");
			}

			result.setData(null);
		}

		return result;
	}

	@Override
	public StatusMessageDto<?> update(KecamatanDto dto, Integer idKecamatan) {
		// TODO Auto-generated method stub
		StatusMessageDto<KecamatanEntity> result = new StatusMessageDto<>();
		KecamatanEntity kecamatanEntity = kecamatanRepository.findById(idKecamatan).get();
		String checkNama = kecamatanRepository.findByNama(dto.getNamaKecamatan());
		String kodeKecamatan = kecamatanEntity.getKabupatenEntity().getKodeKabupaten() + "." + dto.getKodeKecamatan();
		String checkKode = kecamatanRepository.findByKode(kodeKecamatan);
		if (checkNama == null && checkKode == null) {

//			check jika berhasil
			kecamatanEntity.setKodeKecamatan(kodeKecamatan);
			kecamatanEntity.setNamaKecamatan(dto.getNamaKecamatan());
			kecamatanRepository.save(kecamatanEntity);
			result.setMessage("Data Kecamatan Berhasil diperbaharui");
			result.setStatus(HttpStatus.OK.value());
			result.setData(kecamatanEntity);
		} else {
			result.setStatus(HttpStatus.BAD_GATEWAY.value());
			if (checkKode != null) {
				result.setMessage("Gagal, Kode Kecamatan " + kodeKecamatan + " sudah ada");
			} else if (checkNama != null) {
				result.setMessage("Gagal, Nama Kecamatan " + dto.getNamaKecamatan() + " sudah ada");
			}

			result.setData(null);
		}

		return result;
	}

	@Override
	public StatusMessageDto<?> delete(Integer idKecamatan) {
		// TODO Auto-generated method stub
		Integer idKecamatanVal = kecamatanRepository.getById(idKecamatan);
		StatusMessageDto<KecamatanEntity> result = new StatusMessageDto<>();
		if (idKecamatanVal != null) {
			KecamatanEntity kecamatanEntity = kecamatanRepository.findById(idKecamatan).get();
			result.setStatus(200);
			result.setMessage("Data Berhasil di Delete");
			result.setData(kecamatanEntity);
//			provinsiRepository.delete(provinsiEntity);
		} else {
			result.setStatus(200);
			result.setMessage("Id " + idKecamatan + " tidak ditemukan");
			result.setData(null);
		}
		return result;
	}

	@Override
	public List<KecamatanEntity> getAll() {
		// TODO Auto-generated method stub
		List<KecamatanEntity> kecamatanEntities = kecamatanRepository.findAll();
		return kecamatanEntities;
	}

//	Method

	public KecamatanEntity convertToKecamatanEntity(KecamatanDto dto) {
		KecamatanEntity kecamatanEntity = new KecamatanEntity();
		KabupatenEntity kabupatenEntity = kabupatenRepository.findByKodeKabupatenIgnoreCase(dto.getKodeKabupaten());
		String kodeProvinsi = kabupatenEntity.getProvinsiEntity().getKodeProvinsi();
		ProvinsiEntity provinsiEntity = provinsiRepository.findByKodeProvinsiIgnoreCase(kodeProvinsi);
		kecamatanEntity.setKodeKecamatan(kabupatenEntity.getKodeKabupaten()+"."+dto.getKodeKecamatan());
		kecamatanEntity.setNamaKecamatan(dto.getNamaKecamatan());
		kecamatanEntity.setKabupatenEntity(kabupatenEntity);
		kecamatanEntity.setProvinsiEntity(provinsiEntity);
		return kecamatanEntity;
	}

	@Override
	public StatusMessageDto<?> getById(Integer idKecamatan) {
		// TODO Auto-generated method stub
		KecamatanEntity kecamatanEntity = kecamatanRepository.findById(idKecamatan).get();
		StatusMessageDto<KecamatanEntity> result = new StatusMessageDto<>();
		result.setStatus(200);
		result.setMessage("Data ditemukan");
		result.setData(kecamatanEntity);
		return result;
	}

	@Override
	public StatusMessageDto<?> getByKode(String kodeKecamatan) {
		// TODO Auto-generated method stub
		KecamatanEntity kecamatanEntity = kecamatanRepository.findByKodeKecamatanIgnoreCase(kodeKecamatan);
		StatusMessageDto<KecamatanEntity> result = new StatusMessageDto<>();
		result.setStatus(200);
		result.setMessage("Data ditemukan");
		result.setData(kecamatanEntity);
		return result;
	}

}
