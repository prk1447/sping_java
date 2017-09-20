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
	 public Map<String, Object> runSql(Map<String, Object> pm) throws Exception 
	 {
	      int result = 0;
	      int max = 0;
	      List<List<Map<String, String>>> listlist = new ArrayList<List<Map<String,String>>>();
	      List<List<String>> columnslist = new ArrayList<List<String>>();
	      List<String> sqlname = new ArrayList<String>();
	      List<String> sql;
	      String sqlone= "";

	      String num =(String) pm.get("type");

	      List<String> type = new ArrayList<String>();
	      Map<String, Object> map = new HashMap<String, Object>();
	      if (num.equals("one")) 
	      {
	         sql =new ArrayList<String>();
	         sql.add((String) pm.get("sql"));
	         max = 1;
	      } 
	      else 
	      {
	         sql = (ArrayList<String>) pm.get("sql");
	         max = sql.size()-1;
	      }
	      for (int j = 0; j < max; j++) 
	      {
	         sqlone = sql.get(j).trim();
	         Statement statement = dsf.getSqlSession().getConnection().createStatement();

	         if (sqlone.indexOf("select") == 0||sqlone.indexOf("SELECT") == 0)
	         {
	            sqlname.add(sqlone);
	            type.add("select");
	            ResultSet resultSet = statement.executeQuery(sqlone);
	            ResultSetMetaData metadata = resultSet.getMetaData();
	            int columnCount = metadata.getColumnCount();
	            List<String> columns = new ArrayList<String>();

	            for (int i = 1; i <= columnCount; i++) 
	            {
	               String columnName = metadata.getColumnName(i);
	               columns.add(columnName); 
	            }
	            
	            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	            while (resultSet.next()) 
	            {
	               Map<String, String> hm = new HashMap<String, String>();
	               for (String column : columns) 
	               {
	                  hm.put(column, resultSet.getString(column));
	               }
	               list.add(hm);
	            }
	            columnslist.add(columns);
	            listlist.add(list);
	            map.put("list", listlist);
	            map.put("columns", columnslist);
	            map.put("sqlname", sqlname);
	         } 
	         else 
	         {
	            type.add("save");
	            result += statement.executeUpdate(sqlone);
	            map.put("row", result);
	         }
	      }
	      map.put("type", type);
	      return map;
	   }
}
