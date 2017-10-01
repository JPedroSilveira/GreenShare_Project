package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Classe de persistência para a tabela Especie
 * @author joao.silva
 */
@Entity
@Table(name = "ESPECIE")
public class Especie implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_ESPECIE";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_ESPECIE")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "APROVADA")
	private Boolean aprovada;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ATRAI_PASSAROS")
	private Boolean atraiPassaros;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DATA_INSERCAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInsercao;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRICAO", columnDefinition="TEXT")
	private String descricao;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "FOTO_URL", columnDefinition="TEXT")
	private String fotoUrl;

	@Basic(optional = false)
	@NotNull
	@Size(max = 5000)
	@Column(name = "GUIA_CULTIVO", columnDefinition="TEXT")
	private String guiaCultivo;

	@Basic(optional = false)
	@NotNull
	@Column(name = "MEDICINAL")
	private Boolean medicinal;

	@Basic(optional = false)
	@NotNull
	@Column(name = "MELIFERA")
	private Boolean melifera;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NOME_CIENTIFICO")
	private String nomeCientifico;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NOME_POPULAR")
	private String nomePopular;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ORNALMENTAL")
	private Boolean ornamental;

	@Basic(optional = false)
	@NotNull
	@Column(name = "PORTE")
	private BigDecimal porte;

	//Associação Many To One com Crescimento
	@ManyToOne
	@JoinColumn(name="ID_CRESCIMENTO")
	private Crescimento crescimento;

	//Associação Many To Many com Clima
	@ManyToMany
	@JoinTable(
		name="ESPECIE_CLIMA"
		, joinColumns={
			@JoinColumn(name="ID_ESPECIE")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_CLIMA")
			}
		)
	private List<Clima> climas;
	
	//Associção Many To Many com Solo
	@ManyToMany(mappedBy="especies")
	private List<Solo> solos;

	//Associção One To Many com com Flor
	@OneToMany(mappedBy="especie")
	private List<Flor> flores;

	//Associção One To Many com Fruto
	@OneToMany(mappedBy="especie")
	private List<Fruto> frutos;

	//Associção One To Many com Oferta
	@OneToMany(mappedBy="especie")
	private List<Oferta> ofertas;

	//Associção One To Many com Sugestao
	@OneToMany(mappedBy="especie")
	private List<Sugestao> sugestoes;

	protected Especie() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAprovada() {
		return this.aprovada;
	}

	public void setAprovada(Boolean aprovada) {
		this.aprovada = aprovada;
	}

	public Boolean getAtraiPassaros() {
		return this.atraiPassaros;
	}

	public void setAtraiPassaros(Boolean atraiPassaros) {
		this.atraiPassaros = atraiPassaros;
	}

	public Date getDataInsercao() {
		return this.dataInsercao;
	}

	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFotoUrl() {
		return this.fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public String getGuiaCultivo() {
		return this.guiaCultivo;
	}

	public void setGuiaCultivo(String guiaCultivo) {
		this.guiaCultivo = guiaCultivo;
	}

	public Boolean getMedicinal() {
		return this.medicinal;
	}

	public void setMedicinal(Boolean medicinal) {
		this.medicinal = medicinal;
	}

	public Boolean getMelifera() {
		return this.melifera;
	}

	public void setMelifera(Boolean melifera) {
		this.melifera = melifera;
	}

	public String getNomeCientifico() {
		return this.nomeCientifico;
	}

	public void setNomeCientifico(String nomeCientifico) {
		this.nomeCientifico = nomeCientifico;
	}

	public String getNomePopular() {
		return this.nomePopular;
	}

	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	public Boolean getOrnamental() {
		return this.ornamental;
	}

	public void setOrnamental(Boolean ornamental) {
		this.ornamental = ornamental;
	}

	public BigDecimal getPorte() {
		return this.porte;
	}

	public void setPorte(BigDecimal porte) {
		this.porte = porte;
	}

	public Crescimento getCrescimento() {
		return this.crescimento;
	}

	public void setCrescimento(Crescimento crescimento) {
		this.crescimento = crescimento;
	}

	public List<Clima> getClimas() {
		return this.climas;
	}

	public void setClimas(List<Clima> climas) {
		this.climas = climas;
	}

	public List<Solo> getSolos() {
		return this.solos;
	}

	public void setSolos(List<Solo> solos) {
		this.solos = solos;
	}

	public List<Flor> getFlores() {
		return this.flores;
	}

	public void setFlores(List<Flor> flores) {
		this.flores = flores;
	}

	public Flor addFlor(Flor flor) {
		getFlores().add(flor);
		flor.setEspecie(this);

		return flor;
	}

	public Flor removeFlor(Flor flor) {
		getFlores().remove(flor);
		flor.setEspecie(null);

		return flor;
	}

	public List<Fruto> getFrutos() {
		return this.frutos;
	}

	public void setFrutos(List<Fruto> frutos) {
		this.frutos = frutos;
	}

	public Fruto addFruto(Fruto fruto) {
		getFrutos().add(fruto);
		fruto.setEspecie(this);

		return fruto;
	}

	public Fruto removeFruto(Fruto fruto) {
		getFrutos().remove(fruto);
		fruto.setEspecie(null);

		return fruto;
	}

	public List<Oferta> getOfertas() {
		return this.ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public Oferta addOferta(Oferta oferta) {
		getOfertas().add(oferta);
		oferta.setEspecie(this);

		return oferta;
	}

	public Oferta removeOferta(Oferta oferta) {
		getOfertas().remove(oferta);
		oferta.setEspecie(null);

		return oferta;
	}

	public List<Sugestao> getSugestoes() {
		return this.sugestoes;
	}

	public void setSugestaos(List<Sugestao> sugestoes) {
		this.sugestoes = sugestoes;
	}

	public Sugestao addSugestao(Sugestao sugestao) {
		getSugestoes().add(sugestao);
		sugestao.setEspecie(this);

		return sugestao;
	}

	public Sugestao removeSugestao(Sugestao sugestao) {
		getSugestoes().remove(sugestao);
		sugestao.setEspecie(null);

		return sugestao;
	}

}