package br.com.antonio.vendas.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlPrincipal {

    @GetMapping("/administrativo")
    public String AcessarPrincipal() {
        return "administrativo/home";
    }
}
