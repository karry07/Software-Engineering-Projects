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


import com.dao.Friendly_LinksDao;
import com.entity.Friendly_LinksEntity;
import com.service.Friendly_LinksService;
import com.entity.vo.Friendly_LinksVO;
import com.entity.view.Friendly_LinksView;

@Service("friendly_linksService")
public class Friendly_LinksServiceImpl extends ServiceImpl<Friendly_LinksDao, Friendly_LinksEntity> implements Friendly_LinksService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<Friendly_LinksEntity> page = this.selectPage(
                new Query<Friendly_LinksEntity>(params).getPage(),
                new EntityWrapper<Friendly_LinksEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<Friendly_LinksEntity> wrapper) {
		  Page<Friendly_LinksView> page =new Query<Friendly_LinksView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<Friendly_LinksVO> selectListVO(Wrapper<Friendly_LinksEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public Friendly_LinksVO selectVO(Wrapper<Friendly_LinksEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<Friendly_LinksView> selectListView(Wrapper<Friendly_LinksEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public Friendly_LinksView selectView(Wrapper<Friendly_LinksEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
