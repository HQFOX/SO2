package clube_desportivo;

public class ClubeServer {

    public static void main(String args[]) {

        int regPort = 1099;

        if (args.length != 1) {
            System.out.println("Usage: java so2.rmi.PalavrasServer registryPort");
            System.exit(1);
        }

        try {
            regPort = Integer.parseInt(args[0]);

            Clube obj = new ClubeImpl();
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(regPort);
            java.rmi.registry.LocateRegistry.createRegistry(regPort);
            registry.rebind("clube", obj);
            System.out.println("Bound RMI object in registry");
            System.out.println("servidor pronto");

        } catch (Exception excep) {
            excep.printStackTrace();
        }

    }

}
