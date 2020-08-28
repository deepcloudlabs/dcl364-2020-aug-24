package com.example.stockmarket.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.stockmarket.dto.RegisterStockRequest;
import com.example.stockmarket.dto.RegisterStockResponse;
import com.example.stockmarket.dto.UnregisterCompanyRequest;
import com.example.stockmarket.dto.UnregisterCompanyResponse;
import com.example.stockmarket.service.StockService;

@Path("/stocks")
@RequestScoped
public class StockController {
	@Inject
	private StockService stockService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RegisterStockResponse registerStock(RegisterStockRequest request) {
		return stockService.register(request);
	}

	@DELETE
	@Path("{url}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UnregisterCompanyResponse unregisterCompany(UnregisterCompanyRequest request) {
		return stockService.unregister(request);
	}
}
