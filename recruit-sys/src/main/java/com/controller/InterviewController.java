package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.InterviewEntity;
import com.entity.view.InterviewView;
import com.service.InterviewService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * 面试管理
 * 后端接口
 */
@RestController
@RequestMapping("/interview")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, InterviewEntity interview, HttpServletRequest request) {
        EntityWrapper<InterviewEntity> ew = new EntityWrapper<InterviewEntity>();
        PageUtils page = interviewService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, interview), params), params));

        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, InterviewEntity interview, HttpServletRequest request) {
        EntityWrapper<InterviewEntity> ew = new EntityWrapper<InterviewEntity>();
        PageUtils page = interviewService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, interview), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R list(InterviewEntity interview) {
        EntityWrapper<InterviewEntity> ew = new EntityWrapper<InterviewEntity>();
        ew.allEq(MPUtil.allEQMapPre(interview, "interview"));
        return R.ok().put("data", interviewService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(InterviewEntity interview) {
        EntityWrapper<InterviewEntity> ew = new EntityWrapper<InterviewEntity>();
        ew.allEq(MPUtil.allEQMapPre(interview, "interview"));
        InterviewView interviewView = interviewService.selectView(ew);
        return R.ok("查询面试成功").put("data", interviewView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        InterviewEntity interview = interviewService.selectById(id);
        return R.ok().put("data", interview);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id) {
        InterviewEntity interview = interviewService.selectById(id);
        return R.ok().put("data", interview);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody InterviewEntity interview, HttpServletRequest request) {
        interview.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        interviewService.insert(interview);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody InterviewEntity interview, HttpServletRequest request) {
        interview.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        interviewService.insert(interview);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody InterviewEntity interview, HttpServletRequest request) {
        interviewService.updateById(interview);// 全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        interviewService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 提醒接口
     */
    @RequestMapping("/remind/{columnName}/{type}")
    public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
                         @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
        map.put("column", columnName);
        map.put("type", type);

        if (type.equals("2")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date remindStartDate = null;
            Date remindEndDate = null;
            if (map.get("remindstart") != null) {
                Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if (map.get("remindend") != null) {
                Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        Wrapper<InterviewEntity> wrapper = new EntityWrapper<InterviewEntity>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        int count = interviewService.selectCount(wrapper);
        return R.ok().put("count", count);
    }
}
