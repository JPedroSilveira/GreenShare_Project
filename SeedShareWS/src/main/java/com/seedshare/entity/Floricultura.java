package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Classe de persistência para a tabela Floricultura
 * @author joao.silva
 */
@Entity
@Table(name = "FLORICULTURA")
public class Floricultura implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_FLORICULTURA";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_FLORICULTURA")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 14, max = 14)
	@Column(name = "CNPJ")
	private String cnpj;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRICAO", columnDefinition="TEXT")
	private String descricao;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "LOGO_URL", columnDefinition="TEXT")
	private String logoURL;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NOME")
	private String nome;

	//Associação Many To One com Usuario
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	protected Floricultura() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLogoURL() {
		return this.logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}