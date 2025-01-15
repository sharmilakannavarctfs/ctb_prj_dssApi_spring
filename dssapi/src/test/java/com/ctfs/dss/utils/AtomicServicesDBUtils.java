package com.ctfs.dss.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Reporter;

import com.ctfs.common.utils.ApplicationProperties;


public class AtomicServicesDBUtils  {
	private static Connection connection;
	static Statement statement;
	static ResultSet recordSet;

	static String dev_d9ctbora01 = "jdbc:oracle:thin:@d9ctbora01:4150:olt1ddb app_instcr olt1dappinstcr";
	static String Qa_s9cb00oltdb01 =  "jdbc:oracle:thin:@s9cb00oltdb01:4150:olt1ppdb app_instcr_23 0LtIPPdb_YiPk98*vB!_0423";

	static String Dev_userProfile_service = "jdbc:mysql://t9dsdb02.ctal.ctc:3306/dss?verifyServerCertificate=false&useSSL=false&requireSSL=false&autoReconnect=true&serverTimezone=EST5EDT dss_user hXFImWAFCOlIUikS";
	static String QA_userProfile_service = "jdbc:mysql://Q9DSDB-VIP.ctal.ctc:3306/dss?verifyServerCertificate=false&useSSL=false&requireSSL=false&autoReconnect=true&serverTimezone=EST5EDT dss_user R$sx$kp[";


	public static ApplicationProperties propertyLoader = new ApplicationProperties();
	


