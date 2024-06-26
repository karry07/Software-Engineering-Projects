package com.dao;

import com.entity.JobseekerinfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.view.JobseekerinfoView;
import com.entity.vo.JobseekerinfoVO;
import org.apache.ibatis.annotations.Param;


/**
 * 求职者信息
 * 
 * @author 
 * @email 

 */
public interface JobseekerinfoDao extends BaseMapper<JobseekerinfoEntity> {
	
	List<JobseekerinfoVO> selectListVO(@Param("ew") Wrapper<JobseekerinfoEntity> wrapper);
	
	JobseekerinfoVO selectVO(@Param("ew") Wrapper<JobseekerinfoEntity> wrapper);
	
	List<JobseekerinfoView> selectListView(@Param("ew") Wrapper<JobseekerinfoEntity> wrapper);

	List<JobseekerinfoView> selectListView(Pagination page,@Param("ew") Wrapper<JobseekerinfoEntity> wrapper);
	
	JobseekerinfoView selectView(@Param("ew") Wrapper<JobseekerinfoEntity> wrapper);
}
