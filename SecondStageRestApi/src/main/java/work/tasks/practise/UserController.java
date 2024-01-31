package work.tasks.practise;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        String selectQuery = "SELECT id, username, firstname, lastname, age FROM practise_table WHERE id = ?";
        return jdbcTemplate.queryForObject(selectQuery, new Object[] { id }, (resultSet, rowNum) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAge(resultSet.getInt("age"));
            return user;
        });
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        String updateQuery = "UPDATE practise_table SET username = ?, firstname = ?, lastname = ? WHERE id = ?";
        jdbcTemplate.update(updateQuery, updatedUser.getUsername(), updatedUser.getFirstname(), updatedUser.getLastname(), id);
    }
}
