package com.dao;

import com.entity.Job_CategoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.Job_CategoryVO;
import com.entity.view.Job_CategoryView;


/**
 * 岗位分类
 * 
 * @author 
 * @email 

 */
public interface Job_CategoryDao extends BaseMapper<Job_CategoryEntity> {
	
	List<Job_CategoryVO> selectListVO(@Param("ew") Wrapper<Job_CategoryEntity> wrapper);
	
	Job_CategoryVO selectVO(@Param("ew") Wrapper<Job_CategoryEntity> wrapper);
	
	List<Job_CategoryView> selectListView(@Param("ew") Wrapper<Job_CategoryEntity> wrapper);

	List<Job_CategoryView> selectListView(Pagination page,@Param("ew") Wrapper<Job_CategoryEntity> wrapper);
	
	Job_CategoryView selectView(@Param("ew") Wrapper<Job_CategoryEntity> wrapper);
}
