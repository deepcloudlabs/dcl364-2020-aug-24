package com.example.model;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.example.service.RandomNumber;

@Named
@RequestScoped
public class LotteryModel {
	@RandomNumber(min=1,max=50,size=6,sorted=true, distinct=true)
	private List<Integer> numbers;

	public LotteryModel() {
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
	
}
