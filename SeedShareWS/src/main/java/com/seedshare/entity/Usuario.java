package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe de persistência para a tabela Usuario
 * @author joao.silva
 */
@Entity
@Table(name = "USUARIO", uniqueConstraints = { @UniqueConstraint(columnNames = {"EMAIL"})})
public class Usuario extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_USUARIO";
	
	private static final String URL_FOTO_PADRAO = "https://image.freepik.com/icones-gratis/perfil-macho-utilizador-sombra_318-40244.jpg";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ID_USUARIO")
	private Long id;

	@Basic(optional = true)
	@Size(min = 11, max = 11)
	@Column(name = "CPF")
	private String cpf;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "EMAIL")
	private String email;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "FOTO_URL", columnDefinition="TEXT")
	private String fotoUrl;

	@Basic(optional = false)
	@NotNull
	@Size(min = 8, max = 250)
	@Column(name = "SENHA")
	private String senha;

	@Basic(optional = false)
	@NotNull
	@Column(name = "PESSOA_JURIDICA")
	private Boolean pessoaJuridica;

	//Associação One To Many com Endereco
	@OneToMany(mappedBy="usuario")
	private List<Endereco> enderecos;

	//Associação One To Many com Floricultura
	@OneToMany(mappedBy="usuario")
	private List<Floricultura> floriculturas;

	//Associação One To Many com Oferta
	@OneToMany(mappedBy="usuario")
	private List<Oferta> ofertas;

	//Associação One To Many com Solicitacao
	@OneToMany(mappedBy="usuario")
	private List<Solicitacao> solicitacoes;

	//Associação One To Many com Sugestao
	@OneToMany(mappedBy="usuario")
	private List<Sugestao> sugestoes;
	
	@Transient
	private List<String> mensagensErro;

	//Associação Many To Many com Permissao
	@ManyToMany
	@JoinTable(
		name="USUARIO_PERMISSAO"
		, joinColumns={
			@JoinColumn(name="ID_USUARIO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PERMISSAO")
			}
		)
	private List<Permissao> permissoes;

	//Associação One To Many UsuarioSelo
	@OneToMany(mappedBy="usuario")
	private List<UsuarioSelo> usuarioSelos;
		
	protected Usuario() {
	}
	
	public Usuario(String cpf, String email, String senha, String fotoUrl, Boolean pessoaJuridica) {
		this.email = email;
		this.senha = senha;
		this.fotoUrl = (fotoUrl != null) ? fotoUrl : URL_FOTO_PADRAO;
		this.cpf = pessoaJuridica ? null : cpf;
		this.pessoaJuridica = pessoaJuridica;
		this.mensagensErro = new ArrayList<String>();
	}
	
	public Boolean Validador() {
		if(isNullOrEmpty(this.email) || this.email.length()>100){
			this.mensagensErro.add("Email inválido");
		}
		if(isNullOrEmpty(this.senha) || this.senha.length() < 8 || this.senha.length() > 250) {
			this.mensagensErro.add("Senha inválida");
		}
		if(isNullOrEmpty(this.fotoUrl)) {
			this.mensagensErro.add("Erro inexperado ao salvar foto de perfil");
		}
		if(!this.pessoaJuridica && (isNullOrEmpty(this.cpf) || this.cpf.length() != 11)) {
			this.mensagensErro.add("CPF inválido");
		}
		return this.mensagensErro.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFotoUrl() {
		return this.fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
			
	public String getSenha() {
		return this.senha;
	}
	
	public Boolean isPessoaJuridica() {
		return this.pessoaJuridica;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setUsuario(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setUsuario(null);

		return endereco;
	}

	public List<Floricultura> getFloriculturas() {
		return this.floriculturas;
	}

	public void setFloriculturas(List<Floricultura> floriculturas) {
		this.floriculturas = floriculturas;
	}

	public Floricultura addFloricultura(Floricultura floricultura) {
		getFloriculturas().add(floricultura);
		floricultura.setUsuario(this);

		return floricultura;
	}

	public Floricultura removeFloricultura(Floricultura floricultura) {
		getFloriculturas().remove(floricultura);
		floricultura.setUsuario(null);

		return floricultura;
	}

	public List<Oferta> getOfertas() {
		return this.ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public Oferta addOferta(Oferta oferta) {
		getOfertas().add(oferta);
		oferta.setUsuario(this);

		return oferta;
	}

	public Oferta removeOferta(Oferta oferta) {
		getOfertas().remove(oferta);
		oferta.setUsuario(null);

		return oferta;
	}

	public List<Solicitacao> getSolicitacoes() {
		return this.solicitacoes;
	}

	public void setSolicitacaos(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Solicitacao addSolicitacao(Solicitacao solicitacao) {
		getSolicitacoes().add(solicitacao);
		solicitacao.setUsuario(this);

		return solicitacao;
	}

	public Solicitacao removeSolicitacao(Solicitacao solicitacao) {
		getSolicitacoes().remove(solicitacao);
		solicitacao.setUsuario(null);

		return solicitacao;
	}

	public List<Sugestao> getSugestoes() {
		return this.sugestoes;
	}

	public void setSugestoes(List<Sugestao> sugestoes) {
		this.sugestoes = sugestoes;
	}

	public Sugestao addSugestao(Sugestao sugestao) {
		getSugestoes().add(sugestao);
		sugestao.setUsuario(this);

		return sugestao;
	}

	public Sugestao removeSugestao(Sugestao sugestao) {
		getSugestoes().remove(sugestao);
		sugestao.setUsuario(null);

		return sugestao;
	}

	public List<Permissao> getPermissoes() {
		return this.permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public List<UsuarioSelo> getUsuarioSelos() {
		return this.usuarioSelos;
	}

	public void setUsuarioselos(List<UsuarioSelo> usuarioSelos) {
		this.usuarioSelos = usuarioSelos;
	}

	public UsuarioSelo addUsuarioselo(UsuarioSelo usuarioSelo) {
		getUsuarioSelos().add(usuarioSelo);
		usuarioSelo.setUsuario(this);

		return usuarioSelo;
	}

	public UsuarioSelo removeUsuarioselo(UsuarioSelo usuarioSelo) {
		getUsuarioSelos().remove(usuarioSelo);
		usuarioSelo.setUsuario(null);

		return usuarioSelo;
	}

}