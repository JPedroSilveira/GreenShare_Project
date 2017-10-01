package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;


/**
 * Persistence class for the table OFFER
 * @author joao.silva
 */
@Entity
@Table(name = "OFFER")
public class Offer implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "OFFER_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "OFFER_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Basic(optional = false)
	@NotNull
	@Column(name = "UNIT_PRICE")
	private Float unitPrice;

	@Basic(optional = false)
	@NotNull
	@Max(9999)
	@Column(name = "AMOUNT")
	private Integer amount;

	@Basic(optional = false)
	@NotNull
	@Column(name = "OFFER_STATUS", columnDefinition="TEXT")
	private Integer offerStatus;

	@Basic(optional = false)
	@NotNull
	@Column(name = "TYPE")
	private Integer type;

	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name="SPECIES_ID")
	private Species species;

	@OneToMany(mappedBy="offer")
	private List<Request> requests;

	protected Offer() {
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

	public Float getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getOfferStatus() {
		return this.offerStatus;
	}

	public void setOfferStatus(Integer offerStatus) {
		this.offerStatus = offerStatus;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Species getSpecies() {
		return this.species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public List<Request> getRequests() {
		return this.requests;
	}

	public Request addRequest(Request request) {
		getRequests().add(request);
		request.setOffer(this);

		return request;
	}

	public Request removeRequest(Request request) {
		getRequests().remove(request);
		request.setOffer(null);

		return request;
	}
}