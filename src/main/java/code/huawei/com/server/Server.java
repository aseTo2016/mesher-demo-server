package code.huawei.com.server;

import code.huawei.com.configuration.Configuration;
import code.huawei.com.contoller.Controller;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Server extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    @Autowired
    Configuration cfg;

    private Vertx vertx = Vertx.vertx();
    private Router router = Router.router(vertx);

    @Autowired
    private Controller controller;

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();

        router.route("/demo/greeting").handler(ctx -> controller.Greeting(ctx));
        router.route("/demo/hello").handler(ctx -> controller.Hello(ctx));

        LOGGER.info("listen server {}:{}", cfg.getAddress(), cfg.getPort());
        server.requestHandler(router).listen(cfg.getPort(), cfg.getAddress());
    }
}
