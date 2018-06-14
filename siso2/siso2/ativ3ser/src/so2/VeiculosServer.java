package so2;

import java.io.*;
import java.net.*;
import java.util.Vector;


public class VeiculosServer {
    private int serverPort;	
    
    private Vector<Registo> repositorio;
    
    public VeiculosServer(int p) {
	serverPort= p;		
	repositorio= new Vector<>(); // INICIALIZE o Vector
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
            ServerSocket s = new ServerSocket(serverPort);
	    while (true) {
		// espera uma ligacao
                
		// ... accept()
                Socket data = s.accept();
		
		try {
		    Object objPedido= null;
		    // le os dados do pedido, como um OBJECTO "objPedido"
                    ObjectInputStream ois = new ObjectInputStream(data.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(data.getOutputStream());
                    objPedido = ois.readObject();
                    
                    

		    
		    // apreciar o objecto com o pedido recebido:
		                  if (objPedido == null) {
                        System.err.println("PEDIDO NULL: esqueceu-se de alguma coisa");
                    }

                    if (objPedido instanceof PedidoDeConsulta) {
                        PedidoDeConsulta pc = (PedidoDeConsulta) objPedido;

                        // procurar o registo associado a matricula pretendida
                        Object resultado = null;
                        // pesquisar no servidor (Vector, mais tarde num ficheiro)
                        for (Registo r : repositorio) {
                            if (r.getMatricula().equals(pc.getMatricula())) {
                                resultado = r;
                            }
                        }

                        // enviar objecto Cliente via socket		    
                        // se encontra devolve o registo, se não, devolve um novo objecto ou string a representar que nao encontrou
                        if (resultado == null) {
                            resultado = "NAO ENCONTROU";
                        }

                    } else if (objPedido instanceof PedidoDeRegisto) {
                        PedidoDeRegisto pr = (PedidoDeRegisto) objPedido; // ...


			// ver se ja existia registo desta matricula
                        
			
			// adicionar ao rep local (se nao e' uma repeticao)

			repositorio.add(pr.getRegisto());    
			// responder ao cliente
                        obj Resposta = ois.readObject(); 
                        
                        //fazer uma string e right object da string

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
