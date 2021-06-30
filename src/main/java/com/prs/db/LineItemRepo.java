package com.prs.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.LineItem;
import com.prs.business.Request;

public interface LineItemRepo extends CrudRepository<LineItem, Integer>{

	List<LineItem> findAllByRequest(Request request);
	 
	List<LineItem> findAllByRequestId(int id);
}
