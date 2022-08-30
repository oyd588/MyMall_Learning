package com.oyd;



import java.util.Set;

/**
 * Unit test for simple App.
 */
//public class AppTest {
//    /**
//     * Rigorous Test :-)
//     */
//    @Test
//    public void shouldAnswerWithTrue() {
//        assertTrue(true);
//    }
//
//    @Test
//    public void testJedis() {
//        Jedis jedis = new Jedis("localhost", 6379);//Redis服务器的 ip 和端口号
//        String ping = jedis.auth("foobared123");//密码
//        System.out.println(ping);
//
//        jedis.select(2);
//        Set<String> keys = jedis.keys("*");
//        System.out.println(keys);
//        jedis.set("testJedis", "value123");
//        String testJedis = jedis.get("testJedis");
//        System.out.println(testJedis);
//
//
//    }
//    @Test
//    public void testJedisPool() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        //最大空闲数
//        jedisPoolConfig.setMaxIdle(50);
//        //最大连接数
//        jedisPoolConfig.setMaxTotal(100);
//        //最大等待毫秒数
//        jedisPoolConfig.setMaxWaitMillis(20000);
//        //使用配置创建连接池
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost");
//        //从连接池中获取单个连接
//        Jedis jedis = jedisPool.getResource();
//
//        jedis.auth("foobared123");
//        System.out.println(jedis.ping());
//
//    }
//}
