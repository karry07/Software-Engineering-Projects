package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.JobseekerinfoEntity;
import com.entity.view.JobseekerinfoView;
import com.service.JobseekerinfoService;
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
 * 求职者信息
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/jobseekerinfo")
public class JobseekerinfoController {
    @Autowired
    private JobseekerinfoService jobseekerinfoService;
    

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, JobseekerinfoEntity jobseekerinfo, HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("generaluser")) {
			jobseekerinfo.setUsername((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JobseekerinfoEntity> ew = new EntityWrapper<JobseekerinfoEntity>();
		PageUtils page = jobseekerinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jobseekerinfo), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
//	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JobseekerinfoEntity jobseekerinfo, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();

        if(tableName.equals("generaluser")) {
            jobseekerinfo.setUsername((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<JobseekerinfoEntity> ew = new EntityWrapper<JobseekerinfoEntity>();
		PageUtils page = jobseekerinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jobseekerinfo), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JobseekerinfoEntity jobseekerinfo){
       	EntityWrapper<JobseekerinfoEntity> ew = new EntityWrapper<JobseekerinfoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jobseekerinfo, "jobseekerinfo"));
        return R.ok().put("data", jobseekerinfoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JobseekerinfoEntity jobseekerinfo){
        EntityWrapper< JobseekerinfoEntity> ew = new EntityWrapper< JobseekerinfoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jobseekerinfo, "jobseekerinfo"));
		JobseekerinfoView jobseekerinfoView = jobseekerinfoService.selectView(ew);
		return R.ok("查询求职者信息成功").put("data", jobseekerinfoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        JobseekerinfoEntity jobseekerinfo = jobseekerinfoService.selectById(id);
        return R.ok().put("data", jobseekerinfo);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        JobseekerinfoEntity jobseekerinfo = jobseekerinfoService.selectById(id);
        return R.ok().put("data", jobseekerinfo);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        JobseekerinfoEntity jobseekerinfo = jobseekerinfoService.selectById(id);
        if(type.equals("1")) {
        	jobseekerinfo.setThumbsupnum(jobseekerinfo.getThumbsupnum()+1);
        } else {
        	jobseekerinfo.setCrazilynum(jobseekerinfo.getCrazilynum()+1);
        }
        jobseekerinfoService.updateById(jobseekerinfo);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JobseekerinfoEntity jobseekerinfo, HttpServletRequest request){
    	jobseekerinfo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        jobseekerinfoService.insert(jobseekerinfo);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JobseekerinfoEntity jobseekerinfo, HttpServletRequest request){
    	jobseekerinfo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        jobseekerinfoService.insert(jobseekerinfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JobseekerinfoEntity jobseekerinfo, HttpServletRequest request){
        jobseekerinfoService.updateById(jobseekerinfo);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jobseekerinfoService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<JobseekerinfoEntity> wrapper = new EntityWrapper<JobseekerinfoEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("generaluser")) {
			wrapper.eq("username", (String)request.getSession().getAttribute("username"));
		}

		int count = jobseekerinfoService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

}
