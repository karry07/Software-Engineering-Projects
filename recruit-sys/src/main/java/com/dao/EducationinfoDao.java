package com.dao;

import com.entity.EducationinfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.view.EducationinfoView;
import com.entity.vo.EducationinfoVO;
import org.apache.ibatis.annotations.Param;


/**
 * 学历信息
 * 
 * @author 
 * @email 

 */
public interface EducationinfoDao extends BaseMapper<EducationinfoEntity> {
	
	List<EducationinfoVO> selectListVO(@Param("ew") Wrapper<EducationinfoEntity> wrapper);
	
	EducationinfoVO selectVO(@Param("ew") Wrapper<EducationinfoEntity> wrapper);
	
	List<EducationinfoView> selectListView(@Param("ew") Wrapper<EducationinfoEntity> wrapper);

	List<EducationinfoView> selectListView(Pagination page,@Param("ew") Wrapper<EducationinfoEntity> wrapper);
	
	EducationinfoView selectView(@Param("ew") Wrapper<EducationinfoEntity> wrapper);
}
