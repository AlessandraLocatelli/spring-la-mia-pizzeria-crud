package org.learning.springlamiapizzeriacrud.controller;

import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PizzaController {

@Autowired
private PizzaRepository pizzaRepository;


@GetMapping()
public String index(Model model)
{

    List<Pizza> pizzaList = pizzaRepository.findAll();
    model.addAttribute("pizza",pizzaList);

    return "pizza/index";
}

     @GetMapping("show/{id}")
    public String show (@PathVariable("id") Integer id, Model model)
    {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);


        if(optionalPizza.isPresent())
        {
            Pizza pizzaFromDB = optionalPizza.get();
            model.addAttribute("pizza", pizzaFromDB);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id: "+id+" not found.");

        }

        return "pizza/detail";
    }




}
