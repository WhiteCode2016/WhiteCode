package com.whitecode;

import com.whitecode.quartz.dao.mapper.QuartzJobMapper;
import com.whitecode.webmagic.dao.mapper.ZhiHuUserMapper;
import com.whitecode.webmagic.model.ZhiHuUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

/**
 * Created by White on 2017/10/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WhitecodePluginsApplication.class)
public class QuartzJobMapperTest {
    @Resource
    private QuartzJobMapper quartzJobMapper;
    @Resource
    private ZhiHuUserMapper zhiHuUserMapper;

    @Test
    public void getAllJobs() {
//        System.out.println(quartzJobMapper.getAllJobs());
//        System.out.println(quartzJobRepository.findAll());
    }

    @Test
    public void addZhiHuUser() {
        ZhiHuUser zhiHuUser = new ZhiHuUser();
        zhiHuUser.setzHName("123");
        zhiHuUserMapper.addZhiHuUser(zhiHuUser);
    }

}
