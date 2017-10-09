package com.seedshare.service.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seedshare.entity.Achievement;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.AchievementRepository;

/**
 * Service class of Achievement
 * 
 * @author joao.silva
 */
@Service
public class AchievementServiceImpl extends IsHelper implements AchievementService {

	@Autowired
	AchievementRepository achievementRepository;

	@Override
	public ResponseEntity<?> save(Achievement achievement) {
		Achievement newAchievement = new Achievement(achievement.getCategory(), achievement.getDescription(),
				achievement.getName(), achievement.getRequiredScore());
		return newAchievement.isValid()
				? new ResponseEntity<Achievement>(achievementRepository.save(newAchievement), HttpStatus.OK)
				: new ResponseEntity<List<String>>(newAchievement.getValidationErrors(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		if (isNotNull(id)) {
			achievementRepository.delete(id);
			return new ResponseEntity<String>("Arquivo deletado", HttpStatus.OK);
		}
		return new ResponseEntity<String>("É necessário passar um ID", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		if (isNotNull(id)) {
			Achievement achievementDB = achievementRepository.findOne(id);
			return isNotNull(achievementDB) ? new ResponseEntity<Achievement>(achievementDB, HttpStatus.OK)
					: new ResponseEntity<String>("Achivement não encontrado", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("É necessário passar um ID", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findAll() {
		Iterable<Achievement> achievementsDB = achievementRepository.findAll();
		return new ResponseEntity<Iterable<Achievement>>(achievementsDB, HttpStatus.OK);
	}

}
