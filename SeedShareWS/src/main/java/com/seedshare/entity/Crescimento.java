package com.seedshare.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Classe de persistência para a tabela Crescimento
 * @author joao.silva
 */
@Entity
@Table(name = "CRESCIMENTO")
public class Crescimento implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_CRESCIMENTO";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_CRESCIMENTO")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRICAO", columnDefinition="TEXT")
	private String descricao;

	//Associção One To Many com Especie
	@OneToMany(mappedBy="crescimento")
	private List<Especie> especies;

	protected Crescimento() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Especie> getEspecies() {
		return this.especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	public Especie addEspecie(Especie especie) {
		getEspecies().add(especie);
		especie.setCrescimento(this);

		return especie;
	}

	public Especie removeEspecie(Especie especie) {
		getEspecies().remove(especie);
		especie.setCrescimento(null);

		return especie;
	}

}