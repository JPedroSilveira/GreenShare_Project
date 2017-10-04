package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;
import com.seedshare.entity.interfaces.PhotogenicEntity;
import com.seedshare.enumeration.PhotoType;

/**
 * Persistence class for the table FLOWER_SHOP
 * @author joao.silva
 */
@Entity
@Table(name = "FLOWER_SHOP")
public class FlowerShop extends AbstractEntity implements Serializable, PhotogenicEntity {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "FLOWER_SHOP_SEQ";
	
	private static final PhotoType PHOTO_TYPE = PhotoType.FLOWER_SHOP;
		
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "FLOWER_SHOP_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 14, max = 14)
	@Column(name = "CNPJ", length = 14, unique = true)
	private String cnpj;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRIPTION", columnDefinition="TEXT", length = 2500)
	private String description;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "HAS_IMAGE")
	private Boolean hasImage;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "LOGO_URL", columnDefinition="TEXT", length = 2500)
	private String logoURL;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NAME", length = 50)
	private String name;

	@JsonIgnore
	@OneToOne
	private User user;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "RECORD_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordCreationDate;

	@JsonIgnore
	@Transient
	private List<String> validationErrors;
	
	protected FlowerShop() {
	}
	
	public FlowerShop(String cnpj, String description, String logoUrl, User user){
		this.recordCreationDate = new Date();
		this.cnpj = cnpj;
		this.description = description;
		this.logoURL = logoUrl;
		this.user = user;
		this.hasImage = false;
	}
	
	public FlowerShop generateNewValidation() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || this.description.length()>2500){
			this.validationErrors.add("Descrição inválida");
		}
		if(isNullOrEmpty(this.logoURL) || this.logoURL.length() > 2500) {
			this.validationErrors.add("Url do logo inválida");
		}
		if(this.user == null || !(this.user.generateNewValidation().isValid())) {
			this.validationErrors.add("Usuário inválido");
		}
		if(this.cnpj == null || this.cnpj.length() != 14) {
			this.validationErrors.add("CNPJ inválido");
		}
		if(this.recordCreationDate == null || this.recordCreationDate.after(new Date())) {
			this.validationErrors.add("Data de criação inválida");
		}
		
		return this;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLogoURL() {
		return this.logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
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

	public Date getRecordCreationDate() {
		return this.recordCreationDate;
	}
	
	@JsonIgnore
	public Boolean isValid() {
		return this.validationErrors.isEmpty();
	}
	
	@JsonIgnore
	public List<String> getValidationErrors() {
		return validationErrors;
	}

	@Override
	public PhotoType getPhotoType() {
		return FlowerShop.PHOTO_TYPE;
	}
	
	@Override
	public Boolean getHasImage() {
		return this.hasImage;
	}
	
	@Override
	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}

}