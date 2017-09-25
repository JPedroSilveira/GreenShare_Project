package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * Classe de persistência para a tabela Selo
 * @author joao.silva
 */
@Entity
@Table(name = "SELO")
public class Selo implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_SELO";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_SELO")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CATEGORIA", columnDefinition="TEXT")
	private Integer categoria;

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

	@Basic(optional = false)
	@NotNull
	@Column(name = "PONTUACAO_NECESSARIA")
	private Integer pontuacaoNecessaria;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "URL_IMG", columnDefinition="TEXT")
	private String urlImg;

	//Associação One To Many com UsuarioSelo
	@OneToMany(mappedBy="selo")
	private List<UsuarioSelo> usuarioSelos;

	protected Selo() {
	}

	public Long getIdSelo() {
		return this.id;
	}

	public void setIdSelo(Long id) {
		this.id = id;
	}

	public Integer getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
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

	public Integer getPontuacaoNecessaria() {
		return this.pontuacaoNecessaria;
	}

	public void setPontuacaoNecessaria(Integer pontuacaoNecessaria) {
		this.pontuacaoNecessaria = pontuacaoNecessaria;
	}

	public String getUrlImg() {
		return this.urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public List<UsuarioSelo> getUsuarioSelos() {
		return this.usuarioSelos;
	}

	public void setUsuarioselos(List<UsuarioSelo> usuarioSelos) {
		this.usuarioSelos = usuarioSelos;
	}

	public UsuarioSelo addUsuarioselo(UsuarioSelo usuarioSelo) {
		getUsuarioSelos().add(usuarioSelo);
		usuarioSelo.setSelo(this);

		return usuarioSelo;
	}

	public UsuarioSelo removeUsuarioselo(UsuarioSelo usuarioSelo) {
		getUsuarioSelos().remove(usuarioSelo);
		usuarioSelo.setSelo(null);

		return usuarioSelo;
	}

}