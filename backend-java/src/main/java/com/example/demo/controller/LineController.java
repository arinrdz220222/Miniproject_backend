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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Line;
import com.example.demo.repository.LineRepository;

@CrossOrigin(origins = "*")
@RestController
public class LineController {
	@Autowired
	LineRepository lineRepository;
	
	@GetMapping("/find")
	public ResponseEntity<Object> getLine(@RequestParam String start, @RequestParam String stop) {
	    try {
	        List<Line> lines = lineRepository.findByStartAndStop(start, stop);
	        if (lines.isEmpty()) {
	            return new ResponseEntity<>("No lines found for the specified start and stop.", HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(lines, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	
	@PostMapping("/find")
    public ResponseEntity<Object> createLine(@RequestBody Line body) {
        try {
            Line lines = lineRepository.save(body);
            return new ResponseEntity<>(lines, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/find/{lineId}")
	public ResponseEntity<Object> updateEmployee(@PathVariable Integer lineId, @RequestBody Line body) {
		try {
			Optional<Line> lines = lineRepository.findById(lineId);
			if (lines.isPresent()) {
				lines.get().setStart(body.getStart());
				lines.get().setStop(body.getStop());
				lines.get().setLink(body.getLink());
				lineRepository.save(lines.get());

				return new ResponseEntity<>(lines, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(lines, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/find/{lineId}")
	public ResponseEntity<Object> deleteEmplpoEmployee(@PathVariable Integer lineId) {
		try {
			Optional<Line> lines = lineRepository.findById(lineId);
			if (lines.isPresent()) {
				lineRepository.delete(lines.get());

				return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}