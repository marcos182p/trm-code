package trm.core.lps;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.sound.midi.Receiver;
import trm.core.Dominoes;
import trm.core.DominoesGame;
import trm.core.GameEntity;
import trm.core.HandPlayer;
import trm.core.Movement;
import trm.core.Player;
import trm.core.PlayerInf;
import trm.core.SquareNumber;
import trm.core.Stone;
import trm.net.model.InvalidMessageException;
import trm.net.model.Sender;
import trm.net.server.RequestHandler;
import trm.net.server.RequestHandlerImpl;
import trm.net.server.Server;
import trm.net.server.ServerTask;
import trm.net.server.game.GameAction;
import trm.net.server.game.GameActionImpl;
import trm.net.server.game.GameManager;
import trm.net.server.game.PlayerServer;
import trm.net.server.game.RoomGame;
import trm.net.server.game.RoomInf;
import trm.net.server.game.StatePlayer;
import trm.view.game.board.BoardPanel;
import trm.view.game.board.DominoImageLoader;
import trm.view.game.board.DominoView;
import trm.view.game.board.DominosGrid;
import trm.view.game.chat.ChatPanel;
import trm.view.game.chat.listener.ChatListener;
import trm.view.game.login.LoginScreen;
import trm.view.game.login.listener.LoginListener;
import trm.view.game.main.GameScreen;
import trm.view.game.main.ServerView;
import trm.view.game.main.listener.GameScreenListener;
import trm.view.game.player.PlayerList;
import trm.view.game.player.PlayerPanel;
import trm.view.game.player.listener.DominoButtonListener;
import trm.view.game.player.listener.SendStoneListener;
import trm.view.game.player.listener.StartGameListener;
import trm.view.game.utils.BGPanel;
import trm.view.game.utils.BoxPanel;
import trm.view.game.utils.ButtonPanel;
import trm.view.game.utils.ComboBoxPanel;
import trm.view.game.utils.FieldPanel;
import trm.view.game.utils.GameSide;
import trm.view.game.utils.ListPanel;
import trm.view.game.utils.Orientation;
import trm.view.game.utils.RadioButtonListener;
import trm.view.game.utils.RadioButtonPanel;
import trm.view.game.utils.ResourceWindow;
import trm.view.game.utils.StoneSide;
import trm.view.utils.Drawable;
import trm.view.utils.ImageLoader;

/**
 *
 * @author Marcos
 */
public class UMLModel {
    
    private static UMLModel instance = new UMLModel();
    
    private Set<Class> entities;
    
    private Map<String, Set<Class>> modules;
    
    private UMLModel () {
        entities = new HashSet<Class>();
        modules = new HashMap<String,Set<Class>>();
        build();
    } 
    
    public static UMLModel getUMLModel() {
        return instance;
    }
    
    public void build() {
        UMLModel model = this;
        model.register("server", RequestHandler.class);
        model.register("server", RequestHandlerImpl.class);
        model.register("server", Server.class);
        model.register("server", ServerTask.class);
        model.register("server", GameAction.class);
        model.register("server", GameActionImpl.class);
        model.register("server", GameManager.class);
        model.register("server", PlayerServer.class);
        model.register("server", RoomGame.class);
        model.register("server", RoomInf.class);
        model.register("server", StatePlayer.class);
        model.register("net-model", InvalidMessageException.class);
        model.register("net-model", Receiver.class);
        model.register("net-model", Sender.class);


        model.register("core", Dominoes.class);
        model.register("core", DominoesGame.class);
        model.register("core", GameEntity.class);
        model.register("core", HandPlayer.class);
        model.register("core", Movement.class);
        model.register("core", Dominoes.class);
        model.register("core", Player.class);
        model.register("core", PlayerInf.class);
        model.register("core", SquareNumber.class);
        model.register("core", Stone.class);
        
        
        model.register("view", BoardPanel.class);
        model.register("view", DominoImageLoader.class);
        model.register("view", DominoView.class);
        model.register("view", DominosGrid.class);
        model.register("view", ChatPanel.class);
        model.register("view", ChatListener.class);
        model.register("view", LoginScreen.class);
        model.register("view", LoginListener.class);
        model.register("view", GameScreen.class);
        model.register("view", ServerView.class);
        model.register("view", Test.class);
        model.register("view", GameScreenListener.class);
        model.register("view", PlayerList.class);
        model.register("view", PlayerPanel.class);
        model.register("view", DominoButtonListener.class);
        model.register("view", SendStoneListener.class);
        model.register("view", StartGameListener.class);
        model.register("view", BGPanel.class);
        model.register("view", BoxPanel.class);
        model.register("view", ButtonPanel.class);
        model.register("view", ComboBoxPanel.class);
        model.register("view", FieldPanel.class);
        model.register("view", GameSide.class);
        model.register("view", ImageLoader.class);
        model.register("view", ListPanel.class);
        model.register("view", Orientation.class);
        model.register("view", RadioButtonListener.class);
        model.register("view", RadioButtonPanel.class);
        model.register("view", ResourceWindow.class);
        model.register("view", StoneSide.class);
        model.register("view", Drawable.class);
        model.register("view", ImageLoader.class);
    }

    public void register(String module, Class entity) {
        
        if (module == null) {
            throw new RuntimeException("é necessario informar o modulo");
        }
        Set<Class> entitiesModule = modules.get(module);
        
        if (entitiesModule == null) {
            entitiesModule = new HashSet<Class>();
            modules.put(module, entitiesModule);
        }
        
        entities.add(entity);
        entitiesModule.add(entity);
    }
    
    public boolean contains(Class entity) {
        return entities.contains(entity);
    }
    
    public boolean contains(String entity) {
        boolean result = false;
        
        try {
            result = entities.contains(Class.forName(entity));
        } catch (ClassNotFoundException e) {}
        
        return result;
    }
    
    public Set<Class> getEntities() {
        return Collections.unmodifiableSet(entities);
    }
}
