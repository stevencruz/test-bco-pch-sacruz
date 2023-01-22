package com.example.test.steven.cruz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinalResponse {
	private Boolean result;
	
	private String errorMessage;
}
