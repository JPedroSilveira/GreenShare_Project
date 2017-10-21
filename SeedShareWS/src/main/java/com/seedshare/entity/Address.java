package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;

import java.math.BigDecimal;
import java.util.List;
/**
 * Persistence class for the table address
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "address")
public class Address extends AbstractEntity<Address> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "address_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "address_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "Latitude não pode ser nula.")
	@Column(name = "latitude")
	private BigDecimal latitude;

	@Basic(optional = false)
	@NotNull(message = "Longitude não pode ser nula.")
	@Column(name = "longitude")
	private BigDecimal longitude;

	@Basic(optional = false)
	@NotNull(message = "CEP não pode ser nulo.")
	@Size(max = 8, min = 8, message = "O CEP deve conter oito dígitos.")
	@Column(name = "cpd", length = 8)
	private Integer cep;

	@JsonIgnore
	@Basic(optional = true)
	@ManyToOne
	@Valid
	@JoinColumn(name = "user_id")
	private User user;
	
	@Basic(optional = false)
	@NotNull(message = "Cidade não poder ser nula.")
	@ManyToOne
	@Valid
	@JoinColumn(name = "city_id")
	private City city;
	
	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "address")
	private List<Offer> offers;

	protected Address() {
		super(false);
	}

	public Address(BigDecimal latitude, BigDecimal longitude, City city, User user) {
		super(true);
		this.user = user;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNotNull(this.user) && this.user.isNotValid()) {
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if (isNull(this.latitude)) {
			this.validationErrors.add("Latitude inválida.");
		}
		if (isNull(this.longitude)) {
			this.validationErrors.add("Longitude inválida.");
		}
		if (isNull(this.city)) {
			this.validationErrors.add("Cidade nula.");
		} else if (this.city.isNotValid()) {
			this.validationErrors.addAll(this.city.getValidationErrors());
		}
		if (is(this.cep).notEqual(8)) {
			this.validationErrors.add("O CEP não contém oito dígitos.");
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public Integer getCep() {
		return cep;
	}

	public List<Offer> getOffers() {
		return offers;
	}
}