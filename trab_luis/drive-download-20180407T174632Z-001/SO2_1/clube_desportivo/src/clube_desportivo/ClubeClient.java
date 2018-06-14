package clube_desportivo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class ClubeClient {

    public static void main(String args[]) {

        String regHost = "localhost";
        String regPort = "9000";

        if (args.length != 2) {
            System.out.println("Usage: java so2.rmi.PalavrasClient registryHost registryPort");
            System.exit(1);
        }

        regHost = args[0];
        regPort = args[1];

        try {
            Clube obj = (Clube) java.rmi.Naming.lookup("rmi://" + regHost + ":" + regPort + "/clube");
            System.out.println("Bem-Vindo à aplicação do Clube desportivo da Praça do Giraldo!");
            System.out.println("1-Listar espaços e custo");
            System.out.println("2-Verificar um espaço numa certa data");
            System.out.println("3-Reservar espaço");
            System.out.println("4-Listar todas as reservas para um certo espaço");
            System.out.println("5-Sair");
            System.out.println("6-Menu\n");

            while (true) {
                try {
                    Scanner input = new Scanner(System.in);
                    System.out.print("Escolha a opção: ");
                    int selection = input.nextInt();
                    System.out.println("\n");

                    switch (selection) {
                        case 1:
                            // Lista os espaços e respetivos custos
                            Vector v = obj.listSpaces();
                            for (int i = 0; i < v.size(); i += 2) {
                                System.out.println("Campo: " + v.elementAt(i) + "--> " + v.elementAt(i + 1) + "€ hora" + "\n");
                            }
                            break;
                        case 2:
                            // Verifica se num certo dia e possivel fazer reserva 
                            System.out.println("Espaço:");
                            input = new Scanner(System.in);
                            String space1 = input.nextLine();

                            System.out.println("Dia:");
                            input = new Scanner(System.in);
                            int dia = input.nextInt();
                            if (dia < 1 || dia > 31) {
                                System.err.println("Dia invalido");
                                break;
                            }

                            System.out.println("Mes:");
                            input = new Scanner(System.in);
                            int mes = input.nextInt();
                            if (mes < 1 || mes > 12) {
                                System.err.println("Mes invalido");
                                break;
                            }

                            System.out.println("Horas:");
                            input = new Scanner(System.in);
                            int hora = input.nextInt();
                            if (hora < 0 || hora > 24) {
                                System.err.println("Hora invalida");
                                break;
                            }

                            System.out.println("Minutos:");
                            input = new Scanner(System.in);
                            int min = input.nextInt();
                            if (min < 0 || min > 59) {
                                System.err.println("Minutos invalidos");
                                break;
                            }

                            Date d = new Date(117, mes - 1, dia, hora, min);

                            if (obj.checkReserve(space1, d)) {
                                System.out.println("Espaço disponivel\n");
                            } else {
                                System.out.println("Espaço indisponivel\n");
                            }

                            break;
                        case 3:
                            // Realiza uma reserva 
                            System.out.println("Dados Checkin:");
                            System.out.println("Espaço:");
                            input = new Scanner(System.in);
                            String space3 = input.nextLine();

                            System.out.println("Nome:");
                            input = new Scanner(System.in);
                            String name = input.nextLine();

                            System.out.println("Numero de utilizadores:");
                            input = new Scanner(System.in);
                            int num = input.nextInt();

                            System.out.println("Telemovel:");
                            input = new Scanner(System.in);
                            int phone = input.nextInt();

                            System.out.println("Dia:");
                            input = new Scanner(System.in);
                            int diain = input.nextInt();
                            if (diain < 1 || diain > 31) {
                                System.err.println("Dia invalido");
                                break;
                            }

                            System.out.println("Mes:");
                            input = new Scanner(System.in);
                            int mesin = input.nextInt();
                            if (mesin < 1 || mesin > 12) {
                                System.err.println("Mes invalido");
                                break;
                            }

                            System.out.println("Horas:");
                            input = new Scanner(System.in);
                            int horain = input.nextInt();
                            if (horain < 0 || horain > 24) {
                                System.err.println("horas invalidas");
                                break;
                            }

                            System.out.println("Minutos:");
                            input = new Scanner(System.in);
                            int minin = input.nextInt();
                            if (minin < 0 || minin > 59) {
                                System.err.println("Minutos invalidos");
                                break;
                            }

                            Date in = new Date(117, mesin - 1, diain, horain, minin);

                            System.out.println("Dados Checkout:");

                            System.out.println("Horas:");
                            input = new Scanner(System.in);
                            int horaout = input.nextInt();
                            if (horaout < 0 || horaout > 24) {
                                System.err.println("horas invalidas");
                                break;
                            }

                            System.out.println("Minutos:");
                            input = new Scanner(System.in);
                            int minout = input.nextInt();
                            if (minout < 0 || minout > 59) {
                                System.err.println("minutos invalidos");
                                break;
                            }

                            Date out = new Date(117, mesin - 1, diain, horaout, minout);

                            if (in.after(out) || out.before(in)) {
                                System.err.println("Horario incorreto");
                                break;
                            }

                            int price = obj.reserveSpace(space3, name, num, phone, in, out);

                            if (price <= 0) {
                                System.out.println("Impossivel reservar nessa data");
                            } else {
                                System.out.println("Reserva efetuada!\nPreço: " + price);
                            }
                            break;
                        case 4:
                            //Lista todas as reservas para um certo espaço
                            System.out.println("Espaço:");
                            input = new Scanner(System.in);
                            String space2 = input.nextLine();
                            Vector p = obj.listReserve(space2);

                            for (int i = 0; i < p.size(); i++) {
                                System.out.println(p.elementAt(i) + "\n");
                            }
                            break;
                        case 5:
                            // Exit da aplicação
                            System.exit(0);
                            break;
                        case 6:
                            //Faz print das opções de menu                            
                            System.out.println("1-Listar espaços e custo");
                            System.out.println("2-Verificar um espaço numa certa data");
                            System.out.println("3-Reservar um espaço");
                            System.out.println("4-Listar todas as reservas para um certo espaço");
                            System.out.println("5-Sair\n");
                            break;
                        default:
                            System.out.println("Escolha Inválida\n");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Input invalido");
                }
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        }

    }

}
