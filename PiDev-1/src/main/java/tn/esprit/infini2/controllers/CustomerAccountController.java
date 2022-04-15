package tn.esprit.infini2.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.infini2.dto.GeneralScoreStat;
import tn.esprit.infini2.services.CustomerAccountServiceImp;

import java.util.List;

@RestController

public class CustomerAccountController {


    @Autowired
    CustomerAccountServiceImp customerAccountService;

    @PreAuthorize("hasAuthority(@userService.Employee())")
    @GetMapping ("/customerscorestat")
    public List<GeneralScoreStat> getCustomerScoreStat () throws JsonProcessingException {
        return (customerAccountService.retrieveScoreStat());
    }

}
