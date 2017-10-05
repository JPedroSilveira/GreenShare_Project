package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.seedshare.entity.abstracts.AbstractEntity;


/**
 * Persistence class for the table USER_ACHIEVEMENT
 * @author joao.silva
 */
@Entity
@Table(name = "user_achievement")
public class UserAchievement extends AbstractEntity<UserAchievement> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "user_achievement_seq";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "user_achievement_id")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "score")
	private Long score;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name="achievement_id")
	private Achievement achievement;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "conquest_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date conquestDate;

	protected UserAchievement() {
		super();
	}
	
	public UserAchievement(User user, Achievement achievement) {
		super(true);
		this.score = 0L;
		this.achievement = achievement;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNull(this.user) || this.user.isNotValid()){
			this.validationErrors.add("Usuário inválido.");
		}
		if(isNull(this.score) || isPositive(this.score)){
			this.validationErrors.add("Pontuação inválida.");
		}
		if(isNull(this.achievement) || this.achievement.isNotValid()) {
			this.validationErrors.add("Conquista inválida.");
		}
		if(isFromTheFuture(this.conquestDate)) {
			this.validationErrors.add("Data de conquista inválida.");
		}
		addAbstractAttributesValidation();
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public Long getScore() {
		return this.score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}
	
	public Date getConquestDate() {
		return this.conquestDate;
	}

	public void setConquestDate(Date conquestDate) {
		this.conquestDate = conquestDate;
	}
}