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

import com.example.test.steven.cruz.dto.AccountRequest;
import com.example.test.steven.cruz.dto.AccountResponse;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.exception.NotFoundException;
import com.example.test.steven.cruz.service.imp.AccountServiceImp;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

	@Autowired
	private AccountServiceImp accountServiceImp;
	
	@GetMapping("/findAll")
	public List<AccountResponse> findAllAccounts() throws NotFoundException{
		
		var response = accountServiceImp.findAllAccounts();
		log.info("RESPONSE: "+ response);
		return response;
	}
	
	@PostMapping("/save")
	public FinalResponse saveAccount(@RequestBody AccountRequest accountRequest) throws NotFoundException {
		var response = accountServiceImp.saveAccount(accountRequest);
		log.info("RESPONSE: "+ response);
		return response;
	}
	
	@PutMapping("/update")
	public FinalResponse udpateAccount(@RequestBody AccountRequest accountRequest) throws NotFoundException {
		var response = accountServiceImp.updateAccount(accountRequest);
		log.info("RESPONSE: "+ response);
		return response;
			}
	
	@DeleteMapping("/delete")
	public FinalResponse deleteAccount(@RequestBody AccountRequest accountRequest) throws NotFoundException {
		var response = accountServiceImp.deleteAccount(accountRequest);
		log.info("RESPONSE: "+ response);
		return response;
	}
}
