package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.infini2.dto.GeneralScoreStat;
import tn.esprit.infini2.services.CustomerAccountServiceImp;

import java.util.List;

@RestController

public class CustomerAccountController {


    @Autowired
    CustomerAccountServiceImp customerAccountService;

    @GetMapping ("/customerscorestat")
    public List<GeneralScoreStat> getCustomerScoreStat ()
    {
        return (customerAccountService.customerScoreStat());
    }

}
