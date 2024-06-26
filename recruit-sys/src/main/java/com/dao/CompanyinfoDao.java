package com.dao;

import com.entity.CompanyinfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.view.CompanyinfoView;
import com.entity.vo.CompanyinfoVO;
import org.apache.ibatis.annotations.Param;


/**
 * 企业信息
 * 
 * @author 
 * @email 
 * @date 2024-05-13 10:02:22
 */
public interface CompanyinfoDao extends BaseMapper<CompanyinfoEntity> {
	
	List<CompanyinfoVO> selectListVO(@Param("ew") Wrapper<CompanyinfoEntity> wrapper);
	
	CompanyinfoVO selectVO(@Param("ew") Wrapper<CompanyinfoEntity> wrapper);
	
	List<CompanyinfoView> selectListView(@Param("ew") Wrapper<CompanyinfoEntity> wrapper);

	List<CompanyinfoView> selectListView(Pagination page,@Param("ew") Wrapper<CompanyinfoEntity> wrapper);
	
	CompanyinfoView selectView(@Param("ew") Wrapper<CompanyinfoEntity> wrapper);
}
