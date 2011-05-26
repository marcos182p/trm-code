package trm.net.client;

import trm.net.model.protocol.ResponseServer;

/**
 * Recebe uma notificação quando uma mensagem chega do servidor
 */
public interface Listener {

    void update(ResponseServer response);
}
