package com.greenshare.controller.image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greenshare.entity.FlowerShop;
import com.greenshare.entity.achievement.Achievement;
import com.greenshare.entity.interfaces.PhotogenicEntity;
import com.greenshare.entity.offer.Offer;
import com.greenshare.entity.post.Post;
import com.greenshare.entity.user.User;
import com.greenshare.entity.vegetable.Flower;
import com.greenshare.entity.vegetable.Fruit;
import com.greenshare.entity.vegetable.Species;
import com.greenshare.exception.DirectoryException;
import com.greenshare.helpers.ImageHelper;
import com.greenshare.helpers.IsHelper;
import com.greenshare.repository.AchievementRepository;
import com.greenshare.repository.FlowerRepository;
import com.greenshare.repository.FlowerShopRepository;
import com.greenshare.repository.FruitRepository;
import com.greenshare.repository.OfferRepository;
import com.greenshare.repository.PostRepository;
import com.greenshare.repository.SpeciesRepository;
import com.greenshare.repository.UserRepository;
import com.greenshare.service.image.ImageServiceImpl;

/**
 * Controller implementation of {@link com.greenshare.controller.image.ImageUploadController}
 * 
 * @author joao.silva
 */
@RestController
public class ImageUploadControllerImpl extends IsHelper implements ImageUploadController{

	@Autowired
	ImageServiceImpl imageService;
	
	@Autowired
    PostRepository postRepository;
	
	@Autowired
    SpeciesRepository speciesRepository;
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    FlowerShopRepository flowerShopRepository;
	
	@Autowired
	AchievementRepository achievementRepository;
	
	@Autowired
	FlowerRepository flowerRepository;
	
	@Autowired
	FruitRepository fruitRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
    @Override
    @PostMapping("/user/image/")
    public ResponseEntity<String> uploadUserImage(@RequestParam("photo") MultipartFile multipartFile) {
    	if(isNotNull(multipartFile)){
    		User currentUser = getCurrentUser();
    		if(isNotNull(currentUser)) {
            	return saveImage(multipartFile, currentUser);
    		}
        	return new ResponseEntity<String>("Usuário não pode ser nulo.", HttpStatus.UNAUTHORIZED);
    	}
		return new ResponseEntity<String>("Arquivo não pode ser nulo.", HttpStatus.BAD_REQUEST);
    }
    
