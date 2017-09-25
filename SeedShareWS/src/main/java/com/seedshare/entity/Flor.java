package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;


/**
 * Classe de persistência para a tabela Flor
 * @author joao.silva
 */
@Entity
@Table(name = "FLOR")
public class Flor implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_FLOR";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_FLOR")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "AROMATICA")
	private Boolean aromatica;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "COR")
	private String cor;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATA_FIM_FLORACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimFloracao;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATA_INICIO_FLORACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioFloracao;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRICAO", columnDefinition="TEXT")
	private String descricao;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NOME")
	private String nome;

	//Associação Many To One com Especie
	@ManyToOne
	@JoinColumn(name="ID_ESPECIE")
	private Especie especie;

	protected Flor() {
	}

	public Long getId() {
		return this.id;
	}

	public void setIdo(Long id) {
		this.id = id;
	}

	public Boolean getAromatica() {
		return this.aromatica;
	}

	public void setAromatica(Boolean aromatica) {
		this.aromatica = aromatica;
	}

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Date getDataFimFloracao() {
		return this.dataFimFloracao;
	}

	public void setDataFimFloracao(Date dataFimFloracao) {
		this.dataFimFloracao = dataFimFloracao;
	}

	public Date getDataInicioFloracao() {
		return this.dataInicioFloracao;
	}

	public void setDataInicioFloracao(Date dataInicioFloracao) {
		this.dataInicioFloracao = dataInicioFloracao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Especie getEspecie() {
		return this.especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

}