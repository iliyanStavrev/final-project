package com.example.sportclopedia.model.view;

public class StatsView {

    private final int authRequest;
    private final int anonRequest;

    public StatsView(int authRequest, int anonRequest) {
        this.authRequest = authRequest;
        this.anonRequest = anonRequest;
    }

    public int getAuthRequest() {
        return authRequest;
    }

    public int getAnonRequest() {
        return anonRequest;
    }

    public int getTotalRequest() {
        return getAuthRequest() + getAnonRequest();
    }
}
