package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;


/**
 * Classe de persistência para a tabela Fruto
 * @author joao.silva
 */
@Entity
@Table(name = "FRUTO")
public class Fruto implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_FRUTO";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_FRUTO")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CONSUMO_FAUNA")
	private Boolean consumoFauna;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CONSUMO_HUMANO")
	private Boolean consumoHumano;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATA_FIM_FRUTIFICACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimFrutificacao;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATA_INICIO_FRUTIFICACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicioFrutificacao;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRICAO", columnDefinition="TEXT")
	private String descricao;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NOME_FRUTO")
	private String nomeFruto;

	//Associação Many To One com Especie
	@ManyToOne
	@JoinColumn(name="ID_ESPECIE")
	private Especie especie;

	protected Fruto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getConsumoFauna() {
		return this.consumoFauna;
	}

	public void setConsumoFauna(Boolean consumoFauna) {
		this.consumoFauna = consumoFauna;
	}

	public Boolean getConsumoHumano() {
		return this.consumoHumano;
	}

	public void setConsumoHumano(Boolean consumoHumano) {
		this.consumoHumano = consumoHumano;
	}

	public Date getDataFimFrutificacao() {
		return this.dataFimFrutificacao;
	}

	public void setDataFimFrutificacao(Date dataFimFrutificacao) {
		this.dataFimFrutificacao = dataFimFrutificacao;
	}

	public Date getDataInicioFrutificacao() {
		return this.dataInicioFrutificacao;
	}

	public void setDataInicioFrutificacao(Date dataInicioFrutificacao) {
		this.dataInicioFrutificacao = dataInicioFrutificacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeFruto() {
		return this.nomeFruto;
	}

	public void setNomeFruto(String nomeFruto) {
		this.nomeFruto = nomeFruto;
	}

	public Especie getEspecie() {
		return this.especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

}