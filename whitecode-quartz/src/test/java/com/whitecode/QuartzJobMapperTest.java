package com.whitecode;

import com.whitecode.dao.mapper.QuartzJobMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by White on 2017/10/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WhitecodeQuartzApplication.class)
public class QuartzJobMapperTest {
    @Resource
    private QuartzJobMapper quartzJobMapper;
    @Resource

    @Test
    public void getAllJobs() {
//        System.out.println(quartzJobMapper.getAllJobs());
//        System.out.println(quartzJobRepository.findAll());
    }


}
