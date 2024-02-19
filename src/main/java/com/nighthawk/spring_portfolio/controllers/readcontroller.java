package com.nighthawk.spring_portfolio.controllers;
/* MVC code that shows defining a simple Model, calling View, and this file serving as Controller
 * Web Content with Spring MVCSpring Example: https://spring.io/guides/gs/serving-web-con
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.validator.cfg.defs.EmailDef;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;
import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;
@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class readcontroller {

    @Autowired
    private PersonJpaRepository repository;

    // @GetMapping handles GET request for /greet, maps it to greeting() method
    @GetMapping("/reading")
    @PreAuthorize("isAuthenticated()")

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    // @RequestParam handles variables binding to frontend, defaults, etc
    public String person(Model model) {
        List<Person> persons = repository.findAllByOrderByNameAsc();
        model.addAttribute("persons", persons);
        // System.out.println(persons.toString()); for testing purposes
        return "reading";
    }

    @GetMapping("/reading/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        Optional<Person> optionalPerson = repository.findById(id);
    
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
    
            // Check if the person has admin role
            boolean isAdmin = person.getRoles().stream()
                    .anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    
            if (isAdmin) {
                System.out.println("Can't delete admins!");
            } else {
                repository.deleteById(id);
            }
        }
    
        return "redirect:/reading";
    }



//     @GetMapping("/update/{id}")
//     public String updatePerson(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
//     // Retrieve the existing person from the database
//     Optional<Person> optionalPerson = repository.findById(id);

//     // Check if the person with the given id exists
//     if (optionalPerson.isPresent()) {
//         // Update the person's name and email based on the form inputs
//         Person person = optionalPerson.get();
//         name = "test";
//         person.setName(name);
//         //person.setEmail(email);

//         // Save the updated person to the database
//         repository.save(person);

//         // Redirect to the reading page after updating
//         return "redirect:/reading";
//     } else {
//         // If the person is not found, redirect to the reading page
//         return "redirect:/reading";
//     }   
// }

// FOR TESTING PURPOSES ONLY
@PostMapping("/reading/updateName/{id}")
    public String updatePerson(@PathVariable Long id, @RequestParam String name) {
    // Retrieve the existing person from the database
    Optional<Person> optionalPerson = repository.findById(id);

    // Check if the person with the given id exists
    if (optionalPerson.isPresent()) {
        // Update the person's name and email based on the form inputs
        Person person = optionalPerson.get();
        //String email = "email";
        person.setName(name);
        //person.setEmail(email);

        // Save the updated person to the database
        repository.save(person);

        // Redirect to the reading page after updating
        return "redirect:/reading";
    } else {
        // If the person is not found, redirect to the reading page
        return "redirect:/reading";
    }   
}

@PostMapping("/reading/updateEmail/{id}")
    public String updateEmail(@PathVariable Long id, @RequestParam String email) {
    // Retrieve the existing person from the database
    Optional<Person> optionalPerson = repository.findById(id);

    // Check if the person with the given id exists
    if (optionalPerson.isPresent()) {
        // Update the person's name and email based on the form inputs
        Person person = optionalPerson.get();
        //String email = "email";
        person.setEmail(email);

        // Save the updated person to the database
        repository.save(person);

        // Redirect to the reading page after updating
        return "redirect:/reading";
    } else {
        // If the person is not found, redirect to the reading page
        return "redirect:/reading";
    }   
}



}