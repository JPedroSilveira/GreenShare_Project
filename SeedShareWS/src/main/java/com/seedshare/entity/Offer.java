package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Classe de persistência para a tabela Oferta
 * @author joao.silva
 */
@Entity
@Table(name = "OFERTA")
public class Oferta implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_OFERTA";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_OFERTA")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATA_CRIACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Basic(optional = false)
	@NotNull
	@Column(name = "PRECO_UNIDADE")
	private BigDecimal precoUnidade;

	@Basic(optional = false)
	@NotNull
	@Max(9999)
	@Column(name = "QUANTIDADE")
	private Integer quantidade;

	@Basic(optional = false)
	@NotNull
	@Column(name = "STATUS_OFERTA", columnDefinition="TEXT")
	private Integer statusOferta;

	@Basic(optional = false)
	@NotNull
	@Column(name = "TIPO")
	private Integer tipo;

	//Associação Many To One com Usuario
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	//Associação Many To One com Especie
	@ManyToOne
	@JoinColumn(name="ID_ESPECIE")
	private Especie especie;

	//Associação One To Many com Solicitacao
	@OneToMany(mappedBy="oferta")
	private List<Solicitacao> solicitacoes;

	protected Oferta() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getPrecoUnidade() {
		return this.precoUnidade;
	}

	public void setPrecoUnidade(BigDecimal precoUnidade) {
		this.precoUnidade = precoUnidade;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getStatusOferta() {
		return this.statusOferta;
	}

	public void setStatusOferta(Integer statusOferta) {
		this.statusOferta = statusOferta;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	public List<Solicitacao> getSolicitacoes() {
		return this.solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacaos) {
		this.solicitacoes = solicitacaos;
	}

	public Solicitacao addSolicitacao(Solicitacao solicitacao) {
		getSolicitacoes().add(solicitacao);
		solicitacao.setOferta(this);

		return solicitacao;
	}

	public Solicitacao removeSolicitacao(Solicitacao solicitacao) {
		getSolicitacoes().remove(solicitacao);
		solicitacao.setOferta(null);

		return solicitacao;
	}

}