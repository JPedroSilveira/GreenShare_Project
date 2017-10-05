package com.seedshare.service.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.UserUtils;
import com.seedshare.entity.Achievement;
import com.seedshare.entity.Flower;
import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.Fruit;
import com.seedshare.entity.Offer;
import com.seedshare.entity.Post;
import com.seedshare.entity.Species;
import com.seedshare.entity.User;
import com.seedshare.entity.interfaces.PhotogenicEntity;
import com.seedshare.enumeration.PhotoType;
import com.seedshare.repository.AchievementRepository;
import com.seedshare.repository.FlowerRepository;
import com.seedshare.repository.FlowerShopRepository;
import com.seedshare.repository.FruitRepository;
import com.seedshare.repository.OfferRepository;
import com.seedshare.repository.PostRepository;
import com.seedshare.repository.SpeciesRepository;
import com.seedshare.repository.UserRepository;
/**
 * Implementation of photogenicService Service interface
 * @author joao.silva
 */
@Service
public class PhotogenicServiceImpl extends UserUtils implements PhotogenicService{
	/* JPA can't map interfaces :( */
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FlowerShopRepository flowerShopRepository;

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	SpeciesRepository speciesRepository;
	
	@Autowired
	FlowerRepository flowerRepository;
	
	@Autowired
	AchievementRepository achievementRepository;
	
	@Autowired
	FruitRepository fruitRepository;
	
	@Autowired
	OfferRepository offerRepository;

	@Override
	public PhotogenicEntity save(PhotogenicEntity photogenicEntity) {
		Long id = photogenicEntity.getId();
		PhotoType photoType = photogenicEntity.getPhotoType();
		if(photoType == PhotoType.USER) {
			User user = userRepository.findOne(id);
			user.setHasImage(true);
			return userRepository.save(user);
		}else if(photoType == PhotoType.FLOWER_SHOP) {
			FlowerShop flowerShop = flowerShopRepository.findOne(id);
			flowerShop.setHasImage(true);
			return flowerShopRepository.save(flowerShop);
		}else if(photoType == PhotoType.POST) {
			Post post = postRepository.findOne(id);
			post.setHasImage(true);
			return postRepository.save(post);
		}else if(photoType == PhotoType.SPECIES) {
			Species species = speciesRepository.findOne(id);
			species.setHasImage(true);
			return speciesRepository.save(species);
		}else if(photoType == PhotoType.ACHIEVEMENT) {
			Achievement achievement = achievementRepository.findOne(id);
			achievement.setHasImage(true);
			return achievementRepository.save(achievement);
		}else if(photoType == PhotoType.FLOWER) {
			Flower flower = flowerRepository.findOne(id);
			flower.setHasImage(true);
			return flowerRepository.save(flower);
		}else if(photoType == PhotoType.FRUIT) {
			Fruit fruit = fruitRepository.findOne(id);
			fruit.setHasImage(true);
			return fruitRepository.save(fruit);
		}else if(photoType == PhotoType.OFFER) {
			Offer offer = offerRepository.findOne(id);
			offer.setHasImage(true);
			return offerRepository.save(offer);
		}
		return null;
	}
}
