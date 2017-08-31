package com.dmitrijdrandarov.junit5;

import com.dmitrijdrandarov.entities.DummyFruit;
import com.dmitrijdrandarov.repositories.FruitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class SpringBootJUnit5Test {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootJUnit5Test.class);

    @Autowired
    private FruitRepository fruitRepository;

    @Test
    void databaseTest() {
        DummyFruit fruity = fruitRepository.save(new DummyFruit(null, "Fruity", "Description", 10D, DummyFruit.TYPE.APPLE));

        Assertions.assertTrue(fruitRepository.findById(fruity.getId()).map(DummyFruit::toString).isPresent());
    }

    @Test
    void loadData() throws Exception {
        List<DummyFruit> dummyFruits = fruitRepository.find();
        Assertions.assertEquals(3, dummyFruits.size());
    }
}