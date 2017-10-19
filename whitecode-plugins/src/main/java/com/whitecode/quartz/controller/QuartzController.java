package com.whitecode.quartz.controller;

import com.whitecode.common.JsonResult;
import com.whitecode.quartz.model.ScheduleJob;
import com.whitecode.quartz.service.QuartzJobService;
import com.whitecode.tools.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by White on 2017/10/11.
 */
@Controller
@RequestMapping("/common/quartz")
public class QuartzController {

    @Autowired
    private QuartzJobService quartzJobService;

    /**
     * 添加任务
     * @param scheduleJob
     * @return
     */
    @RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult addQuartz(@ModelAttribute("scheduleJob") ScheduleJob scheduleJob) {
        quartzJobService.insertJob(scheduleJob);
        return JsonResultUtil.success();
    }

    /**
     * 更新任务
     * @param scheduleJob
     * @return
     */
    @RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult updateQuartz(@ModelAttribute("scheduleJob") ScheduleJob scheduleJob) {
        quartzJobService.updateJob(scheduleJob);
        return JsonResultUtil.success();
    }

    /**
     * 暂停任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/pause", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult pauseQuartz(@RequestParam("jobId") String jobId) {
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
    public JsonResult resumeQuartz(@RequestParam("jobId") String jobId){
        quartzJobService.resumeJob(jobId);
        return JsonResultUtil.success();
    }

    /**
     * 停止任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/stop", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult stopQuartz(@RequestParam("jobId") String jobId){
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
    public JsonResult deleteQuartz(@RequestParam("jobId") String jobId){
        quartzJobService.deleteJob(jobId);
        return JsonResultUtil.success();
    }

    /**
     * 启动所有触发器
     * @return
     */
    @RequestMapping(value = "/startAll", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult startAllQuartz(){
        quartzJobService.startAllTrigger();
        return JsonResultUtil.success();
    }

    /**
     * 暂停所有触发器
     * @return
     */
    @RequestMapping(value = "/pauseAll", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult pauseAllQuartz(){
        quartzJobService.pauseAllTrigger();
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

    @RequestMapping("/quartzEdit")
    public String editJob(String jobId,Model model) {
        model.addAttribute("scheduleJob",quartzJobService.getScheduleJobById(jobId));
        return "/quartz_edit";
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
