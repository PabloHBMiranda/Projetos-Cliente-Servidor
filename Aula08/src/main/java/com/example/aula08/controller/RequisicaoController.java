package com.example.aula08.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequisicaoController {
    @GetMapping("/aula/{nome}")
    public String greetMessage(@PathVariable String nome){
        System.err.println("O nome " + nome + " veio da requisião");
        return "Voce " + nome + " está acessando a página de aula!!";
    }
}
