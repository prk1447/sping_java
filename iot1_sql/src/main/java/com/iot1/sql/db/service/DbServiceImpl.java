package com.iot1.sql.db.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot1.sql.db.dao.DbDao;
import com.iot1.sql.db.dto.Column;
import com.iot1.sql.db.dto.DataBase;
import com.iot1.sql.db.dto.DbInfo;
import com.iot1.sql.db.dto.Table;

@Service
public class DbServiceImpl implements DbService
{
	@Autowired
	DbDao dbDao;

	@Override
	public List<DbInfo> getDbInfoList(DbInfo di) 
	{
		return dbDao.getDbInfoList(di);
	}

	@Override
	public DbInfo getDbInfo(DbInfo di)
	{
		return dbDao.getDbInfo(di);
	}

	@Override
	public boolean isConnecteDB(DbInfo pDi) throws Exception 
	{
		DbInfo di = dbDao.getDbInfo(pDi);
		return dbDao.isConnectDB(di);
	}

	@Override
	public List<DataBase> getDataBaseList() throws Exception 
	{
		return dbDao.selectDataBaseList();
	}

	@Override
	public List<Table> getTableList(DataBase database) throws Exception
	{
		return dbDao.selectTableList(database);
	}

	@Override
	public List<Column> getColumnList(Table table) throws Exception 
	{
		return dbDao.selectColumnList(table);
	}

	@Override
	public Map<String, Object> runSql(Map<String, Object> pm) throws Exception 
	{
		return dbDao.runSql(pm);
	}
}
