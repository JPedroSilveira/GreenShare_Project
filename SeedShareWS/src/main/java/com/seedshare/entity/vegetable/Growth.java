package com.seedshare.entity.vegetable;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@NotNull(message = "Nome não poder ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100, unique=true)
	private String name;

	@Basic(optional = false)
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "A descrição deve conter de 1 a 2500 caracteres")
	@Column(name = "description", columnDefinition="TEXT", length = 2500)
	private String description;

	@Valid
	@JsonIgnore
	@OneToMany(mappedBy="growth")
	private List<Species> species;

	protected Growth() {
		super(false);
	}
	
	public Growth(String name, String description) {
		super(true);
		this.name = name;
		this.description = description;
	}
	
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(100)){
			this.validationErrors.add("Descrição inválida.");
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

	public List<Species> getSpecies() {
		return this.species;
	}

	public void setEspecies(List<Species> species) {
		this.species = species;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(Growth e) {
		this.description = e.getDescription();
		this.name = e.getName();
	}
	
	

}