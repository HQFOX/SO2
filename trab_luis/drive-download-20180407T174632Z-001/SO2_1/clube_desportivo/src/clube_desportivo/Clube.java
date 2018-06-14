package clube_desportivo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

public interface Clube extends java.rmi.Remote{
    
    public Vector listSpaces() throws java.rmi.RemoteException;
    public boolean checkReserve(String space, Date z) throws java.rmi.RemoteException;
    public Vector listReserve(String space) throws java.rmi.RemoteException;
    public int reserveSpace(String space, String name, int users, int phone, Date checkin, Date checkout) throws java.rmi.RemoteException;       
}
