package com.chess.core.service;

import java.io.PrintStream;

public class EventWriter {

    private PrintStream writer = null;

    public EventWriter() {
        try {
            this.writer = new PrintStream("com/chess/core/game/history");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cacheEvent(final String eventDescription) {
        writer.println(eventDescription);
    }
}
