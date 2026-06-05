/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaCadastroCompras;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author vitor
 */
public class Decisoes {

    Scanner entrada = new Scanner(System.in);

    public void cadastraCliente(int id, ArrayList<Cliente> clientes) {
        System.out.println("insira o nome:");
        String nome = entrada.nextLine();
        System.out.println("insira o CPF:");
        String cpf = entrada.next();
        entrada.nextLine();
        System.out.println("insira o email:");
        String email = entrada.next();
        System.out.print("\n");
        entrada.nextLine();
        Cliente c = new Cliente(id, cpf, email, nome);
        clientes.add(c);
    }

    public void cadastraProduto(int id, ArrayList<Produto> produtos) {
        System.out.println("Insira o nome:");
        String nome = entrada.nextLine();

        float preco;
        while (true) {
            try {
                System.out.println("Insira o preço:");
                preco = entrada.nextFloat();
                entrada.nextLine();
                if (preco >= 0) {
                    break;
                } else {
                    System.out.println("ERRO: Insira um preço válido!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Preço inválido!");
                entrada.nextLine();
            }
        }

        int quantidadeEstoque;
        while (true) {
            try {
                System.out.println("Insira a quantidade no estoque:");
                quantidadeEstoque = entrada.nextInt();
                entrada.nextLine();
                if (quantidadeEstoque > 0) {
                    break;
                } else {
                    System.out.println("ERRO: Informe um valor válido!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Quantidade inválida!");
                entrada.nextLine();
            }
        }

        Produto p = new Produto(id, nome, preco, quantidadeEstoque);
        produtos.add(p);
    }

    public void cadastraCompra(int id, ArrayList<Compra> compras, ArrayList<Produto> produtos, ArrayList<Cliente> clientes) {
        Cliente clienteEncontrado = null;

        while (clienteEncontrado == null) {
            try {
                System.out.println("Informe o ID do cliente:");
                int idCliente = entrada.nextInt();
                entrada.nextLine();

                for (Cliente c : clientes) {
                    if (c.getId() == idCliente) {
                        clienteEncontrado = c;
                        break;
                    }
                }

                if (clienteEncontrado == null) {
                    System.out.println("ERRO: Cliente não encontrado!");
                }

            } catch (InputMismatchException e) {
                System.out.println("ERRO: Informe um ID válido!");
                entrada.nextLine();
            }
        }

        Produto produtoEncontrado = null;

        while (produtoEncontrado == null) {
            try {
                System.out.println("Informe o ID do produto:");
                int idProduto = entrada.nextInt();
                entrada.nextLine();

                for (Produto p : produtos) {
                    if (p.getId() == idProduto) {
                        produtoEncontrado = p;
                        break;
                    }
                }

                if (produtoEncontrado == null) {
                    System.out.println("ERRO: Produto não encontrado!");
                }

            } catch (InputMismatchException e) {
                System.out.println("ERRO: Informe um ID válido!");
                entrada.nextLine();
            }
        }

        int qtd;

        while (true) {
            try {
                System.out.println("Informe a quantidade comprada:");
                qtd = entrada.nextInt();
                entrada.nextLine();

                if (qtd > 0) {
                    break;
                }

                System.out.println("ERRO: A quantidade deve ser maior que zero!");

            } catch (InputMismatchException e) {
                System.out.println("ERRO: Informe uma quantidade válida!");
                entrada.nextLine();
            }
        }

        if (produtoEncontrado.atualizaEstoque(qtd)) {
            Compra compra = new Compra(id, qtd, clienteEncontrado, produtoEncontrado);
            compras.add(compra);
        } else {
            System.out.println("ERRO: Estoque insuficiente!");
        }
    }

    public void consultaDados(int opc, ArrayList<Compra> compras, ArrayList<Produto> produtos, ArrayList<Cliente> clientes) {
        switch (opc) {
            case 0 -> {
                break;
            }
            case 1 -> {
                if (clientes.isEmpty()) { // <- Verifica se a lista está vazia
                    System.out.println("ERRO: Nenhum cliente cadastrado!");
                } else {
                    for (Cliente c : clientes) {
                        c.exibir();
                        System.out.print("\n");
                    }
                }
            }
            case 2 -> {
                if (produtos.isEmpty()) { // <- Verifica se a lista está vazia
                    System.out.println("ERRO: Nenhum produto cadastrado!");
                } else {
                    for (Produto p : produtos) {
                        p.exibir();
                        System.out.print("\n");
                    }
                }
            }
            case 3 -> {
                if (compras.isEmpty()) { // <- Verifica se a lista está vazia
                    System.out.println("ERRO: Nenhuma compra cadastrado!");
                } else {
                    for (Compra c : compras) {
                        c.exibir();
                        System.out.print("\n");
                    }
                }
            }
            default ->
                System.out.println("Escolha uma opção válida!");
        }
    }

    public void consultaCPF(ArrayList<Cliente> clientes) {
        System.out.println("Informe o CPF do cliente procurado:");
        String cpfBuscado = entrada.next();
        boolean encontrado = false;
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpfBuscado)) {
                encontrado = true;
                c.exibir();
                break;
            }
        }
        if (encontrado == false) {
            System.out.println("CPF não encontrado.");
        }
    }

    public void relatorioEstoque(int valorMinimo, ArrayList<Produto> produtos) {
        if (valorMinimo > 0) {
            for (Produto p : produtos) {
                if (p.getQuantidadeEstoque() < valorMinimo) {
                    p.exibir();
                }
            }
        } else {
            System.out.println("ERRO: Informe um valor válido!");
        }
    }

    public void excluiCliente(int qtdRemovido, ArrayList<Cliente> clientes, Persistencia pers) {
        if (qtdRemovido > 0 && qtdRemovido <= clientes.size()) {
            for (int i = 0; i < qtdRemovido; i++) {
                System.out.println("Informe o CPF do cliente a ser removido:");
                String cpfRemovido = entrada.next();
                Cliente clienteRemovido = null;
                for (Cliente c : clientes) {
                    if (c.getCpf().equals(cpfRemovido)) {
                        clienteRemovido = c;
                    }
                }
                if (clienteRemovido != null) {
                    clientes.remove(clienteRemovido);
                    System.out.println("Cliente removido.");
                    pers.salvarCliente(clientes);
                } else {
                    System.out.println("ERRO: CPF não encontrado");
                }
            }
        } else {
            System.out.println("ERRO: Informe uma quantidade válida!");
        }
    }
}
