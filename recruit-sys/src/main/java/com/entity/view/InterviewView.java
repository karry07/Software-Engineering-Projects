package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.InterviewEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@TableName("interview")
public class InterviewView extends InterviewEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public InterviewView(){
    }

  public InterviewView(InterviewEntity interviewEntity){
  	try {
            BeanUtils.copyProperties(this, interviewEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
