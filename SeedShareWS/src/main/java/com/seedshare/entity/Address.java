package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Classe de persistência para a tabela Endereco
 * @author joao.silva
 */
@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_ENDERECO";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_ENDERECO")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATA_CRIACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Basic(optional = false)
	@NotNull
	@Column(name = "LATITUDE")
	private BigDecimal latitude;

	@Basic(optional = false)
	@NotNull
	@Column(name = "LONGITUDE")
	private BigDecimal longitude;

	//Associação Many To One com Usuario
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	protected Endereco() {
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

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}