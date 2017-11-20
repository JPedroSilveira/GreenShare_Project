package com.greenshare.entity.vegetable;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.Suggestion;
import com.greenshare.entity.abstracts.AbstractPhotogenicEntity;
import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.post.Post;
import com.greenshare.enumeration.PhotoType;

import java.util.List;

/**
 * Persistence class for the table species
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "species")
public class Species extends AbstractPhotogenicEntity<Species> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "species_seq";

	@Transient
	private static final PhotoType PHOTO_TYPE = PhotoType.SPECIES;

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "species_id")
	private Long id;

	@Basic(optional = false)
	@Column(name = "enabled")
	private Boolean enabled;

	@Basic(optional = false)
	@NotNull(message = "É obrigatório informar se a espécie atrai passáros.")
	@Column(name = "attract_birds")
	private Boolean attractBirds;

	@Basic(optional = false)
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "A descrição deve conter entre 1 e 2500 caracteres.")
	@Column(name = "description", columnDefinition = "TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull(message = "O guia de cultivo não pode ser nulo.")
	@Size(min = 1, max = 5000, message = "O guia de cultivo deve conter entre 1 e 5000 caracteres.")
	@Column(name = "cultivation_guide", columnDefinition = "TEXT", length = 5000)
	private String cultivationGuide;

	@Basic(optional = false)
	@NotNull(message = "É obrigatório informar se a espécie é medicinal.")
	@Column(name = "medicinal")
	private Boolean isMedicinal;

	@Basic(optional = false)
	@NotNull(message = "É obrigatório informar se a espécie atrai passáros.")
	@Column(name = "attract_bees")
	private Boolean attractBees;

	@Basic(optional = false)
	@NotNull(message = "É obrigatório informar o nome científico da espécie.")
	@Size(min = 1, max = 100, message = "O nome científico deve conter entre 1 e 100 caracteres.")
	@Column(name = "scientific_name", length = 100, unique = true)
	private String scientificName;

	@Basic(optional = false)
	@NotNull(message = "É obrigatório informar o nome popular da espécie.")
	@Size(min = 1, max = 100, message = "O nome popular deve conter entre 1 e 100 caracteres.")
	@Column(name = "common_name", length = 100)
	private String commonName;

	@Basic(optional = false)
	@NotNull(message = "É obrigatório informar se a espécie é ornamental.")
	@Column(name = "ornamental")
	private Boolean isOrnamental;

	@Basic(optional = false)
	@NotNull(message = "É obrigatório informar a altura média da espécie.")
	@Size(min = 0, max = 50000, message = "A altura média da espécie deve ser de no mínimo 0 cm e o máximo de 50000 cm.")
	@Column(name = "average_height", length = 50000)
	private Integer averageHeight;

	@Basic(optional = false)
	@NotNull(message = "É obrigatório informar a profundidade média da raiz.")
	@Size(min = 0, max = 50000, message = "A profundidade média da raiz deve ser de no mínimo 0 cm e o máximo de 50000 cm.")
	@Column(name = "root_depth", length = 50000)
	private Integer rootDepth;

	@ManyToOne
	@NotNull(message = "É obrigatório informar o crescimento da espécie.")
	@Valid
	@JoinColumn(name = "growth_id")
	private Growth growth;

	@ManyToMany
	@Valid
	@JoinTable(name = "species_climate", joinColumns = { @JoinColumn(name = "species_id") }, inverseJoinColumns = {
			@JoinColumn(name = "climate_id") })
	private List<Climate> climates;

	@ManyToMany
	@JoinTable(name = "species_soil", joinColumns = { @JoinColumn(name = "species_id") }, inverseJoinColumns = {
			@JoinColumn(name = "soil_id") })
	@Valid
	private List<Soil> soils;

	@OneToOne
	@Basic(optional = true)
	@JoinColumn(name = "flower_id")
	@Valid
	private Flower flower;

	@OneToOne
	@Basic(optional = true)
	@JoinColumn(name = "fruit_id")
	@Valid
	private Fruit fruit;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "species")
	private List<Offer> offers;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "species")
	private List<Post> posts;

	@JsonIgnore
	@Valid
	@OneToOne(mappedBy = "species")
	private Suggestion suggestions;

	protected Species() {
		super(PHOTO_TYPE, false);
	}

	public Species(Boolean attractBirds, String description, String cultivationGuide, Boolean isMedicinal,
			Boolean attractBees, String scientificName, String commonName, Boolean isOrnamental, Integer averageHeight,
			Growth growth, Integer rootDepth, List<Climate> climates, List<Soil> soils, Flower flower, Fruit fruit) {
		super(PHOTO_TYPE, true);
		this.enabled = false;
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
		this.rootDepth = rootDepth;
		this.climates = climates;
		this.soils = soils;
		this.flower = flower;
		this.fruit = fruit;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Descrição inválida.");
		}
		if (isNullOrEmpty(this.cultivationGuide) || is(this.cultivationGuide).orSmallerThan(1).orBiggerThan(5000)) {
			this.validationErrors.add("Guia de cultivo inválido.");
		}
		if (isNull(this.enabled)) {
			this.validationErrors.add("Definição de aprovação inválida.");
		}
		if (isNull(this.attractBees)) {
			this.validationErrors.add("Definição de atração de abelhas inválida.");
		}
		if (isNull(this.attractBirds)) {
			this.validationErrors.add("Definição de atração de pássaros inválida.");
		}
		if (isNull(this.isMedicinal)) {
			this.validationErrors.add("Definição de planta medicinal inválida.");
		}
		if (isNull(this.isOrnamental)) {
			this.validationErrors.add("Definição de planta ornamental inválida.");
		}
		if (isNull(this.averageHeight) || is(this.averageHeight).orSmallerThan(1).orBiggerThan(50000)) {
			this.validationErrors.add("Altura inválida, deve estar entre 1 e 50000 centímetros.");
		}
		if (isNull(this.rootDepth) || is(this.rootDepth).orSmallerThan(1).orBiggerThan(50000)) {
			this.validationErrors.add("Profundidade da raiz inválida, deve estar entre 1 e 50000 centímetros.");
		}
		if (isNull(this.commonName) || is(this.commonName).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome popular inválido.");
		}
		if (isNull(this.scientificName) || is(this.scientificName).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome científico inválido.");
		}
		if (isNull(this.growth)) {
			this.validationErrors.add("Nível de crescimento não pode ser nulo.");
		} else if (this.growth.isNotValid()) {
			this.validationErrors.addAll(this.growth.getValidationErrors());
		}
		if (isNull(this.soils) || this.soils.isEmpty()) {
			this.validationErrors.add("Solos não podem ser nulos ou vazios.");
		} else if (this.soils.stream().anyMatch(soil -> soil.isNotValid())) {
			this.soils.stream().forEach(soil -> this.validationErrors.addAll(soil.getValidationErrors()));
		}
		if (isNotNull(this.climates) && this.climates.stream().anyMatch(climate -> climate.isNotValid())) {
			this.climates.stream().forEach(climate -> this.validationErrors.addAll(climate.getValidationErrors()));
		}
		if (isNotNull(this.flower) && this.flower.isNotValid()) {
			this.validationErrors.addAll(this.flower.getValidationErrors());
		}
		if (isNotNull(this.fruit) && this.fruit.isNotValid()) {
			this.validationErrors.addAll(this.fruit.getValidationErrors());
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public Boolean getAttractBirds() {
		return this.attractBirds;
	}

	public String getDescription() {
		return this.description;
	}

	public String getCultivationGuide() {
		return this.cultivationGuide;
	}

	public Boolean getIsMedicinal() {
		return this.isMedicinal;
	}

	public Boolean getAttractBees() {
		return this.attractBees;
	}

	public String getScientificName() {
		return this.scientificName;
	}

	public String getCommonName() {
		return this.commonName;
	}

	public Boolean getIsOrnamental() {
		return this.isOrnamental;
	}

	public Integer getAverageHeight() {
		return this.averageHeight;
	}

	public Growth getGrowth() {
		return this.growth;
	}

	public List<Climate> getClimates() {
		return this.climates;
	}

	public List<Soil> getSoils() {
		return this.soils;
	}

	public Flower getFlower() {
		return this.flower;
	}

	public Fruit getFruit() {
		return this.fruit;
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

	public Integer getRootDepth() {
		return rootDepth;
	}

	public void enable() {
		this.enabled = true;
	}
	
	public void disable() {
		this.enabled = false;
	}

	@Override
	public void update(Species e) {
		this.enabled = e.getEnabled();
		this.attractBees = e.getAttractBees();
		this.attractBirds = e.getAttractBirds();
		this.averageHeight = e.getAverageHeight();
		this.climates = e.getClimates();
		this.commonName = e.getCommonName();
		this.cultivationGuide = e.getCultivationGuide();
		this.description = e.getDescription();
		this.flower = e.getFlower();
		this.fruit = e.getFruit();
		this.growth = e.getGrowth();
		this.isMedicinal = e.getIsMedicinal();
		this.isOrnamental = e.getIsOrnamental();
		this.rootDepth = e.getRootDepth();
	}

}