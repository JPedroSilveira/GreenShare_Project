package com.greenshare.entity.abstracts;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenshare.entity.interfaces.PhotogenicEntity;
import com.greenshare.enumeration.PhotoType;

/**
 * Abstract class for entities with images to upload
 * 
 * @author joao.silva
 */
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
