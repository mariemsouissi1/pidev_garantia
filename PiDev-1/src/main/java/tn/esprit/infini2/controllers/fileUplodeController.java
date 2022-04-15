package tn.esprit.infini2.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import tn.esprit.infini2.entities.Offer;
import tn.esprit.infini2.repositories.OfferRepository;

public class fileUplodeController {
	 @Autowired
	    private OfferRepository repo;
	     
	    @PostMapping("/offer/save")
	    public RedirectView saveUser(Offer offer,@RequestParam("image") MultipartFile multipartFile) throws IOException {
	         
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        //offer.setOffer_image(fileName);
	         
	        Offer savedUser = repo.save(offer);
	 
	        String uploadDir = "user-photos/" + savedUser.getOffer_id();
	 
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	         
	        return new RedirectView("/users", true);
	    }
}
