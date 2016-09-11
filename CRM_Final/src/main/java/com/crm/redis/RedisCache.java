package com.crm.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {

	private static Logger logger = LogManager.getLogger(RedisCache.class);
	
	private String id;
	
	private Jedis redisClient = createRedis();  //创建一个jeids连接
	
	//用于同步的锁
	private ReadWriteLock readwritelock = new ReentrantReadWriteLock();
	
	
	public RedisCache(String id){
		if(id==null){
			throw new IllegalArgumentException("Cache instance requires an Id");
		}
		logger.debug("create an cache instance with id"+id);
		this.id=id;
	}




	//清楚缓存数据
	public void clear() {
		this.redisClient.flushDB();
	}

	public String getId() {
		return this.id;
	}

	public Object getObject(Object key) {
		//缓存穿透
		byte[] values = this.redisClient.get(SerialoizebleUtil.serialize(key));
		
		//算法:计算一定时间内没有命中的键，
		if(values==null){
			return null;
		}
		Object obj  = SerialoizebleUtil.unSerialize(values);
		return obj;
	}

	public ReadWriteLock getReadWriteLock() {
		return readwritelock;
	}

	public int getSize() {
		Long size = this.redisClient.dbSize();
		int s= Integer.valueOf(size+"");
		return s;
	}

	public void putObject(Object key, Object value) {
		byte[] keybyte = SerialoizebleUtil.serialize(key);
		byte[] valuebyte = SerialoizebleUtil.serialize(value);
		this.redisClient.set(keybyte, valuebyte);
	}

	public Object removeObject(Object key) {
		byte[] keybyte = SerialoizebleUtil.serialize(key);
		return this.redisClient.expire(keybyte, 0);
	}

	
	private Jedis createRedis() {
		Jedis jedis = RedisPool.getpool().getResource();
		return jedis;
	}
}
