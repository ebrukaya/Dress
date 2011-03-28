package com.fxjs.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fxjsService")
public interface fxjsService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use fxjsService.App.getInstance() to access static instance of fxjsServiceAsync
     */
    public static class App {
        private static fxjsServiceAsync ourInstance = GWT.create(fxjsService.class);

        public static synchronized fxjsServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
