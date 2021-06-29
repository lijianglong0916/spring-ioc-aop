package org.spring.api;

import org.spring.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianglong.li
 * @date 2021-06-12 14:35
 **/

@RestController
@RequestMapping(("/v1/person"))
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("get-person")
    public String getPerson(){
        return personService.getPerson();
    }
}
