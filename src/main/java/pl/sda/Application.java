package pl.sda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private final static Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	@Bean
	public Votes votes() {
		Jedis jedis = new Jedis();
		try {
			jedis.connect();
			LOG.info("Connection with Redis established.");
			LOG.info("Initializing Redis Votes.");
			return new RedisVotes(jedis);
		} catch (JedisConnectionException e) {
			LOG.info("Can't connect with Redis.");
			LOG.info("Initializing Memory Votes.");
			return new MemoryVotes();
		}
	}
}
