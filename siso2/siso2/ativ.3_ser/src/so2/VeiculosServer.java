package so2;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class VeiculosServer {
    private int serverPort;	
    
    private Vector<Registo> repositorio;
    
    public VeiculosServer(int p) {
	serverPort= p;		
	repositorio= ; // INICIALIZE o Vector
    }
    

    
    public static void main(String[] args){
	System.err.println("SERVER...");
	if (args.length<1) {
		System.err.println("Missing parameter: port number");	
		System.exit(1);
	}
	int p=0;
	try {
	    p= Integer.parseInt( args[0] );
	}
	catch (Exception e) {
		e.printStackTrace();
		System.exit(2);
	}
	
	
	VeiculosServer serv= new VeiculosServer(p);
        serv.servico();   // activa o servico
    }
    
    





    public void servico() {

	try {

	    // inicializa o socket para receber ligacoes
	  
	    while (true) {
		// espera uma ligacao
		// ... accept()
		
		try {
		    Object objPedido= null;
		    // le os dados do pedido, como um OBJECTO "objPedido"		


		    
		    // apreciar o objecto com o pedido recebido:
		    if (objPedido==null)
			System.err.println("PEDIDO NULL: esqueceu-se de alguma coisa");
		    
		    if (objPedido instanceof PedidoDeConsulta) {
			PedidoDeConsulta pc= null;
			
			// procurar o registo associado a matricula pretendida

			// pesquisar no servidor (Vector, mais tarde num ficheiro)

			
			// enviar objecto Cliente via socket		    
			// se encontra devolve o registo, se não, devolve um novo objecto ou string a representar que nao encontrou


			
		    }
		    else if (objPedido instanceof PedidoDeRegisto) {
			PedidoDeRegisto pr= null; // ...


			// ver se ja existia registo desta matricula

			
			// adicionar ao rep local (se nao e' uma repeticao)

			
			// responder ao cliente


		    }
		    else
			System.out.println("PROBLEMA");
		    
                }
                catch (Exception exNoAtendimento) {
                    exNoAtendimento.printStackTrace();
                }
                finally {  // fechar o socket de dados
                    // fechar o socket de dados dedicado a este cliente:
                    try {
                        AQUI: fechar o socket de dados
                    }
                    catch (Exception e002) {
                    }
                }
                
		
	    
		
	    }  // ... ciclo de atendimento
	
	}
	catch (Exception problemaBindAccept) {
	    problemaBindAccept.printStackTrace();
	}

    }


}
