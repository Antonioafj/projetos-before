package br.com.antonio.auth_api_jwt.services.impl;

import br.com.antonio.auth_api_jwt.dtos.UsuarioDto;
import br.com.antonio.auth_api_jwt.models.Usuario;
import br.com.antonio.auth_api_jwt.repositories.UsuarioRepository;
import br.com.antonio.auth_api_jwt.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {

        try {

            Usuario usuarioJaExiste = usuarioRepository.findByLogin(usuarioDto.login());

            if (usuarioJaExiste != null) {
                throw new RuntimeException("Usuário já existe");
            }

            Usuario entity = new Usuario(
                    usuarioDto.nome(),
                    usuarioDto.login(),
                    usuarioDto.senha());

            Usuario novoUsuario = usuarioRepository.save(entity);

            return new UsuarioDto(
                    novoUsuario.getNome(),
                    novoUsuario.getLogin(),
                    novoUsuario.getSenha());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuário: " + e.getMessage(), e);
        }
    }
}
