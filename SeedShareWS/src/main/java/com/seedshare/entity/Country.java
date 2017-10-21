package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;

/**
 * Persistence class for the table country
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "country")
public class Country extends AbstractEntity<City> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "country_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "country_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "Nome não poder ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100, unique=true)
	private String name;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "country")
	private List<State> states;

	protected Country() {
		super(false);
	}

	public Country(String name) {
		super(true);
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido");
		}

		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

}
