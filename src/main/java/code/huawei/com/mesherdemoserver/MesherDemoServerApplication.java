package code.huawei.com.mesherdemoserver;

import code.huawei.com.server.Server;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = {"code.huawei.com"})
public class MesherDemoServerApplication {
    @Autowired
    private Server svc;

    public static void main(String[] args) {
        SpringApplication.run(MesherDemoServerApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(svc);
    }
}
