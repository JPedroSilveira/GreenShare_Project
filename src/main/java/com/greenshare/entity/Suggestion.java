package com.greenshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.greenshare.entity.abstracts.AbstractEntity;
import com.greenshare.entity.user.User;
import com.greenshare.entity.vegetable.Species;

/**
 * Persistence class for the table suggestion
 * 
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

	@ManyToOne
	@Basic(optional = false)
	@NotNull(message = "O usuário não pode ser nulo.")
	@Valid
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@Basic(optional = false)
	@NotNull(message = "A espécie não pode ser nula.")
	@Valid
	@JoinColumn(name = "species_id")
	private Species species;

	protected Suggestion() {
		super(false);
	}

	public Suggestion(User user, Species species) {
		super(true);
		this.user = user;
		this.species = species;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.user)) {
			this.validationErrors.add("Usuário não pode ser nulo.");
		} else if (this.user.isNotValid()) {
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if (isNull(this.species)) {
			this.validationErrors.add("Espécie não pode ser nula.");
		} else if (this.species.isNotValid()) {
			this.validationErrors.addAll(this.species.getValidationErrors());
		}
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

	@Override
	public void update(Suggestion e) {
	}
}