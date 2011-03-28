package com.fxjs.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface fxjsServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
