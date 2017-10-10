package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractPhotogenicEntity;
import com.seedshare.enumeration.PhotoType;

/**
 * Persistence class for the table post
 * @author joao.silva
 */
@Entity
@Table(name = "post")
public class Post extends AbstractPhotogenicEntity<Post> implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "post_seq";
	
	private static final PhotoType PHOTO_TYPE = PhotoType.POST;
		
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "post_id")
	private Long id;
	
	@ManyToOne
	@NotNull(message = "O usuário não pode ser nulo.")
	@Valid
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@NotNull(message = "A especie não pode ser nula.")
	@Valid
	@JoinColumn(name="species_id")
	private Species species;
	
	@Basic(optional = false)
	@NotNull(message = "O texto não pode ser nulo.")
	@Size(min = 1, max = 500, message = "O texto deve conter entre 1 e 500 caracteres.")
	@Column(name = "text", columnDefinition="TEXT", length = 500)
	private String text;
	
	@Valid
	@OneToMany(mappedBy = "post")
	private List<PostComment> postComments;
	
	protected Post() {
		super(PHOTO_TYPE, false);
		this.validationErrors = new ArrayList<String>();
	}
	
	public Post(User user, Species species, String text) {
		super(PHOTO_TYPE, true);
		this.user = user;
		this.species = species;
		this.text = text;
		this.hasImage = false;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.text) || is(this.text).orSmallerThan(1).orBiggerThan(500)){
			this.validationErrors.add("Texto inválido.");
		}
		if(isNull(this.hasImage)) {
			this.validationErrors.add("Definição inválida para imagem.");
		}
		if(isNull(this.user)){
			this.validationErrors.add("O usuário não pode ser nulo.");
		}else if(this.user.isNotValid()) {			
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if(isNull(this.species)) {
			this.validationErrors.add("A espécie nao pode ser nula.");
		}else if(this.species.isNotValid()) {
			this.validationErrors.addAll(this.species.getValidationErrors());
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}
	
	public Long getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}

	public Species getSpecies() {
		return this.species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public List<PostComment> getPostComments() {
		return postComments;
	}

}
