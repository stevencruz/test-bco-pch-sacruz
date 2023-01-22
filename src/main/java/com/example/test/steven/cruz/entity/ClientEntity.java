package com.example.test.steven.cruz.entity;

import java.util.List;

import com.example.test.steven.cruz.dto.PersonDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_clients")
public class ClientEntity extends PersonDto{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Integer idClient;
	
	@Column(name = "client_password")
	private String password;
	
	@Column(name = "client_status")
	private Boolean status;
	
	@OneToMany(mappedBy = "client")
	private List<AccountEntity> accountLists;
}
