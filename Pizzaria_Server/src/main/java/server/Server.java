package server;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import recursos_basicos.CaixaRecurso;
import recursos_basicos.ContasAPagarRecurso;
import recursos_negocio.ContaRecurso;
import recursos_negocio.EstoqueRecurso;
import recursos_basicos.PedidoRecurso;
import recursos_basicos.ProdutoRecurso;
import recursos_intermediarios.ControleDeCaixaRecurso;
import recursos_intermediarios.FichaDeEstoqueRecurso;
import recursos_negocio.ComandaRecurso;
import recursos_negocio.ContasRecurso;
import recursos_negocio.PagamentoRecurso;

/**
 *
 * @author Laraina
 */
public class Server {

    public static void main(String[] args) throws Exception {
        //criando componente
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 80);
        component.getClients().add(Protocol.HTTP);

        // roteando os recursos 
        Router routerNegocio = new Router();
        routerNegocio.attach("/comanda", ComandaRecurso.class);  // abrir comanda
        routerNegocio.attach("/comanda/{numeroComanda}", ComandaRecurso.class);  // fazer pedido
        routerNegocio.attach("/estoque/{numeroComanda}", EstoqueRecurso.class);  // entregar pedido e dar baixa no estoque
        routerNegocio.attach("/conta/{tipoConta}", ContaRecurso.class);  // cadastrar conta a pagar
        routerNegocio.attach("/contas/{param}", ContasRecurso.class);  // listar contas por status e por fornecedor
        routerNegocio.attach("/estoque", EstoqueRecurso.class);  // cadastrar produto no estoque
        routerNegocio.attach("/conta", ContaRecurso.class);  // baixar conta
        routerNegocio.attach("/pagamento/{numeroComanda}", PagamentoRecurso.class);  // pagar comanda e atualizar caixa

        Router routerIntermediario = new Router();
        routerIntermediario.attach("/fichaestoque/{numeroComanda}", FichaDeEstoqueRecurso.class);  // entregar pedido
        routerIntermediario.attach("/controlecaixa/{numeroComanda}", ControleDeCaixaRecurso.class);  // pagar comanda

        Router routerBasico = new Router();
        routerBasico.attach("/pedido", PedidoRecurso.class);  // abrir comanda
        routerBasico.attach("/pedido/{numeroComanda}", PedidoRecurso.class);  // fazer pedido e entregar pedido
        routerBasico.attach("/produto/{nomeProduto}", ProdutoRecurso.class);  // baixar produto no estoque
        routerBasico.attach("/contasapagar/{tipoConta}", ContasAPagarRecurso.class);  // cadastrar conta a pagar
        routerBasico.attach("/contasapagar/listar/{param}", ContasAPagarRecurso.class);  // listar contas por status e por fornecedor
        routerBasico.attach("/produto/cad", ProdutoRecurso.class);  // cadastrar produto no estoque
        routerBasico.attach("/contasapagar", ContasAPagarRecurso.class);  // baixar conta
        routerBasico.attach("/caixa/{numeroComanda}", CaixaRecurso.class);  // atualizar caixa
        routerBasico.attach("/pedido/pagar/{numeroComanda}", PedidoRecurso.class);  // pagar comanda

        // roteando a aplicação
        Application pizzariaNegocio = new Application();
        pizzariaNegocio.setInboundRoot(routerNegocio);
        component.getDefaultHost().attach("/pizzariaNegocio", pizzariaNegocio);

        Application pizzariaIntermediario = new Application();
        pizzariaIntermediario.setInboundRoot(routerIntermediario);
        component.getDefaultHost().attach("/pizzariaIntermediario", pizzariaIntermediario);

        Application pizzariaBasico = new Application();
        pizzariaBasico.setInboundRoot(routerBasico);
        component.getDefaultHost().attach("/pizzariaBasico", pizzariaBasico);

        System.out.println("Iniciou o server...");
        component.start();
    }

}
