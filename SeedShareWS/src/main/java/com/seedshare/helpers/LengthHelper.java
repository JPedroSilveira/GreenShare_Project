package com.seedshare.helpers;

/**
 * @author joao.silva
 */
public class LengthHelper {
	
	private String string;
	private int integer;
	private short shortV;
	private long longV;
	private float floatV;
	private double doubleV;
	private short type;
	
	public LengthHelper(String number) {
		this.string = number;
		this.type = 0;
	}
	
	public LengthHelper(int integer) {
		this.integer = integer;
		this.type = 1;
	}
	
	public LengthHelper(short shortV) {
		this.shortV = shortV;
		this.type = 2;
	}
	
	public LengthHelper(long longV) {
		this.longV = longV;
		this.type = 3;
	}
	
	public LengthHelper(float floatV) {
		this.floatV = floatV;
		this.type = 4;
	}
	
	public LengthHelper(double doubleV) {
		this.doubleV = doubleV;
		this.type = 5;
	}


	public boolean equal(double equalNumber) {
		if(this.type == 0) {
			return this.string.length() == equalNumber;
		}else if(this.type == 1) {
			return this.integer == equalNumber;
		}else if(this.type == 2) {
			return this.shortV == equalNumber;
		}else if(this.type == 3) {
			return this.longV == equalNumber;
		}else if(this.type == 4) {
			return this.floatV == equalNumber;
		}else if(this.type == 5) {
			return this.doubleV == equalNumber;
		}
		
		return false;
	}
	
	public boolean notEqual(double notEqualNumber) {
		if(this.type == 0) {
			return this.string.length() != notEqualNumber;
		}else if(this.type == 1) {
			return this.integer != notEqualNumber;
		}else if(this.type == 2) {
			return this.shortV != notEqualNumber;
		}else if(this.type == 3) {
			return this.longV != notEqualNumber;
		}else if(this.type == 4) {
			return this.floatV != notEqualNumber;
		}else if(this.type == 5) {
			return this.doubleV != notEqualNumber;
		}
		
		return false;
	}
	
	public boolean smallerThan(double biggerNumber) {
		if(this.type == 0) {
			return this.string.length() < biggerNumber;
		}else if(this.type == 1) {
			return this.integer < biggerNumber;
		}else if(this.type == 2) {
			return this.shortV < biggerNumber;
		}else if(this.type == 3) {
			return this.longV < biggerNumber;
		}else if(this.type == 4) {
			return this.floatV < biggerNumber;
		}else if(this.type == 5) {
			return this.doubleV < biggerNumber;
		}
		
		return false;
	}
	
	public boolean smallerOrEqual(double number) {
		if(this.type == 0) {
			return this.string.length() <= number;
		}else if(this.type == 1) {
			return this.integer <= number;
		}else if(this.type == 2) {
			return this.shortV <= number;
		}else if(this.type == 3) {
			return this.longV <= number;
		}else if(this.type == 4) {
			return this.floatV <= number;
		}else if(this.type == 5) {
			return this.doubleV <= number;
		}
		
		return false;
	}
	
