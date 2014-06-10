package fr.lhuet;

import org.vertx.java.busmods.BusModBase;

/**
 * Created by lhuet on 30/05/14.
 */
public class MainWebVerticle extends BusModBase {

    @Override
    public void start() {
        super.start();

        container.deployVerticle("fr.lhuet.teleinfo.WebVerticle", config.getObject("webserver"));
        container.deployWorkerVerticle("fr.lhuet.dhw.DHWVerticle", config.getObject("dhw-system"));

        logger.info("MainWebVerticle launched");

    }
}
