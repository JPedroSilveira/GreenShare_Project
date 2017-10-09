package com.seedshare.entity.abstracts;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.interfaces.PhotogenicEntity;
import com.seedshare.enumeration.PhotoType;

@MappedSuperclass
public abstract class AbstractPhotogenicEntity<Entity> extends AbstractEntity<Entity> implements PhotogenicEntity {

	@Basic(optional = false)
	@NotNull
	@Column(name = "HAS_IMAGE")
	protected Boolean hasImage;

	@JsonIgnore
	@Transient
	protected PhotoType photoType;

	public AbstractPhotogenicEntity(PhotoType photoType, Boolean isNew) {
		super(isNew);
		this.hasImage = false;
		this.photoType = photoType;
	}

	@Override
	public void addAbstractAttributesValidation() {
		if (this.insertionDate == null || this.insertionDate.after(new Date())) {
			this.validationErrors.add("Data de inserção inválida.");
		}
		if (this.lastModificationDate == null || this.lastModificationDate.after(new Date())) {
			this.validationErrors.add("Data de última modificação inválida.");
		}
		if (isNull(this.photoType)) {
			this.validationErrors.add("Tipo de imagem de classe inválida.");
		}
		if (this.hasImage == null) {
			this.validationErrors.add("Atributo HasImage inválido.");
		}
	}

	@Override
	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}

	@Override
	public Boolean getHasImage() {
		return this.hasImage;
	}

	@Override
	public PhotoType getPhotoType() {
		return this.photoType;
	}
}
