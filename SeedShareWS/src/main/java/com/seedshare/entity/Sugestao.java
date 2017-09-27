package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Classe de persistência para a tabela Sugestao
 * @author joao.silva
 */
@Entity
@Table(name = "SUGESTAO")
public class Sugestao implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_SUGESTAO";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_SUGESTAO")
	private Long id;

	//Associação Many To One com Usuario
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	//Associação Many To One com Especie
	@ManyToOne
	@JoinColumn(name="ID_ESPECIE")
	private Especie especie;

	protected Sugestao() {
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

	public Especie getEspecie() {
		return this.especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

}