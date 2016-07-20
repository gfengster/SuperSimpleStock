package net.gfeng.exchange;

import net.gfeng.stock.Trade;

public interface Exchange {
	void exchange(String stockSymbol, Trade trade);
	
	void exchange(String stockSymbol, String tradetype, long quantity, double price, long timeStamp);
}
