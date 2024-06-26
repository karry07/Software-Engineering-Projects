package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.ForumEntity;
import com.entity.Job_CategoryEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 论坛
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author
 * @email
 * @date
 */
@TableName("forum")
public class ForumView extends ForumEntity implements Serializable {
    	private static final long serialVersionUID = 1L;

	public ForumView(){
	}

 	public ForumView(ForumEntity forumEntity){
 	try {
			BeanUtils.copyProperties(this, forumEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
