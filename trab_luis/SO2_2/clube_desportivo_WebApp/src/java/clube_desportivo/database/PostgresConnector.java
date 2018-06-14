package clube_desportivo.database;

import clube_desportivo.beans.Reserve;
import clube_desportivo.beans.Space;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimeZone;

public class PostgresConnector implements ClubLogic {

    Connection con = null;
    
    //cria uma conexão à BD
    public PostgresConnector(String host, String db, String user, String pw) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
        try {// url = "jdbc:postgresql://host:port/database",
            con = DriverManager.getConnection("jdbc:postgresql://"
                    + host + ":5432/" + db, user, pw);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public LinkedList<Space> listSpaces() {

        LinkedList<Space> spaces = new LinkedList<>();
        
        if (con != null) {
            try {
                Statement s = con.createStatement();
                try {
                    ResultSet rs = s.executeQuery("SELECT * FROM facility;");
                    try {
                        while (rs.next()) {
                            spaces.add(new Space(rs.getString("field"), rs.getDouble("price")));
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Não foi possivel obter os espaços: " + e.getMessage());
            }
            return spaces;
        }
        return null;
    }

    @Override
    public Space getSpaceInfo(String name) {
        Space space = new Space();
        if (con != null) {
            try {
                Statement s = con.createStatement();
                try {
                    String query = "select * from facility where field='" + name + "';";
                    ResultSet rs = s.executeQuery(query);
                    try {
                        while (rs.next()) {
                            space.setName(name);
                            space.setPrice(rs.getDouble("price"));
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Não possivel obter a informaçao dos espaço " + name + ":" + e.getMessage());
            }
            return space;
        }
        return null;
    }

    @Override
    public boolean checkReserve(String name, Instant chki, Instant chko) {
        boolean reserve = true;
        if (chki.isBefore(chko)) {
            if (chki.isAfter(Instant.now())) {
                if (con != null) {
                    try {
                        Statement s = con.createStatement();
                        try {
                            String query = "select * from reserves where field='" + name + "' order by checkin;";
                            ResultSet rs = s.executeQuery(query);
                            try {
                                while (rs.next()) {
                                    Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

                                    Timestamp in = rs.getTimestamp("checkin", tzUTC);
                                    Instant indb = Instant.ofEpochMilli(in.getTime());

                                    Timestamp out = rs.getTimestamp("checkout", tzUTC);
                                    Instant outdb = Instant.ofEpochMilli(out.getTime());

                                    //chki>= indb && chki<=outdb
                                    if ((chki.isAfter(indb) || chki.equals(indb)) && (chki.isBefore(outdb) || chki.equals(outdb))) {
                                        reserve = false;
                                        break;
                                    }

                                    //chko <= outdb && chko >= indb
                                    if ((chko.isAfter(indb) || chko.equals(indb)) && (chko.isBefore(outdb) || chko.equals(outdb))) {
                                        reserve = false;
                                        break;
                                    }

                                    //chki <= indb && chko >= outdb
                                    if ((chko.isAfter(outdb) || chko.equals(outdb)) && (chki.isBefore(indb) || chki.equals(indb))) {
                                        reserve = false;
                                        break;
                                    }

                                }

                            } finally {
                                rs.close();
                            }
                        } finally {
                            s.close();
                        }
                    } catch (SQLException e) {
                        reserve = false;
                        System.out.println("Não foi possivel verificar a reserva: " + e.getMessage());
                    }
                } else {
                    reserve = false;
                }
            } else {
                reserve = false;
            }
        } else {
            reserve = false;
        }
        return reserve;
    }

    @Override
    public int reserveSpace(Reserve r) {
        int reserveCode = -1;
        if (checkReserve(r.getSpace(), r.getCheckin(), r.getCheckout())) {
            r.setCost(getReserveCost(r.getSpace(), r.getCheckin(), r.getCheckout()));
            if (con != null) {
                try {
                    Statement s = con.createStatement();
                    try {
                        LocalDateTime in = LocalDateTime.ofInstant(r.getCheckin(), ZoneOffset.UTC);
                        LocalDateTime out = LocalDateTime.ofInstant(r.getCheckout(), ZoneOffset.UTC);

                        String query = ("INSERT INTO reserves (field,name,phone,cost,users,checkin,checkout)VALUES ('" + r.getSpace() + "','" + r.getPerson() + "'," + r.getPhone() + "," + r.getCost() + "," + r.getUsers() + ",'" + Timestamp.valueOf(in) + "','" + Timestamp.valueOf(out) + "');");
                        s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                        ResultSet rs = s.getGeneratedKeys();
                        try {
                            while (rs.next()) {
                                java.math.BigDecimal serColVar = rs.getBigDecimal(1);
                                reserveCode = serColVar.intValueExact();
                                
                            }
                        } finally {
                            rs.close();
                        }

                    } finally {
                        s.close();
                    }
                } catch (SQLException e) {
                    System.err.println("ERRO ao escrever na BD: " + e.getMessage());
                }
            }
        }
        return reserveCode;
    }

    @Override
    public double getReserveCost(String space, Instant chki, Instant chko) {
        double hourCost = getSpaceInfo(space).getPrice();
        double time = (double) (chko.getEpochSecond() - chki.getEpochSecond());
        double cost = Math.floor((((time / 3600) * hourCost)*100))/100;
        return cost;
    }

    @Override
    public LinkedList<Reserve> listReserve(String space) {
        LinkedList<Reserve> reservas = new LinkedList<>();
        if (con != null) {
            try {
                Statement s = con.createStatement();
                try {
                    String query = "SELECT * FROM reserves where field='" + space + "';";
                    ResultSet rs = s.executeQuery(query);
                    try {
                        while (rs.next()) {
                            reservas.add(new Reserve(rs.getInt("code"), rs.getString("name"), rs.getTimestamp("checkin").toInstant(), rs.getTimestamp("checkout").toInstant()));
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Não foi possivel obter as Reservas: " + e.getMessage());
            }
            return reservas;
        }
        return null;
    }
}
