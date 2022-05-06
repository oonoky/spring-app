package kz.narxoz.springapp.controller;
import kz.narxoz.springapp.model.User;
import kz.narxoz.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("users")
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
