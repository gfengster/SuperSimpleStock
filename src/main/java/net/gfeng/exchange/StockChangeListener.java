package net.gfeng.exchange;

import net.gfeng.stock.Stock;

public interface StockChangeListener {
	void addStock(Stock stock);
	void removeStock(String stockSymbol);
}
