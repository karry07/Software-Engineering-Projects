package com.dao;

import com.entity.ApplicationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.vo.ApplicationVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ApplicationView;


/**
 * 应聘信息
 * 
 * @author 
 * @email 

 */
public interface ApplicationDao extends BaseMapper<ApplicationEntity> {
	
	List<ApplicationVO> selectListVO(@Param("ew") Wrapper<ApplicationEntity> wrapper);
	
	ApplicationVO selectVO(@Param("ew") Wrapper<ApplicationEntity> wrapper);
	
	List<ApplicationView> selectListView(@Param("ew") Wrapper<ApplicationEntity> wrapper);

	List<ApplicationView> selectListView(Pagination page, @Param("ew") Wrapper<ApplicationEntity> wrapper);
	
	ApplicationView selectView(@Param("ew") Wrapper<ApplicationEntity> wrapper);
}
