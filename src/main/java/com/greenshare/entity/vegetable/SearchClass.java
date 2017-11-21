package com.greenshare.entity.vegetable;

import java.io.Serializable;
import java.util.List;

/**
 * Helper class to search on {@link com.greenshare.entity.offer.Offer}
 * 
 * @author joao.silva
 */
public class SearchClass implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<Growth> growth;
    public Species species;
    public List<Soil> soil;
    public List<Climate> climate;
    public Boolean isMedicinal;
    public Boolean attractBirds;
    public Boolean attractBees;
    public Boolean isOrnamental;
    public Boolean hasFlower;
    public Boolean hasFruit;
    public Integer rootDepth;
    public Integer averageHeight;

    public SearchClass(List<Growth> growth, Species species, List<Soil> soil, List<Climate> climate, Boolean isMedicinal, Boolean attractBirds, Boolean attractBees, Boolean isOrnamental, Boolean hasFruit, Integer rootDepth, Integer averageHeight, Boolean hasFlower) {
        this.growth = growth;
        this.species = species;
        this.soil = soil;
        this.climate = climate;
        this.isMedicinal = isMedicinal;
        this.attractBirds = attractBirds;
        this.attractBees = attractBees;
        this.isOrnamental = isOrnamental;
        this.hasFruit = hasFruit;
        this.rootDepth = rootDepth;
        this.averageHeight = averageHeight;
        this.hasFlower = hasFlower;
    }

    public SearchClass(){}
}
