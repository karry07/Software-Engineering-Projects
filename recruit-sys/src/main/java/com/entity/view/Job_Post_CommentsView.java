package com.entity.view;

import com.entity.Job_Post_CommentsEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 招聘信息评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date
 */
@TableName("job_post_comments")
public class Job_Post_CommentsView  extends Job_Post_CommentsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Job_Post_CommentsView(){
	}
 
 	public Job_Post_CommentsView(Job_Post_CommentsEntity job_post_commentsEntity){
 	try {
			BeanUtils.copyProperties(this, job_post_commentsEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
