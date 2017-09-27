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
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(columnNames = {"EMAIL"})})
public class User extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "SQ_USER";
	
	private static final String URL_FOTO_PADRAO = "3e577c3e-a5a4-4390-8f25-64f6abd883bf";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "USER_ID")
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
	@Column(name = "PHOTO_ID", columnDefinition="TEXT")
	private String photoId;

	@Basic(optional = false)
	@NotNull
	@Size(min = 8, max = 250)
	@Column(name = "PASSWORD")
	private String password;

	@Basic(optional = false)
	@NotNull
	@Column(name = "LEGAL_PERSON")
	private Boolean legalPerson;

	//Associação One To Many com Endereco
	@OneToMany(mappedBy="user")
	private List<Endereco> addresses;

	//Associação One To Many com Floricultura
	@OneToMany(mappedBy="user")
	private List<Floricultura> flowerShops;

	//Associação One To Many com Oferta
	@OneToMany(mappedBy="user")
	private List<Oferta> offers;

	//Associação One To Many com Solicitacao
	@OneToMany(mappedBy="user")
	private List<Solicitacao> requests;

	//Associação One To Many com Sugestao
	@OneToMany(mappedBy="user")
	private List<Sugestao> suggestions;
	
	@Transient
	private List<String> errorMessages;

	//Associação Many To Many com Permissao
	@ManyToMany
	@JoinTable(
		name="USER_PERMISSION"
		, joinColumns={
			@JoinColumn(name="ID_USER")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PERMISSION")
			}
		)
	private List<Permissao> permissions;

	//Associação One To Many UsuarioSelo
	@OneToMany(mappedBy="user")
	private List<UsuarioSelo> userAchievements;
		
	protected User() {
	}
	
	public User(String cpf, String email, String password, String photoId, Boolean legalPerson) {
		this.email = email;
		this.password = password;
		this.photoId = (photoId != null) ? photoId : URL_FOTO_PADRAO;
		this.cpf = legalPerson ? null : cpf;
		this.legalPerson = legalPerson;
		this.errorMessages = new ArrayList<String>();
	}
	
	public Boolean validator() {
		if(isNullOrEmpty(this.email) || this.email.length()>100){
			this.errorMessages.add("Email inválido");
		}
		if(isNullOrEmpty(this.password) || this.password.length() < 8 || this.password.length() > 250) {
			this.errorMessages.add("Senha inválida");
		}
		if(isNullOrEmpty(this.photoId)) {
			this.errorMessages.add("Erro inexperado ao salvar foto de perfil");
		}
		if(!this.legalPerson && (isNullOrEmpty(this.cpf) || this.cpf.length() != 11)) {
			this.errorMessages.add("CPF inválido");
		}
		return this.errorMessages.isEmpty();
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

	public String getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public void setPassword(String password) {
		this.password = password;
	}
			
	public String getPassword() {
		return this.password;
	}
	
	public Boolean isLegalPerson() {
		return this.legalPerson;
	}

	public List<Endereco> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Endereco> addresses) {
		this.addresses = addresses;
	}

	public Endereco addAddress(Endereco address) {
		getAddresses().add(address);
		address.setUser(this);

		return address;
	}

	public Endereco removeAddress(Endereco address) {
		getAddresses().remove(address);
		address.setUser(null);

		return address;
	}

	public List<Floricultura> getFlowerShops() {
		return this.flowerShops;
	}

	public void setFlowerShops(List<Floricultura> flowerShops) {
		this.flowerShops = flowerShops;
	}

	public Floricultura addFlowerShop(Floricultura flowerShop) {
		getFlowerShops().add(flowerShop);
		flowerShop.setUser(this);

		return flowerShop;
	}

	public Floricultura removeFlowerShop(Floricultura flowerShop) {
		getFlowerShops().remove(flowerShop);
		flowerShop.setUser(null);

		return flowerShop;
	}

	public List<Oferta> getOffers() {
		return this.offers;
	}

	public void setOffers(List<Oferta> offers) {
		this.offers = offers;
	}

	public Oferta addOffer(Oferta offer) {
		getOffers().add(offer);
		offer.setUser(this);

		return offer;
	}

	public Oferta removeOferta(Oferta offer) {
		getOffers().remove(offer);
		offer.setUser(null);

		return offer;
	}

	public List<Solicitacao> getRequests() {
		return this.requests;
	}

	public void setRequests(List<Solicitacao> requests) {
		this.requests = requests;
	}

	public Solicitacao addRequests(Solicitacao requests) {
		getRequests().add(requests);
		requests.setUser(this);

		return requests;
	}

	public Solicitacao removeRequests(Solicitacao requests) {
		getRequests().remove(requests);
		requests.setUser(null);

		return requests;
	}

	public List<Sugestao> getSuggestions() {
		return this.suggestions;
	}

	public void setSuggestions(List<Sugestao> suggestions) {
		this.suggestions = suggestions;
	}

	public Sugestao addSuggestions(Sugestao suggestion) {
		getSuggestions().add(suggestion);
		suggestion.setUser(this);

		return suggestion;
	}

	public Sugestao removeSuggestions(Sugestao suggestion) {
		getSuggestions().remove(suggestion);
		suggestion.setUser(null);

		return suggestion;
	}

	public List<Permissao> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permissao> permissions) {
		this.permissions = permissions;
	}

	public List<UsuarioSelo> getUserAchievements() {
		return this.userAchievements;
	}

	public void setUserAchievements(List<UsuarioSelo> userAchievements) {
		this.userAchievements = userAchievements;
	}

	public UsuarioSelo addUserAchievement(UsuarioSelo userAchievement) {
		getUserAchievements().add(userAchievement);
		userAchievement.setUser(this);

		return userAchievement;
	}

	public UsuarioSelo removeUserAchievement(UsuarioSelo userAchievement) {
		getUserAchievements().remove(userAchievement);
		userAchievement.setUser(null);

		return userAchievement;
	}

}