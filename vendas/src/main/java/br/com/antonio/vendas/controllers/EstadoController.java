package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.Estado;
import br.com.antonio.vendas.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @GetMapping("cadastroEstado")
    public ModelAndView cadastar(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
        mv.addObject("estado",  estado);
        return mv;
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result){
        if(result.hasErrors()) {
            return cadastar(estado);
        }

        repository.saveAndFlush(estado);
        return cadastar(new Estado());
    }

}




































