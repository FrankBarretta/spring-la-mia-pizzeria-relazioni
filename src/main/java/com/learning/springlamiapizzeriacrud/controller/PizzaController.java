package com.learning.springlamiapizzeriacrud.controller;

import com.learning.springlamiapizzeriacrud.model.Pizza;
import com.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {


    // Aggiungo la repository come attributo
    @Autowired
    private PizzaRepository pizzaRepository;



    // Metodo che mi mostra le pizze
    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String searchKeyword, Model model) {
        List<Pizza> pizzaList;
        if (searchKeyword != null) {
           pizzaList = pizzaRepository.findByNameContaining(searchKeyword);
        } else {
            pizzaList = pizzaRepository.findAll();
        }

        // Aggiungo la lista delle pizze al model
        model.addAttribute("pizzaList", pizzaList);

        // Precarico il valore dell'input
        model.addAttribute("preloadSearch", searchKeyword);

        // Restituisco il template
        return "pizzas/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            Pizza pizza = result.get();
            model.addAttribute("pizza", pizza);
            return "pizzas/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza " + id + " not found");
        }
    }

    // Metodo che mi restituisce la view /create
    @GetMapping("/create")
    public String create(Model model){
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        // Valido i dati di Pizza
        if (bindingResult.hasErrors()) {
            // Se ci sono errori, ricarico il template del form senza cancellare i dati inseriti
            return "pizzas/create";
        } else {
            // Altrimenti crea la pizza e mostra la view /show con la pizza appena creata
            Pizza savedPizza = pizzaRepository.save(formPizza);
            return "redirect:/pizzas/show/" + savedPizza.getId();
        }
    }

    // Metodo che mi restituisce la view con il form per modificare i dati
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // Recupero la pizza dal DB
        Optional<Pizza> result = pizzaRepository.findById(id);

        // Verifico se è presente
        if (result.isPresent()) {

            // Lo passo come attributo del model
            model.addAttribute("pizza", result.get());

            // Ritorno il template con il form di modifica
            return "pizzas/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            Pizza pizzaToEdit = result.get();
            if (bindingResult.hasErrors()) {
                return "pizzas/edit";
            }

            Pizza savedPizza = pizzaRepository.save(formPizza);
            return "redirect:/pizzas/show/" + id;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // Verifico se la Pizza è presente sul DB
        Optional<Pizza> result = pizzaRepository.findById(id);
        // Mando un messaggio di successo con la redirect
        redirectAttributes.addFlashAttribute("redirectMessage", "Pizza " + result.get().getName() + " deleted");
        if (result.isPresent()) {
            // Se c'è a cancello
            pizzaRepository.deleteById(id);
            return "redirect:/pizzas";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }
}
