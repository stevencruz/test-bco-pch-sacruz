package com.example.test.steven.cruz.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.steven.cruz.dto.AccountResponse;
import com.example.test.steven.cruz.dto.ClientRequest;
import com.example.test.steven.cruz.dto.ClientResponse;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.entity.ClientEntity;
import com.example.test.steven.cruz.exception.NotFoundException;
import com.example.test.steven.cruz.repository.ClientRepository;
import com.example.test.steven.cruz.service.ClientService;

@Service
public class ClientServiceImp implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<ClientResponse> findAllClients() throws NotFoundException {
		var clientResponse = clientRepository.findAll();

		if (Objects.isNull(clientResponse) || clientResponse.size() == 0) {
			throw new NotFoundException("No se encontraron datos de clientes");
		}
		var clientList = new ArrayList<ClientResponse>();
		
	
		
		clientResponse.forEach(clientRow -> {
			
			var accountList = new ArrayList<AccountResponse>();
			clientRow.getAccountLists().forEach(account -> {
				accountList.add(AccountResponse.builder()
						.accountNumber(account.getAccountNumber())
						.accountType(account.getAccountType())
						.build());
			});
			
			clientList.add(
					ClientResponse.builder()
					.idClient(clientRow.getIdClient())
					.password(clientRow.getPassword())
					.status(clientRow.getStatus())
					.idPerson(clientRow.getIdPerson())
					.name(clientRow.getName())
					.identification(clientRow.getIdentification())
					.gender(clientRow.getGender())
					.age(clientRow.getAge())
					.address(clientRow.getAddress())
					.phone(clientRow.getPhone())
					.accountLists(accountList)
					.build());
		});
		
		return clientList;
	}

	@Override
	public FinalResponse validateClientById(ClientRequest clientRequest) throws NotFoundException {
		var responseReposiroty = clientRepository.findById(clientRequest.getIdClient());
		
		return FinalResponse.builder()
				.result(responseReposiroty.isPresent())
				.build();
	}

	@Override
	@Transactional
	public FinalResponse saveClient(ClientRequest clientRequest) {
		var clientEntity = ClientEntity.builder()
				.password(clientRequest.getPassword())
				.status(clientRequest.getStatus())
				.idPerson(clientRequest.getIdPerson())
				.name(clientRequest.getName())
				.identification(clientRequest.getIdentification())
				.gender(clientRequest.getGender())
				.age(clientRequest.getAge())
				.address(clientRequest.getAddress())
				.phone(clientRequest.getPhone())
				.build();

		var clientResponse = clientRepository.save(clientEntity);

		return FinalResponse.builder().result(Objects.nonNull(clientResponse)).build();
	}

	@Override
	public FinalResponse updateClient(ClientRequest clientRequest) {
		var clientEntity = ClientEntity.builder()
				.idClient(clientRequest.getIdClient())
				.password(clientRequest.getPassword())
				.status(clientRequest.getStatus())
				.idPerson(clientRequest.getIdPerson())
				.name(clientRequest.getName())
				.identification(clientRequest.getIdentification())
				.gender(clientRequest.getGender())
				.age(clientRequest.getAge())
				.address(clientRequest.getAddress())
				.phone(clientRequest.getPhone())
				.build();

		var clientResponse = clientRepository.save(clientEntity);

		return FinalResponse.builder().result(Objects.nonNull(clientResponse)).build();
	}

	@Override
	public FinalResponse deleteClient(ClientRequest clientRequest) {
		var clientEntity = ClientEntity.builder()
				.idClient(clientRequest.getIdClient())
				.password(clientRequest.getPassword())
				.status(clientRequest.getStatus())
				.idPerson(clientRequest.getIdPerson())
				.name(clientRequest.getName())
				.identification(clientRequest.getIdentification())
				.gender(clientRequest.getGender())
				.age(clientRequest.getAge())
				.address(clientRequest.getAddress())
				.phone(clientRequest.getPhone())
				.build();
		
		var respuesta = FinalResponse.builder().result(true).build();
		
		clientRepository.delete(clientEntity);	
				
		return respuesta;
	}
}
