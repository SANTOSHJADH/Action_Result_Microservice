package com.collection.ActionResult.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collection.ActionResult.Entity.ActionEntity;
import com.collection.ActionResult.ExceptionHandle.ErrorResponse;
import com.collection.ActionResult.ExceptionHandle.ResourceNotFoundException;
import com.collection.ActionResult.ServiceImpl.ActionServiceImpl;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/actions")
public class ActionController {
    
    private static final Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);
	@Autowired
	ActionServiceImpl actionservice;
	 
	@GetMapping("/{id}")
    public ResponseEntity<Optional<ActionEntity>> getActionsById(@PathVariable String id) {
        logger.info("Received request to get action with ID: {}", id);
        Optional<ActionEntity> actions = actionservice.findActionsById(id);
        if (actions.isEmpty()) {
            throw new ResourceNotFoundException("actions with ID " + id + " not found.");
        }
        return ResponseEntity.ok(actions);
    }
	
	@GetMapping("/allactions")
	public ResponseEntity<List<ActionEntity>> getAllActions()
	{
		List<ActionEntity> allactions = actionservice.findAllActions();
	        if (allactions.isEmpty()) {
	            throw new ResourceNotFoundException("actions not found.");
	        }
	        return ResponseEntity.ok(allactions);
		
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrftoken(HttpServletRequest request)
	{
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("RESOURCE_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
	
	


