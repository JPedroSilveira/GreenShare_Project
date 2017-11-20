package com.greenshare.service.achievement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenshare.entity.achievement.Achievement;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.AchievementRepository;

/**
 * Service class implementation of
 * {@link com.greenshare.service.achievement.AchievementService}
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
				achievement.getName(), achievement.getRequiredScore(), achievement.getScoreByAct());
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
		Iterable<Achievement> achievementListDB = achievementRepository.findAllByOrderByRequiredScoreAsc();
		return new ResponseEntity<Iterable<Achievement>>(achievementListDB, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> findAllByCategory(Short category) {
		Iterable<Achievement> achievementListDB = achievementRepository.findAllByCategoryOrderByRequiredScoreAsc(category);
		return new ResponseEntity<Iterable<Achievement>>(achievementListDB, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(Achievement achievement) {
		if(isNotNull(achievement)) {
			Achievement achievementDB = achievementRepository.findOne(achievement.getId());
			if(isNotNull(achievementDB)) {
				achievementDB.update(achievement);
				if(achievementDB.isValid()) {
					achievementDB = achievementRepository.save(achievementDB);
					return new ResponseEntity<Achievement>(achievementDB, HttpStatus.OK);
				}
				return new ResponseEntity<List<String>>(achievementDB.getValidationErrors(), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Conquista não encontrada.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Nova conquista não pode ser nula.", HttpStatus.BAD_REQUEST);
	}

}
