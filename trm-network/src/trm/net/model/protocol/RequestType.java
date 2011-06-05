package trm.net.model.protocol;

/**
 *
 */
public enum RequestType {

    LOGIN,

    ENTER_ROOM,//solicita a entrada em uma sala
    EXIT_ROOM,//solicita a saida da sala que está jogando
    
    START_GAME,//solicita o inicio do jogo
    END_GAME,//solicita o fim do jogo

    PUT_STONE,//solicitação para colocar uma peça no tabuleiro
    PUT_MESSAGE,//solicitação para postar uma mensagens para os outros players
    PUT_ROOM,//solicita que uma nova sala seja adicionada no servidor
    
    GET_STONES,//solicita peças do tabuleiro
    GET_HAND,//solicita peças da mão do jogador
    GET_PLAYERS,//solicita jogadores na sala
    GET_ROOMS,//solicita salas do servidor
    GET_WINNER,//solicita vencedor de uma sala
    
    CLOSE_CONNECTION,//solicita o fechamento da conexão
    UNDEFINED//reservada para o servidor
}
