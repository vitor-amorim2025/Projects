/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaCadastroCompras;

/**
 *
 * @author 20251TIIMI0337
 */
public class Cliente {

    private int id;
    private String nome, cpf, email;

    public Cliente(int id, String cpf, String email, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void exibir() {
        System.out.println("ID: " + id + " | Nome: " + nome + " | CPF: " + cpf + " | Email: " + email);
    }

}
