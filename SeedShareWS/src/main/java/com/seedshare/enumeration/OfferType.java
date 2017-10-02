package com.seedshare.enumeration;

public enum OfferType {
	Donation(0), 
	Sale(1);
	
	private int offerType;
	
	OfferType(int offerType) {
        this.offerType = offerType;
    }

    public int getOfferType() { 
        return this.offerType;
    }
}
