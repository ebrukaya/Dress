package com.fxjs.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.fxjs.client.fxjsService;

public class fxjsServiceImpl extends RemoteServiceServlet implements fxjsService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}