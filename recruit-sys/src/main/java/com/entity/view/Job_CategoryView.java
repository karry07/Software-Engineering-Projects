package com.entity.view;

import com.entity.Job_CategoryEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 岗位分类
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date
 */
@TableName("job_category")
public class Job_CategoryView  extends Job_CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Job_CategoryView(){
	}
 
 	public Job_CategoryView(Job_CategoryEntity job_categoryEntity){
 	try {
			BeanUtils.copyProperties(this, job_categoryEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
