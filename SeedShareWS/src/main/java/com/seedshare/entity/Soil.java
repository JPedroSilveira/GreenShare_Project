package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractEntity;

import java.util.List;


/**
 * Persistence class for the table SOIL
 * @author joao.silva
 */
@Entity
@Table(name = "SOIL")
public class Soil extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SOIL_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "SOIL_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRIPTION", columnDefinition="TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "NAME", length = 100)
	private String name;

	@ManyToMany
	@JoinTable(
		name="SPECIES_SOIL"
		, joinColumns={
			@JoinColumn(name="SOIL_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="SPECIES_ID")
			}
		)
	private List<Species> species;

	protected Soil() {
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
	public Boolean isValid() {
		// TODO Auto-generated method stub
		return null;
	}

}