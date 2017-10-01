package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;


/**
 * Persistence class for the table REQUEST
 * @author joao.silva
 */
@Entity
@Table(name = "REQUEST")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "REQUEST_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "REQUEST_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "REQUEST_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate;

	@Basic(optional = false)
	@NotNull
	@Column(name = "AMOUNT")
	private Integer amount;

	@ManyToOne
	@JoinColumn(name="OFFER_ID")
	private Offer offer;

	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	protected Request() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}