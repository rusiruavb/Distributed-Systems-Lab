package com.company;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MathServer extends UnicastRemoteObject implements MathService {
    private int count = 0;

    protected MathServer() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        System.out.println("Adding " + a + " and " + b + " in the server");
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        System.out.println("Subtract " + a + " and " + b + " in the server");
        return a - b;
    }

    @Override
    public int multiply(int a, int b) throws RemoteException {
        System.out.println("Multiply " + a + " and " + b + " in the server");
        return a * b;
    }

    @Override
    public int divide(int a, int b) throws RemoteException {
        System.out.println("Divide " + a + " and " + b + " in the server");
        return a / b;
    }

    @Override
    public synchronized int increment() throws RemoteException {
        this.count++;
        return this.count;
    }

    public void test() {
        System.out.println("Test method is executed");
    }

    public static void main(String[] args) { // we can have this main method in another class
        System.setProperty("java.security.policy", "file:allowall.policy");

        try {
            MathServer server = new MathServer();
            // bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("CalculatorService", server);
            System.out.println("Service started...");
        } catch (RemoteException e) {
            System.err.println("Remote exception" + e.getMessage());
        } catch (AlreadyBoundException e) {
            System.err.println("Already bound exception" + e.getMessage());
        }
    }
}
