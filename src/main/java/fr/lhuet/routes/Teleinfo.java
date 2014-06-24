package fr.lhuet.routes;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.eventbus.EventBus;

/**
 * Created by lhuet on 24/06/14.
 */
public class Teleinfo {

    public static EventBus eb;

    public static void getPapp(HttpServerRequest req) {
        eb.send("teleinfo-data", "get",
                (Message<JsonObject> teleinfo) -> {
                    String pinst = teleinfo.body().getInteger("PAPP").toString();
                    req.response().end(pinst);
                });
    }

    public static void getIinst(HttpServerRequest req) {
        eb.send("teleinfo-data", "get",
                (Message<JsonObject> teleinfo) -> {
                    String iinst = teleinfo.body().getInteger("IINST").toString();
                    req.response().end(iinst);
                });
    }

    public static void  getIndex(HttpServerRequest req) {
        eb.send("teleinfo-data", "get",
                (Message<JsonObject> teleinfo) -> {
                    String index = teleinfo.body().getInteger("INDEX").toString();
                    req.response().end(index);
                });
    }
}
