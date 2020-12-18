package com.projectwilayah.projectwilayah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectwilayah.projectwilayah.dto.ProvinsiDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.ProvinsiEntity;
import com.projectwilayah.projectwilayah.repository.ProvinsiRepository;
import com.projectwilayah.projectwilayah.service.ProvinsiServiceImpl;

@RestController
@RequestMapping("/provinsi")
public class ProvinsiController {
	
	@Autowired
	ProvinsiRepository provinsiRepository;
	
	@Autowired
	private ProvinsiServiceImpl provinsiService;
	
//	Get All Data Provinsi
	@GetMapping("/get-all")
	public List<ProvinsiEntity> getAll(){
		List<ProvinsiEntity> provinsiEntities = provinsiRepository.findAll();
		return provinsiEntities;
	}
	
//	save 1 provinsi
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProvinsiDto dto){
		
		StatusMessageDto<?> result = provinsiService.save(dto);
		return ResponseEntity.ok(result);
	}
	
//	save Multi Provinsi
	@PostMapping("/save-multi")
	public ResponseEntity<?> saveMulti(@RequestBody List<ProvinsiDto> dto){
		for (ProvinsiDto e : dto) {
			provinsiService.save(e);
		}
		return ResponseEntity.ok(dto);
	}
	
//	update Provinsi by idProvinsi
	@PutMapping("/update/{idProvinsi}")
	public ResponseEntity<?> update(@PathVariable Integer idProvinsi, @RequestBody ProvinsiDto dto){
		StatusMessageDto<?> result = provinsiService.update(dto, idProvinsi);
		return ResponseEntity.ok(result);
	}
	
//	Delete Provinsi by idProvinsi
	@DeleteMapping("/delete/{idProvinsi}")
	public ResponseEntity<?> delete(@PathVariable Integer idProvinsi){
		StatusMessageDto<?> result = provinsiService.delete(idProvinsi);
		return ResponseEntity.ok(result);
	}
}
