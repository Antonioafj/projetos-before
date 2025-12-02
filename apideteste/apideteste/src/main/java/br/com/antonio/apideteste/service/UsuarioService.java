package br.com.antonio.apideteste.service;


import br.com.antonio.apideteste.dto.UsuarioDto;
import br.com.antonio.apideteste.model.Usuario;
import br.com.antonio.apideteste.repository.IUsuario;
import br.com.antonio.apideteste.security.Token;
import br.com.antonio.apideteste.security.TokenUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    private final IUsuario repository;

    private final PasswordEncoder passwordEncoder;

    private final Logger looger = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService(IUsuario repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public List<Usuario> listarUsuario() {
        List<Usuario> lista = repository.findAll();
        looger.info("Usuario: " + getLogado() + "Listando Usuarios");
        return lista;
    }

    public Usuario criarUsuario(Usuario usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioNovo = repository.save(usuario);
        looger.info("Usuario: " + getLogado() + "Criando Usuarios");

        return usuarioNovo;
    }

    public Usuario editarUsuario(Usuario usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioNovo = repository.save(usuario);
        looger.info("Usuario: " + getLogado() + "Editando Usuarios: " + usuario);
        return usuarioNovo;
    }

    public Boolean excluirUsuario(Integer id) {
        repository.deleteById(id);
        looger.info("Usuario: " + getLogado() + "Excluindo Usuarios");
        return true;
    }


    public Token gerarToken(@Valid UsuarioDto usuario) {
        Usuario user = repository.findBynomeOrEmail(usuario.getNome(), usuario.getEmail());
        if (user != null) {
            Boolean valid = passwordEncoder.matches(usuario.getSenha(), user.getSenha());
            if (valid){
                return  new Token(TokenUtil.createToken(user));
            }
        }
        return null;
    }


    private String  getLogado() {
        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        if (!(userLogado instanceof AnonymousAuthenticationToken)) {
            return userLogado.getName();
        }
        return "Null";
    }
}









