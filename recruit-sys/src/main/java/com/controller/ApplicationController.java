package com.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.ApplicationEntity;
import com.entity.view.ApplicationView;
import com.service.ApplicationService;
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
 * 应聘信息
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ApplicationEntity application, HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("companyinfo")) {
			application.setCompanynumber((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("generaluser")) {
			application.setUsername((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ApplicationEntity> ew = new EntityWrapper<ApplicationEntity>();
		PageUtils page = applicationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, application), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, ApplicationEntity application, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("companyinfo")) {
			application.setCompanynumber((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("generaluser")) {
			application.setUsername((String)request.getSession().getAttribute("username"));
		}
		EntityWrapper<ApplicationEntity> ew = new EntityWrapper<ApplicationEntity>();
		PageUtils page = applicationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, application), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ApplicationEntity application){
       	EntityWrapper<ApplicationEntity> ew = new EntityWrapper<ApplicationEntity>();
      	ew.allEq(MPUtil.allEQMapPre( application, "application"));
        return R.ok().put("data", applicationService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ApplicationEntity application){
        EntityWrapper<ApplicationEntity> ew = new EntityWrapper<ApplicationEntity>();
 		ew.allEq(MPUtil.allEQMapPre( application, "application"));
		ApplicationView applicationView =  applicationService.selectView(ew);
		return R.ok("查询应聘信息成功").put("data", applicationView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        ApplicationEntity application = applicationService.selectById(id);
        return R.ok().put("data", application);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        ApplicationEntity application = applicationService.selectById(id);
        return R.ok().put("data", application);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ApplicationEntity application, HttpServletRequest request){
    	application.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        applicationService.insert(application);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ApplicationEntity application, HttpServletRequest request){
    	application.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        applicationService.insert(application);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ApplicationEntity application, HttpServletRequest request){
        applicationService.updateById(application);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        applicationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
	 * 审核
	 */
	@RequestMapping("/audit")
	public R audit(@RequestBody ApplicationEntity application, HttpServletRequest request){
		// 验证是否包含id
		if(application.getId() == null){
			return R.error("缺少参数ID");
		}
		// 验证是否包含审核信息
		if(application.getIfaudit() == null || application.getAuditresponse() == null){
			return R.error("缺少审核信息");
		}
		// 设置状态为已审核
		application.setStatus("audited");
		// 更新应聘信息
		applicationService.updateById(application);
		return R.ok();
	}
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
						 @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
		// 将列名和类型添加到map中
		map.put("column", columnName);
		map.put("type", type);

		// 如果类型为2，处理提醒开始和结束日期
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart") != null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH, remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend") != null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH, remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}

		// 创建查询条件包装器
		Wrapper<ApplicationEntity> wrapper = new EntityWrapper<ApplicationEntity>();
		if(map.get("remindstart") != null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend") != null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		// 获取表名并根据表名设置查询条件
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("companyinfo")) {
			wrapper.eq("companynumber", (String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("generaluser")) {
			wrapper.eq("username", (String)request.getSession().getAttribute("username"));
		}

		// 查询符合条件的记录数并返回结果
		int count = applicationService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

}
