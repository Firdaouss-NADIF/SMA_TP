package containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MyMainContainer {
public static void main(String[] args) throws ControllerException {
    Runtime runtime = Runtime.instance();
    Properties properties = new Properties();
    properties.setProperty(Profile.GUI, "true");
    ProfileImpl profile = new ProfileImpl(properties);
    AgentContainer mainContainer = runtime.createMainContainer(profile);
    mainContainer.start();
	
}
}
