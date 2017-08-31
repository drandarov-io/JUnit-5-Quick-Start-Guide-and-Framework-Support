package com.dmitrijdrandarov.junit4;

import com.dmitrijdrandarov.entities.DummyFruit;
import com.dmitrijdrandarov.repositories.FruitRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpringBootJUnit4Test {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootJUnit4Test.class);

    @Autowired
    private FruitRepository fruitRepository;

    @Test
    public void databaseTest() {
        DummyFruit fruity = fruitRepository.save(new DummyFruit(null, "Fruity", "Description", 10D, DummyFruit.TYPE.APPLE));

        Assert.assertTrue(fruitRepository.findById(fruity.getId()).map(DummyFruit::toString).isPresent());
    }

    @Test
    public void loadData() throws Exception {
        List<DummyFruit> dummyFruits = fruitRepository.find();
        Assert.assertEquals(3, dummyFruits.size());
    }
}

