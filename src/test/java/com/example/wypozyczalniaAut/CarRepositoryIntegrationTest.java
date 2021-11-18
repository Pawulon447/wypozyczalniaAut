package com.example.wypozyczalniaAut;

import com.example.wypozyczalniaAut.model.Car;

import com.example.wypozyczalniaAut.repository.CarRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;


    @Test
    public void whenFindByName_thenReturnCar() {

        Car fiat = new Car("fiat");
        entityManager.persist(fiat);
        entityManager.flush();

        Car found = carRepository.findByName(fiat.getName());


        assertThat(found.getName())
                .isEqualTo(fiat.getName());
    }

    @Test
    public void whenFindById_thenReturnCar() {

        Car fiat = new Car("fiat");
        entityManager.persist(fiat);
        entityManager.flush();

        Car found = carRepository.findById(fiat.getId());


        assertThat(found.getId())
                .isEqualTo(fiat.getId());
    }




}
