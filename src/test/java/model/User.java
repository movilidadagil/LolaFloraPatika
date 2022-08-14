package model;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class User {
    private String firstName, lastName, email, password;
    Faker faker;
    public User(){
        faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().emailAddress();
        this.password = faker.internet().password(8,12);
    }

}
