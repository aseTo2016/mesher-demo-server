package code.huawei.com.contoller;

import code.huawei.com.service.Service;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {
    @Autowired
    Service service;

    public void Greeting(RoutingContext context) {
        Future<String> future = service.Greeting();
        service.Greeting().setHandler(resp -> {
            if (future.failed()) {
                context.fail(400);
                return;
            }
            context.response().end(resp.result());
        });
    }

    public void Hello(RoutingContext context) {
        Future<String> future = service.Hello();
        future.setHandler(resp -> {
            if (future.failed()) {
                context.fail(400);
                return;
            }
            context.response().end(resp.result());
        });
    }
}
