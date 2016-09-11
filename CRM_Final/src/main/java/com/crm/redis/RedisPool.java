package com.crm.redis;

import java.util.ResourceBundle;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	private static JedisPool pool;
	
	public synchronized static JedisPool getpool(){
		if(pool==null){
			new RedisPool();
		}
		return pool;
	}
	
	private RedisPool(){
		//得到redis.properties 中的配置信息
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if(bundle==null){
			throw new IllegalArgumentException("[redis.properties] is not found");
		}
		
		//配置信息对象config
		JedisPoolConfig config = new JedisPoolConfig();
		
		config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		
		//创建redis连接池
		
		pool =  new JedisPool( 
				config,
				bundle.getString("redis.ip"),
				Integer.valueOf(bundle.getString("redis.port"))
		);
		
	}
	
	
	
}
