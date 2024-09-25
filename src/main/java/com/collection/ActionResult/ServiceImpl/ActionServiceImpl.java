package com.collection.ActionResult.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.collection.ActionResult.Entity.ActionEntity;
import com.collection.ActionResult.Repository.ActionRepository;

@Service
public class ActionServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);

	@Autowired
	ActionRepository actionrepository;
	
	
	public Optional<ActionEntity> findActionsById(String id) {
        logger.info("Fetching action with ID: {}", id);
		Optional<ActionEntity> actions=  actionrepository.findById(id);
		return actions;
	}


	public List<ActionEntity> findAllActions() {
		List<ActionEntity> action=  actionrepository.findAll();
		return  action;
	}


	
	
	

}
