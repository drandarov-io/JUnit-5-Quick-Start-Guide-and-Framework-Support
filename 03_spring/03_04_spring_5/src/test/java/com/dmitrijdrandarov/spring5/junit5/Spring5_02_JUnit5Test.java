package com.dmitrijdrandarov.spring5.junit5;

import com.dmitrijdrandarov.spring5.Spring5Config;
import com.dmitrijdrandarov.entities.DummyFruit;
import com.dmitrijdrandarov.spring5.repositories.DummyFruitRepository;
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

@ContextConfiguration(classes = {Spring5Config.class})
@ExtendWith(SpringExtension.class)
@Transactional
class Spring5_02_JUnit5Test {

    private static final Logger LOG = LoggerFactory.getLogger(Spring5_02_JUnit5Test.class);

    @Autowired
    private DummyFruitRepository fruitRepository;

    @Test
    void saveTest() {
        DummyFruit fruity = fruitRepository.save(new DummyFruit(null, DummyFruit.TYPE.APPLE, "Fruity", "Description", 10D));
        fruity = fruitRepository.findOneById(fruity.getId());
        Assertions.assertNotNull(fruity);

//        LOG.info(fruity.toString());
        System.out.println(fruity.toString());
    }

    @Test
    void dataSQLTest() {
        List<DummyFruit> dummyFruits = fruitRepository.findAllBy();
        Assertions.assertEquals(3, dummyFruits.size());

//        dummyFruits.stream().map(DummyFruit::toString).forEach(LOG::info);
        dummyFruits.stream().map(DummyFruit::toString).forEach(System.out::println);
    }
}