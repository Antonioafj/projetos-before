package br.com.antonio.vendas.controllers;

import br.com.antonio.vendas.models.Estado;
import br.com.antonio.vendas.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastar(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
        mv.addObject("estado",  estado);
        return mv;
    }

    @GetMapping("/listarEstado")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/estados/lista");
        mv.addObject("listarEstado", repository.findAll());
        return mv;
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView edita(@PathVariable("id") Long id) {
        Optional<Estado> estado = repository.findById(id);
        return cadastar(estado.get());
    }

    @GetMapping("/removerEstado/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Estado> estado = repository.findById(id);
        repository.delete(estado.get());
        return listar();
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




































