package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.LineItem;
import com.prs.business.Request;
import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/line-items")
public class LineItemController {

	@Autowired
	private LineItemRepo lineItemRepo;
	@Autowired
	private RequestRepo requestRepo;
	
	@GetMapping("/")
	public Iterable<LineItem> getAll() {
		return lineItemRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<LineItem> get(@PathVariable Integer id) {
		return lineItemRepo.findById(id);
	}
	
	@PostMapping("/")
	public LineItem add(@RequestBody LineItem lineItem) {
		return lineItemRepo.save(lineItem);
	}
	
	@PutMapping("/")
	public LineItem update(@RequestBody LineItem lineItem) {
		return lineItemRepo.save(lineItem);
	}
	
	@DeleteMapping("/{id}")
	public Optional<LineItem> delete(@PathVariable Integer id) {
		Optional<LineItem> lineItem=lineItemRepo.findById(id);
		if (lineItem.isPresent()) {
			try {
			lineItemRepo.deleteById(id);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Exception caught during lineItem delete");
			}
		}
		else {
			System.err.println("LineItem delete error- no lineItem found for id"+id);
		}
		return lineItem;
	}
	
	@GetMapping("/lines-for-pr/{id}")
	public Iterable<LineItem> getAllByRequest(@PathVariable int id) {
		Optional<Request> request=requestRepo.findById(id);
		return lineItemRepo.findAllByRequest(request.get());
	}
	
	
	
}
