package com.jfaldasz.pizzacloud.web;

import com.jfaldasz.pizzacloud.Ingredient;
import com.jfaldasz.pizzacloud.Order;
import com.jfaldasz.pizzacloud.Pizza;
import com.jfaldasz.pizzacloud.data.IngredientRepository;
import com.jfaldasz.pizzacloud.data.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jfaldasz.pizzacloud.Ingredient.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignController {
    private final IngredientRepository ingredientRepo;
    private final PizzaRepository pizzaRepository;

    @Autowired
    public DesignController(IngredientRepository ingredientRepo, PizzaRepository pizzaRepository) {
        this.ingredientRepo = ingredientRepo;
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredients = (List<Ingredient>) ingredientRepo.findAll();

        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("pizza", new Pizza());

        return "design";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @PostMapping
    public String processDesign(@Valid Pizza design, Errors errors, @ModelAttribute Order order) {
        if(errors.hasErrors())
            return "design";

        Pizza saved = pizzaRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
