package com.seedshare.entity.address;

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
import com.seedshare.entity.abstracts.AbstractEntity;

/**
 * Persistence class for the table city
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "city")
public class City extends AbstractEntity<City> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "city_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "city_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "Nome não poder ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne
	@NotNull(message = "O estado não pode ser nulo.")
	@Valid
	@JoinColumn(name = "state_id")
	private State state;
	
	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "city")
	private List<Address> addresses;

	protected City() {
		super(false);
	}

	public City(String name, State state) {
		super(true);
		this.name = name;
		this.state = state;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido");
		}

		if (isNull(this.state)) {
			this.validationErrors.add("Estado não pode ser nulo.");
		} else if (this.state.isNotValid()) {
			this.validationErrors.addAll(this.state.getValidationErrors());
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

	public State getState() {
		return state;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}
	
	public void update(City city) {
		this.name = city.getName();
		this.state = city.getState();
	}
}
