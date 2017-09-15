package com.iot1.sql.goods.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iot1.sql.goods.dto.GoodsInfo;

@Repository
public class GoodsDaoImpl extends SqlSessionDaoSupport implements GoodsDao
{

	@Override
	public GoodsInfo selectGoodsInfo(GoodsInfo gi)
	{
		return this.getSqlSession().selectOne("goods.GOODS_SELECT", gi);
	}

	@Override
	public List<GoodsInfo> selectGoodsInfoList(GoodsInfo gi)
	{
		return this.getSqlSession().selectList("goods.GOODS_SELECT", gi);
	}

	@Override
	public int insertGoodsInfoList(GoodsInfo gi) 
	{
		return this.getSqlSession().insert("goods.GOODS_INSERT", gi);
	}

	@Override
	public int updateGoodsInfoList(GoodsInfo gi)
	{
		return this.getSqlSession().update("goods.GOODS_UPDATE", gi);
	}

	@Override
	public int deleteGoodsInfoList(GoodsInfo gi) 
	{
		return this.getSqlSession().delete("goods.GOODS_DELETE", gi);
	}

}
