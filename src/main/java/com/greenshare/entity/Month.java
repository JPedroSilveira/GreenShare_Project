package com.greenshare.entity;

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
import com.greenshare.entity.abstracts.AbstractEntity;
import com.greenshare.entity.vegetable.Flower;
import com.greenshare.entity.vegetable.Fruit;

/**
 * Persistence class for the table month
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "month")
public class Month extends AbstractEntity<Month> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "month_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "month_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 50, message = "O nome deve conter de 1 e 50 caracteres.")
	@Column(name = "name", length = 50)
	private String name;

	@Basic(optional = false)
	@NotNull(message = "Número não pode ser nulo.")
	@Column(name = "number")
	private Short number;
	
	@ManyToMany(mappedBy = "fruitingMonths")
	@JsonIgnore
	@Valid
	private List<Fruit> fruits;
	
	@ManyToMany(mappedBy = "floweringMonths")
	@JsonIgnore
	@Valid
	private List<Flower> flowers;

	protected Month() {
		super(false);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(50)) {
			this.validationErrors.add("O nome não pode ser nulo e deve conter entre 1 e 50 caracteres.");
		}
		if (isNull(this.number) || is(this.number).orSmallerThan(1).orBiggerThan(12)) {
			this.validationErrors.add("O número não pode ser nulo e deve estar entre 1 e 12.");
		}

		return this.validationErrors.isEmpty();
	}

	@Override
	public void update(Month e) {
		this.name = e.getName();
		this.number = e.getNumber();
	}

	public String getName() {
		return name;
	}

	public Short getNumber() {
		return number;
	}
	
	public List<Fruit> getFruits() {
		return fruits;
	}

	public List<Flower> getFlowers() {
		return flowers;
	}

}
