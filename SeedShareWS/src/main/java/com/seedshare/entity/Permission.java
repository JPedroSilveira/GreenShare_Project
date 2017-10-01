package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * Persistence class for the table PERMISSION
 * @author joao.silva
 */
@Entity
@Table(name = "PERMISSION")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "PERMISSION_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "PERMISSION_ID")
	private Long id;
	
	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NAME", length = 50)
	private String name;

	@ManyToMany(mappedBy="permissions")
	private List<User> users;

	protected Permission() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUser() {
		return this.users;
	}

	public void setUser(List<User> users) {
		this.users = users;
	}

}