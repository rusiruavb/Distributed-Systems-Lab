import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MathClient {
  public static void main(String[] args) {
    System.setProperty("java.security.policy", "file:allowall.policy");
    MathService service = null;

    try {
      service = (MathService) Naming.lookup("//localhost/CalculatorService");
      service.increment();
      System.out.println("Add : " + service.add(5, 10));
      System.out.println("Subtract : " + service.subtract(50, 20));
      System.out.println("Multiply : " + service.multiply(5, 3));
      System.out.println("Divide : " + service.divide(50, 2));
    } catch (NotBoundException e) {
      System.err.println(e.getMessage());
    } catch (MalformedURLException e) {
      System.err.println(e.getMessage());
    } catch (RemoteException e) {
      System.err.println(e.getMessage());
    }
  }
}
