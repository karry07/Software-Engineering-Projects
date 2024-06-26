package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.Job_Post_CommentsEntity;
import com.entity.view.Job_Post_CommentsView;
import com.service.Job_Post_CommentsService;
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
 * 招聘信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/job_post_comments")
public class Job_Post_CommentsController {
    @Autowired
    private Job_Post_CommentsService job_post_commentsService;
    

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,Job_Post_CommentsEntity job_post_comments, HttpServletRequest request){
        EntityWrapper<Job_Post_CommentsEntity> ew = new EntityWrapper<Job_Post_CommentsEntity>();
		PageUtils page = job_post_commentsService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, job_post_comments), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,Job_Post_CommentsEntity job_post_comments, HttpServletRequest request){
        EntityWrapper<Job_Post_CommentsEntity> ew = new EntityWrapper<Job_Post_CommentsEntity>();
		PageUtils page = job_post_commentsService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, job_post_comments), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( Job_Post_CommentsEntity job_post_comments){
       	EntityWrapper<Job_Post_CommentsEntity> ew = new EntityWrapper<Job_Post_CommentsEntity>();
      	ew.allEq(MPUtil.allEQMapPre( job_post_comments, "job_post_comments")); 
        return R.ok().put("data", job_post_commentsService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(Job_Post_CommentsEntity job_post_comments){
        EntityWrapper< Job_Post_CommentsEntity> ew = new EntityWrapper< Job_Post_CommentsEntity>();
 		ew.allEq(MPUtil.allEQMapPre( job_post_comments, "job_post_comments")); 
		Job_Post_CommentsView job_post_commentsView =  job_post_commentsService.selectView(ew);
		return R.ok("查询招聘信息评论表成功").put("data", job_post_commentsView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        Job_Post_CommentsEntity job_post_comments = job_post_commentsService.selectById(id);
        return R.ok().put("data", job_post_comments);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        Job_Post_CommentsEntity job_post_comments = job_post_commentsService.selectById(id);
        return R.ok().put("data", job_post_comments);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Job_Post_CommentsEntity job_post_comments, HttpServletRequest request){
    	job_post_comments.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        job_post_commentsService.insert(job_post_comments);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody Job_Post_CommentsEntity job_post_comments, HttpServletRequest request){
    	job_post_comments.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        job_post_commentsService.insert(job_post_comments);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Job_Post_CommentsEntity job_post_comments, HttpServletRequest request){
        job_post_commentsService.updateById(job_post_comments);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        job_post_commentsService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<Job_Post_CommentsEntity> wrapper = new EntityWrapper<Job_Post_CommentsEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = job_post_commentsService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

}
