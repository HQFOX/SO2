package clube_desportivo.database;

import clube_desportivo.beans.Reserve;
import clube_desportivo.beans.Space;
import java.time.Instant;
import java.util.LinkedList;

public interface ClubLogic {

    public abstract LinkedList<Space> listSpaces();

    public Space getSpaceInfo(String name);

    public double getReserveCost(String space, Instant chki, Instant chko);

    public int reserveSpace(Reserve r);

    public boolean checkReserve(String name, Instant chki, Instant chko);

    public abstract LinkedList<Reserve> listReserve(String space);

    public void disconnect();
}
