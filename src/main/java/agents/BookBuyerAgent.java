package agents;


import containers.BookBuyerContainer;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class BookBuyerAgent extends GuiAgent {
    protected BookBuyerContainer gui;

    @Override
    protected void setup() {
        if (getArguments().length == 1) {
            gui = (BookBuyerContainer) getArguments()[0];
            gui.bookBuyerAgent = this;
        }
        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
        addBehaviour(parallelBehaviour);
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                ACLMessage aclMessage = receive();
                if (aclMessage != null) {
                    gui.logMesssage(aclMessage);
                    ACLMessage reply = aclMessage.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("Trying To Buy "+aclMessage.getContent());
                    send(reply);
                } else {
                    block();
                }
            }
        });
    }

    @Override
    protected void onGuiEvent(GuiEvent arg0) {
    }

}