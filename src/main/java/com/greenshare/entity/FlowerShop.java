package com.greenshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.abstracts.AbstractPhotogenicEntity;
import com.greenshare.entity.address.Address;
import com.greenshare.entity.user.User;
import com.greenshare.enumeration.PhotoType;

/**
 * Persistence class for the table flower_shop
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "flower_shop")
public class FlowerShop extends AbstractPhotogenicEntity<FlowerShop> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "flower_shop_seq";

	private static final PhotoType PHOTO_TYPE = PhotoType.FLOWER_SHOP;

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "flower_shop_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "O CNPJ não pode ser nulo.")
	@Size(min = 14, max = 14, message = "O CNPJ deve conter 14 caracteres.")
	@Column(name = "cnpj", length = 14, unique = true)
	private String cnpj;

	@Basic(optional = false)
	@NotNull(message = "A descrição não pode ser nula.")
	@Size(min = 1, max = 2500, message = "A descrição deve conter de 1 a 2500 caracteres")
	@Column(name = "description", columnDefinition = "TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull(message = "O nome não pode ser nulo.")
	@Size(min = 1, max = 100, message = "O nome deve conter de 1 e 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@Basic(optional = false)
	@Column(name = "enabled")
	private Boolean enabled;

	@Basic(optional = false)
	@NotNull(message = "O endereço não pode ser nulo.")
	@Valid
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "address_id")
	private Address address;

	@JsonIgnore
	@Valid
	@OneToOne(mappedBy = "flowerShop")
	private User user;

	protected FlowerShop() {
		super(PHOTO_TYPE, false);
	}

	public FlowerShop(String cnpj, String description, String name, Address address) {
		super(PHOTO_TYPE, true);
		this.enabled = false;
		this.cnpj = cnpj;
		this.description = description;
		this.name = name;
		this.hasImage = false;
		this.address = address;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Descrição inválida");
		}
		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		if (isNotNull(this.user) && !this.user.getIsLegalPerson()) {
			this.validationErrors.add("Usuário inválido.");
		} else if (isNotNull(this.user) && this.user.isNotValid()) {
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if (isNull(this.address)) {
			this.validationErrors.add("Endereço não pode ser nulo.");
		} else if (this.address.isNotValid()) {
			this.validationErrors.addAll(this.address.getValidationErrors());
		}
		if (isNull(this.cnpj) || !is(this.cnpj).equal(14) || isNotValidCNPJ()) {
			this.validationErrors.add("CNPJ inválido");
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Address getAddress() {
		return address;
	}

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	@Override
	public void update(FlowerShop e) {
		this.address = e.getAddress();
		this.description = e.getDescription();
	}
	
	@JsonIgnore
	public boolean isNotValidCNPJ() {
		return !isValidCNPJ();
	}

	@JsonIgnore
	public boolean isValidCNPJ() {
		String str_cnpj = this.cnpj;
		int soma = 0, dig;
		String cnpj_calc = str_cnpj.substring(0, 12);

		if (str_cnpj.length() != 14)
			return false;

		char[] chr_cnpj = str_cnpj.toCharArray();

		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);

		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		return str_cnpj.equals(cnpj_calc);

	}

}