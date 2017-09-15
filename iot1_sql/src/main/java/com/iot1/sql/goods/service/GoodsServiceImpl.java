package com.iot1.sql.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot1.sql.goods.dao.GoodsDao;
import com.iot1.sql.goods.dto.GoodsInfo;

@Service
public class GoodsServiceImpl implements GoodsService
{
	@Autowired
	GoodsDao gDao;
	
	@Override
	public GoodsInfo getGoodsInfo(GoodsInfo gi)
	{		
		return gDao.selectGoodsInfo(gi);
	}

	@Override
	public List<GoodsInfo> getGoodsInfoList(GoodsInfo gi)
	{
		return gDao.selectGoodsInfoList(gi);
	}

	@Override
	public int insertGoods(GoodsInfo gi) 
	{
		return gDao.insertGoodsInfoList(gi);
	}

	@Override
	public int insertGoods(GoodsInfo[] goodsList)
	{
		int cnt = 0;
		for(GoodsInfo gi : goodsList)
		{
			cnt += gDao.insertGoodsInfoList(gi);
		}
		return cnt;
	}


	@Override
	public int updateGoods(GoodsInfo goodsList)
	{
		return gDao.updateGoodsInfoList(goodsList);
	}

	@Override
	public int deleteGoods(GoodsInfo goodsList) 
	{
		return gDao.deleteGoodsInfoList(goodsList);
	}
}
