package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractPhotogenicEntity;
import com.seedshare.enumeration.PhotoType;

/**
 * Persistence class for the table flower_shop
 * @author joao.silva
 */
@Entity
@Table(name = "flower_shop")
public class FlowerShop extends AbstractPhotogenicEntity<FlowerShop> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "flower_shop_seq";
	
	private static final PhotoType PHOTO_TYPE = PhotoType.FLOWER_SHOP;
		
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "flower_shop_id")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 14, max = 14)
	@Column(name = "cnpj", length = 14, unique = true)
	private String cnpj;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "description", columnDefinition="TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "name", length = 100)
	private String name;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	protected FlowerShop() {
		super(PHOTO_TYPE);
	}
	
	public FlowerShop(String cnpj, String description, User user){
		super(PHOTO_TYPE, true);
		this.cnpj = cnpj;
		this.description = description;
		this.user = user;
		this.hasImage = false;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || is(this.description).biggerThan(100)){
			this.validationErrors.add("Descrição inválida");
		}
		if(isNullOrEmpty(this.name) || is(this.name).biggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		if(isNull(this.user) || this.user.isNotValid()) {
			this.validationErrors.add("Usuário inválido.");
		}
		if(isNull(this.cnpj) || is(this.cnpj).equal(14)) {
			this.validationErrors.add("CNPJ inválido");
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}