package br.com.antonio.projeto.entity.enums;


import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoSituacaoUsuario {

    ATIVO ("A", "Ativo"),
    INATIVO ("I", "Inativo"),
    PENDENTE ("P", "Pendente");

    private String codigo;
    private String descricao;

    private TipoSituacaoUsuario( String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static TipoSituacaoUsuario doValor(String codigo) {
        if (codigo.equalsIgnoreCase("A")){
            return ATIVO;
        }else if (codigo.equalsIgnoreCase("I")){
            return INATIVO;
        } else if (codigo.equalsIgnoreCase("P")) {
            return PENDENTE;
        }else {
            return null;
        }

    }


















}
