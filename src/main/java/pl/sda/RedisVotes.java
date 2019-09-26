package pl.sda;

import redis.clients.jedis.Jedis;

import java.util.Optional;

public class RedisVotes implements Votes {

    private final Jedis jedis;

    private final String DOGS = "dogs";
    private final String CATS = "cats";

    public RedisVotes(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public int getDogs() {
        return Optional.ofNullable(jedis.get(DOGS)).map(Integer::parseInt).orElse(0);
    }

    @Override
    public void increaseDogs() {
        jedis.set(DOGS, Integer.toString(getDogs()+1));
    }

    @Override
    public int getCats() {
        return Optional.ofNullable(jedis.get(CATS)).map(Integer::parseInt).orElse(0);
    }

    @Override
    public void increaseCats() {
        jedis.set(CATS, Integer.toString(getCats()+1));
    }
}

