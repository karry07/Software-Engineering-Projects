package com.dao;

import com.entity.GeneraluserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.vo.GeneraluserVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GeneraluserView;


/**
 * 用户
 * 
 * @author 
 * @email 
 * @date 2024-05-25 10:02:22
 */
public interface GeneraluserDao extends BaseMapper<GeneraluserEntity> {
	
	List<GeneraluserVO> selectListVO(@Param("ew") Wrapper<GeneraluserEntity> wrapper);
	
	GeneraluserVO selectVO(@Param("ew") Wrapper<GeneraluserEntity> wrapper);
	
	List<GeneraluserView> selectListView(@Param("ew") Wrapper<GeneraluserEntity> wrapper);

	List<GeneraluserView> selectListView(Pagination page, @Param("ew") Wrapper<GeneraluserEntity> wrapper);
	
	GeneraluserView selectView(@Param("ew") Wrapper<GeneraluserEntity> wrapper);
}
