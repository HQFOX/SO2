package clube_desportivo.database;

import clube_desportivo.beans.Reserve;
import clube_desportivo.beans.Space;
import java.time.Instant;
import java.util.LinkedList;

public class DataManager implements ClubLogic {

    private static ClubLogic dbManager;

    public DataManager() {

        //Obtem dados do ficheiro de configurações
        GetPropertyValues prop = new GetPropertyValues();
        String h = prop.getProperties("host");
        String b = prop.getProperties("bd");
        String u = prop.getProperties("user");
        String p = prop.getProperties("pw");

        dbManager = new PostgresConnector(h, b, u, p);
    }

    @Override
    public LinkedList<Space> listSpaces() {
        return dbManager.listSpaces();
    }

    @Override
    public LinkedList<Reserve> listReserve(String space) {
        return dbManager.listReserve(space);
    }

    @Override
    public Space getSpaceInfo(String name) {
        return dbManager.getSpaceInfo(name);
    }

    @Override
    public double getReserveCost(String space, Instant chki, Instant chko) {
        return dbManager.getReserveCost(space, chki, chko);
    }

    @Override
    public int reserveSpace(Reserve r) {
        return dbManager.reserveSpace(r);
    }

    @Override
    public boolean checkReserve(String name, Instant chki, Instant chko) {
        return dbManager.checkReserve(name, chki, chko);
    }

    @Override
    public void disconnect() {
        dbManager.disconnect();
    }

}
