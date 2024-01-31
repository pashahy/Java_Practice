package work.task.practice.MicroServiseApp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "practise_table")
public class User {
    @Id
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private int age;
}

