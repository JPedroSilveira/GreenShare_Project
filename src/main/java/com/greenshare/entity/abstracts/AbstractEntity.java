package com.greenshare.entity.abstracts;

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
import com.greenshare.entity.interfaces.BasicEntity;
import com.greenshare.helpers.IsHelper;

@MappedSuperclass
public abstract class AbstractEntity<Entity> extends IsHelper implements BasicEntity<Entity> {

	@Basic(optional = false)
	@Past
	@JsonIgnore
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "insertion_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date insertionDate;
	
	@JsonIgnore
	@Transient
	protected List<String> validationErrors;

	protected AbstractEntity(Boolean isNew) {
		this.validationErrors = new ArrayList<String>();
		if (isNew) {
			this.insertionDate = new Date();
		}
	}

	public Date getInsertionDate() {
		return this.insertionDate;
	}

	public List<String> getValidationErrors() {
		return this.validationErrors;
	}

	@JsonIgnore
	public boolean isNotValid() {
		return !isValid();
	}

}
