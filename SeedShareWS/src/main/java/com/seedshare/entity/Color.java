package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Persistence class for the table color
 * @author joao.silva
 */
@Entity
@Table(name = "color")
public class Color implements Serializable{

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
	@Size(min = 1, max = 25, message = "O nome deve conter de 1 a 25 caracteres.")
	@Column(name = "name", length = 25)
	private String name;

	@OneToMany(mappedBy="color")
	private List<Flower> flowers;
	
	protected Color() {
		
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
