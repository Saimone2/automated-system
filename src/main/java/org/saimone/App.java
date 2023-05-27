package org.saimone;

import org.saimone.logger.ApartmentLogger;
import org.saimone.view.ApartmentView;

import java.util.logging.Level;

public class App {
    public static void main(String[] args)
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> ApartmentLogger.LOGGER.log(Level.INFO, "The program is shutting down")));

        ApartmentView apartmentView = new ApartmentView();
        ApartmentLogger.LOGGER.log(Level.INFO, "The program is started");
        apartmentView.run();
    }
}
