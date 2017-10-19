package com.whitecode.quartz.dao.mapper;

import com.whitecode.quartz.model.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface QuartzJobMapper {
    // 获取所有的Jobs
    List<ScheduleJob> getAllJobs();
    // 根据jobId获取job
    ScheduleJob getJobById(String jobId);
    // 添加job
    void insertJob(ScheduleJob scheduleJob);
    // 更新job
    void updateJob(ScheduleJob scheduleJob);
    // 删除job
    void deleteJob(String jobId);

}
