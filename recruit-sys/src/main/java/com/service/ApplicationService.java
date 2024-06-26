package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.ApplicationEntity;
import com.entity.view.ApplicationView;
import com.entity.vo.ApplicationVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 应聘信息
 *
 * @author 
 * @email 
 * @date
 */
public interface ApplicationService extends IService<ApplicationEntity> {

    // 分页查询
	PageUtils queryPage(Map<String, Object> params);

	// 查询列表
   	List<ApplicationVO> selectListVO(Wrapper<ApplicationEntity> wrapper);

	// 查询单个
   	ApplicationVO selectVO(@Param("ew") Wrapper<ApplicationEntity> wrapper);

	// 查询列表
   	List<ApplicationView> selectListView(Wrapper<ApplicationEntity> wrapper);

	// 查询单个
   	ApplicationView selectView(@Param("ew") Wrapper<ApplicationEntity> wrapper);

	// 分页查询
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ApplicationEntity> wrapper);
}

