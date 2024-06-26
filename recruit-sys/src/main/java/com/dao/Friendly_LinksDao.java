package com.dao;

import com.entity.Friendly_LinksEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.Friendly_LinksVO;
import com.entity.view.Friendly_LinksView;


/**
 * 友情链接
 * 
 * @author 
 * @email 

 */
public interface Friendly_LinksDao extends BaseMapper<Friendly_LinksEntity> {
	
	List<Friendly_LinksVO> selectListVO(@Param("ew") Wrapper<Friendly_LinksEntity> wrapper);
	
	Friendly_LinksVO selectVO(@Param("ew") Wrapper<Friendly_LinksEntity> wrapper);
	
	List<Friendly_LinksView> selectListView(@Param("ew") Wrapper<Friendly_LinksEntity> wrapper);

	List<Friendly_LinksView> selectListView(Pagination page,@Param("ew") Wrapper<Friendly_LinksEntity> wrapper);
	
	Friendly_LinksView selectView(@Param("ew") Wrapper<Friendly_LinksEntity> wrapper);
}
