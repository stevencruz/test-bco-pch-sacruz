package com.example.test.steven.cruz.service.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.test.steven.cruz.dto.AccountRequest;
import com.example.test.steven.cruz.dto.AccountResponse;
import com.example.test.steven.cruz.dto.CustomMovementResponse;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.dto.MovementRequest;
import com.example.test.steven.cruz.dto.MovementResponse;
import com.example.test.steven.cruz.entity.AccountEntity;
import com.example.test.steven.cruz.entity.MovementEntity;
import com.example.test.steven.cruz.exception.NotDataValidateException;
import com.example.test.steven.cruz.exception.NotFoundException;
import com.example.test.steven.cruz.exception.NotLogicValidateException;
import com.example.test.steven.cruz.repository.MovementRepository;
import com.example.test.steven.cruz.service.MovementService;

import jakarta.transaction.Transactional;

@Service
public class MovementServiceImp implements MovementService { 

	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private AccountServiceImp accountService;

	@Value("${MAX_VALUE}")
    private Integer maxValue;
	
	@Override
	public List<MovementResponse> findAllMovements() throws NotFoundException {
		var movementResponse = movementRepository.findAll();
		
		if(Objects.isNull(movementRepository) || movementResponse.size()==0) {
			throw new NotFoundException("No se encontraron datos de movimientos");
		}
		
		var movementList = new ArrayList<MovementResponse>();
		
		movementResponse.forEach(movementRow -> {
			movementList.add(MovementResponse.builder()
					.idMovement(movementRow.getIdMovement())
					.date(movementRow.getDate())
					.movementType(movementRow.getMovementType())
					.value(movementRow.getValue())
					.balance(movementRow.getBalance())
					.account(AccountResponse.builder()
							.accountNumber(movementRow.getAccount().getAccountNumber())
							.accountType(movementRow.getAccount().getAccountType())
							.idAccount(movementRow.getAccount().getIdAccount())
							.build())
					.build());
		}
		);
		return movementList;
	}

	@Override
	@Transactional
	public FinalResponse saveMovement(MovementRequest movementRequest) throws NotFoundException, NotDataValidateException, NotLogicValidateException {

		getValidateAccount(movementRequest);
		
		Double balance;
		
		Double sumDebitDay;

		Integer movementId = movementRepository.findLastMovementIdByAccountId(movementRequest.getAccount().getIdAccount());
		
		//obtener el saldo de la cuenta
		if(movementId == null) {
			 balance = accountService.findInitialAmountByAccountId(movementRequest.getAccount().getIdAccount());
		 }
		else {
			balance = movementRepository.findLastBalanceByMovementId(movementId);
		}
		
		 LocalDate date1 = LocalDate.now();
		 LocalDate date2 = LocalDate.now().plusDays(1);
		  
		sumDebitDay = movementRepository.findSumValueMovementByDateAndTypeAndAccountId(date1.atStartOfDay(),  date2.atStartOfDay(), movementRequest.getMovementType(), movementRequest.getAccount().getIdAccount());
		
		if (Objects.isNull(sumDebitDay)) {
			sumDebitDay=(double) 0;
		}
		
		//validar el tipo de movimiento
		//credito
		if(movementRequest.getMovementType().equals("C")) {
			balance += movementRequest.getValue();
		}
		//debito
		else if (movementRequest.getMovementType().equals("D")){
			//validaciones para el debito 
			if (movementRequest.getValue() + sumDebitDay > maxValue) {
				throw new NotLogicValidateException(" “Cupo diario Excedido");
			}
			
			if (balance == 0 || balance < movementRequest.getValue()) {
				throw new NotLogicValidateException("Saldo no disponible");
			}
			balance -= movementRequest.getValue();
		}
		else {
			throw new NotDataValidateException("Tipo de Movimiento No Valido");
		}
		
		var movementEntity = MovementEntity.builder()
				.date(LocalDateTime.now())
				.movementType(movementRequest.getMovementType())
				.value(movementRequest.getValue())
				.balance(balance)
				.account(AccountEntity.builder()
						.idAccount(movementRequest.getAccount().getIdAccount())
						.build())
				.build();

		var movementResponse = movementRepository.save(movementEntity);
		
		return FinalResponse.builder().result(Objects.nonNull(movementResponse)).build();
	}

	private void getValidateAccount(MovementRequest movementRequest) throws NotFoundException {
		var resultValidate = accountService.validateAccountById(movementRequest.getAccount());
		
		if(!resultValidate.getResult()) {
			throw new NotFoundException("Cuenta no existe");			
		}
	}

		
	@Override
	public FinalResponse updateMovement(MovementRequest movementRequest) throws NotFoundException {
	
		getValidateAccount(movementRequest);
		
		var movementEntity = MovementEntity.builder()
				.idMovement(movementRequest.getIdMovement())
				.date(movementRequest.getDate())
				.movementType(movementRequest.getMovementType())
				.value(movementRequest.getValue())
				.balance(movementRequest.getBalance())
				.account(AccountEntity.builder()
						.idAccount(movementRequest.getAccount().getIdAccount())
						.build())
				.build();

		var movementResponse = movementRepository.save(movementEntity);
		
		return FinalResponse.builder().result(Objects.nonNull(movementResponse)).build();
	
	}

	@Override
	public FinalResponse deleteMovement(MovementRequest movementRequest) throws NotFoundException {
		getValidateAccount(movementRequest);
		
		var movementEntity = MovementEntity.builder()
				.idMovement(movementRequest.getIdMovement())
				.date(movementRequest.getDate())
				.movementType(movementRequest.getMovementType())
				.value(movementRequest.getValue())
				.balance(movementRequest.getBalance())
				.account(AccountEntity.builder()
						.idAccount(movementRequest.getAccount().getIdAccount())
						.build())
				.build();

		var respuesta = FinalResponse.builder().result(true).build();
		
		movementRepository.delete(movementEntity);
		
		return respuesta;

	}

	@Override
	public List<MovementResponse> findMovementByDateAndAccountId(MovementRequest movementRequest) throws NotFoundException {
		var movementResponse = movementRepository.findMovementByDateAndAccountId(movementRequest.getDate(),movementRequest.getFinalDate(),movementRequest.getAccount().getIdAccount());
			
		if(Objects.isNull(movementRepository) || movementResponse.size()==0) {
			throw new NotFoundException("No se encontraron movimientos en el rango de fecha para la cuenta");
		}
		
		var movementList = new ArrayList<MovementResponse>();
		
		movementResponse.forEach(movementRow -> {
			movementList.add(MovementResponse.builder()
					.idMovement(movementRow.getIdMovement())
					.date(movementRow.getDate())
					.movementType(movementRow.getMovementType())
					.value(movementRow.getValue())
					.balance(movementRow.getBalance())
					.account(AccountResponse.builder()
							.accountNumber(movementRow.getAccount().getAccountNumber())
							.accountType(movementRow.getAccount().getAccountType())
							.idAccount(movementRow.getAccount().getIdAccount())
							.build())
					.build());
		}
		);
		return movementList;
	}

	@Override
	public List<CustomMovementResponse> findMovementByDateAndClientId(MovementRequest movementRequest)
			throws NotFoundException {
		var customResponse = movementRepository.findMovementByDateAndClientId(movementRequest.getDate(), movementRequest.getFinalDate(), movementRequest.getIdClient());
		
		if(Objects.isNull(movementRepository) || customResponse.size()==0) {
			throw new NotFoundException("No se encontraron movimientos en el rango de fecha para el cliente");
		}
		
		return customResponse;
	}
}
