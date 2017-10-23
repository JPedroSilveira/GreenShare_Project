package com.seedshare.entity.vegetable;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractPhotogenicEntity;
import com.seedshare.entity.Month;
import com.seedshare.enumeration.PhotoType;

import java.util.ArrayList;
import java.util.List;

/**
 * Persistence class for the table fruit
 * 
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

	@ManyToMany
	@JoinTable(name = "fruiting_month", joinColumns = { @JoinColumn(name = "fruit_id") }, inverseJoinColumns = {
			@JoinColumn(name = "month_id") })
	@Valid
	private List<Month> fruitingMonths;

	@Basic(optional = false)
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "A descrição deve conter de 1 a 2500 caracteres.")
	@Column(name = "description", length = 2500, columnDefinition = "TEXT")
	private String description;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter de 1 a 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@OneToOne(mappedBy = "fruit")
	@Valid
	private Species species;

	protected Fruit() {
		super(PHOTO_TYPE, false);
	}

	public Fruit(Boolean faunaConsumption, Boolean humanConsumption, List<Month> fruitingMonths, String description,
			String name) {
		super(PHOTO_TYPE, true);
		this.faunaConsumption = faunaConsumption;
		this.humanConsumption = humanConsumption;
		this.fruitingMonths = fruitingMonths;
		this.description = description;
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.faunaConsumption)) {
			this.validationErrors.add("Bicondicional consumo para fauna inválido.");
		}
		if (isNull(this.humanConsumption)) {
			this.validationErrors.add("Bicondicional consumo para humanos inválido.");
		}
		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Descrição inválida.");
		}
		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Nome inválido.");
		}
		if (isNull(this.species)) {
			this.validationErrors.add("Espécie inválida.");
		} else if (this.species.isNotValid()) {
			this.validationErrors.addAll(this.species.getValidationErrors());
		}
		if (this.fruitingMonths.stream().anyMatch(fm -> fm.isValid())) {
			this.fruitingMonths.stream().forEach(fm -> this.validationErrors.addAll(fm.getValidationErrors()));
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public List<Short> getMonthNumbers() {
		List<Short> monthNumbers = new ArrayList<Short>();
		this.fruitingMonths.stream().map(Month::getNumber).forEach(monthNumbers::add);
		return monthNumbers;
	}

	public Boolean getFaunaConsumption() {
		return this.faunaConsumption;
	}

	public Boolean getHumanConsumption() {
		return this.humanConsumption;
	}

	public List<Month> getFruitingMonths() {
		return this.fruitingMonths;
	}

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public Species getSpecies() {
		return this.species;
	}

	public void update(Fruit fruit) {
		this.description = fruit.getDescription();
		this.faunaConsumption = fruit.getFaunaConsumption();
		this.fruitingMonths = fruit.getFruitingMonths();
		this.humanConsumption = fruit.getHumanConsumption();
		this.name = fruit.getName();
		this.species = fruit.getSpecies();
	}

}