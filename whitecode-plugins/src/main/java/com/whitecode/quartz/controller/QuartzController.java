package com.whitecode.quartz.controller;

import com.whitecode.common.JsonResult;
import com.whitecode.quartz.model.ScheduleJob;
import com.whitecode.quartz.service.QuartzJobService;
import com.whitecode.tools.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by White on 2017/10/11.
 */
@Controller
@RequestMapping("/common/quartz")
public class QuartzController {

    @Autowired
    private QuartzJobService quartzJobService;

    @RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult addQuartz(@ModelAttribute("scheduleJob") ScheduleJob scheduleJob) {
        quartzJobService.insertJob(scheduleJob);
        return JsonResultUtil.success();
    }

    /**
     * 任务创建与更新(未存在的就创建，已存在的则更新)
     * @param scheduleJob
     * @return
     */
    @RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
    public String updateQuartz(@ModelAttribute("scheduleJob") ScheduleJob scheduleJob) {
        //jobMethod.updateJob(scheduleJob);
        return "update";
    }

    /**
     * 暂停任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/pause", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult pauseQuartz(@RequestParam("jobId") int jobId) {
        quartzJobService.pauseJob(jobId);
        return JsonResultUtil.success();
    }

    /**
     * 恢复任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/resume", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult resumeQuartz(@RequestParam("jobId") int jobId){
        quartzJobService.resumeJob(jobId);
        return JsonResultUtil.success();
    }

    /**
     * 删除任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult deleteQuartz(@RequestParam("jobId") int jobId){
        quartzJobService.deleteJob(jobId);
        return JsonResultUtil.success();
    }

    //***************************************************************************

    @RequestMapping("/quartzList")
    public String listJob(Model model) {
        List<ScheduleJob> scheduleJobList = quartzJobService.getAllJobs();
        model.addAttribute("scheduleJobList",scheduleJobList);
        return "/quartz_list";
    }

    @RequestMapping("/quartzAdd")
    public String addJob(Model model) {
        return "/quartz_add";
    }

    /**
     * 立即执行定时任务
     * @param scheduleJob
     * @return
     */
    @RequestMapping(value = "executeJob",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public JsonResult excuteJob(ScheduleJob scheduleJob) {
        quartzJobService.runOnce(scheduleJob.getJobId());
        return JsonResultUtil.success();
    }
}
