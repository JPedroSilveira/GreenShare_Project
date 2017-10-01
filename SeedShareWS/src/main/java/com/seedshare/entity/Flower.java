package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;


/**
 * Persistence class for the table FLOWER
 * @author joao.silva
 */
@Entity
@Table(name = "FLOWER")
public class Flower implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "FLOWER_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "FLOWER_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "AROMATIC")
	private Boolean isAromatic;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "COLOR", length = 50)
	private String color;

	@ManyToMany
	@JoinTable(
		name="FLOWERING_MONTHS"
		, joinColumns={
			@JoinColumn(name="FLOWER_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="MONTH_ID")
			}
		)
	private List<Month> floweringMonths;

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

	@ManyToOne
	@JoinColumn(name="SPECIES_ID")
	private Species species;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "RECORD_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordCreationDate;

	protected Flower() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsAromatic() {
		return this.isAromatic;
	}

	public void setIsAromatic(Boolean isAromatic) {
		this.isAromatic = isAromatic;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Month> getFloweringMonths() {
		return this.floweringMonths;
	}

	public void setFloweringMonths(List<Month> months) {
		this.floweringMonths = months;
	}
	
	public Month addFloweringMonths(Month month) {
		getFloweringMonths().add(month);
		return month;
	}
	
	public Month removeFloweringMonths(Month month) {
		getFloweringMonths().remove(month);
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

	public void setNome(String name) {
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

}