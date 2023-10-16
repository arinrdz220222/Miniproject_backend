package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bus;
import com.example.demo.repository.BusRepository;
@CrossOrigin(origins = "*")
@RestController
public class BusController {
	
	@Autowired
	BusRepository busRepository;
	
	@GetMapping("/bus")
	public ResponseEntity<Object> getBus(){
		try {
			List<Bus> buses = busRepository.findAll() ;
			return new ResponseEntity<>(buses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
	
	@PostMapping("/bus")
	public ResponseEntity<Object> addBus(@RequestBody Bus body){
		try {
			Bus bus = busRepository.save(body);
			return new ResponseEntity<>(bus, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/bus/{busId}")
	public ResponseEntity<Object> getBusDetail(@PathVariable Integer busId){
		try {
			Optional<Bus> bus = busRepository.findById(busId);
			if (bus.isPresent()) {
				return new ResponseEntity<>(bus, HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Sever Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/bus/{busId}")
	public ResponseEntity<Object> updateBus(@PathVariable Integer busId, @RequestBody Bus body) {
		try {
			Optional<Bus> bus = busRepository.findById(busId);
			if (bus.isPresent()) {
				bus.get().setBusName(body.getBusName());
			
				busRepository.save(bus.get());

				return new ResponseEntity<>(bus, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(bus, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/bus/{busId}")
	public ResponseEntity<Object> deleteBus(@PathVariable Integer busId) {
		try {
			Optional<Bus> bus = busRepository.findById(busId);
			if (bus.isPresent()) {
				busRepository.delete(bus.get());

				return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
