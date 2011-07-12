package trm.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.core.Dominoes;
import trm.core.DominoesGame;
import trm.core.GameEntity;
import trm.core.HandPlayer;
import trm.core.Movement;
import trm.core.Player;
import trm.core.PlayerInf;
import trm.core.SquareNumber;
import trm.core.Stone;
import trm.core.lps.UMLModel;
import trm.net.model.InvalidMessageException;
import trm.net.model.Receiver;
import trm.net.model.Sender;
import trm.net.server.game.GameAction;
import trm.net.server.game.GameActionImpl;
import trm.net.server.game.GameManager;
import trm.net.server.game.PlayerServer;
import trm.net.server.game.RoomGame;
import trm.net.server.game.RoomInf;
import trm.net.server.game.StatePlayer;

/**
 * @author TRM
 * @version 0.99
 */
public class Server implements Runnable {

    private ServerSocket server;
    private ExecutorService executor;

    public Server(int port) {
        
        try {
            server = new ServerSocket(port);

            executor = Executors.newFixedThreadPool(100);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                Socket socket = server.accept();
                executor.submit(new ServerTask(socket));
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void main(String[] args) {
        new Server(8080).run();
    }
}
