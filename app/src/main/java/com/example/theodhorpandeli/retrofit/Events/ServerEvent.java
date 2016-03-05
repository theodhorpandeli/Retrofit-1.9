package com.example.theodhorpandeli.retrofit.Events;

import com.example.theodhorpandeli.retrofit.ServerResponse;

/**
 * Created by Theodhor Pandeli on 2/11/2016.
 */
public class ServerEvent {
    private ServerResponse serverResponse;

    public ServerEvent(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

}
