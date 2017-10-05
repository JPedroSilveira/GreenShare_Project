package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;

import java.math.BigDecimal;


/**
 * Persistence class for the table address
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
	@NotNull
	@Column(name = "latitude")
	private BigDecimal latitude;

	@Basic(optional = false)
	@NotNull
	@Column(name = "longitude")
	private BigDecimal longitude;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	protected Address() {
		super();
	}
	
	public Address(BigDecimal latitude, BigDecimal longitude, User user) {
		super(true);
		this.user = user;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNull(this.user) || !(this.user.isNotValid())){
			this.validationErrors.add("Usuário inválido");
		}
		if(isNull(this.latitude)) {
			this.validationErrors.add("Latitude inválida");
		}
		if(isNull(this.longitude)) {
			this.validationErrors.add("Longitude inválida");
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
}