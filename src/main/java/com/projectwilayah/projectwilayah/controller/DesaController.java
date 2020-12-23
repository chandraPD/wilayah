package com.projectwilayah.projectwilayah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projectwilayah.projectwilayah.dto.DesaDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.DesaEntity;
import com.projectwilayah.projectwilayah.service.DesaServiceImpl;

@RestController
@RequestMapping("/desa")
public class DesaController {
	
	@Autowired
	public DesaServiceImpl desaService;
	
	@GetMapping("/get-all")
	public List<DesaEntity> getAll(){
		List<DesaEntity> desaEntities = desaService.getAll();
		return desaEntities;
	}
	
	@GetMapping("/get-by-id/{idDesa}")
	public ResponseEntity<?> getById(@PathVariable Integer idDesa){
		StatusMessageDto<?> result = desaService.getById(idDesa);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/get-by-kode/{kodeDesa}")
	public ResponseEntity<?> getByKode(@PathVariable String kodeDesa){
		StatusMessageDto<?> result = desaService.getByKode(kodeDesa);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody DesaDto dto){
		StatusMessageDto<?> result = desaService.save(dto);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/update/{idDesa}")
	public ResponseEntity<?> update(@PathVariable Integer idDesa, @RequestBody DesaDto dto){
		StatusMessageDto<?> result = desaService.update(dto, idDesa);
		return ResponseEntity.ok(result);
	}
	

	@DeleteMapping("/delete/{idDesa}")
	public ResponseEntity<?> delete(@PathVariable Integer idDesa){
		StatusMessageDto<?> result = desaService.delete(idDesa);
		return ResponseEntity.ok(result);
	}
}
