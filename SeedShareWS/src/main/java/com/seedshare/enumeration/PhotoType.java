package com.seedshare.enumeration;

public enum PhotoType {
	USER(0,"User"), 
	POST(1,"Post"),
	FLOWER_SHOP(2,"FlowerShop"),
	SPECIES(3,"Species");
	
	private String directoryName;
	private int numberType;
	
	PhotoType(int numberType, String directoryName) {
		this.numberType = numberType;
        this.directoryName = directoryName;
    }

    public int getNumberType() { 
        return this.numberType;
    }
    
    public String getDirectoryName() {
    	return this.directoryName;
    }
}
