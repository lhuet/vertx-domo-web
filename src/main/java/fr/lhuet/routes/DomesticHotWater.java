package fr.lhuet.routes;

import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;

/**
 * Created by lhuet on 24/06/14.
 */
public class DomesticHotWater {

    public static EventBus eb;

    public static void getDhwTemp(HttpServerRequest req) {
        eb.send("dhw-temp", "dhw", (Message<String> msg) -> req.response().end(msg.body()) );
    }

    public static void getBufferTemp(HttpServerRequest req) {
        eb.send("dhw-temp", "buffer", (Message<String> msg) -> req.response().end(String.valueOf(msg.body())));
    }
}
