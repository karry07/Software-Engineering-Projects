package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.Friendly_LinksEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.Friendly_LinksVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.Friendly_LinksView;


/**
 * 友情链接
 *
 * @author 
 * @email 
 * @date
 */
public interface Friendly_LinksService extends IService<Friendly_LinksEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<Friendly_LinksVO> selectListVO(Wrapper<Friendly_LinksEntity> wrapper);
   	
   	Friendly_LinksVO selectVO(@Param("ew") Wrapper<Friendly_LinksEntity> wrapper);
   	
   	List<Friendly_LinksView> selectListView(Wrapper<Friendly_LinksEntity> wrapper);
   	
   	Friendly_LinksView selectView(@Param("ew") Wrapper<Friendly_LinksEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<Friendly_LinksEntity> wrapper);
}

