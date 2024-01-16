package com.learning.springlamiapizzeriacrud.repository;

import com.learning.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {


    // Metodo per cercare una pizza tramite nome
    List<Pizza> findByNameContaining(String search);

}
