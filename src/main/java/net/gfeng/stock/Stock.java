package net.gfeng.stock;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import net.gfeng.type.StockType;


/**
 * Holding a stock information and trades records.
 */
public class Stock implements TradeListener {
		
	private final String mSymbol;
	private final StockType mType;
	private double mLastDividend;
	private final double mFixedDividend;
	private final double mParValue;
    private final SortedMap<Long, Trade> mTrades;
    
	public Stock(String symbol, StockType type, double lastDividend, double fixedDividend, Double parValue) {
		this.mSymbol = symbol;
		this.mType = type;
		this.mLastDividend = lastDividend;
		this.mFixedDividend = fixedDividend;
		this.mParValue = parValue;
		this.mTrades = Collections.synchronizedSortedMap(new TreeMap<Long, Trade>());
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + mSymbol + ", type=" + mType + ", lastDividend="
				+ mLastDividend + ", fixedDividend=" + mFixedDividend
				+ ", parValue=" + mParValue + "]";
	}
	
	public String getSymbol(){
		return mSymbol;
	}

	public double getDividendYield() {
		
		final double lastPrice = getLastPrice();
		
		if (lastPrice == 0)
			throw new RuntimeException("Market price 0 passed in getDividend.");
				
		switch(mType) {
			case COMMON:
				return mLastDividend/lastPrice;
			case PREFERRED:
				return (mFixedDividend * mParValue)/lastPrice;
			default:
				return 0.0;
		}
	}
	
	public double getPERatio() {
		if (mLastDividend == 0)
			throw new RuntimeException("LastDividend is 0 in PE ratio calculation.");
		
		final double lastPrice = getLastPrice();
		
		return lastPrice/mLastDividend;
	}
	
	public double getLastPrice() {
		if (!mTrades.isEmpty()) {
			final long lastKey = mTrades.lastKey();
			return mTrades.get(lastKey).getPrice();
		} else {
			return 0.0d;
		}
	}
	
	public double getVolumeWeightedStockPrice() {
		final long now = System.currentTimeMillis();
		
		final long startTime = now - FIFTEEN_MINUTES;
		
		final SortedMap<Long, Trade> last15MinsTrades = mTrades.tailMap(startTime);
		double volumeWeigthedStockPrice = 0.0d;
		long totalQuantity = 0L;
		
		for (Trade trade : last15MinsTrades.values()) {
			volumeWeigthedStockPrice = totalQuantity * volumeWeigthedStockPrice 
										+ trade.getPrice() * trade.getQuantity();
			
			volumeWeigthedStockPrice 
				= volumeWeigthedStockPrice / (totalQuantity + trade.getQuantity());
					
			totalQuantity += trade.getQuantity();
		}
		
		return volumeWeigthedStockPrice;
	}

	public double getGeometricMean(){
		double gm = 1;
		long n = 1;
		
		for (Trade trade : mTrades.values()) {
			double tmp = Math.pow(gm, n);
			tmp *= trade.getPrice();
			gm = Math.pow(tmp, 1.0d/(double)n);
			n++;
		}
		
		return gm;
	}
	
	@Override
	public void add(Trade trade) {
		if (trade.getQuantity() == 0)
			throw new RuntimeException("The trade quantity is 0 on stock " + mSymbol);
			
		mTrades.put(trade.getTimeStamp(), trade);
	}
}
