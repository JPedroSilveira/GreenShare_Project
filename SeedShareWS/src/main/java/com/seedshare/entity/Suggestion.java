package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.seedshare.entity.abstracts.AbstractEntity;


/**
 * Persistence class for the table suggestion
 * @author joao.silva
 */
@Entity
@Table(name = "suggestion")
public class Suggestion extends AbstractEntity<Suggestion> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "suggestion_seq";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "suggestion_id")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "active")
	private Boolean isActive;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@OneToOne
	@JoinColumn(name="species_id")
	private Species species;

	protected Suggestion() {
		super();
	}
	
	public Suggestion(User user, Species species) {
		super(true);
		this.user = user;
		this.species = species;
		this.isActive = true;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNull(this.user) || this.user.isNotValid()){
			this.validationErrors.add("Usuário inválido.");
		}
		if(isNull(this.species) || this.species.isNotValid()){
			this.validationErrors.add("Espécie inválida.");
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}
	
	public User getUser() {
		return this.user;
	}

	public Species getSpecies() {
		return this.species;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}