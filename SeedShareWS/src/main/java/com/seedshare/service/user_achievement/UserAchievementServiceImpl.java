package com.seedshare.service.user_achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.achievement.UserAchievement;
import com.seedshare.entity.user.User;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.UserAchievementRepository;

/**
 * Implementation of
 * {@link com.seedshare.service.user_achievement.UserAchievementService}
 * interface
 * 
 * @author joao.silva
 * @author gabriel.schneider
 */
@Service
public class UserAchievementServiceImpl extends IsHelper implements UserAchievementService {

	@Autowired
	UserAchievementRepository userAchievementRepository;

	@Override
	public ResponseEntity<?> save(UserAchievement userAchievement) {
		if (isNotNull(userAchievement)) {
			User userDB = getCurrentUser();
			Iterable<UserAchievement> userAchievementListDB = userAchievementRepository
					.findAllByUserAndAchievement(userDB, userAchievement.getAchievement());
			if (isNull(userAchievementListDB)) {
				UserAchievement newUserAchievement = new UserAchievement(userDB, userAchievement.getAchievement());
				return newUserAchievement.isValid()
						? new ResponseEntity<UserAchievement>(userAchievementRepository.save(newUserAchievement),
								HttpStatus.OK)
						: new ResponseEntity<List<String>>(newUserAchievement.getValidationErrors(),
								HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Conquista já adicionada ao usuário.", HttpStatus.CONFLICT);
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
}
