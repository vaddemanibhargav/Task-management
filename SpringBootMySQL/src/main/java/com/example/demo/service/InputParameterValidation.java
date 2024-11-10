package com.example.demo.service;

public class InputParameterValidation extends RuntimeException {

	private static final long serialVersionUID = 2291667903172148669L;

	public InputParameterValidation(String errorMessage) {
		super(errorMessage);
	}
}
