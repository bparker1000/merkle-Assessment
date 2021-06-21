package com.assessment.merkle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;

@Controller
public class RepositoryController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String addUser(@RequestParam String first, @RequestParam String last,
                          @RequestParam String address1, @RequestParam String address2,
                          @RequestParam String city, @RequestParam String state,
                          @RequestParam String zipCode) {
        User user = new User();
        user.setFirstName(first);
        user.setLastName(last);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setCity(city);
        user.setState(state);
        user.setZipCode(zipCode);
        user.setDate(Date.from(Instant.now()));
        userRepository.save(user);
        return "confirmation";
    }

    @GetMapping("/registered-user-report")
    public String getUsers(Model model) {
       model.addAttribute("listUsers", userRepository.findAll(Sort.by("registrationDate").descending()));
       return "users";
    }

}
