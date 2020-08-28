package com.example.stockmarket.repository.jpa;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.stockmarket.domain.Company;
import com.example.stockmarket.domain.Description;
import com.example.stockmarket.domain.Price;
import com.example.stockmarket.domain.Stock;
import com.example.stockmarket.domain.Symbol;
import com.example.stockmarket.entity.StockEntity;
import com.example.stockmarket.repository.StockRepository;

@Stateless
public class JpaStockRepository implements StockRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Stock> findBySymbol(Symbol symbol) {
		return convertStockEntityToOptionalStock(symbol, entityManager.find(StockEntity.class, symbol.getValue()));
	}

	@Override
	public List<Stock> find(int page, int size) {
		return entityManager.createNamedQuery("StockEntity.findAll", StockEntity.class).setFirstResult(page * size)
				.setMaxResults(size).getResultList().stream()
				.map(stockEntity -> convertStockEntityToStock(Symbol.of(stockEntity.getSymbol()), stockEntity))
				.collect(toList());
	}

	@Override
	@Transactional
	public void createStock(Stock stock) {
		entityManager.persist(convertStockToStockEntity(stock));
	}

	@Override
	@Transactional
	public void saveStock(Stock stock) {
		var stockEntity = convertStockToStockEntity(stock);
		var managedStockEntity = entityManager.find(StockEntity.class, stock.getSymbol().getValue());
		if (Objects.isNull(managedStockEntity))
			throw new IllegalArgumentException("Cannot find stock to update");
		managedStockEntity.setPrice(stockEntity.getPrice());
		managedStockEntity.setCurrency(stockEntity.getCurrency());
	}

	@Override
	@Transactional
	public Optional<Stock> removeStock(Symbol symbol) {
		var managedStockEntity = entityManager.find(StockEntity.class, symbol.getValue());
		if (Objects.nonNull(managedStockEntity))
			entityManager.remove(managedStockEntity);
		return convertStockEntityToOptionalStock(symbol, managedStockEntity);
	}

	@Override
	public List<Stock> findByCompanyUrl(String url) {
		return entityManager.createNamedQuery("StockEntity.ByCompanyUrl", StockEntity.class).setParameter("url", url)
				.getResultList().stream()
				.map(stockEntity -> convertStockEntityToStock(Symbol.of(stockEntity.getSymbol()), stockEntity))
				.collect(toList());
	}

	private StockEntity convertStockToStockEntity(Stock stock) {
		return new StockEntity(stock.getSymbol().getValue(), stock.getDecription().getValue(),
				stock.getCompany().getUrl(), stock.getCompany().getName(), stock.getPrice().getValue(),
				stock.getPrice().getCurrency());
	}

	private Optional<Stock> convertStockEntityToOptionalStock(Symbol symbol, StockEntity stockEntity) {
		if (Objects.isNull(stockEntity))
			return Optional.empty();
		var stock = new Stock(symbol, new Company(stockEntity.getCompanyUrl(), stockEntity.getCompanyname()));
		stock.setPrice(new Price(stockEntity.getPrice(), stockEntity.getCurrency()));
		stock.setDecription(new Description(stockEntity.getDescription()));
		return Optional.of(stock);
	}

	private Stock convertStockEntityToStock(Symbol symbol, StockEntity stockEntity) {
		var stock = new Stock(symbol, new Company(stockEntity.getCompanyUrl(), stockEntity.getCompanyname()));
		stock.setPrice(new Price(stockEntity.getPrice(), stockEntity.getCurrency()));
		stock.setDecription(new Description(stockEntity.getDescription()));
		return stock;
	}
}
