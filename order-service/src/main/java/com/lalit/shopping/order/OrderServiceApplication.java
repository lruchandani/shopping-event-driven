package com.lalit.shopping.order;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.beans.BeanProperty;
import java.util.Arrays;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2WebFlux
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public RSocketStrategies rsocketStrategies() {
		return RSocketStrategies.builder()
				.encoders(encoders -> encoders.addAll(Arrays.asList(new Jackson2JsonEncoder(),new Jackson2CborEncoder())))
				.decoders(decoders -> decoders.addAll(Arrays.asList(new Jackson2JsonDecoder(),new Jackson2CborDecoder())))
				.routeMatcher(new PathPatternRouteMatcher())
				.build();
	}

	@Bean
	RSocketRequester rSocketRequester() {
		return RSocketRequester.builder()
				.rsocketStrategies(rsocketStrategies())
				.dataMimeType(MimeTypeUtils.APPLICATION_JSON)
				.connectTcp("localhost", 7002)
				.block();
	}
}
