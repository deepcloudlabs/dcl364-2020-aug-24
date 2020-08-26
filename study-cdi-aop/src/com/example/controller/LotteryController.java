package com.example.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.example.model.LotteryModel;
import com.example.service.LotteryService;

@Path("/numbers")
public class LotteryController {
	@Inject private LotteryModel lotteryModel;
	@Inject private LotteryService lotteryService;
	
	// http://localhost:5100/aspect/api/v1/numbers
	@GET
	@Produces("application/json")
	public LotteryModel getLotteryNumbers(){
		lotteryModel.setNumbers(lotteryService.draw(50, 6));
		return lotteryModel; 
	}
}
