package com.example.test.steven.cruz.service;

import java.util.List;

import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.dto.ClientRequest;
import com.example.test.steven.cruz.dto.ClientResponse;
import com.example.test.steven.cruz.exception.NotFoundException;

public interface ClientService {

	public List<ClientResponse> findAllClients() throws NotFoundException;

	public FinalResponse validateClientById(ClientRequest clientRequest) throws NotFoundException;

	public FinalResponse saveClient(ClientRequest clientRequest);

	public FinalResponse updateClient(ClientRequest clientRequest);

	public FinalResponse deleteClient(ClientRequest clientRequest);
	
}
