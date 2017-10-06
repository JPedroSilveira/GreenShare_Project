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
	@NotNull(message = "Obrigatório informar se a flor é aromática.")
	@Column(name = "aromatic")
	private Boolean isAromatic;

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
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "A descrição deve conter de 1 a 2500 caracteres.")
	@Column(name = "description", columnDefinition="TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@Basic(optional = false)
	@ManyToOne
	@NotNull(message = "A espécie não pode ser nula.")
	@JoinColumn(name="species_id")
	private Species species;
	
	@Basic(optional = false)
	@ManyToOne
	@NotNull(message = "A cor não pode ser nula.")
	@JoinColumn(name = "color_id")
	private Color color;

	protected Flower() {
		super(PHOTO_TYPE);
	}
	
	public Flower(Boolean isAromatic, Color color, String description, String name, Species species) {
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
		if(isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)){
			this.validationErrors.add("Espécie inválida.");
		}
		if(isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
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

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
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