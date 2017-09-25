package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Classe de persistência para a tabela UsuarioSelo
 * @author joao.silva
 */
@Entity
@Table(name = "USUARIO_SELO")
public class UsuarioSelo implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_USUARIO_SELO";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_USUARIO_SELO")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "PONTUACAO")
	private Long pontuacao;

	//Associação Many To One com Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	//Associação Many To One com Selo
	@ManyToOne
	@JoinColumn(name="ID_SELO")
	private Selo selo;

	protected UsuarioSelo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPontuacao() {
		return this.pontuacao;
	}

	public void setPontuacao(Long pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Selo getSelo() {
		return this.selo;
	}

	public void setSelo(Selo selo) {
		this.selo = selo;
	}

}