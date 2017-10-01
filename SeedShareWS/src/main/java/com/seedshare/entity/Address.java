package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Persistence class for the table ADDRESS
 * @author joao.silva
 */
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {
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

	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	protected Address() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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