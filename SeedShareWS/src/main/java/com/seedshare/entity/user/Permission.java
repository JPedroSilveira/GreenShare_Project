package com.seedshare.entity.user;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;

import java.util.List;

/**
 * Persistence class for the table permission
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "permission")
public class Permission extends AbstractEntity<Permission> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "permission_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "permission_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100, unique = true)
	private String name;

	@Valid
	@JsonIgnore
	@ManyToMany(mappedBy = "permissions")
	private List<User> users;

	protected Permission() {
		super(false);
	}

	public Permission(String name) {
		super(true);
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void update(Permission e) {

	}
}