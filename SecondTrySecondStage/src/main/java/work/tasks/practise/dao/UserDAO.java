package work.tasks.practise.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import work.tasks.practise.services.FileService;
import work.tasks.practise.services.KafkaService;
import work.tasks.practise.models.User;

import java.io.IOException;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private final KafkaService kafkaService;
    private final FileService fileService;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, KafkaService kafkaService, FileService fileService) {
        this.jdbcTemplate = jdbcTemplate;
        this.kafkaService = kafkaService;
        this.fileService = fileService;
    }
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM practise_table";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public User findById(int id) {
        String sql = "SELECT * FROM practise_table WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    public User createUser(User user) {
        String sql = "INSERT INTO practise_table (username, firstname, lastname, age) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getFirstname(), user.getLastname(), user.getAge());

        kafkaService.sendMessage("User created: id " + user.getId()+", username "+user.getUsername()+", firstname "+user.getFirstname()+", lastname "+user.getLastname()+", age "+user.getAge()+"\n");
        try {
            fileService.writeToFile("User created: " + user.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User updateUser(int id, User user) {
        String sql = "UPDATE practise_table SET username = ?, firstname = ?, lastname = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getFirstname(), user.getLastname(), user.getAge(), id);

        kafkaService.sendMessage("User " + user.getUsername()+ " updated on: " + user.getId()+", username "+user.getUsername()+", firstname "+user.getFirstname()+", lastname "+user.getLastname()+", age "+user.getAge());
        try {
            fileService.writeToFile("User " + user.getUsername()+ " updated on: " + user.getId()+", username "+user.getUsername()+", firstname "+user.getFirstname()+", lastname "+user.getLastname()+", age "+user.getAge()+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
