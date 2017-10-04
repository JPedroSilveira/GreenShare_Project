package com.seedshare.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;

import java.util.ArrayList;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Persistence class for the table GROWTH
 * @author joao.silva
 */
@Entity
@Table(name = "GROWTH")
public class Growth extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "GROWTH_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "GROWTH_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRIPTION", columnDefinition="TEXT", length = 2500)
	private String description;

	@OneToMany(mappedBy="growth")
	private List<Species> species;
	
	@JsonIgnore
	@Transient
	private List<String> validationErrors;

	protected Growth() {
		this.validationErrors = new ArrayList<String>();
	}
	
	public Growth(String description) {
		super();
		this.description = description;
		this.validationErrors = new ArrayList<String>();
	}

	public Growth generateNewValidation() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || this.description.length()>2500){
			this.validationErrors.add("Descrição inválida.");
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Species> getSpecies() {
		return this.species;
	}

	public void setEspecies(List<Species> species) {
		this.species = species;
	}

	public Species addEspecie(Species species) {
		getSpecies().add(species);
		species.setGrowth(this);

		return species;
	}

	public Species removeEspecie(Species species) {
		getSpecies().remove(species);
		species.setGrowth(null);

		return species;
	}

}