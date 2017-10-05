package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.seedshare.entity.abstracts.AbstractEntity;

/**
 * Persistence class for the table request
 * @author joao.silva
 */
@Entity
@Table(name = "request")
public class Request extends AbstractEntity<Request> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "request_seq";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "request_id")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "amount")
	private Integer amount;

	@ManyToOne
	@JoinColumn(name="offer_id")
	private Offer offer;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	protected Request() {
		super();
	}
	
	protected Request(Integer amount, Offer offer, User user) {
		super(true);
		this.amount = amount;
		this.offer = offer;
		this.user = user;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNull(this.amount) || is(this.amount).biggerThan(0)){
			this.validationErrors.add("Quantidade inválida.");
		}
		if(isNull(this.offer) || this.offer.isNotValid()){
			this.validationErrors.add("Oferta inválida.");
		}
		if(isNull(this.user) || this.user.isNotValid()){
			this.validationErrors.add("Usuário inválido.");
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
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