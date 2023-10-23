package telran.monitoring.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name = "persons")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Person {
 @Id
 long id;
 String email;
 String name;
}
