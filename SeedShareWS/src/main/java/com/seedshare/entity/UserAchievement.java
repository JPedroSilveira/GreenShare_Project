package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Persistence class for the table USER_ACHIEVEMENT
 * @author joao.silva
 */
@Entity
@Table(name = "USER_ACHIEVEMENT")
public class UserAchievement implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "USER_ACHIEVEMENT_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "USER_ACHIEVEMENT_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "SCORE")
	private Long score;

	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name="ID_ACHIEVEMENT")
	private Achievement achievement;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "CONQUEST_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date conquestDate;

	protected UserAchievement() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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