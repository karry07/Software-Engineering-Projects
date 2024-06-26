package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.Job_CategoryEntity;
import com.entity.view.Job_CategoryView;
import com.service.Job_CategoryService;
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
 * 岗位分类
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/job_category")
public class Job_CategoryController {
    @Autowired
    private Job_CategoryService job_categoryService;

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,Job_CategoryEntity job_category, HttpServletRequest request){
        EntityWrapper<Job_CategoryEntity> ew = new EntityWrapper<Job_CategoryEntity>();
		PageUtils page = job_categoryService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, job_category), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,Job_CategoryEntity job_category, HttpServletRequest request){
        EntityWrapper<Job_CategoryEntity> ew = new EntityWrapper<Job_CategoryEntity>();
		PageUtils page = job_categoryService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, job_category), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( Job_CategoryEntity job_category){
       	EntityWrapper<Job_CategoryEntity> ew = new EntityWrapper<Job_CategoryEntity>();
      	ew.allEq(MPUtil.allEQMapPre( job_category, "job_category")); 
        return R.ok().put("data", job_categoryService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(Job_CategoryEntity job_category){
        EntityWrapper< Job_CategoryEntity> ew = new EntityWrapper< Job_CategoryEntity>();
 		ew.allEq(MPUtil.allEQMapPre( job_category, "job_category")); 
		Job_CategoryView job_categoryView =  job_categoryService.selectView(ew);
		return R.ok("查询岗位分类成功").put("data", job_categoryView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        Job_CategoryEntity job_category = job_categoryService.selectById(id);
        return R.ok().put("data", job_category);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        Job_CategoryEntity job_category = job_categoryService.selectById(id);
        return R.ok().put("data", job_category);
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Job_CategoryEntity job_category, HttpServletRequest request){
    	job_category.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        job_categoryService.insert(job_category);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody Job_CategoryEntity job_category, HttpServletRequest request){
    	job_category.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        job_categoryService.insert(job_category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Job_CategoryEntity job_category, HttpServletRequest request){
        job_categoryService.updateById(job_category);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        job_categoryService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<Job_CategoryEntity> wrapper = new EntityWrapper<Job_CategoryEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = job_categoryService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

}
