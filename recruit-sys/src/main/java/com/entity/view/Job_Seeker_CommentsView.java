package com.entity.view;

import com.entity.Job_Seeker_CommentsEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 求职者信息评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date
 */
@TableName("job_seeker_comments")
public class Job_Seeker_CommentsView  extends Job_Seeker_CommentsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Job_Seeker_CommentsView(){
	}
 
 	public Job_Seeker_CommentsView(Job_Seeker_CommentsEntity job_seeker_commentsEntity){
 	try {
			BeanUtils.copyProperties(this, job_seeker_commentsEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
