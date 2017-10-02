package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Persistence class for the table POST
 * @author joao.silva
 */
@Entity
@Table(name = "POST")
public class Post extends BasicEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "POST_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "PERMISSION_ID")
	private Long id;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="SPECIES_ID")
	private Species species;
	
	@Basic(optional = false)
	@NotNull
	@Size(max = 500)
	@Column(name = "TEXT", columnDefinition="TEXT", length = 2500)
	private String text;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "HAS_IMAGE")
	private Boolean hasImage;

	@JsonIgnore
	@Transient
	private List<String> validationErrors;

	protected Post() {
		this.validationErrors = new ArrayList<String>();
	}
	
	public Post(User user, Species species, String text) {
		this.user = user;
		this.species = species;
		this.text = text;
		this.creationDate = new Date();
		this.hasImage = false;
		this.validationErrors = new ArrayList<String>();
	}
	
	public Post generateNewValidation() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.text) || this.text.length()>500){
			this.validationErrors.add("Texto inválido.");
		}
		if(this.hasImage == null) {
			this.validationErrors.add("Definição inválida para imagem.");
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
		return id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Species getSpecies() {
		return this.species;
	}
	
	public Boolean getHasImage() {
		return this.hasImage;
	}
	
	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
