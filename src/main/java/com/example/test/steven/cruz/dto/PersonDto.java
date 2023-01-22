package com.example.test.steven.cruz.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersonDto {
	
	private String name;
	private String identification;
	private String gender;
	private Integer age;
	private String address;
	private String phone;
	private Integer idPerson;

}
