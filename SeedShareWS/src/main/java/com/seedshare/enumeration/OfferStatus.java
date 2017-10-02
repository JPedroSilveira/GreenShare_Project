package com.seedshare.enumeration;

public enum OfferStatus {
	Active(0), 
	Closed(1);
	
	private int offerStatus;
	
	OfferStatus(int offerStatus) {
        this.offerStatus = offerStatus;
    }

    public int getOfferStatus() { 
        return this.offerStatus;
    }
}
