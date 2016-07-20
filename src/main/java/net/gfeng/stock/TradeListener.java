package net.gfeng.stock;

import java.util.concurrent.TimeUnit;

public interface TradeListener {
	public static final long FIFTEEN_MINUTES = TimeUnit.MINUTES.toMillis(15);
	
	void add(Trade trade);
}

