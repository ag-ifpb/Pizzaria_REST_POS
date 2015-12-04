/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import org.json.JSONException;

/**
 *
 * @author Jannayna
 */
public class Testes {

    public static void main(String[] args) throws JSONException, IOException {
        cadastrarProduto();
        abrirComanda();
        fazerPedido();
        entregarItem();
        pagarComanda();
        cadastrarConta();
        listarContas();
        baixarConta();
    }

    public static void abrirComanda() throws JSONException, IOException {
        System.out.println("\n--- Testes para a funcionalidade \"abrir comanda\" ---\n");

        /* teste positivo: abrir uma comanda com um número que ainda não existe. Deve retornar true. */
        boolean testePositivo = Cliente_Pizzaria.abrirComanda(1001);
        System.out.println("\nTeste positivo: " + testePositivo + "\n(Comanda aberta com sucesso)");

        /* teste negativo: abrir uma comanda com um número que já existe. Deve retornar false. */
        boolean testeNegativo = Cliente_Pizzaria.abrirComanda(1001);
        System.out.println("\nTeste negativo: " + testeNegativo + "\n(Já existe uma comanda aberta com esse número)");
    }

    public static void fazerPedido() throws JSONException, IOException {
        System.out.println("\n--- Testes para a funcionalidade \"fazer pedido\" ---\n");

        /* teste positivo: cadastrar um pedido em uma comanda que já foi aberta. Deve retornar true. */
        boolean testePositivo = Cliente_Pizzaria.fazerPedido(1001);
        System.out.println("\nTeste positivo: " + testePositivo + "\n(Pedido registrado com sucesso)");

        /* teste negativo: cadastrar um pedido em uma comanda que ainda não foi aberta. Deve retornar false. */
        boolean testeNegativo = Cliente_Pizzaria.fazerPedido(5000);
        System.out.println("\nTeste negativo: " + testeNegativo + "\n(É necessário abrir uma comanda para registrar pedidos)");
    }

    public static void entregarItem() throws JSONException, IOException {
        System.out.println("\n--- Testes para a funcionalidade \"entregar item\" ---\n");

        /* teste positivo: entregar um item que ainda nao foi entregue. Deve retornar true. */
        boolean testePositivo = Cliente_Pizzaria.entregarItem("Refrigerante");
        System.out.println("\nTeste positivo: " + testePositivo + "\n(Item entregue com sucesso)");

        /* teste negativo: entregar um item que já foi entregue. Deve retornar false. */
        boolean testeNegativo = Cliente_Pizzaria.entregarItem("Refrigerante");
        System.out.println("\nTeste negativo: " + testeNegativo + "\n(O item já foi entregue)");
    }

    public static void cadastrarConta() throws JSONException, IOException {
        System.out.println("\n--- Testes para a funcionalidade \"cadastrar título de conta a pagar\" ---\n");

        /* teste positivo: cadastrar uma conta com um número que ainda não existe. Deve retornar true. */
        boolean testePositivo = Cliente_Pizzaria.cadastrarConta("101");
        System.out.println("\nTeste positivo: " + testePositivo + "\n(Conta cadastrada com sucesso)");

        /* teste negativo: cadastrar uma conta com um número que já existe. Deve retornar false. */
        boolean testeNegativo = Cliente_Pizzaria.cadastrarConta("101");
        System.out.println("\nTeste negativo: " + testeNegativo + "\n(Já existe uma conta cadastrada com esse número)");
    }

    public static void listarContas() throws JSONException, IOException {
        System.out.println("\n--- Testes para a funcionalidade \"listar contas\" ---\n");

        /* teste positivo: o status em que se deseja listar é um dos pré-definidos no projeto 
         (A vencer, vencido, pago). Deve retornar true. */
        boolean testePositivo = Cliente_Pizzaria.listarContasPorStatus("A_VENCER");
        System.out.println("\nTeste positivo: " + testePositivo + "\nLista: vide servidor");

        /* teste negativo: o status em que se deseja listar não é um dos pré-definidos no projeto 
         (A vencer, vencido, pago). Deve retornar false. */
        boolean testeNegativo = Cliente_Pizzaria.listarContasPorStatus("ESTORNADO");
        System.out.println("\nTeste negativo: " + testeNegativo + "\n(Status não existente)");
    }

    public static void cadastrarProduto() throws JSONException, IOException {
        System.out.println("\n--- Testes para a funcionalidade \"cadastrar produto no estoque\" ---\n");

        /* teste positivo: cadastrar um produto de um dos tipos pré-definidos para o projeto (Refrigerante,
         Pizza 1 sabor, Pizza 2 sabores e salgado). Deve retornar true. */
        boolean testePositivo = Cliente_Pizzaria.cadastrarProduto("Refrigerante", 8, 2);
        System.out.println("\nTeste positivo: " + testePositivo + "\n(Produto cadastrado com sucesso)");

        /* teste negativo: cadastrar um produto de um dos tipos pré-definidos para o projeto (Refrigerante,
         Pizza 1 sabor, Pizza 2 sabores e salgado). Deve retornar false. */
        boolean testeNegativo = Cliente_Pizzaria.cadastrarProduto("Bolo de chocolate", 8, 2);
        System.out.println("\nTeste negativo: " + testeNegativo + "\n(Produto não cadastrado, pois não é um dos tipos definidos no projeto)");
    }

    public static void baixarConta() throws JSONException, IOException {
        System.out.println("\n--- Testes para a funcionalidade \"baixar conta\" ---\n");
        // teste positivo: numero do documento enviado existe (dao retornando true)
        boolean testePositivo = Cliente_Pizzaria.baixarConta("101");
        System.out.println("\nTeste positivo: " + testePositivo + "\n(Conta baixa com sucesso)");

        /* teste negativo: numero do documento enviado não existe (dao retorna false)        
         Outra opcao: tentar dar baixa em uma conta que já está paga (dao retorna false) */
        boolean testeNegativo = Cliente_Pizzaria.baixarConta("5000");
        System.out.println("\nTeste negativo: " + testeNegativo + "\n(Não existe uma conta cadastrada com esse número)");
    }

    public static void pagarComanda() throws JSONException, IOException {
        System.out.println("\n--- Testes para a funcionalidade \"pagar comanda\" ---\n");
        // teste positivo: numero da comanda enviada existe (dao retornando true)
        boolean testePositivo = Cliente_Pizzaria.pagarComanda(1001);
        System.out.println("\nTeste positivo: " + testePositivo + "\n(Comanda paga com sucesso)");

        // teste negativo: numero da comanda enviada não existe (dao retorna false)
        boolean testeNegativo = Cliente_Pizzaria.pagarComanda(5000);
        System.out.println("\nTeste negativo: " + testeNegativo + "\n(Não existe uma comanda cadastrada com esse número)");
    }
}
