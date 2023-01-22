package com.example.test.steven.cruz.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.steven.cruz.dto.AccountRequest;
import com.example.test.steven.cruz.dto.AccountResponse;
import com.example.test.steven.cruz.dto.ClientResponse;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.entity.AccountEntity;
import com.example.test.steven.cruz.entity.ClientEntity;
import com.example.test.steven.cruz.exception.NotFoundException;
import com.example.test.steven.cruz.repository.AccountRepository;
import com.example.test.steven.cruz.service.AccountService;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImp implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ClientServiceImp clientService;
	
	
	
	@Override
	public List<AccountResponse> findAllAccounts() throws NotFoundException {
		var accountResponse = accountRepository.findAll();
		
		if(Objects.isNull(accountRepository) || accountResponse.size()==0) {
			throw new NotFoundException("No se encontraron datos de clientes");
		}
				
		var accountList = new ArrayList<AccountResponse>();
		
		accountResponse.forEach(accountRow -> {
			accountList.add(AccountResponse.builder()
					.idAccount(accountRow.getIdAccount())
					.accountNumber(accountRow.getAccountNumber())
					.accountType(accountRow.getAccountType())
					.initialAmount(accountRow.getInitialAmount())
					.status(accountRow.getStatus())
					.client(ClientResponse.builder()
							.idClient(accountRow.getClient().getIdClient())
							.name(accountRow.getClient().getName())
							.address(accountRow.getClient().getAddress())
							.identification(accountRow.getClient().getIdentification())
							.build())
					.build());
		});
		return accountList;
	}
	
	@Override
	public FinalResponse validateAccountById(AccountRequest accountRequest) throws NotFoundException {
		var responseRepository =  accountRepository.findById(accountRequest.getIdAccount());
		
		return FinalResponse.builder()
				.result(responseRepository.isPresent())
				.build();
	}
	
	@Override
	@Transactional
	public FinalResponse saveAccount(AccountRequest accountRequest) throws NotFoundException {
		
		getValidateClient(accountRequest);
		
		var accountEntity = AccountEntity.builder()
				.accountNumber(accountRequest.getAccountNumber())
				.accountType(accountRequest.getAccountType())
				.initialAmount(accountRequest.getInitialAmount())
				.status(accountRequest.getStatus())
				.client(
						ClientEntity.builder()
						.idClient(accountRequest.getClient().getIdClient())
						.build()
						)
				.build();
		
		var accountResponse = accountRepository.save(accountEntity);
		
		return FinalResponse.builder().result(Objects.nonNull(accountResponse)).build();
	}

	private void getValidateClient(AccountRequest accountRequest) throws NotFoundException {
		var resultValidate = clientService.validateClientById(accountRequest.getClient());
		
		if (!resultValidate.getResult()) {
			throw new NotFoundException("Cliente No existe");

		}
	}

	@Override
	@Transactional
	public FinalResponse updateAccount(AccountRequest accountRequest) throws NotFoundException {

		getValidateClient(accountRequest);
	
		var accountEntity = AccountEntity.builder()
				.idAccount(accountRequest.getIdAccount())
				.accountNumber(accountRequest.getAccountNumber())
				.accountType(accountRequest.getAccountType())
				.initialAmount(accountRequest.getInitialAmount())
				.status(accountRequest.getStatus())
				.client(
						ClientEntity.builder()
						.idClient(accountRequest.getClient().getIdClient())
						.build()
						)
				.build();
		
		var accountResponse = accountRepository.save(accountEntity);
		
		return FinalResponse.builder().result(Objects.nonNull(accountResponse)).build();
	}

	@Override
	@Transactional
	public FinalResponse deleteAccount(AccountRequest accountRequest) throws NotFoundException {

		getValidateClient(accountRequest);
		
		var accountEntity = AccountEntity.builder()
				.idAccount(accountRequest.getIdAccount())
				.accountNumber(accountRequest.getAccountNumber())
				.accountType(accountRequest.getAccountType())
				.initialAmount(accountRequest.getInitialAmount())
				.status(accountRequest.getStatus())
				.client(
						ClientEntity.builder()
						.idClient(accountRequest.getClient().getIdClient())
						.build()
						)
				.build();
		
		var respuesta = FinalResponse.builder().result(true).build();
		
		accountRepository.delete(accountEntity);
		
		return respuesta;
	}

	@Override
	public Double findInitialAmountByAccountId(Integer accountId) {
		return accountRepository.findInitialAmountByAccountId(accountId);
	}
}
