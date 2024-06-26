package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ApplicationDao;
import com.entity.ApplicationEntity;
import com.entity.view.ApplicationView;
import com.entity.vo.ApplicationVO;
import com.service.ApplicationService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ApplicationService")
public class ApplicationServiceImpl extends ServiceImpl<ApplicationDao, ApplicationEntity> implements ApplicationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {//分页查询
        Page<ApplicationEntity> page = this.selectPage(
                new Query<ApplicationEntity>(params).getPage(),
                new EntityWrapper<ApplicationEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ApplicationEntity> wrapper) {//分页查询
		  Page<ApplicationView> page =new Query<ApplicationView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<ApplicationVO> selectListVO(Wrapper<ApplicationEntity> wrapper) {//查询列表
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ApplicationVO selectVO(Wrapper<ApplicationEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}//查询列表
	
	@Override
	public List<ApplicationView> selectListView(Wrapper<ApplicationEntity> wrapper) {//查询列表
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ApplicationView selectView(Wrapper<ApplicationEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}//查询列表

}
