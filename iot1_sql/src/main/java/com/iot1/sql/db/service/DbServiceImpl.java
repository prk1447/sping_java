package com.iot1.sql.db.service;

import java.util.HashMap;
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


//	select dinum, uinum, dbtitle, dburl, dbms, id, pwd, port, drivername
//	from db_info
//	where 1=1
//	<if test="diNum != 0">
//		and dinum = #{diNum}
//	</if>
//	order by dinum desc
	
	private String makeSelectSql(List<Column> colList, String tableName){
		String xml = " select  \r\n";
		String whereXml = " where 1=1 \r\n";
		String orderByXml = "";
		for(Column c : colList){
			if(c.getColumnKey()!=null && c.getColumnKey().equals("PRI")){
				orderByXml = " order by " +c.getColumnName();
			}
			xml += c.getColumnName() + ", \r\n";
			String conditionValue = "null";
			if(c.getDataType().equals("int")){
				conditionValue = "0";
			}
			whereXml += "<if test=\"" + c.getColumnKey() +" != " + conditionValue + "\"> \r\n";
			whereXml += " and " + c.getColumnName() + " = #{" + c.getColumnName() + "} \r\n ";
			whereXml += "</if> \r\n";
		}
		xml = xml.substring(0, xml.length()-1);
		xml += " from " + tableName + whereXml + orderByXml;
		return xml;
	}

	private String makeInsertSql(List<Column> colList, String tableName){
		String xml = " insert into " + tableName + " \r\n(";
		String valueXml = "";
		for(Column c : colList){
			xml += c.getColumnName() + ", \r\n";
			valueXml += "#{" + c.getColumnName() + "}, \r\n";
		}
		xml = xml.substring(0, xml.length()-1);
		xml += ") values (";
		valueXml  = valueXml.substring(0, valueXml.length()-1);
		xml += valueXml +")";
		return xml;
	}

	private String makeUpdateSql(List<Column> colList, String tableName){
		return null;
	}

	private String makeDeleteSql(List<Column> colList, String tableName){
		String xml = " delete fromt " + tableName;
		String keyColumn = "";
		for(Column c : colList){
			if(c.getColumnKey()!=null && c.getColumnKey().equals("PRI")){
				keyColumn = c.getColumnName();
			}
		}
		if(!keyColumn.equals("")){
			xml += " \r\n where " + keyColumn + "#{" + keyColumn + "}";
		}
		return xml;
	}
	@Override
	public Map<String,Map<String, String>> getMakeXml(DataBase database) throws Exception {
		Map<String,Map<String, String>> xmlMap = new HashMap<String,Map<String, String>>();
		List<Table> tableList = dbDao.selectTableList(database);
		for(Table t : tableList){
			List<Column> colList = dbDao.selectColumnList(t);
			Map<String, String> tableMap = new HashMap<String,String>();
			tableMap.put("select", makeSelectSql(colList, t.getTableName()));
			tableMap.put("insert", makeInsertSql(colList, t.getTableName()));
			tableMap.put("update", makeUpdateSql(colList, t.getTableName()));
			tableMap.put("delete", makeDeleteSql(colList, t.getTableName()));
			xmlMap.put(t.getTableName(), tableMap);
		}
		return xmlMap;
	}
}
