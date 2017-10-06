package com.seedshare.entity.abstracts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.interfaces.BasicEntity;
import com.seedshare.helpers.IsHelper;

@MappedSuperclass
public abstract class AbstractEntity<Entity> extends IsHelper implements BasicEntity<Entity>{
	
	@Basic(optional = false)
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "insertion_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date insertionDate;
	
	@Basic(optional = false)
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "last_modification_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModificationDate;

	@JsonIgnore
	@Transient
	protected List<String> validationErrors;
	
	protected AbstractEntity() {
		this.validationErrors = new ArrayList<String>();
	}
	protected AbstractEntity(Boolean isNew) {
		this.validationErrors = new ArrayList<String>();
		this.insertionDate = new Date();
		this.lastModificationDate = new Date();
	}
	
	public Date getInsertionDate() {
		return this.insertionDate;
	}
	
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}
	
	public void addAbstractAttributesValidation() {
		if(isNullOrFromTheFuture(this.insertionDate)) {
			this.validationErrors.add("Data de inserção inválida");
		}
		if(isNullOrFromTheFuture(this.lastModificationDate)) {
			this.validationErrors.add("Data de última modificação inválida");
		}
	}
	
	public List<String> getValidationErrors() {
		return this.validationErrors;
	}
	
	public boolean isNotValid() {
		return !isValid();
	}
	
}
