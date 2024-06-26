package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.Job_CategoryDao;
import com.entity.Job_CategoryEntity;
import com.service.Job_CategoryService;
import com.entity.vo.Job_CategoryVO;
import com.entity.view.Job_CategoryView;

@Service("job_categoryService")
public class Job_CategoryServiceImpl extends ServiceImpl<Job_CategoryDao, Job_CategoryEntity> implements Job_CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<Job_CategoryEntity> page = this.selectPage(
                new Query<Job_CategoryEntity>(params).getPage(),
                new EntityWrapper<Job_CategoryEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<Job_CategoryEntity> wrapper) {
		  Page<Job_CategoryView> page =new Query<Job_CategoryView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<Job_CategoryVO> selectListVO(Wrapper<Job_CategoryEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public Job_CategoryVO selectVO(Wrapper<Job_CategoryEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<Job_CategoryView> selectListView(Wrapper<Job_CategoryEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public Job_CategoryView selectView(Wrapper<Job_CategoryEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
