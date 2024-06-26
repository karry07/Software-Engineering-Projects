package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.EmploymentEntity;
import com.entity.view.EmploymentView;
import com.service.EmploymentService;
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
 * 招聘信息
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/employment")
public class EmploymentController {
    @Autowired
    private EmploymentService employmentService;
    

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, EmploymentEntity employment, HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("companyinfo")) {
			employment.setCompanynumber((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<EmploymentEntity> ew = new EntityWrapper<EmploymentEntity>();
		PageUtils page = employmentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew,employment), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
//	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, EmploymentEntity employment, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("companyinfo")) {
			employment.setCompanynumber((String)request.getSession().getAttribute("username"));
		}
//        System.out.println("tablename+"+request.getSession().getAttribute("tableName"));


        EntityWrapper<EmploymentEntity> ew = new EntityWrapper<EmploymentEntity>();
		PageUtils page = employmentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, employment), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( EmploymentEntity employment){
       	EntityWrapper<EmploymentEntity> ew = new EntityWrapper<EmploymentEntity>();
      	ew.allEq(MPUtil.allEQMapPre( employment, "employment"));
        return R.ok().put("data", employmentService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(EmploymentEntity employment){
        EntityWrapper<EmploymentEntity> ew = new EntityWrapper<EmploymentEntity>();
 		ew.allEq(MPUtil.allEQMapPre( employment, "employment"));
		EmploymentView employmentView = employmentService.selectView(ew);
		return R.ok("查询招聘信息成功").put("data", employmentView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        EmploymentEntity employment = employmentService.selectById(id);
        return R.ok().put("data", employment);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        EmploymentEntity employment = employmentService.selectById(id);
        return R.ok().put("data", employment);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        EmploymentEntity employment = employmentService.selectById(id);
        if(type.equals("1")) {
        	employment.setThumbsupnum(employment.getThumbsupnum()+1);
        } else {
        	employment.setCrazilynum(employment.getCrazilynum()+1);
        }
        employmentService.updateById(employment);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody EmploymentEntity employment, HttpServletRequest request){
    	employment.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        employmentService.insert(employment);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody EmploymentEntity employment, HttpServletRequest request){
        employmentService.insert(employment);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody EmploymentEntity employment, HttpServletRequest request){
        employmentService.updateById(employment);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        employmentService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<EmploymentEntity> wrapper = new EntityWrapper<EmploymentEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("companyinfo")) {
			wrapper.eq("companyinfo", (String)request.getSession().getAttribute("username"));
		}

		int count = employmentService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

}
