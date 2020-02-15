import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main (String args[]){

        //Traditional invocation
        Customer customerNative = new Customer();
        customerNative.setName("Sr. Arnold");
        customerNative.getName();

        //Reflection invocation
        try {
            //Get a new instance from Customer Class
            Object customerReflexion = Class.forName("Customer").newInstance();

            //Find the set method
            for (Method method : customerReflexion.getClass().getDeclaredMethods()) {
                if(method.getName().equalsIgnoreCase("setName")){
                    //Invoke the set method with new name
                    method.invoke(customerReflexion, "Mr. Bean");
                }
            }

            //Received value from get  method from Customer class
            String newCustomerName = (String) customerReflexion.getClass().getMethod("getName").invoke(customerReflexion);

            System.out.println(newCustomerName);

        } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
