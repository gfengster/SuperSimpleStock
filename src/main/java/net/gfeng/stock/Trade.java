package net.gfeng.stock;

import net.gfeng.type.TradeType;

public class Trade {
	
	private final TradeType mType;
	private final long mQuantity;
	private final double mPrice;
	private final long mTimeStamp;

	public Trade(TradeType type, long quantity, double price, long timeStamp) {
		this.mType = type;
		this.mQuantity = quantity;
		this.mPrice = price;
		this.mTimeStamp = timeStamp;
	}

	public TradeType getType() {
		return mType;
	}

	public long getQuantity() {
		return mQuantity;
	}

	public double getPrice() {
		return mPrice;
	}
	
	public long getTimeStamp(){
		return mTimeStamp;
	}
	
	@Override
	public String toString() {
		return "Trade [type=" + mType + ", quantity=" + mQuantity + ", price="
				+ mPrice + ", timestamp=" + mTimeStamp + "]";
	}
}
