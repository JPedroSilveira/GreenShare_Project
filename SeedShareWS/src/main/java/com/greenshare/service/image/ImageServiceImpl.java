package com.greenshare.service.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenshare.entity.FlowerShop;
import com.greenshare.entity.achievement.Achievement;
import com.greenshare.entity.interfaces.PhotogenicEntity;
import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.post.Post;
import com.greenshare.entity.user.User;
import com.greenshare.entity.vegetable.Flower;
import com.greenshare.entity.vegetable.Fruit;
import com.greenshare.entity.vegetable.Species;
import com.greenshare.enumeration.PhotoType;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.AchievementRepository;
import com.greenshare.repository.FlowerRepository;
import com.greenshare.repository.FlowerShopRepository;
import com.greenshare.repository.FruitRepository;
import com.greenshare.repository.OfferRepository;
import com.greenshare.repository.PostRepository;
import com.greenshare.repository.SpeciesRepository;
import com.greenshare.repository.UserRepository;

/**
 * Implementation of {@link com.greenshare.service.image.ImageService}
 * 
 * @author joao.silva
 */
@Service
public class ImageServiceImpl extends IsHelper implements ImageService {
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
		if(isNotNull(photogenicEntity) && isNotNull(photogenicEntity.getId()) && isNotNull(photogenicEntity.getPhotoType())) {
			Long id = photogenicEntity.getId();
			PhotoType photoType = photogenicEntity.getPhotoType();
			if (photoType == PhotoType.USER) {
				User user = userRepository.findOne(id);
				if(isNotNull(user)) {
					user.setHasImage(true);
					return userRepository.save(user);
				}
			} else if (photoType == PhotoType.FLOWER_SHOP) {
				FlowerShop flowerShop = flowerShopRepository.findOne(id);
				if(isNotNull(flowerShop)) {
					flowerShop.setHasImage(true);
					return flowerShopRepository.save(flowerShop);
				}
			} else if (photoType == PhotoType.POST) {
				Post post = postRepository.findOne(id);
				if(isNotNull(post)) {
					post.setHasImage(true);
					return postRepository.save(post);
				}
			} else if (photoType == PhotoType.SPECIES) {
				Species species = speciesRepository.findOne(id);
				if(isNotNull(species)) {
					species.setHasImage(true);
					return speciesRepository.save(species);
				}
			} else if (photoType == PhotoType.ACHIEVEMENT) {
				Achievement achievement = achievementRepository.findOne(id);
				if(isNotNull(achievement)) {
					achievement.setHasImage(true);
					return achievementRepository.save(achievement);
				}
			} else if (photoType == PhotoType.FLOWER) {
				Flower flower = flowerRepository.findOne(id);
				if(isNotNull(flower)) {
					flower.setHasImage(true);
					return flowerRepository.save(flower);
				}
			} else if (photoType == PhotoType.FRUIT) {
				Fruit fruit = fruitRepository.findOne(id);
				if(isNotNull(fruit)) {
					fruit.setHasImage(true);
					return fruitRepository.save(fruit);
				}
			} else if (photoType == PhotoType.OFFER) {
				Offer offer = offerRepository.findOne(id);
				if(isNotNull(offer)) {
					offer.setHasImage(true);
					return offerRepository.save(offer);
				}
			}
		}
		return null;
	}
}
