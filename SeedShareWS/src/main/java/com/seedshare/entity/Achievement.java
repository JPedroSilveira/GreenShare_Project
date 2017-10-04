package com.seedshare.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seedshare.entity.abstracts.AbstractEntity;

import java.util.List;


/**
 * Persistence class for the table ACHIEVEMENT
 * @author joao.silva
 */
@Entity
@Table(name = "ACHIEVEMENT")
public class Achievement extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String SEQUENCE_NAME = "ACHIEVEMENT_SEQ";
	
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
	@Column(name = "ACHIEVEMENT_ID")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "CATEGORY", columnDefinition="TEXT")
	private Integer category;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "DESCRIPTION", columnDefinition="TEXT", length = 2500)
	private String description;

	@Basic(optional = false)
	@NotNull
	@Size(max = 100)
	@Column(name = "NAME", length = 100)
	private String name;

	@Basic(optional = false)
	@NotNull
	@Column(name = "REQUIRED_SCORE")
	private Long requiredScore;

	@Basic(optional = false)
	@NotNull
	@Size(max = 2500)
	@Column(name = "IMG_URL", columnDefinition="TEXT")
	private String imgUrl;

	@OneToMany(mappedBy="achievement")
	private List<UserAchievement> userAchievements;

	protected Achievement() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRequiredScore() {
		return this.requiredScore;
	}

	public void setRequiredScore(Long requiredScore) {
		this.requiredScore = requiredScore;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<UserAchievement> getUserAchievements() {
		return this.userAchievements;
	}

	public void setUserAchievements(List<UserAchievement> userAchievements) {
		this.userAchievements = userAchievements;
	}

	public UserAchievement addUserAchievement(UserAchievement userAchievement) {
		getUserAchievements().add(userAchievement);
		userAchievement.setAchievement(this);

		return userAchievement;
	}

	public UserAchievement removeUserAchievement(UserAchievement userAchievement) {
		getUserAchievements().remove(userAchievement);
		userAchievement.setAchievement(null);

		return userAchievement;
	}

	@Override
	public Boolean isValid() {
		// TODO Auto-generated method stub
		return null;
	}

}