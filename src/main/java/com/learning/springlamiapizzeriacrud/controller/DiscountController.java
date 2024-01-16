package com.learning.springlamiapizzeriacrud.controller;

import com.learning.springlamiapizzeriacrud.repository.DiscountRepository;
import com.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/discounts")


public class DiscountController {


    @Autowired
    private PizzaRepository pizzaRepository;


    @Autowired
    private DiscountRepository discountRepository;

}
