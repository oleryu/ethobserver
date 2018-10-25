package xyz.oleryu.wallet.eth.observer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ oleryu.xyz
 */
@SpringBootApplication
//@Configuration
//@ComponentScan(basePackages={"xyz.oleryu.wallet.eth.observer.*"})
@EnableAutoConfiguration
//@EnableAsync
//@EnableScheduling
public class EthobserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(EthobserverApplication.class, args);
	}
}
