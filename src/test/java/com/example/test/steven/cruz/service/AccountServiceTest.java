package com.example.test.steven.cruz.service;

import static org.mockito.Mockito.mockitoSession;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.test.steven.cruz.entity.AccountEntity;
import com.example.test.steven.cruz.entity.ClientEntity;
import com.example.test.steven.cruz.exception.NotFoundException;
import com.example.test.steven.cruz.repository.AccountRepository;
import com.example.test.steven.cruz.service.imp.AccountServiceImp;
import com.example.test.steven.cruz.service.imp.ClientServiceImp;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private ClientServiceImp clientService;
	
	@InjectMocks
	private AccountServiceImp accountServiceImp;
	
	@Test
	public void shouldFindAllAccountsThenResponseOk() throws NotFoundException {
		List<AccountEntity> accountList = new ArrayList<AccountEntity>();
		accountList.add(AccountEntity.builder()
				.idAccount(1)
				.accountNumber("12345")
				.client(ClientEntity.builder().build())
				.build());
		Mockito.when(accountRepository.findAll()).thenReturn(accountList);
		var responseService = accountServiceImp.findAllAccounts();
		Assertions.assertEquals("12345", responseService.get(0).getAccountNumber());
	}
	
	@Test
	public void shouldFindAllAccountsThenException() {
		Mockito.when(accountRepository.findAll()).thenThrow(NullPointerException.class);
		Assertions.assertThrows(NullPointerException.class, ()->{
			accountServiceImp.findAllAccounts();
		});		
	}
}
