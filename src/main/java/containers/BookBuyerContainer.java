package containers;



import agents.BookBuyerAgent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookBuyerContainer extends Application {

    public BookBuyerAgent bookBuyerAgent;
    protected ListView<String> listViewMessages;
    protected ObservableList<String> observableListData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startContainer();
        primaryStage.setTitle("Book Buyer");
        BorderPane borderPane = new BorderPane();

        VBox VBox1 = new VBox();
        VBox1.setPadding(new Insets(10));
        VBox1.setSpacing(10);

        observableListData = FXCollections.observableArrayList();
        listViewMessages = new ListView<String>(observableListData);
        VBox1.getChildren().add(listViewMessages);
        borderPane.setCenter(VBox1);

        Scene scene = new Scene(borderPane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startContainer() throws Exception {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController agentController = agentContainer.createNewAgent("BookBuyerAgent",
                BookBuyerAgent.class.getName(), new Object[] { this });
        agentController.start();
    }

    public void logMesssage(ACLMessage aclMessage) {
        Platform.runLater(()->{
            observableListData.add(aclMessage.getSender().getName() + " =>" + aclMessage.getContent());
        });
    }

}