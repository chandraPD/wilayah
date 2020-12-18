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

import com.projectwilayah.projectwilayah.dto.KabupatenDto;
import com.projectwilayah.projectwilayah.dto.StatusMessageDto;
import com.projectwilayah.projectwilayah.entity.KabupatenEntity;
import com.projectwilayah.projectwilayah.service.KabupatenServiceImpl;

@RestController
@RequestMapping("/kabupaten")
public class KabupatenController {
	
	@Autowired
	public KabupatenServiceImpl kabupatenService;
	
//	Get All Data Kabupaten
	@GetMapping("/get-all")
	public List<KabupatenEntity> getAll(){
		List<KabupatenEntity> kabupatenEntities = kabupatenService.getAll();
		return kabupatenEntities;
	}
	
//	save 1 kabupaten
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody KabupatenDto dto){
		StatusMessageDto<?> result = kabupatenService.save(dto);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/update/{idKabupaten}")
	public ResponseEntity<?> update(@PathVariable Integer idKabupaten, @RequestBody KabupatenDto dto){
		StatusMessageDto<?> result = kabupatenService.update(dto, idKabupaten);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/delete/{idKabupaten}")
	public ResponseEntity<?> delete(@PathVariable Integer idKabupaten){
		StatusMessageDto<?> result = kabupatenService.delete(idKabupaten);
		return ResponseEntity.ok(result);
	}
}
