package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractPhotogenicEntity;
import com.seedshare.enumeration.PhotoType;

import java.util.List;


/**
 * Persistence class for the table species
 * @author joao.silva
 */
@Entity
@Table(name = "species")
public class Species extends AbstractPhotogenicEntity<Species> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "species_seq";
	
	private static final PhotoType PHOTO_TYPE = PhotoType.SPECIES;
		
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "species_id")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "approved")
	private Boolean isApproved;

	@Basic(optional = false)
	@NotNull
	@Column(name = "attract_birds")
	private Boolean attractBirds;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "description", columnDefinition="TEXT", length = 2500)
	private String description;
	
	@Basic(optional = false)
	@NotNull
	@Size(max = 5000)
	@Column(name = "cultivation_guide", columnDefinition="TEXT", length = 5000)
	private String cultivationGuide;

	@Basic(optional = false)
	@NotNull
	@Column(name = "medicinal")
	private Boolean isMedicinal;

	@Basic(optional = false)
	@NotNull
	@Column(name = "attract_bees")
	private Boolean attractBees;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "scientific_name", length = 100)
	private String scientificName;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "common_name", length = 100)
	private String commonName;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ornamental")
	private Boolean isOrnamental;

	@Basic(optional = false)
	@NotNull
	@Column(name = "average_height")
	private Long averageHeight;

	@ManyToOne
	@JoinColumn(name="growth_id")
	private Growth growth;

	@ManyToMany
	@JoinTable(
		name="species_climate"
		, joinColumns={
			@JoinColumn(name="species_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="climate_id")
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
	private List<Post> posts;

	@OneToOne(mappedBy="species")
	private Suggestion suggestions;

	protected Species() {
		super(PHOTO_TYPE);
	}
	
	public Species(Boolean attractBirds, String description, String cultivationGuide, Boolean isMedicinal, 
			Boolean attractBees, String scientificName, String commonName, Boolean isOrnamental, Long averageHeight, 
			Growth growth) {
		super(PHOTO_TYPE, true);
		this.isApproved = false;
		this.attractBirds = attractBirds;
		this.description = description;
		this.cultivationGuide = cultivationGuide;
		this.isMedicinal = isMedicinal;
		this.attractBees = attractBees;
		this.scientificName = scientificName;
		this.commonName = commonName;
		this.isOrnamental = isOrnamental;
		this.averageHeight = averageHeight;
		this.growth = growth;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || is(this.description).biggerThan(2500)){
			this.validationErrors.add("Descrição inválida.");
		}
		if(isNullOrEmpty(this.cultivationGuide) || is(this.cultivationGuide).biggerThan(5000)){
			this.validationErrors.add("Guia de cultivo inválido.");
		}
		if(isNull(this.isApproved)){
			this.validationErrors.add("Definição de aprovação inválida.");
		}
		if(isNull(this.attractBees)){
			this.validationErrors.add("Definição de atração de abelhas inválida.");
		}
		if(isNull(this.attractBirds)){
			this.validationErrors.add("Definição de atração de pássaros inválida.");
		}
		if(isNull(this.isMedicinal)){
			this.validationErrors.add("Definição de planta medicinal inválida.");
		}
		if(isNull(this.isOrnamental)){
			this.validationErrors.add("Definição de planta ornamental inválida.");
		}
		if(isNull(this.averageHeight) || is(this.averageHeight).biggerThan(30000)){
			this.validationErrors.add("Altura inválida.");
		}
		if(isNull(this.commonName) || is(this.commonName).biggerThan(100)){
			this.validationErrors.add("Nome popular inválido.");
		}
		if(isNull(this.scientificName) || is(this.scientificName).biggerThan(100)){
			this.validationErrors.add("Nome científico inválido.");
		}
		if(isNull(this.growth) || this.growth.isNotValid()){
			this.validationErrors.add("Nível de crescimento inválido.");
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Soil> getSoils() {
		return this.soils;
	}

	public List<Flower> getFlowers() {
		return this.flowers;
	}

	public List<Fruit> getFruits() {
		return this.fruits;
	}

	public List<Offer> getOffers() {
		return this.offers;
	}
	
	public List<Post> getPosts() {
		return this.posts;
	}

	public Suggestion getSuggestions() {
		return this.suggestions;
	}
}