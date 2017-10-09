package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractPhotogenicEntity;
import com.seedshare.enumeration.Month;
import com.seedshare.enumeration.PhotoType;

import java.util.List;


/**
 * Persistence class for the table fruit
 * @author joao.silva
 */
@Entity
@Table(name = "fruit")
public class Fruit extends AbstractPhotogenicEntity<Fruit> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "fruit_seq";
	
	private static final PhotoType PHOTO_TYPE = PhotoType.FRUIT;
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "fruit_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "Obrigatório informar se a fruta é para consumo animal.")
	@Column(name = "fauna_consumption")
	private Boolean faunaConsumption;

	@Basic(optional = false)
	@NotNull(message = "Obrigatório informar se a fruta é para consumo humano.")
	@Column(name = "human_consumption")
	private Boolean humanConsumption;

	@ElementCollection(targetClass = Month.class)
	@JoinTable(name = "Fruiting_Month", joinColumns = @JoinColumn(name = "fruit_id"))
	@Column(name = "month", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private List<Month> fruitingMonths;

	@Basic(optional = false)
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "A descrição deve conter de 1 a 2500 caracteres.")
	@Column(name = "description", length = 2500, columnDefinition="TEXT")
	private String description;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter de 1 a 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne
	@NotNull(message = "A espécie não pode ser nula.")
	@Valid
	@JoinColumn(name="species_id")
	private Species species;

	protected Fruit() {
		super(PHOTO_TYPE, false);
	}
	
	public Fruit(Boolean faunaConsumption, Boolean humanConsumption, String description, String name, Species species) {
		super(PHOTO_TYPE, true);
		this.faunaConsumption = faunaConsumption;
		this.humanConsumption = humanConsumption;
		this.description = description;
		this.name = name;
		this.species = species;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNull(this.faunaConsumption)){
			this.validationErrors.add("Bicondicional consumo para fauna inválido.");
		}
		if(isNull(this.humanConsumption)){
			this.validationErrors.add("Bicondicional consumo para humanos inválido.");
		}
		if(isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)){
			this.validationErrors.add("Descrição inválida.");
		}
		if(isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Nome inválido.");
		}
		if(isNull(this.species)) {
			this.validationErrors.add("Espécie inválida.");
		}else if(this.species.isNotValid()) {
			this.validationErrors.addAll(this.species.getValidationErrors());
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public Boolean getFaunaConsumption() {
		return this.faunaConsumption;
	}

	public void setFaunaConsumption(Boolean faunaConsumption) {
		this.faunaConsumption = faunaConsumption;
	}

	public Boolean getHumanConsumption() {
		return this.humanConsumption;
	}

	public void setHumanConsumption(Boolean humanConsumption) {
		this.humanConsumption = humanConsumption;
	}

	public List<Month> getFruitingMonths() {
		return this.fruitingMonths;
	}

	public void setFruitingMonths(List<Month> months) {
		this.fruitingMonths = months;
	}
	
	public Month addFruitingMonths(Month month) {
		getFruitingMonths().add(month);
		return month;
	}
	
	public Month removeFruitingMonths(Month month) {
		getFruitingMonths().remove(month);
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

	public void setName(String name) {
		this.name = name;
	}

	public Species getSpecies() {
		return this.species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

}