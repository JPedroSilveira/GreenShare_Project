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
 * Persistence class for the table flower
 * @author joao.silva
 */
@Entity
@Table(name = "flower")
public class Flower extends AbstractPhotogenicEntity<Flower> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "flower_seq";
	
	private static final PhotoType PHOTO_TYPE = PhotoType.FLOWER;
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "flower_id")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "aromatic")
	private Boolean isAromatic;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "color", length = 50)
	private String color;

	@ManyToMany
	@JoinTable(
		name="flowerting_months"
		, joinColumns={
			@JoinColumn(name="flower_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="month_id")
			}
		)
	private List<Month> floweringMonths;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "description", columnDefinition="TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne
	@JoinColumn(name="species_id")
	private Species species;

	protected Flower() {
		super(PHOTO_TYPE);
	}
	
	public Flower(Boolean isAromatic, String color, String description, String name, Species species) {
		super(PHOTO_TYPE, true);
		this.isAromatic = isAromatic;
		this.color = color;
		this.description = description;
		this.name = name;
		this.species = species;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNull(this.isAromatic)){
			this.validationErrors.add("Dado isAromatic inválido.");
		}
		if(isNullOrEmpty(this.description) || is(this.description).biggerThan(2500)){
			this.validationErrors.add("Espécie inválida.");
		}
		if(isNullOrEmpty(this.name) || is(this.name).biggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		if(isNull(this.species) || this.species.isNotValid()) {
			this.validationErrors.add("Espécie de vegetal inválida.");
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public Boolean getIsAromatic() {
		return this.isAromatic;
	}

	public void setIsAromatic(Boolean isAromatic) {
		this.isAromatic = isAromatic;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Month> getFloweringMonths() {
		return this.floweringMonths;
	}

	public void setFloweringMonths(List<Month> months) {
		this.floweringMonths = months;
	}
	
	public Month addFloweringMonths(Month month) {
		getFloweringMonths().add(month);
		return month;
	}
	
	public Month removeFloweringMonths(Month month) {
		getFloweringMonths().remove(month);
		return month;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return this.name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public Species getSpecies() {
		return this.species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

}