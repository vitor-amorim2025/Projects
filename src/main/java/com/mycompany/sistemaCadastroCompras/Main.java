/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sistemaCadastroCompras;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author 20251TIIMI0337
 */
public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Compra> compras = new ArrayList<>();

        //IDs que serão salvos e carregados no programa
        int proximoIdCliente = 1;
        int proximoIdProduto = 1;
        int proximoIdCompra = 1;

        Persistencia pers = new Persistencia(); // <- Classe com os métodos para escrita e carregamento de texto em arquivos .txt
        Decisoes d = new Decisoes(); // <- Classe com os métodos que realizam as operações escolhidas pelo usuário

        // Carrega dados
        pers.carregarClientes(clientes);
        pers.carregarProdutos(produtos);
        pers.carregarCompras(compras, produtos, clientes);

        // restaura os contadores
        for (Cliente c : clientes) {
            if (c.getId() >= proximoIdCliente) {
                proximoIdCliente = c.getId() + 1;
            }
        }
        for (Produto p : produtos) {
            if (p.getId() >= proximoIdProduto) {
                proximoIdProduto = p.getId() + 1;
            }
        }
        for (Compra c : compras) {
            if (c.getIdCompra() >= proximoIdCompra) {
                proximoIdCompra = c.getIdCompra() + 1;
            }
        }

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar novos clientes");
            System.out.println("2 - Cadastrar novos produtos");
            System.out.println("3 - Realizar compra");
            System.out.println("4 - Consulta de dados");
            System.out.println("5 - Buscar cliente por CPF");
            System.out.println("6 - Relatório de estoque baixo");
            System.out.println("7 - Excluir cliente");
            System.out.println("0 - Sair");
            int opcao = entrada.nextInt();
            switch (opcao) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    //Cadastro de novos clientes
                    int qtdClientes;
                    while (true) {
                        try {
                            System.out.println("Informe o total de novos clientes:");
                            qtdClientes = entrada.nextInt();
                            entrada.nextLine(); // <- limpa o buffer
                            if (qtdClientes > 0) {
                                break;
                            } else {
                                System.out.print("ERRO: Informe um valor válido!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("ERRO: Digite um valor válido!");
                            entrada.nextLine(); // <- limpa o buffer
                        }
                    }
                    for (int i = 0; i < qtdClientes; i++) {
                        int id = proximoIdCliente++;
                        d.cadastraCliente(id, clientes);
                    }
                    System.out.println("Cliente(s) cadastrado!");
                    pers.salvarCliente(clientes); //Registra o cliente em clientes.txt
                }
                case 2 -> {
                    //Cadastro de Produtos
                    int qtdProdutos;

                    while (true) {
                        try {
                            System.out.println("Informe o total de novos produtos:");
                            qtdProdutos = entrada.nextInt();
                            entrada.nextLine();
                            if (qtdProdutos > 0) {
                                break;
                            } else {
                                System.out.println("ERRO: Informe um valor válido!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("ERRO: Digite um número válido.");
                            entrada.nextLine();
                        }
                    }

                    for (int i = 0; i < qtdProdutos; i++) {
                        int id = proximoIdProduto++;
                        d.cadastraProduto(id, produtos);
                    }
                    System.out.println("Produto(s) registrado!");
                    pers.salvarProduto(produtos);
                }
                case 3 -> {
                    // Realização da compra
                    if (clientes.isEmpty() || produtos.isEmpty()) {
                        System.out.println("ERRO: Não há clientes ou produtos cadastrados!");
                    } else {

                        int comp;

                        while (true) {
                            try {
                                System.out.println("Informe o total de compras a serem registradas:");
                                comp = entrada.nextInt();
                                entrada.nextLine();

                                if (comp > 0) {
                                    break;
                                }

                                System.out.println("ERRO: Informe uma quantidade maior que zero!");

                            } catch (InputMismatchException e) {
                                System.out.println("ERRO: Digite um número válido!");
                                entrada.nextLine();
                            }
                        }

                        System.out.println("\nClientes disponíveis:");
                        for (Cliente c : clientes) {
                            c.exibir();
                            System.out.println();
                        }

                        System.out.println("\nProdutos disponíveis:");
                        for (Produto p : produtos) {
                            p.exibir();
                            System.out.println();
                        }

                        for (int i = 0; i < comp; i++) {
                            int id = proximoIdCompra++;
                            d.cadastraCompra(id, compras, produtos, clientes);
                        }

                        System.out.println("Compra(s) registrada(s) com sucesso!");

                        pers.salvarCompra(compras);
                        pers.salvarProduto(produtos);
                    }
                }
                case 4 -> {
                    //Consulta de dados
                    System.out.println("1 - Listar clientes cadastrados");
                    System.out.println("2 - Listar produtos cadastrados");
                    System.out.println("3 - Listar compras realizadas");
                    System.out.println("0 - Voltar");

                    try {
                        int opc = entrada.nextInt();
                        d.consultaDados(opc, compras, produtos, clientes);
                    } catch (InputMismatchException e) {
                        System.out.println("ERRO: Digite um valor válido!");
                        entrada.nextLine();
                        break;
                    }

                }
                case 5 -> {
                    //Busca por CPF
                    if (clientes.isEmpty()) { // <- Verifica se a lista está vazia
                        System.out.println("ERRO: Nenhum cadastrado!");
                    } else {
                        d.consultaCPF(clientes);
                    }
                }
                case 6 -> {
                    //Relatório de estoque baixo
                    if (produtos.isEmpty()) {
                        System.out.println("ERRO: Não há produtos cadastrados");
                    } else {
                        try {
                            System.out.println("Informe o menor valor aceitável de estoque:");
                            int valorMinimo = entrada.nextInt();
                            d.relatorioEstoque(valorMinimo, produtos);
                        } catch (InputMismatchException e) {
                            System.out.println("ERRO: Digite um valor válido!");
                            entrada.nextLine();
                            break;
                        }
                    }
                }
                case 7 -> {
                    //Exclusão de clientes
                    if (clientes.isEmpty()) {
                        System.out.println("ERRO: Não há clientes cadastrados");
                    } else {
                        int qtdRemovido;
                        while (true) {
                            try {
                                System.out.println("Informe a quantidade de clientes a serem excluídos do sistema:");
                                qtdRemovido = entrada.nextInt();
                                d.excluiCliente(qtdRemovido, clientes, pers);
                                entrada.nextLine();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("ERRO: Digite um número válido.");
                                entrada.nextLine();
                            }
                        }
                    }
                }
                default ->
                    System.out.println("ERRO: Escolha uma opção válida!");
            }
        }
    }
}
