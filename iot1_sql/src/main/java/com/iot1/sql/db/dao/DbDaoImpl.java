package com.iot1.sql.db.dao;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iot1.sql.common.DataSourceFactory;
import com.iot1.sql.db.dto.Column;
import com.iot1.sql.db.dto.DataBase;
import com.iot1.sql.db.dto.DbInfo;
import com.iot1.sql.db.dto.Table;

@Repository
public class DbDaoImpl extends SqlSessionDaoSupport implements DbDao
{
	@Autowired
	DataSourceFactory dsf;

	@Override
	public DbInfo getDbInfo(DbInfo di) 
	{
		return this.getSqlSession().selectOne("db.DB_SELECT", di);
	}

	@Override
	public List<DbInfo> getDbInfoList(DbInfo di)
	{
		return this.getSqlSession().selectList("db.DB_SELECT", di);
	}

	@Override
	public boolean isConnectDB(DbInfo di) throws Exception
	{
		return dsf.isConnecteDB(di);
	}

	@Override
	public List<DataBase> selectDataBaseList() throws Exception 
	{
		DatabaseMetaData meta = dsf.getSqlSession().getConnection().getMetaData();
		ResultSet ctlgs = meta.getCatalogs();
		List<DataBase> databaseList = new ArrayList<DataBase>();
		while(ctlgs.next())
		{
			DataBase db = new DataBase();
			db.setDatabase(ctlgs.getString(1));
			databaseList.add(db);
		}
		return databaseList;
	}

	@Override
	public List<Table> selectTableList(DataBase database) throws Exception 
	{
		dsf.getSqlSession().selectList("db.USE_DATABASE", database);
		return dsf.getSqlSession().selectList("db.TABLE_SELECT", database);
	}

	@Override
	public List<Column> selectColumnList(Table table) throws Exception
	{
		return this.getSqlSession().selectList("db.COLUMN_SELECT", table);
	}

	@Override
	public Map<String, Object> runSql(Map<String, String> pm) throws Exception
	{
		String sql = pm.get("sql");
		sql = sql.trim();
		Map<String, Object> map = new HashMap<String, Object>();
		Statement statement = dsf.getSqlSession().getConnection().createStatement();
		if(sql.indexOf("select") == 0)
		{
			ResultSet resultSet = statement.executeQuery(sql);
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			List<String> columns = new ArrayList<String>();
			for(int i = 1; i <= columnCount; i++)
			{
				String columnName = metaData.getColumnName(i);
				columns.add(columnName);
			}
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			while(resultSet.next())
			{
				Map<String, String> hm = new HashMap<String, String>();
				for(String column : columns)
				{
					hm.put(column, resultSet.getString(column));
				}
				list.add(hm);
			}
			map.put("type", "select");
			map.put("list", list);
			map.put("columns", columns);
		}
		else
		{
			int result = statement.executeUpdate(sql);
			map.put("type", "save");
			map.put("row", result);
		}
		return map;
	}
	
	
}
