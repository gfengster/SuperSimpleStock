package net.gfeng.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.gfeng.exchange.Market;
import net.gfeng.stock.Stock;
import net.gfeng.type.StockType;

public class MarketTest {
	
	final Market market = new Market();
	
	@Before
	public void setup(){
		Stock stock;
		
		stock = new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0);
		market.addStock(stock);
		
		stock = new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0);
		market.addStock(stock);
		
		stock = new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0);
		market.addStock(stock);
		
        stock = new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0);
        market.addStock(stock);

        stock = new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0);
        market.addStock(stock);
	}
	
	@Test
	public void testBuy() {
		market.exchange("POP", "BUY", 2, 12.3, System.currentTimeMillis());
		
		assertEquals(12.3, market.getStock("POP").getLastPrice(), 0.0);
	}

	@Test
	public void testSell() {
		market.exchange("POP", "Sell", 2, 12.3, System.currentTimeMillis());
		
		assertEquals(12.3, market.getStock("POP").getLastPrice(), 0.0);
	}
}
