package com.dmitrijdrandarov.junit4;

import com.dmitrijdrandarov.entities.DummyFruit;
import com.dmitrijdrandarov.repositories.DummyFruitRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@Transactional
@Ignore
public class SpringBoot5_01_JUnit4Test {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBoot5_01_JUnit4Test.class);

    @Autowired
    private DummyFruitRepository fruitRepository;

    @Test
    public void saveTest() {
        DummyFruit fruity = fruitRepository.save(new DummyFruit(null, DummyFruit.TYPE.APPLE, "Fruity", "Description", 10D));
        fruity = fruitRepository.findOneById(fruity.getId());
        Assert.assertNotNull(fruity);

        LOG.info(fruity.toString());
    }

    @Test
    public void dataSQLTest() {
        List<DummyFruit> dummyFruits = fruitRepository.findAllBy();
        Assert.assertEquals(3, dummyFruits.size());

        dummyFruits.stream().map(DummyFruit::toString).forEach(LOG::info);
    }
}