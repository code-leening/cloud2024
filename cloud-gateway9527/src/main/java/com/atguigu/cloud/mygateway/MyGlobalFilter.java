package com.atguigu.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author leening
 * @create 2024-08-21-15:12
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long visitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (visitTime != null) {
                log.info("访问主机：" + exchange.getRequest().getURI().getHost());
                log.info("访问端口：" + exchange.getRequest().getURI().getPort());
                log.info("访问端口URL：" + exchange.getRequest().getURI().getPath());
                log.info("访问端口URL后面参数：" + exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口时长：" + (System.currentTimeMillis() - visitTime) + "毫秒");
                log.info("==========分割线==========");
                System.out.println();
            }
        }));
    }

    /**
     * 数字越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
