package com.whitecode;


import com.whitecode.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SysUserService sysUserService;


    @Test
    public void redisTest() {
        stringRedisTemplate.opsForValue().set("aaa","111");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void findByUsername(){
        System.out.println("第一次查询结果：");
        System.out.println(sysUserService.findByUsername("admin"));
        System.out.println("第二次查询结果：");
        System.out.println(sysUserService.findByUsername("admin"));
    }

}
