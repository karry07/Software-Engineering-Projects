package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.GeneraluserEntity;
import com.entity.view.GeneraluserView;
import com.service.GeneraluserService;
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
 * 用户
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@CrossOrigin(origins = {"http://127.0.0.1:8082", "http://127.0.0.1:8081"})
@RestController
@RequestMapping("/generaluser")
public class GeneraluserController {
    @Autowired
    private GeneraluserService generaluserService;
    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		GeneraluserEntity user = generaluserService.selectOne(new EntityWrapper<GeneraluserEntity>().eq("username", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(user.getId(),username, "generaluser", "用户");
		return R.ok().put("token", token);
	}
	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody GeneraluserEntity generaluser){
    	GeneraluserEntity user = generaluserService.selectOne(new EntityWrapper<GeneraluserEntity>().eq("username", generaluser.getUsername()));
		if(user!=null) {
			return R.error("注册用户已存在");
		}
		generaluser.setId(new Date().getTime());
        generaluserService.insert(generaluser);
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
        GeneraluserEntity user = generaluserService.selectById(id);
        return R.ok().put("data", user);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	GeneraluserEntity user = generaluserService.selectOne(new EntityWrapper<GeneraluserEntity>().eq("username", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}
    	user.setPassword("123456");
        generaluserService.updateById(user);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, GeneraluserEntity generaluser, HttpServletRequest request){
        EntityWrapper<GeneraluserEntity> ew = new EntityWrapper<GeneraluserEntity>();
		PageUtils page = generaluserService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, generaluser), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, GeneraluserEntity generaluser, HttpServletRequest request){
        EntityWrapper<GeneraluserEntity> ew = new EntityWrapper<GeneraluserEntity>();
		PageUtils page = generaluserService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, generaluser), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GeneraluserEntity generaluser){
       	EntityWrapper<GeneraluserEntity> ew = new EntityWrapper<GeneraluserEntity>();
      	ew.allEq(MPUtil.allEQMapPre( generaluser, "generaluser"));
        return R.ok().put("data", generaluserService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GeneraluserEntity generaluser){
        EntityWrapper<GeneraluserEntity> ew = new EntityWrapper<GeneraluserEntity>();
 		ew.allEq(MPUtil.allEQMapPre( generaluser, "generaluser"));
		GeneraluserView generaluserView =  generaluserService.selectView(ew);
		return R.ok("查询用户成功").put("data", generaluserView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        GeneraluserEntity generaluser = generaluserService.selectById(id);
        return R.ok().put("data", generaluser);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        GeneraluserEntity generaluser = generaluserService.selectById(id);
        return R.ok().put("data", generaluser);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GeneraluserEntity generaluser, HttpServletRequest request){
    	generaluser.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	GeneraluserEntity user = generaluserService.selectOne(new EntityWrapper<GeneraluserEntity>().eq("generaluser", generaluser.getUsername()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		generaluser.setId(new Date().getTime());
        generaluserService.insert(generaluser);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
	@IgnoreAuth
    @RequestMapping("/add")
    public R add(@RequestBody GeneraluserEntity generaluser, HttpServletRequest request){
    	generaluser.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	GeneraluserEntity user = generaluserService.selectOne(new EntityWrapper<GeneraluserEntity>().eq("generaluser", generaluser.getUsername()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		generaluser.setId(new Date().getTime());
        generaluserService.insert(generaluser);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GeneraluserEntity generaluser, HttpServletRequest request){
        generaluserService.updateById(generaluser);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        generaluserService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<GeneraluserEntity> wrapper = new EntityWrapper<GeneraluserEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = generaluserService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	

}
