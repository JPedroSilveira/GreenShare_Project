package com.greenshare.enumeration;

/**
 * Enum for Type of {@link com.greenshare.entity.offer.Offer}
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
public enum OfferType {
	Donation(0), Sale(1);

	private int offerType;

	OfferType(int offerType) {
		this.offerType = offerType;
	}

	public int getOfferType() {
		return this.offerType;
	}

	public static boolean exists(int id) {
		for (OfferType e : values()) {
			if (e.getOfferType() == id) {
				return true;
			}
		}
		return false;
	}
}
