package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.seedshare.entity.abstracts.AbstractEntity;


/**
 * Persistence class for the table SUGGESTION
 * @author joao.silva
 */
@Entity
@Table(name = "SUGGESTION")
public class Suggestion extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SUGGESTION_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "SUGGESTION_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name="SPECIES_ID")
	private Species species;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "RECORD_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordCreationDate;

	protected Suggestion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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