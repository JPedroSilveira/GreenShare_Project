package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;

/**
 * Persistence class for the table color
 * 
 * @author gabriel.schneider
 * @author joao.silva
 */
@Entity
@Table(name = "color")
public class Color extends AbstractEntity<Color> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "color_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "color_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 50, message = "O nome deve conter de 1 a 50 caracteres.")
	@Column(name = "name", length = 25, unique = true)
	private String name;

	@Valid
	@JsonIgnore
	@ManyToMany(mappedBy = "colors")
	private List<Flower> flowers;

	protected Color() {
		super(false);
	}

	public Color(String name) {
		super(true);
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(50)) {
			this.validationErrors.add("Nome inválido.");
		}

		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Flower> getFlowers() {
		return flowers;
	}

}
