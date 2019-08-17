package org.mlib.Network;

public interface MNetworkEvent {
    void onSuccess(String response);

    void onFailed(String cause);
}