	public LengthOr orSmallerThan(double biggerNumber) {
		if(this.type == 0) {
			if(this.string.length() < biggerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 1) {
			if(this.integer < biggerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 2) {
			if(this.shortV < biggerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 3) {
			if(this.longV < biggerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 4) {
			if(this.floatV < biggerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 5) {
			if(this.doubleV < biggerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}
		return new LengthOr(this, null);
	}
	
	public boolean biggerThan(double smallerNumber) {
		if(this.type == 0) {
			return this.string.length() > smallerNumber;
		}else if(this.type == 1) {
			return this.integer > smallerNumber;
		}else if(this.type == 2) {
			return this.shortV > smallerNumber;
		}else if(this.type == 3) {
			return this.longV > smallerNumber;
		}else if(this.type == 4) {
			return this.floatV > smallerNumber;
		}else if(this.type == 5) {
			return this.doubleV > smallerNumber;
		}
		return false;
	}
	
	public boolean biggerOrEqual(double number) {
		if(this.type == 0) {
			return this.string.length() >= number;
		}else if(this.type == 1) {
			return this.integer >= number;
		}else if(this.type == 2) {
			return this.shortV >= number;
		}else if(this.type == 3) {
			return this.longV >= number;
		}else if(this.type == 4) {
			return this.floatV >= number;
		}else if(this.type == 5) {
			return this.doubleV >= number;
		}
		return false;
	}
	
	public LengthOr orBiggerThan(double smallerNumber) {
		if(this.type == 0) {
			if(this.string.length() > smallerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 1) {
			if(this.integer > smallerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 2) {
			if(this.shortV > smallerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 3) {
			if(this.longV > smallerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 4) {
			if(this.floatV > smallerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}else if(this.type == 5) {
			if(this.doubleV > smallerNumber) {
				return new LengthOr(this, true);
			}
			return new LengthOr(this, false);
		}
		return new LengthOr(this, null);
	}
	
	public LengthAnd between(double smallerNumber) {
		if(this.type == 0) {
			if(this.string.length() > smallerNumber) {
				return new LengthAnd(this, true);
			}		
		}else if(this.type == 1) {
			if(this.integer > smallerNumber) {
				return new LengthAnd(this, true);
			}	
		}else if(this.type == 2) {
			if(this.shortV > smallerNumber) {
				return new LengthAnd(this, true);
			}	
		}else if(this.type == 3) {
			if(this.longV > smallerNumber) {
				return new LengthAnd(this, true);
			}
		}else if(this.type == 4) {
			if(this.floatV > smallerNumber) {
				return new LengthAnd(this, true);
			}
		}else if(this.type == 5) {
			if(this.doubleV > smallerNumber) {
				return new LengthAnd(this, true);
			}
		}
		return new LengthAnd(this, false);
	}
    
    public class LengthAnd {
        
    	private LengthHelper lengthHelper;
    	private Short type;
    	private boolean isTrue;
    	
    	public LengthAnd(LengthHelper lengthHelper, Boolean isTrue) {
    		this.lengthHelper = lengthHelper;
    		this.isTrue = isTrue;
    		this.type = lengthHelper.type;
    	}
    	
    	public boolean and(double biggerNumber) {
    		if(isTrue) {
    			if(this.type == 0) {
        			return this.lengthHelper.string.length() < biggerNumber;
        		}else if(this.type == 1) {
        			return this.lengthHelper.integer < biggerNumber;
        		}else if(this.type == 2) {
        			return this.lengthHelper.shortV < biggerNumber;
        		}else if(this.type == 3) {
        			return this.lengthHelper.longV < biggerNumber;
        		}else if(this.type == 4) {
        			return this.lengthHelper.floatV < biggerNumber;
        		}else if(this.type == 5) {
        			return this.lengthHelper.doubleV < biggerNumber;
        		}
    		}    		
    		return false;
    	}   
    }
    
    public class LengthOr {
        
    	private LengthHelper lengthHelper;
    	private Short type;
    	private Boolean isTrue;
    	
    	public LengthOr(LengthHelper lengthHelper, Boolean isTrue) {
    		this.lengthHelper = lengthHelper;
    		this.isTrue = isTrue;
    		this.type = lengthHelper.type;
    	}
    	
    	public boolean orEqual(double equalNumber) {
    		if(this.isTrue != null) {
    			if(this.isTrue) {
    				return true;
    			}
    			if(this.type == 0) {
        			return this.lengthHelper.string.length() == equalNumber;
        		}else if(this.type == 1) {
        			return this.lengthHelper.integer == equalNumber;
        		}else if(this.type == 2) {
        			return this.lengthHelper.shortV == equalNumber;
        		}else if(this.type == 3) {
        			return this.lengthHelper.longV == equalNumber;
        		}else if(this.type == 4) {
        			return this.lengthHelper.floatV == equalNumber;
        		}else if(this.type == 5) {
        			return this.lengthHelper.doubleV == equalNumber;
        		}
    		}
    		return false;
    	}   
    	
    	public boolean orBiggerThan(double smallerNumber) {
    		if(this.isTrue != null) {
    			if(this.isTrue) {
    				return true;
    			}
	    		if(this.type == 0) {
	    			return this.lengthHelper.string.length() > smallerNumber;
	    		}else if(this.type == 1) {
	    			return this.lengthHelper.integer > smallerNumber;
	    		}else if(this.type == 2) {
	    			return this.lengthHelper.shortV > smallerNumber;
	    		}else if(this.type == 3) {
	    			return this.lengthHelper.longV > smallerNumber;
	    		}else if(this.type == 4) {
	    			return this.lengthHelper.floatV > smallerNumber;
	    		}else if(this.type == 5) {
	    			return this.lengthHelper.doubleV > smallerNumber;
	    		}
    		}
    		return false;
    	}
    	
    	public boolean orSmallerThan(double biggerNumber) {
    		if(this.isTrue != null) {
    			if(this.isTrue) {
    				return true;
    			}
	    		if(this.type == 0) {
	    			return this.lengthHelper.string.length() < biggerNumber;
	    		}else if(this.type == 1) {
	    			return this.lengthHelper.integer < biggerNumber;
	    		}else if(this.type == 2) {
	    			return this.lengthHelper.shortV < biggerNumber;
	    		}else if(this.type == 3) {
	    			return this.lengthHelper.longV < biggerNumber;
	    		}else if(this.type == 4) {
	    			return this.lengthHelper.floatV < biggerNumber;
	    		}else if(this.type == 5) {
	    			return this.lengthHelper.doubleV < biggerNumber;
	    		}
    		}
    		return false;
    	}
    }
}

