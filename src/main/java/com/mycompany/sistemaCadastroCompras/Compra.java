/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaCadastroCompras;

/**
 *
 * @author 20251TIIMI0337
 */
public class Compra {

    private int idCompra, quantidadeComprada, idCliente, idProduto;
    private float valorTotal;
    private Cliente cliente;
    private Produto produto;

    //Construtor
    public Compra(int idCompra, int quantidadeComprada, Cliente cliente, Produto produto) {
        this.idCliente = cliente.getId();
        this.idProduto = produto.getId();
        this.idCompra = idCompra;
        this.quantidadeComprada = quantidadeComprada;
        this.cliente = cliente;
        this.produto = produto;
        this.valorTotal = calcularTotal();
    }

    //Método para calcular total
    public float calcularTotal() {
        valorTotal = quantidadeComprada * (produto.getPreco());
        return valorTotal;
    }

    //Getters e Setters
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    //Método para exibir dados da compra
    public void exibir() {
        System.out.println("ID: " + idCompra + " | ID comprador: " + idCliente + " | ID produto: " + idProduto + " | Quantidade comprada: " + quantidadeComprada + " | Total: R$ " + valorTotal);
    }
}
