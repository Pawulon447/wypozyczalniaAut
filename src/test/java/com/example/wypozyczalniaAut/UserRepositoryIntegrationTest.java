package com.example.wypozyczalniaAut;

import com.example.wypozyczalniaAut.model.User;
import com.example.wypozyczalniaAut.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void whenFindByName_thenReturnUser() {

        User janusz = new User("janusz");
        entityManager.persist(janusz);
        entityManager.flush();


        User found = userRepository.findByName(janusz.getName());


        assertThat(found.getName())
                .isEqualTo(janusz.getName());
    }

}
