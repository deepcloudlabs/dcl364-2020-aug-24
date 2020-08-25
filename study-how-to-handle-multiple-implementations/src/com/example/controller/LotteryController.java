package com.example.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.example.service.LotteryService;

@Path("/numbers")
public class LotteryController {
	@Inject private LotteryService lotteryService;
	
	// http://localhost:5100/multiple/api/v1/numbers?max=50&size=6&column=10
	@GET
	@Produces("application/json")
	public List<List<Integer>> getLotteryNumbers(
			 @QueryParam("max") int max, 
			 @QueryParam("size") int size,
			 @QueryParam("column") int column){
		return lotteryService.draw(max, size, column);
	}
}
