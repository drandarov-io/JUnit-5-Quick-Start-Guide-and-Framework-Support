package com.dmitrijdrandarov.junit5;

import com.dmitrijdrandarov.Spring4Config;
import com.dmitrijdrandarov.entities.DummyFruit;
import com.dmitrijdrandarov.repositories.DummyFruitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Spring4Config.class})
@Transactional
class Spring4_02_JUnit5Test {

    private static final Logger LOG = LoggerFactory.getLogger(Spring4_02_JUnit5Test.class);

    @Autowired
    private DummyFruitRepository fruitRepository;

    @Test
    void saveTest() {
        DummyFruit fruity = fruitRepository.save(new DummyFruit(null, DummyFruit.TYPE.APPLE, "Fruity", "Description", 10D));
        fruity = fruitRepository.findOneById(fruity.getId());
        Assertions.assertNotNull(fruity);

        LOG.info(fruity.toString());
    }

    @Test
    void dataSQLTest() {
        List<DummyFruit> dummyFruits = fruitRepository.findAllBy();
        Assertions.assertEquals(3, dummyFruits.size());

        dummyFruits.stream().map(DummyFruit::toString).forEach(LOG::info);
    }
}