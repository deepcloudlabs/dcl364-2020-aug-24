package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.model.LotteryModel;
import com.example.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("/numbers")
public class LotteryController {
	@Autowired private LotteryService lotteryService;
	
	// http://localhost:5300/aop/api/v1/numbers
	@GetMapping
	public LotteryModel getLotteryNumbers(){
		var lotteryModel = new LotteryModel();
		lotteryModel.setNumbers(lotteryService.draw(50, 6));
		return lotteryModel; 
	}
}
