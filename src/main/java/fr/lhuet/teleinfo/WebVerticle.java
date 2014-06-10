package fr.lhuet.teleinfo;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;
import org.vertx.mods.web.WebServerBase;

/**
 * Created by lhuet on 28/05/14.
 */
public class WebVerticle extends WebServerBase {

    @Override
    protected RouteMatcher routeMatcher() {

        RouteMatcher matcher = new RouteMatcher();

        matcher.get("/isAlive", (HttpServerRequest req) -> {
            req.response().end("Teleinfo Web Application started !");
        });

        matcher.get("/rest/inst/p", (HttpServerRequest req) -> {
            eb.send("teleinfo-data", "get", (Message<JsonObject> teleinfo) -> {
                String pinst = teleinfo.body().getInteger("PAPP").toString();
                req.response().end(pinst);
            });
        });

        matcher.get("/rest/inst/i", (HttpServerRequest req) -> {
            eb.send("teleinfo-data", "get", (Message<JsonObject> teleinfo) -> {
                    String iinst = teleinfo.body().getInteger("IINST").toString();
                    req.response().end(iinst);
            });
        });

        matcher.get("/rest/inst/index", (HttpServerRequest req) -> {
            eb.send("teleinfo-data", "get", (Message<JsonObject> teleinfo) -> {
                    String index = teleinfo.body().getInteger("INDEX").toString();
                    req.response().end(index);
            });
        });

        matcher.get("/rest/dhw/dhw", (HttpServerRequest req) -> {
            eb.send("dhw-temp", "dhw", (Message<String> msg) -> {
                    req.response().end(String.valueOf(msg.body()));
            });
        });

        matcher.get("/rest/dhw/buffer", (HttpServerRequest req) -> {
            eb.send("dhw-temp", "buffer", (Message<String> msg) -> {
                    req.response().end(String.valueOf(msg.body()));
            });
        });

        matcher.noMatch(staticHandler());

        return matcher;
    }

}
