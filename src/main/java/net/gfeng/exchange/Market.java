package net.gfeng.exchange;

import net.gfeng.stock.Stock;
import net.gfeng.stock.Trade;
import net.gfeng.type.TradeType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Market implements Exchange, StockChangeListener {
	
	private final Map<String, Stock> mStocks;
	
	public Market(){
		mStocks = Collections.synchronizedMap(new HashMap<String, Stock>());
	}
	
	public void addStock(Stock stock) {
		mStocks.put(stock.getSymbol(), stock);
	}

	public void removeStock(String stockSymbol) {
		mStocks.remove(stockSymbol);
		
	}

	public void exchange(String stockSymbol, Trade trade) {
		final Stock stock = mStocks.get(stockSymbol);
		if (stock != null) {
			stock.add(trade);
		} else
			throw new RuntimeException(stockSymbol + " is not a valid stock");
	}
	
	public void exchange(String stockSymbol, String tradetype, long quantity, double price, long timeStamp) {
		final Stock stock = mStocks.get(stockSymbol);
		if (stock == null) {
			throw new RuntimeException(stockSymbol + " cannot find");
		}
		
		final TradeType type = TradeType.getType(tradetype);
		
		if (type == TradeType.UNKNOWN)
			throw new RuntimeException(tradetype + " is not a valid trade type");
		
		final Trade trade = new Trade(type, quantity, price, timeStamp);
		
		stock.add(trade);
	}
	
	public double getDividendYield(String stockSymbol){
		final Stock stock = mStocks.get(stockSymbol);
		if (stock != null) {
			return stock.getDividendYield();
		} else {
			throw new RuntimeException(stockSymbol + " cannot find");
		}
	}
	
	public double getPERatio(String stockSymbol){
		final Stock stock = mStocks.get(stockSymbol);
		if (stock != null) {
			return stock.getPERatio();
		} else {
			throw new RuntimeException(stockSymbol + " cannot find");
		}
	}
	
	public double getVolumeWeightedStockPrice(String stockSymbol){
		final Stock stock = mStocks.get(stockSymbol);
		if (stock != null) {
			return stock.getVolumeWeightedStockPrice();
		} else {
			throw new RuntimeException(stockSymbol + " cannot find");
		}
	}
	
	public Stock getStock(String stockSymbol) {
		final Stock stock = mStocks.get(stockSymbol);
		if (stock != null) {
			return stock;
		} else {
			throw new RuntimeException(stockSymbol + " cannot find");
		}
	}
	
	public double getGBCE(){
		double gm = 1;
		long n = 1;
		
		for (Stock stock : mStocks.values()) {
			double tmp = Math.pow(gm, n);
			tmp *= stock.getLastPrice();
			gm = Math.pow(tmp, 1.0d/(double)n);
			n++;
		}
		
		return gm;
	}
}
