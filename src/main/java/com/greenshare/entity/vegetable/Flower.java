package com.greenshare.entity.vegetable;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.Month;
import com.greenshare.entity.abstracts.AbstractPhotogenicEntity;
import com.greenshare.enumeration.PhotoType;

import java.util.ArrayList;
import java.util.List;

/**
 * Persistence class for the table flower
 * 
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

	@Basic(optional = false)
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "A descrição deve conter de 1 a 2500 caracteres.")
	@Column(name = "description", columnDefinition = "TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@JsonIgnore
	@OneToOne(mappedBy = "flower")
	@NotNull(message = "A espécie não pode ser nula.")
	@Valid
	private Species species;

	@Valid
	@ManyToMany
	@JoinTable(name = "flower_color", joinColumns = { @JoinColumn(name = "flower_id") }, inverseJoinColumns = {
			@JoinColumn(name = "color_id") })
	private List<Color> colors;

	@ManyToMany
	@JoinTable(name = "flowering_Month", joinColumns = { @JoinColumn(name = "flower_id") }, inverseJoinColumns = {
			@JoinColumn(name = "month_id") })
	@Valid
	private List<Month> floweringMonths;

	protected Flower() {
		super(PHOTO_TYPE, false);
	}

	public Flower(Boolean isAromatic, String description, String name, Species species, List<Color> colors,
			List<Month> floweringMonths) {
		super(PHOTO_TYPE, true);
		this.isAromatic = isAromatic;
		this.description = description;
		this.name = name;
		this.species = species;
		this.colors = colors;
		this.floweringMonths = floweringMonths;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.isAromatic)) {
			this.validationErrors.add("Dado isAromatic inválido.");
		}
		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Espécie inválida.");
		}
		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		if (isNull(this.species) || this.species.isNotValid()) {
			this.validationErrors.add("Espécie de vegetal inválida.");
		}
		if (this.colors.stream().anyMatch(color -> color.isNotValid())) {
			this.validationErrors.add("Cores inválidas.");
		}
		if (this.floweringMonths.stream().anyMatch(fm -> fm.isValid())) {
			this.floweringMonths.stream().forEach(fm -> this.validationErrors.addAll(fm.getValidationErrors()));
		}
		return this.validationErrors.isEmpty();
	}

	public List<Short> getMonthNumbers() {
		List<Short> monthNumbers = new ArrayList<Short>();
		this.floweringMonths.stream().map(Month::getNumber).forEach(monthNumbers::add);
		return monthNumbers;
	}

	public Long getId() {
		return this.id;
	}

	public Boolean getIsAromatic() {
		return this.isAromatic;
	}

	public List<Color> getColors() {
		return this.colors;
	}

	public List<Month> getFloweringMonths() {
		return this.floweringMonths;
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

	public void update(Flower flower) {
		this.description = flower.getDescription();
		this.name = flower.getName();
		this.isAromatic = flower.getIsAromatic();
		this.species = flower.getSpecies();
		this.colors = flower.getColors();
	}

}