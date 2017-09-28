package com.dmitrijdrandarov.repositories;

import com.dmitrijdrandarov.entities.DummyFruit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DummyFruitRepository extends CrudRepository<DummyFruit, Long> {

    List<DummyFruit> findAllBy();
    DummyFruit findOneById(Long id);

}
