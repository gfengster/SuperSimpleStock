package net.gfeng.type;

public enum TradeType {
	BUY, 
	SELL,
	UNKNOWN;
	
	public static TradeType getType(String type) {
		
		for (TradeType t : TradeType.values()) {
			if (t.name().equalsIgnoreCase(type))
				return t;
		}
		
		return TradeType.UNKNOWN;
	}
}
