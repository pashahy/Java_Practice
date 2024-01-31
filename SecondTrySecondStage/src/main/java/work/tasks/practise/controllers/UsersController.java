package work.tasks.practise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.tasks.practise.dao.UserDAO;
import work.tasks.practise.models.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userDAO.findById(id);
    }

    @GetMapping("/{id}/update")
    public User updateUser(
            @PathVariable int id,
            @RequestParam int column,
            @RequestParam String newValue) {

        User userBeforeUpdate = userDAO.findById(id);
        User user = userDAO.findById(id);

        switch (column) {
            case 1:
                user.setUsername(newValue);
                break;
            case 2:
                user.setFirstname(newValue);
                break;
            case 3:
                user.setLastname(newValue);
                break;
            case 4:
                user.setAge(Integer.parseInt(newValue));
                break;

            default:
                break;
        }

        userDAO.updateUser(id, user);
        return user;
    }
}