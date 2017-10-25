package com.seedshare.service.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedshare.entity.FlowerShop;
import com.seedshare.entity.achievement.Achievement;
import com.seedshare.entity.interfaces.PhotogenicEntity;
import com.seedshare.entity.offer.Offer;
import com.seedshare.entity.post.Post;
import com.seedshare.entity.user.User;
import com.seedshare.entity.vegetable.Flower;
import com.seedshare.entity.vegetable.Fruit;
import com.seedshare.entity.vegetable.Species;
import com.seedshare.enumeration.PhotoType;
import com.seedshare.helpers.IsHelper;
import com.seedshare.repository.AchievementRepository;
import com.seedshare.repository.FlowerRepository;
import com.seedshare.repository.FlowerShopRepository;
import com.seedshare.repository.FruitRepository;
import com.seedshare.repository.OfferRepository;
import com.seedshare.repository.PostRepository;
import com.seedshare.repository.SpeciesRepository;
import com.seedshare.repository.UserRepository;

/**
 * Implementation of {@link com.seedshare.service.image.ImageService}
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
