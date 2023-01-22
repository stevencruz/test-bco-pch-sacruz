package com.example.test.steven.cruz.service;

import java.util.List;

import com.example.test.steven.cruz.dto.AccountRequest;
import com.example.test.steven.cruz.dto.CustomMovementResponse;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.dto.MovementRequest;
import com.example.test.steven.cruz.dto.MovementResponse;
import com.example.test.steven.cruz.exception.NotDataValidateException;
import com.example.test.steven.cruz.exception.NotFoundException;
import com.example.test.steven.cruz.exception.NotLogicValidateException;

public interface MovementService {
	
	public List<MovementResponse> findAllMovements() throws NotFoundException;
	
	public FinalResponse saveMovement(MovementRequest movementRequest) throws NotFoundException, NotDataValidateException, NotLogicValidateException;

	public FinalResponse updateMovement(MovementRequest movementRequest) throws NotFoundException;

	public FinalResponse deleteMovement(MovementRequest movementRequest) throws NotFoundException;

	public List<MovementResponse> findMovementByDateAndAccountId(MovementRequest movementRequest) throws NotFoundException;
	
	public List<CustomMovementResponse> findMovementByDateAndClientId(MovementRequest movementRequest) throws NotFoundException;

}
