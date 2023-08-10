package demo.joe.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

  @Value("${login.service.base-url:https://localhost:8081}")
  private static String loginServiceBaseUrl;

  @Bean
  public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()
        // example_route is the id of the route
        .route("example_route", r -> r
//            GET is the http method of the route
            .method("GET").and()
//            /example is the path of the route
            .path("/example")
//            /example -> / is the rewrite path of the route
            .filters(f -> f.rewritePath("/example", "/"))
//            LOGIN_SERVICE_URI is the uri of the route
            .uri(loginServiceBaseUrl))
        .build();
  }
}
