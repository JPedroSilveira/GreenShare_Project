package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractEntity;

import java.util.List;

/**
 * Persistence class for the table climate.
 * @author joao.silva
 */
@Entity
@Table(name = "climate")
public class Climate extends AbstractEntity<Climate> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "climate_seq";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@NotNull
    @Column(name = "climate_id")
	private Long id;

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

	@ManyToMany(mappedBy="climates")
	private List<Species> species;

	protected Climate() {
		super();
	}
	
	public Climate(String description, String name){
		super(true);
		this.description = description;
		this.name = name;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || is(this.description).biggerThan(2500)){
			this.validationErrors.add("Espécie inválida.");
		}
		if(isNullOrEmpty(this.name) || is(this.name).biggerThan(100)) {
			this.validationErrors.add("Nome grande demais.");
		}
		addAbstractAttributesValidation();
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
}