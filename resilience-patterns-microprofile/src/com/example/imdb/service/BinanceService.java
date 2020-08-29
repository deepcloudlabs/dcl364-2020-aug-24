package com.example.imdb.service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import com.example.imdb.dto.Ticker;

@Stateless
public class BinanceService {
	/**
	 * The configured the max retries is 90 but the max duration is 1000ms. Once the
	 * duration is reached, no more retries should be performed, even through it has
	 * not reached the max retries.
	 */
	@Retry(maxRetries = 90, maxDuration = 1000)
	public void retrieveBtcusdtPrice() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
	}

	/**
	 * There should be 0-800ms (jitter is -400ms - 400ms) delays between each
	 * invocation. there should be at least 4 retries but no more than 10 retries.
	 */
	@Retry(delay = 400, maxDuration = 3200, jitter = 400, maxRetries = 10)
	public void serviceA() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
	}

	/**
	 * Sets retry condition, which means Retry will be performed on IOException.
	 */
	@Retry(retryOn = { IOException.class })
	public void serviceB() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
	}

	/**
	 * 
	 * Circuit Breaker prevents repeating timeout, so that invoking dysfunctional
	 * services or APIs fail fast. Applying @CircuitBreaker on method or class level
	 * will have CircuitBreaker applied.
	 * 
	 * Open the circuit once 3 (4x0.75) failures occur among the rolling window of 4
	 * consecutive invocations. The circuit will stay open for 1000ms and then back
	 * to half open. After 10 consecutive successful invocations, the circuit will
	 * be back to close again. When a circuit is open, A CircuitBreakerOpenException
	 * will be thrown.
	 * 
	 */
	@CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio = 0.75, delay = 1000)
	public Ticker serviceC() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
		return ticker;
	}

	/**
	 * 
	 * Timeout prevents from the execution from waiting forever.
	 * 
	 * @Timeout is used to specify a timeout and it can be used on methods or class.
	 *          When a timeout occurs, a TimeoutException will be thrown.
	 * 
	 */
	@Timeout(400) // timeout is 400ms
	public Ticker serviceD() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
		return ticker;
	}

	/**
	 * 
	 * The Bulkhead pattern is to prevent faults in one part of the system from
	 * cascading to the entire system, which might bring down the whole system. The
	 * implementation is to limit the number of concurrent requests accessing to an
	 * instance.
	 * 
	 * There are two different approaches to the bulkhead: thread pool isolation and
	 * semaphore isolation.
	 * 
	 * Semaphore style Bulkhead: Annotating a method or a class with @Bulkhead
	 * applies a semaphore style bulkhead, which allows the specified concurrent
	 * number of requests.
	 * 
	 */
	@Bulkhead(5)
	public Ticker serviceE() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
		return ticker;
	}

	/**
	 * 
	 * Thread pool style Bulkhead: When @Bulkhead is used with @Asynchronous, the
	 * thread pool isolation approach will be used. The thread pool approach allows
	 * to configure the maximum concurrent requests together with the waiting queue
	 * size. The semaphore approach only allows the concurrent number of requests
	 * configuration.
	 * 
	 * @Asynchronous causes an invocation to be executed by a different thread.
	 * 
	 */
	@Asynchronous
	@Bulkhead(value = 5, waitingTaskQueue = 8)
	public Future<Ticker> serviceF() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
		return CompletableFuture.completedFuture(ticker);
	}

	/**
	 * Most previous annotations increase the success rate in method invocation.
	 * However, they cannot completely eliminate exception. Exception should still
	 * be dealt with. Often it is useful to fall back to a different operation on a
	 * dysfunctional operation. A method can be annotated with @Fallback, which
	 * means the method will have Fallback policy applied. when the method fails and
	 * retry reaches its maximum retry, the fallback operation will be performed.
	 * The method TickerFallbackHandler.handle(ExecutionContext context) will be
	 * invoked.
	 */
	@Retry(maxRetries = 1)
	@Fallback(TickerFallbackHandler.class)
	public Ticker serviceH() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
		return ticker;
	}

	@Retry(maxRetries = 2)
	@Fallback(fallbackMethod = "fallbackForServiceJ")
	public Ticker serviceJ() {
		var ticker = ClientBuilder.newClient().target("https://api.binance.com/api/v3/ticker/price")
				.queryParam("symbol", "BTCUSDT").request(MediaType.APPLICATION_JSON).get(Ticker.class);
		System.err.println(ticker);
		return ticker;
	}

	@SuppressWarnings("unused")
	private Ticker fallbackForServiceJ() {
		return new Ticker("btcusdt", "10_000.0");
	}
}
