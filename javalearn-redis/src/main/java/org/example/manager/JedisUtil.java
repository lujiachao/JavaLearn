package org.example.manager;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JedisUtil {

    @Autowired
    private JedisPool _jedisPool;

    private Jedis _jedis;

    private String _pong = "PONG";

    private String _ok = "OK";



    /**
     * 获取Jedis
     * */
     private Jedis GetJedis(){
         if (_jedis == null) {
             _jedis = _jedisPool.getResource();
         }
         return _jedis;
     }

    /**
     *测试是否能连接
     * */
    public Boolean IsConnect(){
        GetJedis();
        String ping = _jedis.ping();
        if (_pong.equals(ping))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * 切换数据库
     */
    public Boolean SelectDb(int db)
    {
        GetJedis();
        String result = _jedis.select(db);
        if (_ok.equals(result))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 查看库中当前所有key
     */
    public Set<String> GetAallKeys(String pattern) {
        GetJedis();
        if (pattern == null || pattern == "")
        {
            pattern = "*";
        }
        return _jedis.keys(pattern);
    }

    /**
     * 获取键的数量
     */
    public Long GetKeyNums() {
        GetJedis();
        return _jedis.dbSize();
    }

    /**
     * 存储string类型
     */
    public Boolean Add(String key, String value)
    {
        GetJedis();
        String result = _jedis.set(key, value);
        if (_ok.equals(result))
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

    public Boolean Exist(String key)
    {
        GetJedis();
        if (_jedis.exists(key))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 存储string类型带过期时间 NX 不存在时set， XX 存在时set。 EX 秒， PX毫秒。
     */
    public Boolean Add(String key, String value, long expireTime)
    {
        GetJedis();
        if (_jedis.exists(key))
        {
            _jedis.del(key);
        }
        String result = _jedis.set(key, value, "NX", "PX", expireTime);
        if (_ok.equals(result))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 获取string类型键值
     */
    public <T> T GetValue(String key)
    {
        GetJedis();
        String result = _jedis.get(key);
        return (T)result;
    }

    /**
     * list类型 返回数组长度
     */
     public Long ListAdd(String key, String... lists)
     {
         GetJedis();
         return _jedis.lpush(key, lists);
     }

    /**
     * list类型 获取数组
     */
     public List<String> ListGet(String key, int start, int end)
     {
         GetJedis();
         return _jedis.lrange(key, start, end);
     }

     /**
     * set类型
     */
     public Long SetAdd(String key, String... members)
     {
         GetJedis();
         return  _jedis.sadd(key, members);
     }

    /**
     * 获取
     */
     public Set<String> SetGet(String key)
     {
         GetJedis();
         return _jedis.smembers(key);
     }

    /**
     *释放Jedis连接
     * */
     public void close(){
         if (_jedis != null)
         {
             GetJedis();
             _jedis.close();
         }
     }

    /**
     * sorted set类型
     */
     public Long SortedAdd(String key, double score, String member){
         GetJedis();
         return _jedis.zadd(key, score, member);
     }

    /**
     * sorted range类型
     */
    public Set<String> SortedRange(String key, int start, int end, Boolean sort){
        GetJedis();
        if (sort) {
            return _jedis.zrange(key, start, end);
        }
        else
        {
            return _jedis.zrevrange(key, start, end);
        }
    }

    /**
     * hash
     */
    public Long HashAdd(String key, String field, String value)
    {
        GetJedis();
        return _jedis.hset(key, field, value);
    }

    public Map<String,String> HashGetAll(String key)
    {
        GetJedis();
        return _jedis.hgetAll(key);
    }

    public List<String> HashMapGet(String key, String... fields)
    {
        GetJedis();
        return _jedis.hmget(key, fields);
    }
}
