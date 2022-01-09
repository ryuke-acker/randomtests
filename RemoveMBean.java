import javax.management.remote.*;
import javax.management.*;
import java.util.*;

public class RemoveMBean {

    private static String OBJECTNAME = "MLetRevShell:name=revShell,id=1337";

    public static void main(String[] args) throws Throwable {
        try {
            removeMBean(args[0], args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void removeMBean(String serverName, String port) throws Throwable {
        try {
            Map<String, ?> env = null;
            JMXServiceURL u = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + serverName + ":" + port + "/jmxrmi");
            System.out.println("URL: " + u + ", connecting");

            JMXConnector c = JMXConnectorFactory.connect(u, env);

            System.out.println("Connected: " + c.getConnectionId());

            MBeanServerConnection m = c.getMBeanServerConnection();

            try {
                m.unregisterMBean(new ObjectName(OBJECTNAME));
                System.out.println("Should have unregistered MBean: " + OBJECTNAME);
            } catch (Exception e) {
                // Retrieval of OBJECTNAME failed, try to create it from scratch
                System.out.println("Could not retrieve saved instance. Aborting...");
            }

        }
        catch (Exception e) {
            System.out.println("Brrr");
        }
    }
}
