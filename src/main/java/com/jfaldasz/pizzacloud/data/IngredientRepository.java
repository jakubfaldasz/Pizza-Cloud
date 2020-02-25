package com.jfaldasz.pizzacloud.data;

import com.jfaldasz.pizzacloud.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {}

