package fr.lhuet;

import fr.lhuet.routes.DomesticHotWater;
import fr.lhuet.routes.Teleinfo;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.mods.web.WebServerBase;

/**
 * Created by lhuet on 28/05/14.
 */
public class WebVerticle extends WebServerBase {

    @Override
    protected RouteMatcher routeMatcher() {

        RouteMatcher matcher = new RouteMatcher();
        Teleinfo.eb = eb;
        DomesticHotWater.eb = eb;

        return matcher
            .get("/isAlive", req -> req.response().end("Teleinfo Web Application started !"))

            .get("/rest/inst/p", Teleinfo::getPapp)
            .get("/rest/inst/i", Teleinfo::getIinst)
            .get("/rest/inst/index", Teleinfo::getIndex)

            .get("/rest/dhw/dhw", DomesticHotWater::getDhwTemp)
            .get("/rest/dhw/buffer", DomesticHotWater::getBufferTemp)

            .noMatch(staticHandler());

    }

}
