package com.projectwilayah.projectwilayah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectwilayah.projectwilayah.dto.KecamatanDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.KecamatanEntity;
import com.projectwilayah.projectwilayah.service.KecamatanServiceImpl;

@RestController
@RequestMapping("/kecamatan")
public class KecamatanController {
	
	@Autowired
	public KecamatanServiceImpl kecamatanService;
	
//	Get All Data Kecamatan
	@GetMapping("/get-all")
	public List<KecamatanEntity> getAll(){
		List<KecamatanEntity> kecamatanEntities = kecamatanService.getAll();
		return kecamatanEntities;
	}
	
//	Save 1 Kecamatan
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody KecamatanDto dto){
		StatusMessageDto<?> result = kecamatanService.save(dto);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/update/{idKecamatan}")
	public ResponseEntity<?> update(@PathVariable Integer idKecamatan, @RequestBody KecamatanDto dto){
		StatusMessageDto<?> result = kecamatanService.update(dto, idKecamatan);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/delete/{idKecamatan}")
	public ResponseEntity<?> delete(@PathVariable Integer idKecamatan){
		StatusMessageDto<?> result = kecamatanService.delete(idKecamatan);
		return ResponseEntity.ok(result);
	}
}
