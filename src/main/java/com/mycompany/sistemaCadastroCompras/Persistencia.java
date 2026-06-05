/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaCadastroCompras;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author 20251TIIMI0337
 */
public class Persistencia {

    private static final String CLIENTES_ARQUIVO = "clientes.txt";
    private static final String PRODUTOS_ARQUIVO = "produtos.txt";
    private static final String COMPRAS_ARQUIVO = "compras.txt";

    //Métodos para salvar
    public void salvarCliente(ArrayList<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CLIENTES_ARQUIVO))) {
            for (Cliente cliente : clientes) {
                bw.write(cliente.getId() + "|" + cliente.getNome() + "|" + cliente.getCpf() + "|" + cliente.getEmail());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public void salvarProduto(ArrayList<Produto> produtos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PRODUTOS_ARQUIVO))) {
            for (Produto produto : produtos) {
                bw.write(produto.getId() + "|" + produto.getNome() + "|" + produto.getPreco() + "|" + produto.getQuantidadeEstoque());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public void salvarCompra(ArrayList<Compra> compras) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(COMPRAS_ARQUIVO))) {
            for (Compra compra : compras) {
                bw.write(compra.getIdCompra() + "|" + compra.getIdCliente() + "|" + compra.getIdProduto() + "|" + compra.getQuantidadeComprada() + "|" + compra.calcularTotal());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    //Métodos pra carregar
    public void carregarClientes(ArrayList<Cliente> clientes) {
        File arquivo = new File(CLIENTES_ARQUIVO);
        if (!arquivo.exists()) {
            return; // se não existe, não faz nada
        }
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTES_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue; // ← pula linhas em branco
                }                // Passo 1 - separar
                String[] partes = linha.split("\\|");

                // Passo 2 - converter
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String cpf = partes[2];
                String email = partes[3];

                // Passo 3 - criar o objeto
                Cliente c = new Cliente(id, cpf, email, nome);

                // Passo 4 - adiciona na lista
                clientes.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public void carregarProdutos(ArrayList<Produto> produtos) {
        File arquivo = new File(PRODUTOS_ARQUIVO);
        if (!arquivo.exists()) {
            return; // se não existe, não faz nada
        }
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUTOS_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue; // ← pula linhas em branco
                }                // Passo 1 - separar
                String[] partes = linha.split("\\|");

                // Passo 2 - converter
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                float preco = Float.parseFloat(partes[2]);
                int quantidadeEstoque = Integer.parseInt(partes[3]);

                // Passo 3 - criar o objeto
                Produto p = new Produto(id, nome, preco, quantidadeEstoque);

                // Passo 4 - adiciona na lista
                produtos.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public void carregarCompras(ArrayList<Compra> compras, ArrayList<Produto> produtos, ArrayList<Cliente> clientes) {
        File arquivo = new File(COMPRAS_ARQUIVO);
        if (!arquivo.exists()) {
            return; // se não existe, não faz nada
        }
        try (BufferedReader br = new BufferedReader(new FileReader(COMPRAS_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue; // ← pula linhas em branco
                }
                String[] partes = linha.split("\\|"); // quebra uma String em pedaços baseado em um separador, e devolve um array

                int id = Integer.parseInt(partes[0]);
                int idCliente = Integer.parseInt(partes[1]);
                int idProduto = Integer.parseInt(partes[2]);
                int qtd = Integer.parseInt(partes[3]);
                Cliente clienteEncontrado = null; // <- declara antes
                for (Cliente c : clientes) {
                    if (c.getId() == idCliente) {
                        clienteEncontrado = c; // <- atribui o objeto inteiro
                    }
                }

                Produto produtoEncontrado = null; // <- declara antes
                for (Produto p : produtos) {
                    if (p.getId() == idProduto) {
                        produtoEncontrado = p; // <- atribui o objeto inteiro
                    }
                }

                if (clienteEncontrado != null && produtoEncontrado != null) {
                    Compra c = new Compra(id, qtd, clienteEncontrado, produtoEncontrado);
                    compras.add(c);
                } else {
                    System.out.println("Cliente ou produto não encontrado!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
