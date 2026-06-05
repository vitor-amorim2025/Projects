/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaCadastroCompras;

/**
 *
 * @author 20251TIIMI0337
 */
public class Produto {

    private int id, quantidadeEstoque;
    private float preco;
    private String nome;

    //Construtor
    public Produto(int id, String nome, float preco, int quantidadeEstoque) {
        this.id = id;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.nome = nome;
    }

    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Método para atualizar estoque
    public boolean atualizaEstoque(int quantidadeComprada) {
        if (quantidadeComprada > quantidadeEstoque) {
            System.out.println("ERRO: Quantidade não disponível!");
            return false;
        } else {
            quantidadeEstoque -= quantidadeComprada;
            return true;
        }
    }

    //Método para exibir os dados do produto
    public void exibir() {
        System.out.println("ID: " + id + " | Nome: " + nome + " | Preço: R$ " + preco + " | Quantidade no estoque: " + quantidadeEstoque);
    }

}
