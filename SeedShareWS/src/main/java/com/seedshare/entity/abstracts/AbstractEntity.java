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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.interfaces.BasicEntity;
import com.seedshare.helpers.LengthHelper;

@MappedSuperclass
public abstract class AbstractEntity<Entity> implements BasicEntity<Entity>{
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "insertion_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date insertionDate;
	
	@Basic(optional = false)
	@NotNull
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
	
	protected boolean isNullOrEmpty(String string) {
	    return string == null || string.isEmpty();
	}
	
	protected boolean isNull(Object obj) {
		return obj == null;
	}
	
	protected boolean isNotNull(Object obj) {
		return obj == null;
	}
	
	protected boolean isNullOrFromTheFuture(Date date) {
		return isNull(date) || date.after(new Date());
	}
	
	protected boolean isFromTheFuture(Date date) {
		if(isNull(date)) {
			return false;
		}
		return date.after(new Date());
	}
	
	protected LengthHelper is(String string) {
		return new LengthHelper(string);
	}
	
	protected LengthHelper is(short number) {
		return new LengthHelper(number);
	}
	
	protected LengthHelper is(int number) {
		return new LengthHelper(number);
	}
	
	protected LengthHelper is(float number) {
		return new LengthHelper(number);
	}
	
	protected boolean isPositive(Short number) {
		return number>0;
	}
	
	protected boolean isPositive(Integer number) {
		return number>0;
	}
	
	protected boolean isPositive(Long number) {
		return number>0;
	}
	
	public boolean isNotValid() {
		return !isValid();
	}
	
}
