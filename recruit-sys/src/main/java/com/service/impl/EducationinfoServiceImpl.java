package com.service.impl;

import com.dao.EducationinfoDao;
import com.entity.EducationinfoEntity;
import com.entity.view.EducationinfoView;
import com.entity.vo.EducationinfoVO;
import com.service.EducationinfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;



@Service("educationinfoService")
public class EducationinfoServiceImpl extends ServiceImpl<EducationinfoDao, EducationinfoEntity> implements EducationinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EducationinfoEntity> page = this.selectPage(
                new Query<EducationinfoEntity>(params).getPage(),
                new EntityWrapper<EducationinfoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<EducationinfoEntity> wrapper) {
		  Page<EducationinfoView> page =new Query<EducationinfoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<EducationinfoVO> selectListVO(Wrapper<EducationinfoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public EducationinfoVO selectVO(Wrapper<EducationinfoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<EducationinfoView> selectListView(Wrapper<EducationinfoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public EducationinfoView selectView(Wrapper<EducationinfoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
