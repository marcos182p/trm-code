package trm.net.model.protocol;

/**
 *
 */
public enum RequestType {

    LOGIN,

    LIST_ROOMS,

    CREATE_ROOM,

    ENTER_ROOM,
    EXIT_ROOM,

    MOVE_STONE,
    POST_MESSAGE,
    LIST_BOARD_STONES,
    LIST_HAND,
    LIST_PLAYERS,

    START_GAME,
    END_GAME,
    
    CLOSE_CONNECTION,
    UNDEFINED
}
