package com.greenshare.entity.address;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.FlowerShop;
import com.greenshare.entity.abstracts.AbstractEntity;
import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.user.User;
import com.greenshare.enumeration.AddressType;

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
	@Column(name = "number")
	private Integer number;

	@Basic(optional = false)
	@NotNull(message = "O tipo de endereço não pode ser nulo.")
	@Column(name = "type")
	private Integer type;

	@Basic(optional = false)
	@Size(min = 1, max = 200, message = "O bairro deve conter entre 1 e 200 caracteres.")
	@NotNull(message = "O bairro não pode ser nulo.")
	@Column(name = "neighborhood", length = 200)
	private String neighborhood;

	@Basic(optional = false)
	@Size(min = 1, max = 200, message = "O endereço deve conter entre 1 e 200 caracteres.")
	@NotNull(message = "O endereço não pode ser nulo.")
	@Column(name = "addressName", length = 200)
	private String addressName;

	@Basic(optional = true)
	@Column(name = "complement", length = 200)
	private String complement;

	@Basic(optional = true)
	@Column(name = "referencia", length = 200)
	private String apartment;

	@Basic(optional = false)
	@NotNull(message = "Cidade não poder ser nula.")
	@ManyToOne
	@Valid
	@JoinColumn(name = "city_id")
	private City city;

	@JsonIgnore
	@Basic(optional = true)
	@OneToOne(mappedBy = "address")
	@Valid
	private User user;

	@JsonIgnore
	@Basic(optional = true)
	@Valid
	@OneToOne(mappedBy = "address")
	private Offer offer;

	@JsonIgnore
	@Basic(optional = true)
	@Valid
	@OneToOne(mappedBy = "address")
	private FlowerShop flowerShop;

	protected Address() {
		super(false);
	}

	public Address(City city, Integer number, String neighborhood, String addressName, String complement,
			String apartment, Integer type) {
		super(true);
		this.city = city;
		this.number = number;
		this.neighborhood = neighborhood;
		this.addressName = addressName;
		if (isNotNull(complement) && complement.isEmpty()) {
			this.complement = null;
		} else {
			this.complement = complement;
		}
		if (isNull(apartment) || apartment.isEmpty() || type != AddressType.Apartment.getValue()) {
			this.apartment = null;
		} else {
			this.apartment = apartment;
		}
		this.type = type;
        this.validationErrors = new ArrayList<>();
	}
	
	public Address(Address address) {
		super(true);
		this.city = address.getCity();
		this.number = address.getNumber();
		this.neighborhood = address.getNeighborhood();
		this.addressName = address.getAddressName();
		if (isNull(address.getComplement()) || address.getComplement().isEmpty()) {
			this.complement = null;
		} else {
			this.complement = address.getComplement();
		}
		if (isNull(address.getApartment()) || address.getApartment().isEmpty() || address.getType() != AddressType.Apartment.getValue()) {
			this.apartment = null;
		} else {
			this.apartment = address.getApartment();
		}
		this.type = address.getType();
        this.validationErrors = new ArrayList<>();
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.city)) {
			this.validationErrors.add("Cidade nula.");
		} else if (this.city.isNotValid()) {
			this.validationErrors.addAll(this.city.getValidationErrors());
		}
		if (isNull(this.number) || is(this.number).smallerThan(1)) {
			this.validationErrors.add("O número não pode ser nulo ou menor que um.");
		}
		if (isNull(this.neighborhood) || is(this.neighborhood).orSmallerThan(1).orBiggerThan(200)) {
			this.validationErrors.add("O bairro não pode ser nulo e deve conter entre 1 e 200 caracteres.");
		}
		if (isNull(this.addressName) || is(this.addressName).orSmallerThan(1).orBiggerThan(200)) {
			this.validationErrors.add("O endereço não pode ser nulo e deve conter entre 1 e 200 caracteres.");
		}
		if (isNotNull(this.complement) && is(this.complement).orSmallerThan(1).orBiggerThan(200)) {
			this.validationErrors.add("O complemento deve conter entre 1 e 200 caracteres.");
		}
		if ((this.type == AddressType.Apartment.getValue()) && (isNull(this.apartment) || is(this.apartment).orSmallerThan(1).orBiggerThan(200))) {
			this.validationErrors.add("A número do apartamento não pode ser nulo e deve conter entre 1 e 200 caracteres quando o tipo de endereço for apartamento.");
		}
		if (isNull(this.type) || !AddressType.exists(this.type)) {
			this.validationErrors.add("O tipo de endereço é inválido.");
		}
		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public User getUser() {
		return this.user;
	}

	public City getCity() {
		return city;
	}

	public Integer getNumber() {
		return number;
	}

	public Integer getType() {
		return type;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getAddressName() {
		return addressName;
	}

	public String getComplement() {
		return complement;
	}

	public String getApartment() {
		return apartment;
	}
	
	public Offer getOffer() {
		return offer;
	}

	public FlowerShop getFlowerShop() {
		return flowerShop;
	}

	@Override
	public void update(Address address) {
		this.number = address.getNumber();
		this.neighborhood = address.getNeighborhood();
		this.addressName = address.getAddressName();
		this.complement = address.getComplement();
		this.apartment = address.getApartment();
		this.city = address.getCity();
		this.type = address.getType();
	}

	@JsonIgnore
	public boolean isInUse() {
		return isNull(this.flowerShop) && isNull(this.user);
	}
}