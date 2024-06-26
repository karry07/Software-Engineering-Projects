package com.service.impl;

import com.dao.CompanyinfoDao;
import com.entity.CompanyinfoEntity;
import com.entity.view.CompanyinfoView;
import com.entity.vo.CompanyinfoVO;
import com.service.CompanyinfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


@Service("companyinfoService")
public class CompanyinfoServiceImpl extends ServiceImpl<CompanyinfoDao, CompanyinfoEntity> implements CompanyinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CompanyinfoEntity> page = this.selectPage(
                new Query<CompanyinfoEntity>(params).getPage(),
                new EntityWrapper<CompanyinfoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CompanyinfoEntity> wrapper) {
		  Page<CompanyinfoView> page =new Query<CompanyinfoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<CompanyinfoVO> selectListVO(Wrapper<CompanyinfoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CompanyinfoVO selectVO(Wrapper<CompanyinfoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CompanyinfoView> selectListView(Wrapper<CompanyinfoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CompanyinfoView selectView(Wrapper<CompanyinfoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
