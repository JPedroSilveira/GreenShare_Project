package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;
import com.seedshare.enumeration.OfferStatus;
import com.seedshare.enumeration.OfferType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Persistence class for the table OFFER
 * @author joao.silva
 */
@Entity
@Table(name = "OFFER")
public class Offer extends AbstractEntity implements Serializable {
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
	
	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRIPTION", columnDefinition="TEXT", length = 2500)
	private String description;

	@JsonIgnore
	@Transient
	private List<String> validationErrors;
	
	protected Offer() {
		this.validationErrors = new ArrayList<String>();
	}
	
	public Offer(Float unitPrice, Integer amount, User user, Species species, String description) {
		if(this.unitPrice == null || this.unitPrice == (float) 0) {
			this.type = OfferType.Donation.getOfferType();
			this.unitPrice = (float) 0;
		}else {
			this.type = OfferType.Sale.getOfferType();
			this.unitPrice = unitPrice;
		}
		this.amount = amount;
		this.species = species;
		this.offerStatus = OfferStatus.Active.getOfferStatus();
		this.description = description;
		this.validationErrors = new ArrayList<String>();
	}
	
	public Offer generateNewValidation() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || this.description.length()>2500){
			this.validationErrors.add("Descrição inválida");
		}
		if(this.type != null && this.unitPrice != null) {
			if(this.type == OfferType.Sale.getOfferType() && this.unitPrice <= (float) 0) {
				this.validationErrors.add("Preço unitário inválido para uma venda");
			}
			if(this.type == OfferType.Donation.getOfferType() && this.unitPrice != (float) 0) {
				this.validationErrors.add("Preço unitário inválido para uma doação");
			}
		} else {
			if(this.type == null) {
				this.validationErrors.add("Tipo de oferta inválida");
			}
			if(this.unitPrice == null) {
				this.validationErrors.add("Preço unitário inválido");
			}
		}
		if(this.amount == null || this.amount<=0) {
			this.validationErrors.add("Quantidade inválida");
		}
		if(this.creationDate == null || this.creationDate.after(new Date())) {
			this.validationErrors.add("Data de criação inválida");
		}
		if(!this.user.generateNewValidation().isValid()) {
			this.validationErrors.add("Usuário inválido");
		}
		if(!this.species.generateNewValidation().isValid()) {
			this.validationErrors.add("Espécie inválida");
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

	public Float getUnitPrice() {
		return this.unitPrice;
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

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus.getOfferStatus();
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(OfferType type) {
		this.type = type.getOfferType();
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}