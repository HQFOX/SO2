package clube_desportivo;

import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClubeImpl extends UnicastRemoteObject implements Clube, java.io.Serializable {

    //Obtem dados do ficheiro de configurações
    GetPropertyValues prop = new GetPropertyValues();
    String h = prop.getProperties("host");
    String b = prop.getProperties("bd");
    String u = prop.getProperties("user");
    String p = prop.getProperties("pw");

    PostgresConnector pc = new PostgresConnector(h, b, u, p);

    Vector espaços;

    public ClubeImpl() throws java.rmi.RemoteException {

        espaços = new Vector<String>();

    }

    //Faz a ligação a base de dados e retorna um vetor com todos os espaços e seus respetivos preços
    public Vector listSpaces() throws java.rmi.RemoteException {
        espaços = new Vector();

        try {
            pc.connect();
            Statement s = pc.getStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM facility;");
            while (rs.next()) {
                espaços.add(rs.getString("field"));
                espaços.add(rs.getString("price"));
            }
            pc.disconnect();
        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return espaços;
    }

    //Faz a ligação a base de dados e verifica se e possivel efetuar uma reserva na data z
    public boolean checkReserve(String space, Date z) throws java.rmi.RemoteException {
        long dia = z.getTime();
        boolean b = true;
        try {
            pc.connect();
            Statement s = pc.getStatement();
            String sql = "select * from reserves where field='" + space + "' order by checkin";
            ResultSet rs = s.executeQuery(sql);

            if (!rs.next()) {
                b = false;
            }

            while (b && rs.next()) {
                Timestamp t1 = rs.getTimestamp("checkin");
                long in = t1.getTime();

                Timestamp t2 = rs.getTimestamp("checkout");
                long out = t2.getTime();

                if ((dia >= in) && (dia < out)) {
                    b = false;
                    break;
                }
            }
            pc.disconnect();

        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return b;
    }

    //Retorna um vetor com todas as reservas para um certo espaço
    public Vector listReserve(String space) throws java.rmi.RemoteException {
        Vector reservas = new Vector();
        try {
            pc.connect();
            Statement s = pc.getStatement();
            String query = "SELECT * FROM reserves where field='" + space + "';";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                reservas.add("[" + rs.getString("code") + "]" + rs.getString("name") + " ---> INICIO: |" + rs.getString("checkin") + "| FIM: |" + rs.getString("checkout") + "|");
            }
            pc.disconnect();
        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return reservas;
    }

    //Retorna o preço de uma reserva, se o preço for -1 a reserva nao pode ser feita
    public synchronized int reserveSpace(String space, String name, int users, int phone, Date checkin, Date checkout) throws java.rmi.RemoteException {

        long chkin = checkin.getTime();
        long chkout = checkout.getTime();
        boolean free = true;
        int cost = 0;

        //faz check das datas 
        free = this.checkReserve(space, checkin) && this.checkReserve(space, checkout);

        if (free) {
            try {
                pc.connect();
                Statement s = pc.getStatement();
                ResultSet rs = s.executeQuery("select * from facility where field='" + space + "';");
                rs.next();
                int price = rs.getInt("price");
                cost = (int) (price * ((chkout - chkin) / 3600000));
                pc.disconnect();

            } catch (Exception excep) {
                excep.printStackTrace();
            }

            try {
                Timestamp in = new java.sql.Timestamp(checkin.getTime());
                Timestamp out = new java.sql.Timestamp(checkout.getTime());
                pc.connect();
                Statement s = pc.getStatement();
                s.executeUpdate("INSERT INTO reserves (field,name,phone,cost,users,checkin,checkout)VALUES ('" + space + "','" + name + "'," + phone + "," + cost + "," + users + ",'" + in + "','" + out + "');");
                pc.disconnect();
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        } else {
            cost = -1;
        }
        return cost;
    }
}
