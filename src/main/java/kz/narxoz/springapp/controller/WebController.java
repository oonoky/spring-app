package kz.narxoz.springapp.controller;

import kz.narxoz.springapp.model.User;
import kz.narxoz.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    UserRepository repository;

    @GetMapping("")
    public String showUserList(@RequestParam(name = "email", required = false, defaultValue = "") String email,
                               @RequestParam(name = "name", required = false, defaultValue = "") String name,
                               @RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
                               @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
                               @RequestParam(name = "qid", required = false, defaultValue = "") Long qid,
                               @RequestParam(name = "isSorted", required = false) boolean isSorted,
                               Model model) {

        List<User> users = repository.findAll();

        // Check if `param` is not empty

        if (!email.isEmpty()) {
            users = repository.findFirstByEmailContainingOrderBySurname(email);
        }

        else if (!firstName.isEmpty() && !lastName.isEmpty()) {
            users = repository.findByNameAndSurname(firstName, lastName);
        }

        else if (!name.isEmpty()) {
            users = repository.findTop2ByNameStartsWith(name);
        }

        else if (qid != null) {
            users = repository.findByGreaterId(qid);
        }

        else if (isSorted) {
            users = repository.findAllSorted();
        }

        model.addAttribute("users", users);
        return "index";
    }

    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user) {
        addUser(user);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    private void deleteById(long id) {
        repository.deleteById(id);
    }

    private void addUser(User newUser) {
        repository.save(newUser);
    }

    private void updateUser(User updateUser) {
        User oldUser = repository.getById(updateUser.getId());

        oldUser.setName(updateUser.getName());
        oldUser.setSurname(updateUser.getSurname());
        oldUser.setEmail(updateUser.getEmail());

        repository.save(oldUser);
    }
}