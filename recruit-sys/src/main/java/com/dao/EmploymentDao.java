package com.dao;

import com.entity.EmploymentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.vo.EmploymentVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.EmploymentView;


/**
 * 招聘信息
 * 
 * @author 
 * @email 

 */
public interface EmploymentDao extends BaseMapper<EmploymentEntity> {
	
	List<EmploymentVO> selectListVO(@Param("ew") Wrapper<EmploymentEntity> wrapper);
	
	EmploymentVO selectVO(@Param("ew") Wrapper<EmploymentEntity> wrapper);
	
	List<EmploymentView> selectListView(@Param("ew") Wrapper<EmploymentEntity> wrapper);

	List<EmploymentView> selectListView(Pagination page, @Param("ew") Wrapper<EmploymentEntity> wrapper);
	
	EmploymentView selectView(@Param("ew") Wrapper<EmploymentEntity> wrapper);
}
