package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.MessagesDao;
import com.entity.MessagesEntity;
import com.service.MessagesService;
import com.entity.vo.MessagesVO;
import com.entity.view.MessagesView;

@Service("messagesService")
public class MessagesServiceImpl extends ServiceImpl<MessagesDao, MessagesEntity> implements MessagesService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MessagesEntity> page = this.selectPage(
                new Query<MessagesEntity>(params).getPage(),
                new EntityWrapper<MessagesEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MessagesEntity> wrapper) {
		  Page<MessagesView> page =new Query<MessagesView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<MessagesVO> selectListVO(Wrapper<MessagesEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public MessagesVO selectVO(Wrapper<MessagesEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<MessagesView> selectListView(Wrapper<MessagesEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MessagesView selectView(Wrapper<MessagesEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
