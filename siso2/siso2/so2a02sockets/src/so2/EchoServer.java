package so2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private int serverPort;

    public EchoServer(int p) {
        serverPort = p;
    }

    public static void main(String[] args) {
        System.err.println("SERVER...");
        if (args.length < 1) {
            System.err.println("Missing parameter: port number");
            System.exit(1);
        }
        int p = 0;
        try {
            p = Integer.parseInt(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }

        EchoServer serv = new EchoServer(p);
        serv.servico();   // activa o servico
    }

    // activa o servidor no porto indicado em "serverPort"
    public void servico() {
        try {
            // exercicio 2: inicializar um socket para aceitar ligacoes...
            ServerSocket s = new ServerSocket(serverPort);
            //ciclo de atendimento do servco
            while(true){
                Socket data = s.accept();
                //ler o pedido
                InputStream socketIn = data.getInputStream();
                byte[] b = new byte[256];
                int lidos = socketIn.read(b, 0, 256);
                String mensagem = new String(b, 0, lidos);
                System.out.println("---->" + mensagem);
                mensagem += "#";
                //enviar resposta
                OutputStream socketOut = data.getOutputStream();
                socketOut.write(mensagem.getBytes());
                data.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
