package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TushufenleiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.TushufenleiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.TushufenleiView;


/**
 * 图书分类
 *
 * @author 
 * @email 
 * @date 2022-04-23 15:10:36
 */
public interface TushufenleiService extends IService<TushufenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TushufenleiVO> selectListVO(Wrapper<TushufenleiEntity> wrapper);
   	
   	TushufenleiVO selectVO(@Param("ew") Wrapper<TushufenleiEntity> wrapper);
   	
   	List<TushufenleiView> selectListView(Wrapper<TushufenleiEntity> wrapper);
   	
   	TushufenleiView selectView(@Param("ew") Wrapper<TushufenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TushufenleiEntity> wrapper);
   	

}

