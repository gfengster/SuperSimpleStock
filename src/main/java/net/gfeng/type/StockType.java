package net.gfeng.type;

/**
 * Stock types in enum format.
 */
public enum StockType {
	COMMON("Common"), 
	PREFERRED("Preferred");
	
	private final String mDesc;
	private StockType(String desc) {
		this.mDesc = desc;
	}
	
	public String getDescription(){
		return this.mDesc;
	}
}
