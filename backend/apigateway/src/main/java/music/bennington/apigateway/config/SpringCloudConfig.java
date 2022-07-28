package music.bennington.apigateway.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/musicapp/backend/**")
                        .filters(f -> f.addRequestHeader("accessedThroughGateway", "TRUE"))
                        .uri("lb://USER-DASHBOARD"))
                .build();
    }

}
