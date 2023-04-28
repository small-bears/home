package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QiugoutushuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QiugoutushuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QiugoutushuView;


/**
 * 求购图书
 *
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface QiugoutushuService extends IService<QiugoutushuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QiugoutushuVO> selectListVO(Wrapper<QiugoutushuEntity> wrapper);
   	
   	QiugoutushuVO selectVO(@Param("ew") Wrapper<QiugoutushuEntity> wrapper);
   	
   	List<QiugoutushuView> selectListView(Wrapper<QiugoutushuEntity> wrapper);
   	
   	QiugoutushuView selectView(@Param("ew") Wrapper<QiugoutushuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QiugoutushuEntity> wrapper);
   	

}

