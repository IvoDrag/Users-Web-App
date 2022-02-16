package com.ivodrag.springbootusers;

import com.ivodrag.springbootusers.entity.User;
import com.ivodrag.springbootusers.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserTesting {
    @Autowired
    private UserRepository repository;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("IvoDrag@gmail.com");
        user.setPassword("pass123");
        user.setFirstName("Ivailo");
        user.setLastName("Draganov");
        User savedUser = repository.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