	public static void connectToDb(String db) throws SQLException {
		String[] db_Creds = null;
		String[] db_env = db.split("-",2);
		//		for (String string : db_env) {
		//			System.out.println("Connections Strings "+string);
		//		}
		if(propertyLoader.getEnvironment().equals("d9cbwpdssa01"))//  d9cbwpdssa01
		{
			Reporter.log("Connecting to dev " + db_env[0],true);
			db_Creds = db_env[0].split(",");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			Reporter.log("Connecting to QA " + db_env[1],true);

			db_Creds = db_env[1].split(",");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		//		connection = DriverManager.getConnection("jdbc:oracle:thin:@d9ctbora04:4200:css1tdb","sdd","sdd0123");
		connection = DriverManager.getConnection(db_Creds[0], db_Creds[1], db_Creds[2]);
		statement = connection.createStatement();
	}
	
	

//	public static void connectToDbMiscFunction() {
//		Connection connection = com.ctfs.dss.utils.ConnectionHelper.getConnection(DataBase.q9dsdb);
//		try {
//			statement = connection.createStatement();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		/*Loyalty,
//	PDUser,
//	TEST01D,
//	InstantCredit,
//	WCC1PPDB,
//	CCSPPJX,
//	q9dsdb,
//	PDUser1*/
//	}
//
//	static void connectToDbMobCon(String db) throws SQLException 
//	{
//		String[] db_Creds = null;
//		String[] db_env = db.split("%"); //db_env[0]-> dev  db_env[1]-> qa
//		if(EnvLoader.getInstance().getEnvironmentMobCon().contains("q9dssa-avip.ctal.ctc:8443"))
//		{
//			db_Creds = db_env[1].split(","); //1
//		}
//
//		else 
//		{
//			db_Creds = db_env[0].split(",");
//			System.out.println("in else");
//			System.out.println(db_Creds);
//		}
//
//		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//		connection = DriverManager.getConnection(db_Creds[0], db_Creds[1], db_Creds[2]);
//		statement = connection.createStatement();
//	}

	public static String runCustomQuery(String db, String query, String column) {
		String record="";
		try{
			connectToDb(db);
			System.out.println(query);
			recordSet = statement.executeQuery(query);
			recordSet.next();

			if(column!=null)
				record = recordSet.getString(column);
				System.out.println(record);

		} catch(SQLException e) {
			e.printStackTrace();
			return "no records found";
		}
		finally {
			try {
				recordSet.close();
				statement.close();
				connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return record;	
	}

	/**
	 * @param db
	 * @param query - update query
	 * @return  
	 */
	public static String runupdateQuery(String db, String query) 
	{
		String record="";
		try{
			connectToDb(db);
			System.out.println(query);
			connection.setAutoCommit(true);
			int updated = statement.executeUpdate(query);
			System.out.println(updated+" rows updated");
			if(updated==0)
				throw new SQLException("No Records are updated");
		} catch(SQLException e) {
			e.printStackTrace();
			return "no records found";
		}
		finally {
			try {
				statement.close();
				connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return record;

	}

//	public static String getMobConRecord(String db,String tableName, String prim_col, String prim_val, String column, String filter) {
//		String record = null;
//		try {
//
//			String query = "SELECT * from " + tableName + " where  " + prim_col + " = '" + prim_val + "' " ;
//			System.out.println(query);
//			connectToDbMobCon(db);
//
//			recordSet = statement.executeQuery(query);
//			recordSet.next();
//			if (column == null)
//			{
//				record = recordSet.getString(prim_col);
//
//				System.out.println(record);
//			}
//
//			else {
//				record = recordSet.getString(column);
//				System.out.println(record);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				recordSet.close();
//				statement.close();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return record;
//
//	}

	public static String getAuditRecord(String db, String tableName, String primaryColumn, 
			String primaryColumn_value, String column, String filter) {
		String record = null;
		try {
			String query = "select JSONREQUEST, JSONRESPONSE, METHOD from " + tableName + " where  " + primaryColumn + " = '" + primaryColumn_value + "'" + " and ROWNUM=1 " + "order by " + filter;
						System.out.println(query);
			connectToDb(db);
			recordSet = statement.executeQuery(query);
			recordSet.next();

			if (column == null) {
				record = recordSet.getString(primaryColumn);
				System.out.println(record);
			}

			else
				record = recordSet.getString(column);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				recordSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return record;

	}

	/**
	 * @param db
	 * @param tableName
	 * @param primaryColumn
	 * @param primaryColumn_value
	 * @param column
	 * @return String value of the column
	 */
	public static String getRecord(String db, String tableName, String primaryColumn, String primaryColumn_value, String column) {
		String record = null;
		try {
			String query = "select * from " + tableName + " where  " + primaryColumn + " = '" + primaryColumn_value + "'";
						System.out.println(query);
			connectToDb(db);
//			System.out.println(query);
			recordSet = statement.executeQuery(query);
//			ResultSetMetaData rsmd = recordSet.getMetaData();
//			int columnsNumber = rsmd.getColumnCount();
//			while (recordSet.next()) {
//			    for (int i = 1; i <= columnsNumber; i++) {
//			        if (i > 1) System.out.print(",  ");
//			        String columnValue = recordSet.getString(i);
//			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
//			    }
//			    System.out.println("");
//			}
			recordSet.next();

			if (column == null) {
				record = recordSet.getString(primaryColumn);
				System.out.println(record);
				
			}

			else
				{record = recordSet.getString(column);
				System.out.println(record);}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				recordSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return record;

	}
	
	public static List<String> getRecords(String db, String tableName, String primaryColumn, String primaryColumn_value, String column) {
		List<String> list = new ArrayList<>();
		try {
			String query = "select * from " + tableName + " where  " + primaryColumn + " = '" + primaryColumn_value + "'";
			//			System.out.println(query);
			connectToDb(db);
			System.out.println(query);
			recordSet = statement.executeQuery(query);
			//recordSet.next();

			if (column == null) 
			{
				 list.add(recordSet.getString(primaryColumn));
				System.out.println(list);	
			}
			else
			{
				while(recordSet.next())
				{
					list.add(recordSet.getString(column));
				}
			}
						
			} catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try {
				recordSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	/**
	 * @param db
	 * @param tableName
	 * @param primaryColumn
	 * @param primaryColumn_value
	 * @param column
	 * @return String value of the column
	 * 
	 */
	public static String getColumnRecord(String db, String tableName, String primaryColumn, String primaryColumn_value, String column) {
		String record = null;
		try {
			String query = "select "+ column +" from " + tableName + " where  " + primaryColumn + " = '" + primaryColumn_value + "'";
			//			System.out.println(query);
			connectToDb(db);
			System.out.println(query);
			recordSet = statement.executeQuery(query);
			recordSet.next();

			if (column == null) {
				record = recordSet.getString(primaryColumn);
				System.out.println(record);
			}

			else
				record = recordSet.getString(column);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				recordSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return record;

	}
	
	public static String getColumnRec(String db, String column, String tableName) {
		String record = null;
		try {
			String query = "select "+ column +" from " + tableName + " where rownum = 200;";
			//			System.out.println(query);
			connectToDb(db);
			System.out.println(query);
			recordSet = statement.executeQuery(query);
			recordSet.next();
			record = recordSet.getString(column);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				recordSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return record;

	}
	
	
	/**
	 * @param db - Database for the query
	 * @param tableName 
	 * @param primaryColumn - Primary 
	 * @param primaryColumn_value
	 * @param columns
	 * @return Map of Column and corresponding values populated for the column
	 */
	public static Map<String,String>  getmultipleColumnValues(String db, String tableName, String primaryColumn,
			String primaryColumn_value,String sec_column,String sec_columnValue,Set<String> columns) {
		Map<String,String> columnMap = new HashMap<String, String>();
		try{

			connectToDb(db);
			String query = "select * from " + tableName + " where  " + primaryColumn + " = '" + primaryColumn_value + "'" + "and " + sec_column + " = '" + sec_columnValue + "'";
			System.out.println(query);
			recordSet = statement.executeQuery(query);
			System.out.println(recordSet);
			
			recordSet.next();
			for (String column : columns) {
				columnMap.put(column, recordSet.getString(column));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try{
				recordSet.close();
				statement.close();
				connection.close();
			}
			catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return columnMap;
	}



	public static String getRecordsMySQLDB(String db, String tableName, String prim_col, String prim_val, String column) throws Exception {
		String record = null;
		try {

			String[] db_Creds = null;

			String query = "select * from " + tableName + " where  " + prim_col + " = '" + prim_val + "'";

			String[] db_env = db.split("--");

			if (propertyLoader.getEnvironment().equals("d9cbwpdssa01")) db_Creds  = db_env[0].split(","); 
			else		 

				db_Creds = db_env[1].split(",");

			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(db_Creds[0], db_Creds[1], db_Creds[2]);
			System.out.println(db_Creds);
			statement = connection.createStatement();

			recordSet = statement.executeQuery(query);
			recordSet.next();

			if (column == null)
				record = recordSet.getString(prim_col);
			else
				record = recordSet.getString(column);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				recordSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return record;

	}

	public static String updateRecordsMySQLDB( String query) throws Exception {
		String record = null;

		String[] dbstring = null ;


		if (propertyLoader.getEnvironment().equals("d9cbwpdssa01")) {			

			dbstring = Dev_userProfile_service.split(" ");
		}else {
			dbstring = QA_userProfile_service.split(" ");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


			connection = DriverManager.getConnection(dbstring[0], dbstring[1], dbstring[2]);
			statement = connection.createStatement();

			statement.executeUpdate(query);




		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				recordSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return record;

	}




	public static void updateRecordsMySQLDB(String db, String tableName, String prim_col, String prim_val,String column, String status) throws Exception 
	{
		try {

			String[] db_Creds = null;

			String query = "Update " + tableName + " set " + column + " = '" + status + "' where  " + prim_col + " = '"
					+ prim_val + "'";

			String[] db_env = db.split("--");


			if (propertyLoader.getEnvironment().equals("d9cbwpdssa01")) db_Creds
			= db_env[0].split(","); else


				db_Creds = db_env[1].split(",");

			Class.forName("com.mysql.cj.jdbc.Driver");
			//			connection.setAutoCommit(false);
			connection = DriverManager.getConnection(db_Creds[0], db_Creds[1], db_Creds[2]);
			statement = connection.createStatement();

			statement.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//recordSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	public static void DeleteRecord(String db , String SqlQuery) throws SQLException {


		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			connectToDb(db);
			//			connection = DriverManager.getConnection(t9dsdb02_DB_NAME, t9dsdb02_USN, t9dsdb02_PWD);
			//			statement = connection.createStatement();
			statement.executeUpdate(SqlQuery );


		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			//recordSet.close();
			statement.close();
			connection.close();
		}


	}

	public static  List<String> GetListofColumValues(String SqlQuery, String ColName) throws SQLException, ClassNotFoundException {

		List<String> Payment_ID = new ArrayList<String>();

		String[] dbstring = null ;
		String DatabaseName = null;
		if (propertyLoader.getEnvironment().equals("d9cbwpdssa01")) {


			dbstring = Dev_userProfile_service.split(" ");
		}else {

			dbstring = QA_userProfile_service.split(" ");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


			connection = DriverManager.getConnection(dbstring[0], dbstring[1], dbstring[2]);
			statement = connection.createStatement();
			recordSet = statement.executeQuery(SqlQuery );

			while(recordSet.next()) {
				Payment_ID.add(recordSet.getString(ColName));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			//recordSet.close();
			statement.close();
			connection.close();
		}

		return Payment_ID;

	}

	public static void UpdateRecord(String db , String SqlQuery) throws SQLException {

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			connectToDb(db);
			int count = statement.executeUpdate(SqlQuery);


		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			//recordSet.close();
			statement.close();
			connection.close();
		}


	}

}
