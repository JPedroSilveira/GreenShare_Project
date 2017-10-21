package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractEntity;

import java.util.List;

/**
 * Persistence class for the table address
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "address")
public class Address extends AbstractEntity<Address> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "address_seq";

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "address_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "O número não pode ser nulo.")
	@Size(min = 1, message = "O número deve conter no minimo 1 número.")
	@Column(name = "numero")
	private Integer numero;

	@Basic(optional = false)
	@Size(min = 1, max = 200, message = "O bairro deve conter entre 1 e 200 caracteres.")
	@NotNull(message = "O bairro não pode ser nulo.")
	@Column(name = "bairro", length = 200)
	private String bairro;

	@Basic(optional = false)
	@Size(min = 1, max = 200, message = "O endereço deve conter entre 1 e 200 caracteres.")
	@NotNull(message = "O endereço não pode ser nulo.")
	@Column(name = "endereco", length = 200)
	private String endereco;

	@Basic(optional = true)
	@Size(min = 1, max = 200, message = "O complemento deve conter entre 1 e 200 caracteres.")
	@NotNull(message = "O complemento não pode ser nulo.")
	@Column(name = "complemento", length = 200)
	private String complemento;

	@Basic(optional = false)
	@Size(min = 1, max = 200, message = "A referencia deve conter entre 1 e 200 caracteres.")
	@NotNull(message = "A referencia não pode ser nulo.")
	@Column(name = "referencia", length = 200)
	private String referencia;

	@JsonIgnore
	@Basic(optional = true)
	@ManyToOne
	@Valid
	@JoinColumn(name = "user_id")
	private User user;

	@Basic(optional = false)
	@NotNull(message = "Cidade não poder ser nula.")
	@ManyToOne
	@Valid
	@JoinColumn(name = "city_id")
	private City city;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "address")
	private List<Offer> offers;

	protected Address() {
		super(false);
	}

	public Address(City city, User user, Integer numero, String bairro, String endereco, String complemento, String referencia) {
		super(true);
		this.user = user;
		this.city = city;
		this.numero = numero;
		this.bairro = bairro;
		this.endereco = endereco;
		this.complemento = complemento;
		this.referencia = referencia;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNotNull(this.user) && this.user.isNotValid()) {
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if (isNull(this.city)) {
			this.validationErrors.add("Cidade nula.");
		} else if (this.city.isNotValid()) {
			this.validationErrors.addAll(this.city.getValidationErrors());
		}
		if(isNull(this.numero) || is(this.numero).smallerThan(1)) {
			this.validationErrors.add("O número não pode ser nulo ou menor que um.");
		}
		if(isNull(this.bairro) || is(this.bairro).orSmallerThan(1).orBiggerThan(200)) {
			this.validationErrors.add("O bairro não pode ser nulo e deve conter entre 1 e 200 caracteres.");
		}
		if(isNull(this.endereco) || is(this.endereco).orSmallerThan(1).orBiggerThan(200)) {
			this.validationErrors.add("O endereço não pode ser nulo e deve conter entre 1 e 200 caracteres.");
		}
		if(isNull(this.complemento) || is(this.complemento).orSmallerThan(1).orBiggerThan(200)) {
			this.validationErrors.add("O complemento não pode ser nulo e deve conter entre 1 e 200 caracteres.");
		}
		if(isNull(this.bairro) || is(this.bairro).orSmallerThan(1).orBiggerThan(200)) {
			this.validationErrors.add("O bairro deve conter entre 1 e 200 caracteres.");
		}
		if(isNull(this.bairro) || is(this.bairro).orSmallerThan(1).orBiggerThan(200)) {
			this.validationErrors.add("A referencia não pode ser nula e deve conter entre 1 e 200 caracteres");
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}