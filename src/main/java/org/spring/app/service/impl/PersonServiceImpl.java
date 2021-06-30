package org.spring.app.service.impl;

import org.spring.app.service.PersonService;
import org.spring.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jianglong.li
 * @date 2021-06-12 15:13
 **/
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public String getPerson() {
        return personRepository.getPerson();
    }
}
