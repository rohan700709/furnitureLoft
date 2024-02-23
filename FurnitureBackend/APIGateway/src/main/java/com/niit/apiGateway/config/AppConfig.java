package com.niit.apiGateway.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p.path("/api/v4/**").uri("lb://furniture-cart-service"))
                .route(p->p.path("/api/v3/**").uri("lb://furniture-home-service"))
                .route(p->p.path("/api/v1/**").uri("lb://user-authentication-service"))
                .route(p->p.path("/api/v2/**").uri("lb://user-service"))
                .route(p->p.path("/api/v5/**").uri("lb://furniture-sorting-searching"))
                .build();

    }

//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new JwtFilter());
//        filterRegistrationBean.addUrlPatterns("/api/v1/*");
//        return filterRegistrationBean;
//    }


}
