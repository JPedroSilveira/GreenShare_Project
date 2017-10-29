package com.greenshare.entity.vegetable;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.abstracts.AbstractEntity;

import java.util.List;

/**
 * Persistence class for the table climate.
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "climate")
public class Climate extends AbstractEntity<Climate> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "climate_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "climate_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "Descrição deve conter entre 1 e 2500 caracteres.")
	@Column(name = "description", columnDefinition = "TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@Valid
	@JsonIgnore
	@ManyToMany(mappedBy = "climates")
	private List<Species> species;

	protected Climate() {
		super(false);
	}

	public Climate(String description, String name) {
		super(true);
		this.description = description;
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Espécie inválida.");
		}
		if (isNullOrEmpty(this.name)) {
			this.validationErrors.add("O nome não pode ser nulo.");
		}else if(is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("O nome deve conter entre 1 e 100 caracteres.");
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
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

	public List<Species> getSpecies() {
		return this.species;
	}

	public void update(Climate climate) {
		this.description = climate.getDescription();
		this.name = climate.getName();
	}
}