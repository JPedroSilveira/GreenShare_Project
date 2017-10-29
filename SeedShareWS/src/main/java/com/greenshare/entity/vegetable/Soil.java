package com.greenshare.entity.vegetable;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.abstracts.AbstractEntity;

import java.util.List;


/**
 * Persistence class for the table soil
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Entity
@Table(name = "soil")
public class Soil extends AbstractEntity<Soil> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "soil_seq";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "soil_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "A descrição deve conter entre 1 e 2500 caracteres.")
	@Column(name = "description", columnDefinition="TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100, unique=true)
	private String name;

	@ManyToMany(mappedBy = "soils")
	@JsonIgnore
	private List<Species> species;

	protected Soil() {
		super(false);
	}
	
	public Soil(String description, String name) {
		super(true);
		this.description = description;
		this.name = name;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)){
			this.validationErrors.add("Descrição inválida.");
		}
		if(isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
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

	public void setName(String name) {
		this.name = name;
	}

	public List<Species> getSpecies() {
		return this.species;
	}

	public void setSpecies(List<Species> species) {
		this.species = species;
	}

	@Override
	public void update(Soil e) {
		this.description = e.getDescription();
		this.name = e.getName();
	}
}