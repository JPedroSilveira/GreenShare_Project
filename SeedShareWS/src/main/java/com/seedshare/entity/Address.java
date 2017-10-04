package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Persistence class for the table ADDRESS
 * @author joao.silva
 */
@Entity
@Table(name = "ADDRESS")
public class Address extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "ADDRESS_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ADDRESS_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Basic(optional = false)
	@NotNull
	@Column(name = "LATITUDE")
	private BigDecimal latitude;

	@Basic(optional = false)
	@NotNull
	@Column(name = "LONGITUDE")
	private BigDecimal longitude;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	@JsonIgnore
	@Transient
	private List<String> validationErrors;
	
	protected Address() {
		this.validationErrors = new ArrayList<String>();
	}
	
	public Address(BigDecimal latitude, BigDecimal longitude, User user) {
		this.creationDate = new Date();
		this.user = user;
		this.latitude = latitude;
		this.longitude = longitude;
		this.validationErrors = new ArrayList<String>();
	}
	
	public Address generateNewValidation() {
		this.validationErrors.clear();
		
		if(this.user == null || !(this.user.generateNewValidation().isValid())){
			this.validationErrors.add("Usuário inválido");
		}
		if(this.latitude == null || this.longitude == null) {
			this.validationErrors.add("Coordenadas inválidas");
		}
		if(this.creationDate == null || this.creationDate.after(new Date())) {
			this.validationErrors.add("Data de criação inválida");
		}
		
		return this;
	}
	
	@JsonIgnore
	public Boolean isValid() {
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public Date getCreationDate() {
		return this.creationDate;
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