package com.example.stockmarket.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.event.StockPriceChangedEvent;
import com.example.stockmarket.service.StockService;

// Dependency Injection Annotations used in Java EE
// 1. @Inject (CDI) : Any class
// 2. @PersistenceContext (JPA): EntityManager
// 3. @Context (Jax-RS) -> Sse
// 4. @EJB (EJB)
// 5. @Resource (Managed resource: MailServer, DataSource, ThreadPool, Queue/Topic, ...)

// Dependency Injection Annotations used in Spring
// 1. @Autowired ("Official")
// 2. @Resource
// 3. @Inject (CDI)

// http://localhost:8080/stockmarket/api/v1/stocks
@Path("/stocks")
@ApplicationScoped
@SuppressWarnings("unused")
public class StockRestController {
	@Inject
	private StockService stockService;
	private Sse sse;
	private SseBroadcaster sseBroadcaster;
	private OutboundSseEvent.Builder eventBuilder;
	// jndi api (Java SE) -> directory server (open ldap, active directory, ..)
	@Resource(mappedName = "java:jboss/ee/concurrency/executor/default") 
	private ManagedExecutorService executorService;

	@Context
	public void setSse(Sse sse) {
		this.sse = sse;
		this.eventBuilder = sse.newEventBuilder();
		this.sseBroadcaster = sse.newBroadcaster();
	}

	// http://localhost:8080/stockmarket/api/v1/stocks/orcl
	// http://localhost:8080/stockmarket/api/v1/stocks/ibm
	@GET
	@Path("/{symbol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Stock findBySymbol(@PathParam("symbol") String symbol) {
		System.err.println("StockRestController::findBySymbol -> " + Thread.currentThread().getName());
		return stockService.findStock(symbol);
	}

	@GET
	@Path("subscribe")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void listen(@Context SseEventSink sseEventSink) { // @OnOpen (Websocket)
		this.sseBroadcaster.register(sseEventSink);
	}

	public void listenStockPriceChanges(@Observes StockPriceChangedEvent event) {
		System.err.println("Event received: " + event);
		OutboundSseEvent sseEvent = eventBuilder.name("stockPriceChangedEvent").id(UUID.randomUUID().toString())
				.mediaType(MediaType.APPLICATION_JSON_TYPE).data(StockPriceChangedEvent.class, event)
				.reconnectDelay(3000).comment("price change").build();
		sseBroadcaster.broadcast(sseEvent);
	}

	// http://localhost:8080/stockmarket/api/v1/stocks?page=0&size=10
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void findAll(@QueryParam("page") int page, @QueryParam("size") int size,
			@Suspended AsyncResponse asyncResponse) {
		System.err.println(Thread.currentThread().getName() + " is running StockRestController::findAll");
		executorService.execute(() -> asyncResponse.resume(stockService.findAll(page, size)));
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON) // Response Body: application/json
	@Consumes(MediaType.APPLICATION_JSON) // Request Body: application/json
	public void addStock(@Valid Stock stock, @Suspended AsyncResponse asyncResponse) {
		stockService.add(stock);
		asyncResponse.resume(stock);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON) // Response Body: application/json
	@Consumes(MediaType.APPLICATION_JSON) // Request Body: application/json
	public Stock updateStock(@Valid Stock stock) {
		return stockService.update(stock);
	}

	@DELETE
	@Path("/{symbol}")
	@Produces(MediaType.APPLICATION_JSON) // Response Body: application/json
	public Stock deleteStock(@PathParam("symbol") String symbol) {
		((Object) null).toString();
		return stockService.delete(symbol);
	}

}
