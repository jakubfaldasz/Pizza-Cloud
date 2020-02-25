package com.jfaldasz.pizzacloud.data;

import com.jfaldasz.pizzacloud.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {}
