package com.seedshare.entity.achievement;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seedshare.entity.abstracts.AbstractPhotogenicEntity;
import com.seedshare.enumeration.PhotoType;

import java.util.List;

/**
 * Persistence class for the table achievement
 * 
 * @author joao.silva
 */
@Entity
@Table(name = "achievement")
public class Achievement extends AbstractPhotogenicEntity<Achievement> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "achievement_seq";

	private static final PhotoType PHOTO_TYPE = PhotoType.ACHIEVEMENT;

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	@Basic(optional = false)
	@Column(name = "achievement_id")
	private Long id;

	@Basic(optional = false)
	@NotNull(message = "Categoria não pode ser nula.")
	@Column(name = "category")
	private Short category;

	@Basic(optional = false)
	@NotNull(message = "Nome não pode ser nulo")
	@Size(min = 1, max = 2500, message = "Descrição deve conter entre 1 e 2500 caracteres.")
	@Column(name = "description", columnDefinition = "TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull(message = "Nome não pode ser nulo")
	@Size(min = 1, max = 100, message = "Nome deve conter entre 1 e 100 caracteres.")
	@Column(name = "name", length = 100)
	private String name;

	@Basic(optional = false)
	@NotNull(message = "Pontuação necessária não pode ser nula.")
	@Min(1)
	@Column(name = "required_score")
	private Long requiredScore;
	
	@Basic(optional = false)
	@NotNull(message = "Pontuação por ato não pode ser nula.")
	@Min(1)
	@Column(name = "score_by_act")
	private Long scoreByAct;

	@JsonIgnore
	@Valid
	@OneToMany(mappedBy = "achievement")
	private List<UserAchievement> userAchievements;

	protected Achievement() {
		super(PHOTO_TYPE, false);
	}

	public Achievement(Short category, String description, String name, Long requiredScore, Long scoreByAct) {
		super(PHOTO_TYPE, true);
		this.category = category;
		this.description = description;
		this.name = name;
		this.requiredScore = requiredScore;
		this.scoreByAct = scoreByAct;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.category) || isPositive(this.category)) {
			this.validationErrors.add("Categoria inválida.");
		}
		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Descrição inválida.");
		}
		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
		}
		if (isNull(this.requiredScore) || is(this.requiredScore).smallerThan(1)) {
			this.validationErrors.add("Pontuação necessário inválida.");
		}
		if(isNull(this.scoreByAct) || is(this.scoreByAct).smallerThan(1)) {
			this.validationErrors.add("Pontuação por ato deve ser maior que um.");
		}
		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Short getCategory() {
		return this.category;
	}

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public Long getRequiredScore() {
		return this.requiredScore;
	}

	public Long getScoreByAct() {
		return scoreByAct;
	}

	public List<UserAchievement> getUserAchievements() {
		return this.userAchievements;
	}

	public void update(Achievement achievement) {
		this.description = achievement.getDescription();
		this.name = achievement.getName();
		this.scoreByAct = achievement.getScoreByAct();
	}
}