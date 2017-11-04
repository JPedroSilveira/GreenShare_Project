package com.greenshare.entity.address;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.abstracts.AbstractEntity;

/**
 * Persistence class for the table state
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "state")
public class State extends AbstractEntity<State> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "state_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "state_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "Nome não poder ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne
	@Basic(optional = false)
	@NotNull(message = "O pais não pode ser nulo.")
	@Valid
	@JoinColumn(name = "country_id")
	private Country country;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "state")
	private List<City> cities;

	protected State() {
		super(false);
	}

	public State(String name, Country country) {
		super(false);
		this.name = name;
		this.country = country;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido");
		}

		if (isNull(this.country)) {
			this.validationErrors.add("Pais não pode ser nulo.");
		} else if (this.country.isNotValid()) {
			this.validationErrors.addAll(this.country.getValidationErrors());
		}

		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}

	public List<City> getCities() {
		return cities;
	}

	@Override
	public void update(State e) {
		this.country = e.getCountry();
		this.name = e.getName();	
	}
}
