package com.service.impl;

import com.dao.GeneraluserDao;
import com.entity.vo.GeneraluserVO;
import com.service.GeneraluserService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.entity.GeneraluserEntity;
import com.entity.view.GeneraluserView;

@Service("GeneraluserService")
public class GeneraluserServiceImpl extends ServiceImpl<GeneraluserDao, GeneraluserEntity> implements GeneraluserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GeneraluserEntity> page = this.selectPage(
                new Query<GeneraluserEntity>(params).getPage(),
                new EntityWrapper<GeneraluserEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GeneraluserEntity> wrapper) {
		  Page<GeneraluserView> page =new Query<GeneraluserView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<GeneraluserVO> selectListVO(Wrapper<GeneraluserEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public GeneraluserVO selectVO(Wrapper<GeneraluserEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<GeneraluserView> selectListView(Wrapper<GeneraluserEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GeneraluserView selectView(Wrapper<GeneraluserEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
