package com.example.test.steven.cruz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.steven.cruz.dto.ClientRequest;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.dto.MovementRequest;
import com.example.test.steven.cruz.dto.MovementResponse;
import com.example.test.steven.cruz.exception.NotDataValidateException;
import com.example.test.steven.cruz.exception.NotFoundException;
import com.example.test.steven.cruz.exception.NotLogicValidateException;
import com.example.test.steven.cruz.service.imp.MovementServiceImp;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/movement")
@Slf4j
public class MovementController {

	@Autowired
	private MovementServiceImp movementServiceImp;

	@GetMapping("/findMovement")
	public List<MovementResponse> findMovementByDateAndAccountId(@RequestBody MovementRequest movementRequest)
			throws NotFoundException {
		log.info("REQUEST: " + movementRequest);
		var response = movementServiceImp.findMovementByDateAndAccountId(movementRequest);
		log.info("RESPONSE: " + response);
		return response;
	}

	@PostMapping("/save")
	public FinalResponse saveMovement(@RequestBody MovementRequest movementRequest) throws NotFoundException, NotDataValidateException, NotLogicValidateException {
		log.info("REQUEST: " + movementRequest);
		var response = movementServiceImp.saveMovement(movementRequest);
		log.info("RESPONSE: " + response);
		return response;
	}

	@PutMapping("/update")
	public FinalResponse udpateMovement(@RequestBody MovementRequest movementRequest) throws NotFoundException {
		log.info("REQUEST: " + movementRequest);
		var response = movementServiceImp.updateMovement(movementRequest);
		log.info("RESPONSE: " + response);
		return response;
	}
	
	@DeleteMapping("/delete")
	public FinalResponse deleteMovement(@RequestBody MovementRequest movementRequest) throws NotFoundException {
		log.info("REQUEST: " + movementRequest);
		var response = movementServiceImp.deleteMovement(movementRequest);
		log.info("RESPONSE: "+ response);
		return response;
	}

	@GetMapping("/findMovement/All")
	public List<MovementResponse> findAllMovements() throws NotFoundException {
		var response = movementServiceImp.findAllMovements();
		log.info("RESPONSE: " + response);
		return response;
	}

}
