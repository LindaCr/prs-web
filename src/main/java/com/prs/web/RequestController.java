package com.prs.web;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.Request;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/requests")
public class RequestController {

	@Autowired
	private RequestRepo requestRepo;
	
	@GetMapping("/")
	public Iterable<Request> getAll() {
		return requestRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Request> get(@PathVariable Integer id) {
		return requestRepo.findById(id);
	}
	
	@PostMapping("/")
	public Request add(@RequestBody Request request) {
		LocalDate currentDate=LocalDate.now();
		request.setStatus("New");
		request.setSubmittedDate(currentDate);
		return requestRepo.save(request);
	}
	
	@PutMapping("/")
	public Request update(@RequestBody Request request) {
		return requestRepo.save(request);
	}
	
	@DeleteMapping("/{id}")
	public Optional<Request> delete(@PathVariable Integer id) {
		Optional<Request> request=requestRepo.findById(id);
		if (request.isPresent()) {
			try {
			requestRepo.deleteById(id);
			}
			catch (DataIntegrityViolationException dive) {
				//catch dive when movie exists as fk on another table
				System.err.println(dive.getRootCause().getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Foreign Key Constraint Issue- request id: "+id+"is referred to elsewhere.");
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Exception caught during request delete");
			}
		}
		else {
			System.err.println("Request delete error- no request found for id"+id);
		}
		return request;
	}
	
	@PutMapping("/submit-review")
	public Request updateStatus(@RequestBody Request request) {
		if (request.getTotal()<=50) {
			request.setStatus("Approved");
		}
		else {
			request.setStatus("Review");
		}
		return requestRepo.save(request);
	}
	
	@GetMapping("/list-review/{id}")
	public Iterable<Request> getAllByStatus(@PathVariable int id) {
		return requestRepo.findAllByStatusAndUserIdNot("Review", id);
	}
	
	@PutMapping("/approve")
	public Request approveStatus(@RequestBody Request request) {
		request.setStatus("Approved");
		return requestRepo.save(request);
	}
	
	@PutMapping("/reject")
	public Request rejectStatus(@RequestBody Request request) {
		request.setStatus("Rejected");
		return requestRepo.save(request);
	}
	
	
	
		
}
