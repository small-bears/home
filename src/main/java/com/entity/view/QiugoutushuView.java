package com.entity.view;

import com.entity.QiugoutushuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 求购图书
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
@TableName("qiugoutushu")
public class QiugoutushuView  extends QiugoutushuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public QiugoutushuView(){
	}
 
 	public QiugoutushuView(QiugoutushuEntity qiugoutushuEntity){
 	try {
			BeanUtils.copyProperties(this, qiugoutushuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
