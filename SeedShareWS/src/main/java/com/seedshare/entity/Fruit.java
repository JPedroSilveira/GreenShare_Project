package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractEntity;

import java.util.Date;
import java.util.List;


/**
 * Persistence class for the table FRUIT
 * @author joao.silva
 */
@Entity
@Table(name = "FRUIT")
public class Fruit extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "FRUIT_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "FRUIT_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "FAUNA_CONSUMPTION")
	private Boolean faunaConsumption;

	@Basic(optional = false)
	@NotNull
	@Column(name = "HUMAN_CONSUMPTION")
	private Boolean humanConsumption;

	@ManyToMany
	@JoinTable(
		name="FRUITING_MONTHS"
		, joinColumns={
			@JoinColumn(name="FRUIT_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="MONTH_ID")
			}
		)
	private List<Month> fruitingMonths;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRIPTION", columnDefinition="TEXT")
	private String description;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name="SPECIES_ID")
	private Species species;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "RECORD_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordCreationDate;

	protected Fruit() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFaunaConsumption() {
		return this.faunaConsumption;
	}

	public void setFaunaConsumption(Boolean faunaConsumption) {
		this.faunaConsumption = faunaConsumption;
	}

	public Boolean getHumanConsumption() {
		return this.humanConsumption;
	}

	public void setHumanConsumption(Boolean humanConsumption) {
		this.humanConsumption = humanConsumption;
	}

	public List<Month> getFruitingMonths() {
		return this.fruitingMonths;
	}

	public void setFruitingMonths(List<Month> months) {
		this.fruitingMonths = months;
	}
	
	public Month addFruitingMonths(Month month) {
		getFruitingMonths().add(month);
		return month;
	}
	
	public Month removeFruitingMonths(Month month) {
		getFruitingMonths().remove(month);
		return month;
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

	public Species getSpecies() {
		return this.species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}
	
	public Date getRecordCreationDate() {
		return this.recordCreationDate;
	}

	public void setRecordCreationDate(Date recordCreationDate) {
		this.recordCreationDate = recordCreationDate;
	}

	@Override
	public Boolean isValid() {
		// TODO Auto-generated method stub
		return null;
	}

}