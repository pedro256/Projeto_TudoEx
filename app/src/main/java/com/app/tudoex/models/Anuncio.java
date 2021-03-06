package com.app.tudoex.models;

import com.app.tudoex.config.FirebaseConfig;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.List;

public class Anuncio implements Serializable {

    private String id;
    private String categoria;
    private String cor;
    private String telefone;
    private String tamanho;
    private String titulo;
    private String valor;
    private String descricao;
    private List<String> fotos;


    public Anuncio() {
        DatabaseReference anuncioRef = FirebaseConfig.getFirebaseDatabase().child("meus_anuncios");
        setId(anuncioRef.push().getKey());
    }
    public void salvar(){
        String idUser = FirebaseConfig.getIdUsuario();
        DatabaseReference anuncioRef = FirebaseConfig.getFirebaseDatabase().child("meus_anuncios");
        anuncioRef.child(idUser)
                .child(getId())
                .setValue(this);

        salvarAnuncioPublico();

    }
    public void salvarAnuncioPublico(){
        DatabaseReference anuncioRef = FirebaseConfig.getFirebaseDatabase()
                .child("anuncios");
        anuncioRef.child(getCategoria())
                .child(getCor())
                .child(getId())
                .setValue(this);
    }
    public void remover(){
        String idUser = FirebaseConfig.getIdUsuario();
        DatabaseReference anuncioRef = FirebaseConfig.getFirebaseDatabase().child("meus_anuncios").child(idUser).child(getId());

        anuncioRef.removeValue();
        removerPublico();
    }

    public void removerPublico(){
        DatabaseReference anuncioRef = FirebaseConfig.getFirebaseDatabase().child("anuncios").child(getCategoria()).child(getCor()).child(getId());
        anuncioRef.removeValue();

        anuncioRef.removeValue();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}
