package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;


/**
 * Persistence class for the table SPECIES
 * @author joao.silva
 */
@Entity
@Table(name = "SPECIES")
public class Species implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SPECIES_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "SPECIES_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "APPROVED")
	private Boolean isApproved;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ATTRACT_BIRDS")
	private Boolean attractBirds;

	@Basic(optional = false)
	@NotNull
	@Column(name = "INSERTION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertionDate;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRIPTION", columnDefinition="TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "PHOTO_URL", columnDefinition="TEXT", length = 2500)
	private String photoUrl;

	@Basic(optional = false)
	@NotNull
	@Size(max = 5000)
	@Column(name = "CULTIVATION_GUIDE", columnDefinition="TEXT", length = 5000)
	private String cultivationGuide;

	@Basic(optional = false)
	@NotNull
	@Column(name = "MEDICINAL")
	private Boolean isMedicinal;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ATTRACT_BEES")
	private Boolean attractBees;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "SCIENTIFIC_NAME", length = 100)
	private String scientificName;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "COMMON_NAME", length = 100)
	private String commonName;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ORNAMENTAL")
	private Boolean isOrnamental;

	@Basic(optional = false)
	@NotNull
	@Column(name = "AVERAGE_HEIGHT")
	private Long averageHeight;

	@ManyToOne
	@JoinColumn(name="GROWTH_ID")
	private Growth growth;

	@ManyToMany
	@JoinTable(
		name="SPECIES_CLIMATE"
		, joinColumns={
			@JoinColumn(name="SPECIES_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="CLIMATE_ID")
			}
		)
	private List<Climate> climates;
	
	@ManyToMany(mappedBy="species")
	private List<Soil> soils;

	@OneToMany(mappedBy="species")
	private List<Flower> flowers;

	@OneToMany(mappedBy="species")
	private List<Fruit> fruits;

	@OneToMany(mappedBy="species")
	private List<Offer> offers;

	@OneToMany(mappedBy="species")
	private List<Suggestion> suggestions;

	protected Species() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Boolean getAttractBirds() {
		return this.attractBirds;
	}

	public void setAttractBirds(Boolean attractBirds) {
		this.attractBirds = attractBirds;
	}

	public Date getInsertionDate() {
		return this.insertionDate;
	}

	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getCultivationGuide() {
		return this.cultivationGuide;
	}

	public void setCultivationGuide(String cultivationGuide) {
		this.cultivationGuide = cultivationGuide;
	}

	public Boolean getIsMedicinal() {
		return this.isMedicinal;
	}

	public void setIsMedicinal(Boolean isMedicinal) {
		this.isMedicinal = isMedicinal;
	}

	public Boolean getAttractBees() {
		return this.attractBees;
	}

	public void setAttractBees(Boolean attractBees) {
		this.attractBees = attractBees;
	}

	public String getScientificName() {
		return this.scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getCommonName() {
		return this.commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public Boolean getIsOrnamental() {
		return this.isOrnamental;
	}

	public void setIsOrnamental(Boolean isOrnamental) {
		this.isOrnamental = isOrnamental;
	}

	public Long getAverageHeight() {
		return this.averageHeight;
	}

	public void setAverageHeight(Long averageHeight) {
		this.averageHeight = averageHeight;
	}

	public Growth getGrowth() {
		return this.growth;
	}

	public void setGrowth(Growth growth) {
		this.growth = growth;
	}

	public List<Climate> getClimates() {
		return this.climates;
	}

	public void setClimas(List<Climate> climates) {
		this.climates = climates;
	}

	public List<Soil> getSoils() {
		return this.soils;
	}

	public void setSoils(List<Soil> soils) {
		this.soils = soils;
	}

	public List<Flower> getFlowers() {
		return this.flowers;
	}

	public void setFlowers(List<Flower> flowers) {
		this.flowers = flowers;
	}

	public Flower addFlower(Flower flower) {
		getFlowers().add(flower);
		flower.setSpecies(this);

		return flower;
	}

	public Flower removeFlower(Flower flower) {
		getFlowers().remove(flower);
		flower.setSpecies(null);

		return flower;
	}

	public List<Fruit> getFruits() {
		return this.fruits;
	}

	public void setFruits(List<Fruit> fruits) {
		this.fruits = fruits;
	}

	public Fruit addFruit(Fruit fruit) {
		getFruits().add(fruit);
		fruit.setSpecies(this);

		return fruit;
	}

	public Fruit removeFruit(Fruit fruit) {
		getFruits().remove(fruit);
		fruit.setSpecies(null);

		return fruit;
	}

	public List<Offer> getOffers() {
		return this.offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Offer addOffer(Offer offers) {
		getOffers().add(offers);
		offers.setSpecies(this);

		return offers;
	}

	public Offer removeOffer(Offer offers) {
		getOffers().remove(offers);
		offers.setSpecies(null);

		return offers;
	}

	public List<Suggestion> getSuggestions() {
		return this.suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	public Suggestion addSuggestion(Suggestion suggestion) {
		getSuggestions().add(suggestion);
		suggestion.setSpecies(this);

		return suggestion;
	}

	public Suggestion removeSuggestion(Suggestion suggestion) {
		getSuggestions().remove(suggestion);
		suggestion.setSpecies(null);

		return suggestion;
	}

}