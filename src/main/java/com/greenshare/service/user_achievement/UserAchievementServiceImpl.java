package com.greenshare.service.user_achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.achievement.Achievement;
import com.greenshare.entity.achievement.UserAchievement;
import com.greenshare.entity.user.User;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.AchievementRepository;
import com.greenshare.repository.UserAchievementRepository;

/**
 * Implementation of
 * {@link com.greenshare.service.user_achievement.UserAchievementService}
 * interface
 * 
 * @author joao.silva
 */
@Service
public class UserAchievementServiceImpl extends IsHelper implements UserAchievementService {

	@Autowired
	UserAchievementRepository userAchievementRepository;

	@Autowired
	AchievementRepository achievementRepository;

	@Override
	public ResponseEntity<?> save(UserAchievement userAchievement) {
		if (isNotNull(userAchievement)) {
			User userDB = getCurrentUser();
			Achievement achievement = achievementRepository.findOne(userAchievement.getAchievement().getId());
			if (isNotNull(achievement)) {
				Iterable<UserAchievement> userAchievementListDB = userAchievementRepository
						.findAllByUserAndAchievement(userDB, achievement);
				if (isNull(userAchievementListDB)) {
					UserAchievement newUserAchievement = new UserAchievement(userDB, achievement);
					return newUserAchievement.isValid()
							? new ResponseEntity<UserAchievement>(userAchievementRepository.save(newUserAchievement),
									HttpStatus.OK)
							: new ResponseEntity<List<String>>(newUserAchievement.getValidationErrors(),
									HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<String>("Conquista já adicionada ao usuário.", HttpStatus.CONFLICT);
			}
			return new ResponseEntity<String>("Conquista não encontrada.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Relação conquista usuário não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			UserAchievement userAchievementDB = userAchievementRepository.findOne(id);
			if (isNotNull(userAchievementDB) && userAchievementDB.getUser().getId() == getCurrentUser().getId()) {
				userAchievementRepository.delete(id);
				return new ResponseEntity<String>("Relação conquista usuário deletada.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Relação conquista usuário não pertence ao usuário logado.",
					HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			UserAchievement userAchievementDB = userAchievementRepository.findOne(id);
			if (isNotNull(userAchievementDB)) {
				return new ResponseEntity<UserAchievement>(userAchievementDB, HttpStatus.OK);
			}
			return new ResponseEntity<String>("Relação conquista usuário não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAllByUser(Long id) {
		if (isNotNull(id)) {
			Iterable<UserAchievement> userAchievementListDB = userAchievementRepository.findAllByUser(id);
			return new ResponseEntity<Iterable<UserAchievement>>(userAchievementListDB, HttpStatus.OK);
		}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
}
