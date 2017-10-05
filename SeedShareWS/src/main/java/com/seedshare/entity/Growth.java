package com.seedshare.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractEntity;

import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Persistence class for the table growth
 * @author joao.silva
 */
@Entity
@Table(name = "growth")
public class Growth extends AbstractEntity<Growth> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "growth_seq";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "growth_id")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "description", columnDefinition="TEXT", length = 2500)
	private String description;

	@OneToMany(mappedBy="growth")
	private List<Species> species;

	protected Growth() {
		super();
	}
	
	public Growth(String description) {
		super(true);
		this.description = description;
	}
	
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || is(this.description).biggerThan(2500)){
			this.validationErrors.add("Descrição inválida.");
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

	public List<Species> getSpecies() {
		return this.species;
	}

	public void setEspecies(List<Species> species) {
		this.species = species;
	}

}