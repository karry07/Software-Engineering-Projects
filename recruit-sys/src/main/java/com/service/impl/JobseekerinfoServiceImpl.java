package com.service.impl;

import com.dao.JobseekerinfoDao;
import com.entity.JobseekerinfoEntity;
import com.entity.view.JobseekerinfoView;
import com.entity.vo.JobseekerinfoVO;
import com.service.JobseekerinfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;



@Service("jobseekerinfoService")
public class JobseekerinfoServiceImpl extends ServiceImpl<JobseekerinfoDao, JobseekerinfoEntity> implements JobseekerinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JobseekerinfoEntity> page = this.selectPage(
                new Query<JobseekerinfoEntity>(params).getPage(),
                new EntityWrapper<JobseekerinfoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JobseekerinfoEntity> wrapper) {
		  Page<JobseekerinfoView> page =new Query<JobseekerinfoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<JobseekerinfoVO> selectListVO(Wrapper<JobseekerinfoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JobseekerinfoVO selectVO(Wrapper<JobseekerinfoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JobseekerinfoView> selectListView(Wrapper<JobseekerinfoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JobseekerinfoView selectView(Wrapper<JobseekerinfoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
