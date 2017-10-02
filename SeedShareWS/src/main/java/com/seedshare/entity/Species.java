package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Persistence class for the table SPECIES
 * @author joao.silva
 */
@Entity
@Table(name = "SPECIES")
public class Species extends BasicEntity implements Serializable {
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
	private List<Post> posts;

	@OneToMany(mappedBy="species")
	private List<Suggestion> suggestions;
	
	@JsonIgnore
	@Transient
	private List<String> validationErrors;

	protected Species() {
		this.validationErrors = new ArrayList<String>();
	}
	
	public Species(Boolean attractBirds, String description, String photoUrl, String cultivationGuide, Boolean isMedicinal, 
			Boolean attractBees, String scientificName, String commonName, Boolean isOrnamental, Long averageHeight, 
			Growth growth) {
		super();
		this.isApproved = false;
		this.attractBirds = attractBirds;
		this.insertionDate = new Date();
		this.description = description;
		this.photoUrl = photoUrl;
		this.cultivationGuide = cultivationGuide;
		this.isMedicinal = isMedicinal;
		this.attractBees = attractBees;
		this.scientificName = scientificName;
		this.commonName = commonName;
		this.isOrnamental = isOrnamental;
		this.averageHeight = averageHeight;
		this.growth = growth;
		this.validationErrors = new ArrayList<String>();
	}

	
	public Species generateNewValidation() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || this.description.length()>2500){
			this.validationErrors.add("Descrição inválida.");
		}
		if(isNullOrEmpty(this.cultivationGuide) || this.cultivationGuide.length()>5000){
			this.validationErrors.add("Guia de cultivo inválido.");
		}
		if(this.isApproved == null){
			this.validationErrors.add("Definição de aprovação inválida.");
		}
		if(this.attractBees == null){
			this.validationErrors.add("Definição de atração de abelhas inválida.");
		}
		if(this.attractBirds == null){
			this.validationErrors.add("Definição de atração de pássaros inválida.");
		}
		if(this.isMedicinal == null){
			this.validationErrors.add("Definição de planta medicinal inválida.");
		}
		if(this.isOrnamental == null){
			this.validationErrors.add("Definição de planta ornamental inválida.");
		}
		if(this.averageHeight == null || this.averageHeight > 30000){
			this.validationErrors.add("Altura inválida.");
		}
		if(this.commonName == null || this.commonName.length() > 100){
			this.validationErrors.add("Nome popular inválido.");
		}
		if(this.scientificName == null || this.scientificName.length() > 100){
			this.validationErrors.add("Nome científico inválido.");
		}
		if(this.growth == null || !(this.growth.generateNewValidation().isValid())){
			this.validationErrors.add("Nível de crescimento inválido.");
		}
		if(this.photoUrl == null || this.photoUrl.length() > 2500){
			this.validationErrors.add("URL da foto inválida.");
		}
		if(this.insertionDate == null || this.insertionDate.after(new Date())) {
			this.validationErrors.add("Data de inserção inválida");
		}
		return this;
	}
	
	@JsonIgnore
	public Boolean isValid() {
		return this.validationErrors.isEmpty();
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

	public List<Suggestion> getSuggestions() {
		return this.suggestions;
	}

}