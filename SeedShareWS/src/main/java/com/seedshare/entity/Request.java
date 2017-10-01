package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import java.util.Date;


/**
 * Classe de persistência para a tabela Solicitacao
 * @author joao.silva
 */
@Entity
@Table(name = "SOLICITACAO")
public class Solicitacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_SOLICITACAO";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_SOLICITACAO")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATA_SOLICITACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSolicitacao;

	@Basic(optional = false)
	@NotNull
	@Max(9999)
	@Column(name = "DESCRICAO")
	private Integer quantidade;

	//Associação Many To One com Oferta
	@ManyToOne
	@JoinColumn(name="ID_OFERTA")
	private Oferta oferta;

	//Associação Many To One com Usuario
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	protected Solicitacao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataSolicitacao() {
		return this.dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Oferta getOferta() {
		return this.oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}