package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.Friendly_LinksEntity;
import com.entity.view.Friendly_LinksView;
import com.service.Friendly_LinksService;
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
 * 友情链接
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/friendly_links")
public class Friendly_LinksController {
    @Autowired
    private Friendly_LinksService friendly_linksService;
    

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,Friendly_LinksEntity friendly_links, HttpServletRequest request){
        EntityWrapper<Friendly_LinksEntity> ew = new EntityWrapper<Friendly_LinksEntity>();
		PageUtils page = friendly_linksService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, friendly_links), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,Friendly_LinksEntity friendly_links, HttpServletRequest request){
        EntityWrapper<Friendly_LinksEntity> ew = new EntityWrapper<Friendly_LinksEntity>();
		PageUtils page = friendly_linksService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, friendly_links), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( Friendly_LinksEntity friendly_links){
       	EntityWrapper<Friendly_LinksEntity> ew = new EntityWrapper<Friendly_LinksEntity>();
      	ew.allEq(MPUtil.allEQMapPre( friendly_links, "friendly_links")); 
        return R.ok().put("data", friendly_linksService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(Friendly_LinksEntity friendly_links){
        EntityWrapper< Friendly_LinksEntity> ew = new EntityWrapper< Friendly_LinksEntity>();
 		ew.allEq(MPUtil.allEQMapPre( friendly_links, "friendly_links")); 
		Friendly_LinksView friendly_linksView =  friendly_linksService.selectView(ew);
		return R.ok("查询友情链接成功").put("data", friendly_linksView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        Friendly_LinksEntity friendly_links = friendly_linksService.selectById(id);
        return R.ok().put("data", friendly_links);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        Friendly_LinksEntity friendly_links = friendly_linksService.selectById(id);
        return R.ok().put("data", friendly_links);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Friendly_LinksEntity friendly_links, HttpServletRequest request){
    	friendly_links.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        friendly_linksService.insert(friendly_links);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody Friendly_LinksEntity friendly_links, HttpServletRequest request){
    	friendly_links.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        friendly_linksService.insert(friendly_links);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Friendly_LinksEntity friendly_links, HttpServletRequest request){
        friendly_linksService.updateById(friendly_links);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        friendly_linksService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<Friendly_LinksEntity> wrapper = new EntityWrapper<Friendly_LinksEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = friendly_linksService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

}
