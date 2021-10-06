import redis.clients.jedis.JedisPool

fun redis(){
    //创建一个数据库连接池对象（单例），需要指定服务的ip和端口号
    val jedisPool = JedisPool("127.0.0.1", 6379)
    //从连接池中获得连接
    val jedis = jedisPool.resource

    //使用Jedis操作数据库（方法级别使用）
    val result = jedis.get("user")
    println(result)
    //一定要关闭Jedis连接
    jedis.close()
    //系统关闭前关闭连接池
    jedisPool.close()
}

fun main(args: Array<String>) {
    redis()
}