    @Override
    @PostMapping("/post/image/")
    public ResponseEntity<String> uploadPostImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("postId") Long postId) {
    	if(isNotNull(postId) && isNotNull(multipartFile)) {
    		Post postDB = postRepository.findOne(postId);
    		if(isNotNull(postDB)) {
    			User currentUser = getCurrentUser();
    			if(postDB.getUser().getId() == currentUser.getId()) {
    	    		return saveImage(multipartFile, postDB);
    	    	}
    	    	return new ResponseEntity<String>("Usuário inválido.", HttpStatus.UNAUTHORIZED);
    		}
			return new ResponseEntity<String>("Postagem não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID e arquivo não podem ser nulos.", HttpStatus.BAD_REQUEST);
    }
    
    @Override
    @PostMapping("/flower_shop/image/")
    public ResponseEntity<String> uploadFlowerShopImage(@RequestParam("photo") MultipartFile multipartFile) {
    	if(isNotNull(multipartFile)) {
        	User currentUser = getCurrentUser();
        	if(isNotNull(currentUser) && currentUser.getIsLegalPerson()) {
        		FlowerShop flowerShop = currentUser.getFlowerShop();
        		if(isNotNull(flowerShop)) {
        			if(flowerShop.getEnabled()) {
                    	return saveImage(multipartFile, flowerShop);
        			}
                	return new ResponseEntity<String>("Floricultura inativa, contate um administrador.", HttpStatus.UNAUTHORIZED);
        		}
            	return new ResponseEntity<String>("Floricultura não encontrada.", HttpStatus.NOT_FOUND);
        	}
        	return new ResponseEntity<String>("Usuário inválido para esta operação.", HttpStatus.UNAUTHORIZED);
    	}
		return new ResponseEntity<String>("Arquivo não pode ser nulo.", HttpStatus.BAD_REQUEST);
    }
    
    @Override
    @PostMapping("/species/image/")
    public ResponseEntity<String> uploadSpeciesImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("speciesId") Long speciesId) {
    	if(isNotNull(speciesId) && isNotNull(multipartFile)) {
        	Species speciesDB = speciesRepository.findOne(speciesId);
        	if(isNotNull(speciesDB)) {
            	return saveImage(multipartFile, speciesDB);
        	}
        	return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID e arquivo não podem ser nulos.", HttpStatus.BAD_REQUEST);
    }
    
    @Override
    @PostMapping("/offer/image/")
    public ResponseEntity<String> uploadOfferImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("offerId") Long offerId) {
    	if(isNotNull(offerId) && isNotNull(multipartFile)) {
        	Offer offerDB = offerRepository.findOne(offerId);
        	if(isNotNull(offerDB)) {
            	return saveImage(multipartFile, offerDB);
        	}
        	return new ResponseEntity<String>("Oferta não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID e arquivo não podem ser nulos.", HttpStatus.BAD_REQUEST);
    }
    
    @Override
    @PostMapping("/achievement/image/")
    public ResponseEntity<String> uploadAchievementImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("achievementId") Long achievementId) {
    	if(isNotNull(achievementId) && isNotNull(multipartFile)) {
    		Achievement achievementDB = achievementRepository.findOne(achievementId);
        	if(isNotNull(achievementDB)) {
            	return saveImage(multipartFile, achievementDB);
        	}
        	return new ResponseEntity<String>("Conquista não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID e arquivo não podem ser nulos.", HttpStatus.BAD_REQUEST);
    }
    
    @Override
    @PostMapping("/flower/image/")
    public ResponseEntity<String> uploadFlowerImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("flowerId") Long flowerId) {
    	if(isNotNull(flowerId) && isNotNull(multipartFile)) {
    		Flower flowerDB = flowerRepository.findOne(flowerId);
        	if(isNotNull(flowerDB)) {
            	return saveImage(multipartFile, flowerDB);
        	}
        	return new ResponseEntity<String>("Flor não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID e arquivo não podem ser nulos.", HttpStatus.BAD_REQUEST);
    }
    
    @Override
    @PostMapping("/fruit/image/")
    public ResponseEntity<String> uploadFruitImage(@RequestParam("photo") MultipartFile multipartFile, @RequestParam("fruitId") Long fruitId) {
    	if(isNotNull(fruitId) && isNotNull(multipartFile)) {
    		Fruit fruitDB = fruitRepository.findOne(fruitId);
        	if(isNotNull(fruitDB)) {
            	return saveImage(multipartFile, fruitDB);
        	}
        	return new ResponseEntity<String>("Fruto não encontrado.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID e arquivo não podem ser nulos.", HttpStatus.BAD_REQUEST);
    }
    
    
    @Override
    @GetMapping("/user/image/")
	public ResponseEntity<?> getUserImage() {
    	User currentUser = getCurrentUser();
    	return getImage(currentUser);
	}
    
    @Override
    @GetMapping("/post/image/{postId}")
	public ResponseEntity<?> getPostImage(@PathVariable Long postId) {
    	Post post = postRepository.findOne(postId);
		return getImage(post);
	}
    
    @Override
    @GetMapping("/flower_shop/")
	public ResponseEntity<?> getFlowerShopImage() {
    	User currentUser = getCurrentUser();
    	if(isNotNull(currentUser)) {
        	FlowerShop flowerShop = currentUser.getFlowerShop();
        	if(isNotNull(flowerShop) && flowerShop.getEnabled()) {
            	return getImage(flowerShop);
        	}
        	return new ResponseEntity<String>("Usuário atual não possui floricultura ou ela esta inativa.", HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<String>("Usuário inválido.", HttpStatus.UNAUTHORIZED);
	}
    
    @Override
    @GetMapping("/species/image/{speciesId}")
	public ResponseEntity<?> getSpeciesImage(@PathVariable Long speciesId) {
    	if(isNotNull(speciesId)) {
        	Species species = speciesRepository.findOne(speciesId);
        	if(isNotNull(species)) {
        		return getImage(species);
        	}
        	return new ResponseEntity<String>("Espécie não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
    
    @Override
    @GetMapping("/achievement/image/{achievementId}")
	public ResponseEntity<?> getAchievementImage(@PathVariable Long achievementId) {
    	if(isNotNull(achievementId)) {
    		Achievement achievementDB = achievementRepository.findOne(achievementId);
        	if(isNotNull(achievementDB)) {
        		return getImage(achievementDB);
        	}
        	return new ResponseEntity<String>("Conquista não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
    
    @Override
    @GetMapping("/flower/image/{flowerId}")
	public ResponseEntity<?> getFlowerImage(@PathVariable Long flowerId) {
    	if(isNotNull(flowerId)) {
    		Flower flowerDB = flowerRepository.findOne(flowerId);
        	if(isNotNull(flowerDB)) {
        		return getImage(flowerDB);
        	}
        	return new ResponseEntity<String>("Flor não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
    
    @Override
    @GetMapping("/fruit/image/{flowerId}")
	public ResponseEntity<?> getFruitImage(@PathVariable Long fruitId) {
    	if(isNotNull(fruitId)) {
    		Fruit fruitDB = fruitRepository.findOne(fruitId);
        	if(isNotNull(fruitDB)) {
        		return getImage(fruitDB);
        	}
        	return new ResponseEntity<String>("Fruto não encontrado.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
    
    @Override
    @GetMapping("/offer/image/{flowerId}")
	public ResponseEntity<?> getOfferImage(@PathVariable Long offerId) {
    	if(isNotNull(offerId)) {
    		Offer offerDB = offerRepository.findOne(offerId);
        	if(isNotNull(offerDB)) {
        		return getImage(offerDB);
        	}
        	return new ResponseEntity<String>("Oferta não encontrada.", HttpStatus.NOT_FOUND);
    	}
		return new ResponseEntity<String>("ID não pode ser nulo.", HttpStatus.BAD_REQUEST);
	}
     
    private ResponseEntity<String> saveImage(MultipartFile multiPartFile, PhotogenicEntity photogenicEntity){
    	try {
    		ImageHelper imageHelper = new ImageHelper(photogenicEntity);
    		if(imageHelper.save(multiPartFile)) {
    			if(!photogenicEntity.getHasImage()){
        			photogenicEntity.setHasImage(true);
        			if(isNull(imageService.save(photogenicEntity))) {
        				return new ResponseEntity<String>("Erro ao salvar dados no banco.", HttpStatus.INTERNAL_SERVER_ERROR);
        			}
        		}
    			return new ResponseEntity<String>("Imagem salva com sucesso", HttpStatus.CREATED);
    		}
    		return new ResponseEntity<String>("Formato de imagem inválido ou arquivo muito grande", HttpStatus.NOT_ACCEPTABLE);
		} catch (DirectoryException e) {
	    	return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
	    	return new ResponseEntity<String>("Erro ao ler imagem.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    private ResponseEntity<?> getImage(PhotogenicEntity photogenicEntity){
		try {
			ImageHelper imageHelper = new ImageHelper(photogenicEntity);
			try {
				String base64String = imageHelper.getImage();
				User currentUser = 	getCurrentUser();
				currentUser.setImage(base64String);
				if(isNotNull(base64String)) {
					byte[] imageByteArray = Base64.getDecoder().decode(currentUser.getImage());

					// Write a image byte array into file system
					FileOutputStream imageOutFile = new FileOutputStream(
							"dois.jpg");

					imageOutFile.write(imageByteArray);

					imageOutFile.close();
					return new ResponseEntity<User>(currentUser, HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("Imagem não encontrada", HttpStatus.NOT_FOUND);
				}
			} catch (IOException e) {
		    	return new ResponseEntity<String>("Erro ao ler imagem.", HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (JSONException e) {
		    	return new ResponseEntity<String>("Erro ao converter imagem.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DirectoryException e) {
	    	return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}
