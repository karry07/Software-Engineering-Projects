package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.CompanyinfoEntity;
import com.entity.view.CompanyinfoView;
import com.service.CompanyinfoService;
import com.service.TokenService;
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
 * 企业信息
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@CrossOrigin(origins = {"http://127.0.0.1:8082", "http://127.0.0.1:8081"})
@RestController
@RequestMapping("/companyinfo")
public class CompanyinfoController {
    @Autowired
    private CompanyinfoService companyinfoService;
    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		CompanyinfoEntity user = companyinfoService.selectOne(new EntityWrapper<CompanyinfoEntity>().eq("companynumber", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(user.getId(),username, "companyinfo", "企业信息");
		return R.ok().put("token", token);
	}
	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody CompanyinfoEntity companyinfo){
    	CompanyinfoEntity user = companyinfoService.selectOne(new EntityWrapper<CompanyinfoEntity>().eq("companynumber", companyinfo.getCompanynumber()));
		if(user!=null) {
			return R.error("注册用户已存在");
		}
		companyinfo.setId(new Date().getTime());
        companyinfoService.insert(companyinfo);
        return R.ok();
    }
	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        CompanyinfoEntity user = companyinfoService.selectById(id);
        return R.ok().put("data", user);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	CompanyinfoEntity user = companyinfoService.selectOne(new EntityWrapper<CompanyinfoEntity>().eq("companynumber", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}
    	user.setPassword("123456");
        companyinfoService.updateById(user);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CompanyinfoEntity companyinfo, HttpServletRequest request){
        EntityWrapper<CompanyinfoEntity> ew = new EntityWrapper<CompanyinfoEntity>();
		PageUtils page = companyinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, companyinfo), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,CompanyinfoEntity companyinfo, HttpServletRequest request){
        EntityWrapper<CompanyinfoEntity> ew = new EntityWrapper<CompanyinfoEntity>();
		PageUtils page = companyinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, companyinfo), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CompanyinfoEntity companyinfo){
       	EntityWrapper<CompanyinfoEntity> ew = new EntityWrapper<CompanyinfoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( companyinfo, "companyinfo"));
        return R.ok().put("data", companyinfoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CompanyinfoEntity companyinfo){
        EntityWrapper< CompanyinfoEntity> ew = new EntityWrapper< CompanyinfoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( companyinfo, "companyinfo"));
		CompanyinfoView companyinfoView =  companyinfoService.selectView(ew);
		return R.ok("查询企业信息成功").put("data", companyinfoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        CompanyinfoEntity companyinfo = companyinfoService.selectById(id);
        return R.ok().put("data", companyinfo);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        CompanyinfoEntity companyinfo = companyinfoService.selectById(id);
        return R.ok().put("data", companyinfo);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CompanyinfoEntity companyinfo, HttpServletRequest request){
    	companyinfo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	CompanyinfoEntity user = companyinfoService.selectOne(new EntityWrapper<CompanyinfoEntity>().eq("companynumber", companyinfo.getCompanynumber()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		companyinfo.setId(new Date().getTime());
        companyinfoService.insert(companyinfo);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CompanyinfoEntity companyinfo, HttpServletRequest request){
    	companyinfo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	CompanyinfoEntity user = companyinfoService.selectOne(new EntityWrapper<CompanyinfoEntity>().eq("companynumber", companyinfo.getCompanynumber()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		companyinfo.setId(new Date().getTime());
        companyinfoService.insert(companyinfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CompanyinfoEntity companyinfo, HttpServletRequest request){
        companyinfoService.updateById(companyinfo);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        companyinfoService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<CompanyinfoEntity> wrapper = new EntityWrapper<CompanyinfoEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = companyinfoService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

}
