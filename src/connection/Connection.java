package connection;

import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class Connection {
	
	public static void createDB(String name) {
		try {
			if(connection == null)
				openConnection();
			String Query = "CREATE DATABASE " + name;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			closeConection();
			System.out.println("Se ha creado la base de datos " + name + " de forma exitosa.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error creando DB.");
		}
	}
	
	public static void createTable(String db, String name, String campos) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);
			
			String Query = "CREATE TABLE " + name + " " + campos;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error creando tabla.");
		}
	}

}
