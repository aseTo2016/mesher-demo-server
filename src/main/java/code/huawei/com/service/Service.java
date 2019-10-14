package code.huawei.com.service;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.RequestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Service {
    private Vertx vertx = Vertx.vertx();
    private HttpClient client = vertx.createHttpClient();
    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    public Future<String> Greeting() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setHost("127.0.0.1").setPort(30101).setURI("http://demo-mesher:8080/demo/hello");

        Promise<String> promise = Promise.promise();
        client.get(requestOptions, resp -> resp.bodyHandler(body -> {
            if (resp.statusCode() > 200 || resp.statusCode() > 299) {
                LOGGER.error("get data failed, {}", body.toString());
                promise.fail("get data failed");
            } else {
                promise.complete(body.toString());
                LOGGER.info("data is {}", body.toString());
            }
        })).end();
        return promise.future();
    }

    public Future<String> Hello() {
        return Future.succeededFuture("hello, java mesher demo");
    }
}
