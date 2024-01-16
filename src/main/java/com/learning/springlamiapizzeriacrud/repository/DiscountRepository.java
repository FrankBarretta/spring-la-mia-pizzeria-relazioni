package com.learning.springlamiapizzeriacrud.repository;

import com.learning.springlamiapizzeriacrud.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
