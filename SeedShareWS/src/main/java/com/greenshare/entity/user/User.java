package com.greenshare.entity.user;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.FlowerShop;
import com.greenshare.entity.Suggestion;
import com.greenshare.entity.abstracts.AbstractPhotogenicEntity;
import com.greenshare.entity.achievement.UserAchievement;
import com.greenshare.entity.address.Address;
import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.offer.OfferComment;
import com.greenshare.entity.offer.Request;
import com.greenshare.entity.post.Post;
import com.greenshare.entity.post.PostComment;
import com.greenshare.enumeration.PhotoType;
import com.greenshare.helpers.CPFHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Persistence class for the table greenshare_user
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "greenshare_user")
public class User extends AbstractPhotogenicEntity<User> implements Serializable {

	public String getNickname() {
		return nickname;
	}

	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "greenshare_user_seq";

	private static final String EMAIL_PATTERN = "(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)";

	private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

	private static final PhotoType PHOTO_TYPE = PhotoType.USER;

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "user_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "Apelido não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O apelido deve conter entre 1 e 100 caracteres.")
	@Column(name = "nickname", length = 100)
	private String nickname;
	
	@Basic(optional = false)
	@NotNull(message = "Nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@Basic(optional = true)
	@NotNull(message = "CPF não pode ser nulo.")
	@Size(min = 11, max = 11, message = "CPF deve conter 11 digitos.")
	@Column(name = "cpf", length = 11, unique = true)
	private String cpf;

	@Basic(optional = false)
	@NotNull(message = "Email não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O email deve conter entre 1 e 100 caracteres.")
	@Email(message = "Email inválido.")
	@Column(name = "email", length = 100, unique = true)
	private String email;

	@Basic(optional = false)
	@NotNull(message = "A senha não pode ser nula.")
	@Size(min = 8, max = 250, message = "A senha deve conter no mínimo 8 caracteres e no máximo 250.")
	@Column(name = "password", length = 250)
	private String password;
	
	@Basic(optional = true)
	@NotNull(message = "O telefone não pode ser nulo.")
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;

	@Basic(optional = false)
	@NotNull(message = "Obrigatório informar se o usuário é uma pessoa jurídica.")
	@Column(name = "legal_person")
	private Boolean isLegalPerson;
	
	@Basic(optional = true)
	@Valid
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@Basic(optional = true)
	@Valid
	@OneToOne(mappedBy = "user")
	private FlowerShop flowerShop;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "user")
	private List<Offer> offers;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "user")
	private List<OfferComment> offerComments;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "user")
	private List<PostComment> postComments;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "user")
	private List<Request> requests;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "user")
	private List<Suggestion> suggestions;

	@JsonIgnore
	@Valid
	@ManyToMany
	@JoinTable(name = "user_permission", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = {
			@JoinColumn(name = "id_permission") })
	private List<Permission> permissions;

	@Valid
	@OneToMany(mappedBy = "user")
	private List<UserAchievement> userAchievements;

	protected User() {
		super(PHOTO_TYPE, false);
	}

	public User(String cpf, String nickname, String name, String email, String password, Boolean isLegalPerson, Address address, String phoneNumber) {
		super(PHOTO_TYPE, true);
		this.email = email;
		this.nickname = nickname;
		this.name = name;
		this.password = password;
		this.isLegalPerson = isNull(isLegalPerson) ? false : isLegalPerson;
		this.cpf = this.isLegalPerson ? null : cpf.replace(" ", "");
		this.validationErrors = new ArrayList<String>();
		this.phoneNumber = phoneNumber.replace(" ", ""); 
		this.hasImage = false;
		if (isNotNull(this.password) && this.password.length() >= 8) {
			this.encodePassword();
		}
		this.address = address;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if(isNullOrEmpty(this.nickname) || is(this.nickname).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Apelido inválido.");
		}
		if(isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		if (isNullOrEmpty(this.email) || is(this.email).orSmallerThan(1).orBiggerThan(100) || isNotValidEmail()) {
			this.validationErrors.add("Email inválido.");
		}
		if (isNotNull(this.phoneNumber) && (is(this.phoneNumber).orSmallerThan(1).orBiggerThan(20) || !StringUtils.isNumeric(this.phoneNumber))) {
			this.validationErrors.add("Número de telefone inválido.");
		}
		if (hasInvalidPassword()) {
			this.validationErrors.add("Senha inválida.");
		}
		if (!this.isLegalPerson && (isNullOrEmpty(this.cpf) || !StringUtils.isNumeric(this.cpf) || CPFHelper.isNotCPF(this.cpf))) {
			this.validationErrors.add("CPF inválido.");
		}
		if(!this.isLegalPerson && isNotNull(this.flowerShop)) {
			this.validationErrors.add("O usuário físico não pode possuir floricultura.");
		}
		if(isNotNull(this.flowerShop) && this.flowerShop.isNotValid()) {
			this.validationErrors.addAll(this.flowerShop.getValidationErrors());
		}
		if (hasInvalidName()) {
			this.validationErrors.add("Nome inválido.");
		}
		if(isNotNull(this.address) && this.address.isNotValid()){
			this.validationErrors.addAll(this.address.getValidationErrors());
		}

		return this.validationErrors.isEmpty();
	}

	public boolean hasInvalidPassword() {
		return isNullOrEmpty(this.password) || is(this.password).orSmallerThan(8).orBiggerThan(250);
	}

	public boolean hasValidPassword() {
		return !hasInvalidPassword();
	}

	public boolean hasInvalidName() {
		return isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100);
	}

	public boolean hasValidName() {
		return !hasInvalidName();
	}

	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public Long getId() {
		return this.id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public Boolean getIsLegalPerson() {
		return this.isLegalPerson;
	}

	public void setIsLegalPerson(Boolean isLegalPerson) {
		this.isLegalPerson = isLegalPerson;
	}

	public Address getAddress() {
		return this.address;
	}

	public FlowerShop getFlowerShop() {
		return this.flowerShop;
	}

	public List<Offer> getOffers() {
		return this.offers;
	}

	public List<Request> getRequests() {
		return this.requests;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public List<Suggestion> getSuggestions() {
		return this.suggestions;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public List<UserAchievement> getUserAchievements() {
		return this.userAchievements;
	}

	public List<String> getValidationErrors() {
		return validationErrors;
	}

	public List<OfferComment> getOfferComments() {
		return offerComments;
	}

	public void setOfferComments(List<OfferComment> offerComments) {
		this.offerComments = offerComments;
	}

	public void addValidationError(String message) {
		this.validationErrors.add(message);
	}

	@JsonIgnore
	public Boolean isValidEmail() {
		return PATTERN.matcher(this.email).matches();
	}

	@JsonIgnore
	public Boolean isNotValidEmail() {
		return !isValidEmail();
	}

	public void encodePassword() {
		this.setPassword(passwordEncoder().encode(this.password));
	}

	public List<PostComment> getPostComments() {
		return postComments;
	}

	@Override
	@JsonIgnore
	public PhotoType getPhotoType() {
		return User.PHOTO_TYPE;
	}

	@Override
	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}

	@Override
	public Boolean getHasImage() {
		return this.hasImage;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	@Override
	public void update(User e) {
		this.name = e.getName();
		this.nickname = e.getNickname();
	}
	
	public boolean hasFlowerShop() {
		return isNotNull(this.flowerShop) && this.isLegalPerson;
	}
	
	@JsonIgnore
	public Address getAddressForOffer() {
		if(hasAddress()) {
			if(hasFlowerShopAddress() && hasEnabledFlowerShop()) {
				return this.flowerShop.getAddress();
			}
			return this.address;	
		}
		return null;
	}
	
	public boolean hasAddress() {
		return isNotNull(this.address) || isNotNull(this.flowerShop.getAddress()); 
	}
	
	@JsonIgnore
	public boolean hasFlowerShopAddress() {
		return hasFlowerShop() && isNotNull(this.flowerShop.getAddress()); 
	}
	
	public boolean hasEnabledFlowerShop() {
		return this.flowerShop.getEnabled();
	}
	
	public void clearPrivateData() {
		this.cpf = null;
		this.email = null;
		this.address = null;
		this.phoneNumber = null;
		this.password = null;
	}
}