package com.example.test.steven.cruz.service;

import java.util.List;

import com.example.test.steven.cruz.dto.AccountRequest;
import com.example.test.steven.cruz.dto.AccountResponse;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.exception.NotFoundException;

public interface AccountService {

	public List<AccountResponse> findAllAccounts() throws NotFoundException;

	public FinalResponse validateAccountById(AccountRequest accountRequest) throws NotFoundException;

	public FinalResponse saveAccount(AccountRequest accountRequest) throws NotFoundException;

	public FinalResponse updateAccount(AccountRequest accountRequest)throws NotFoundException;

	public FinalResponse deleteAccount(AccountRequest accountRequest) throws NotFoundException;
	
	public Double findInitialAmountByAccountId(Integer accountId);
}
