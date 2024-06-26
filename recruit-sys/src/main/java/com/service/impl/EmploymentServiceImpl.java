package com.service.impl;

import com.dao.EmploymentDao;
import com.entity.vo.EmploymentVO;
import com.service.EmploymentService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;

import com.entity.EmploymentEntity;
import com.entity.view.EmploymentView;

@Service("EmploymentService")
public class EmploymentServiceImpl extends ServiceImpl<EmploymentDao, EmploymentEntity> implements EmploymentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EmploymentEntity> page = this.selectPage(
                new Query<EmploymentEntity>(params).getPage(),
                new EntityWrapper<EmploymentEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<EmploymentEntity> wrapper) {
		  Page<EmploymentView> page =new Query<EmploymentView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<EmploymentVO> selectListVO(Wrapper<EmploymentEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public EmploymentVO selectVO(Wrapper<EmploymentEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<EmploymentView> selectListView(Wrapper<EmploymentEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public EmploymentView selectView(Wrapper<EmploymentEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
