package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Persistence class for the table FLOWER_SHOP
 * @author joao.silva
 */
@Entity
@Table(name = "FLOWER_SHOP")
public class FlowerShop implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "FLOWER_SHOP_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "FLOWER_SHOP_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 14, max = 14)
	@Column(name = "CNPJ", length = 14, unique = true)
	private String cnpj;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRIPTION", columnDefinition="TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "LOGO_URL", columnDefinition="TEXT", length = 2500)
	private String logoURL;

	@Basic(optional = false)
	@NotNull
	@Size(max = 50)
	@Column(name = "NAME", length = 50)
	private String name;

	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "RECORD_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recordCreationDate;

	protected FlowerShop() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogoURL() {
		return this.logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public String getName() {
		return this.name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRecordCreationDate() {
		return this.recordCreationDate;
	}

	public void setRecordCreationDate(Date recordCreationDate) {
		this.recordCreationDate = recordCreationDate;
	}
}