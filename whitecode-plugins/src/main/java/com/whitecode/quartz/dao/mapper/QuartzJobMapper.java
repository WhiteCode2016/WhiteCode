package com.whitecode.quartz.dao.mapper;

import com.whitecode.quartz.model.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by White on 2017/10/11.
 */
@Mapper
public interface QuartzJobMapper {
    List<ScheduleJob> getAllJobs();
    ScheduleJob getJobById(int jobId);
}
