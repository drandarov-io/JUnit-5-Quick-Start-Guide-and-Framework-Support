package com.dmitrijdrandarov.spring4.junit4;

import com.dmitrijdrandarov.spring4.Spring4Config;
import com.dmitrijdrandarov.entities.DummyFruit;
import com.dmitrijdrandarov.spring4.repositories.DummyFruitRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(classes = {Spring4Config.class})
@RunWith(SpringRunner.class)
@Transactional
public class Spring4_01_JUnit4Test {

    private static final Logger LOG = LoggerFactory.getLogger(Spring4_01_JUnit4Test.class);

    @Autowired
    private DummyFruitRepository fruitRepository;

    @Test
    public void saveTest() {
        DummyFruit fruity = fruitRepository.save(new DummyFruit(null, DummyFruit.TYPE.APPLE, "Fruity", "Description", 10D));
        fruity = fruitRepository.findOneById(fruity.getId());
        Assert.assertNotNull(fruity);

//        LOG.info(fruity.toString());
        System.out.println(fruity.toString());
    }

    @Test
    public void dataSQLTest() {
        List<DummyFruit> dummyFruits = fruitRepository.findAllBy();
        Assert.assertEquals(3, dummyFruits.size());

//        dummyFruits.stream().map(DummyFruit::toString).forEach(LOG::info);
        dummyFruits.stream().map(DummyFruit::toString).forEach(System.out::println);
    }
}