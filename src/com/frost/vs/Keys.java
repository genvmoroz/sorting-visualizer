package com.frost.vs;

public enum  Keys {

    DEFAULT("Default"),
    BUBBLE("Bubble"),
    SWAP("Swap"),
    GNOME("Gnome"),
    QUITE("Quite"),
    COMB("Comb"),
    HEAP("Heap"),
    SHELL("Shell"),
    SELECTION("Selection");

    private String key;

    Keys(String key){
        this.key = key;
    }
    public String getKey(){
        return key;
    }
}
