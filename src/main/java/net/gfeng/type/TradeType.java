package net.gfeng.type;

public enum TradeType {
	BUY, 
	SELL,
	UNKNOWN;

	/**
	 * Get a TradeType from a given string. The string comparison is case ignore.
	 * 
	 * @param type String
	 * @return TradeType
	 */
	public static TradeType getType(String type) {
		for (TradeType t : TradeType.values()) {
			if (t.name().equalsIgnoreCase(type))
				return t;
		}
		
		return TradeType.UNKNOWN;
	}
}
