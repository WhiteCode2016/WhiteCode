package com.whitecode.controller;

import com.whitecode.common.JsonResult;
import com.whitecode.entity.ScheduleJob;
import com.whitecode.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addQuartz(@ModelAttribute("scheduleJob") ScheduleJob scheduleJob) {
        quartzJobService.insertJob(scheduleJob);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 更新任务
     * @param scheduleJob
     * @return
     */
    @RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public ResponseEntity updateQuartz(@ModelAttribute("scheduleJob") ScheduleJob scheduleJob) {
        quartzJobService.updateJob(scheduleJob);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 暂停任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/pause", method = { RequestMethod.POST, RequestMethod.GET })
    public String pauseQuartz(@RequestParam("jobId") String jobId) {
        quartzJobService.pauseJob(jobId);
        return "redirect:/common/quartz/quartzList";
    }

    /**
     * 恢复任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/resume", method = { RequestMethod.POST, RequestMethod.GET })
    public String resumeQuartz(@RequestParam("jobId") String jobId){
        quartzJobService.resumeJob(jobId);
        return "redirect:/common/quartz/quartzList";
    }

    /**
     * 停止任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/stop", method = { RequestMethod.POST, RequestMethod.GET })
    public String stopQuartz(@RequestParam("jobId") String jobId){
        quartzJobService.stopJob(jobId);
        return "redirect:/common/quartz/quartzList";
    }

    /**
     * 删除任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
    public String deleteQuartz(@RequestParam("jobId") String jobId){
        quartzJobService.deleteJob(jobId);
        return "redirect:/common/quartz/quartzList";
    }

    /**
     * 立即执行定时任务
     * @param scheduleJob
     * @return
     */
    @RequestMapping(value = "executeJob",method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity excuteJob(ScheduleJob scheduleJob) {
        quartzJobService.runOnce(scheduleJob.getJobId());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 启动所有触发器
     * @return
     */
    @RequestMapping(value = "/startAll", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public ResponseEntity startAllQuartz(){
        quartzJobService.startAllTrigger();
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 暂停所有触发器
     * @return
     */
    @RequestMapping(value = "/pauseAll", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public ResponseEntity pauseAllQuartz(){
        quartzJobService.pauseAllTrigger();
        return new ResponseEntity(HttpStatus.OK);
    }

    //******************************* View ********************************************

    @RequestMapping("/quartzList")
    public String listJob(Model model) {
        List<ScheduleJob> scheduleJobList = quartzJobService.getAllJobs();
        System.out.println(scheduleJobList);
        model.addAttribute("scheduleJobList",scheduleJobList);
        return "/quartz/quartz_list";
    }

    @RequestMapping("/quartzAdd")
    public String addJob(Model model) {
        return "/quartz/quartz_add";
    }

    @RequestMapping("/quartzEdit")
    public String editJob(String jobId,Model model) {
        model.addAttribute("scheduleJob",quartzJobService.getScheduleJobById(jobId));
        return "/quartz/quartz_edit";
    }


}
