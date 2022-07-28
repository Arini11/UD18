package connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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


	//INSERTAR DATOS EN TABLAS MYSQL

	public static void insertData (String db, String nombre_tabla, String campos) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query= "INSERT INTO " +table_name + " " +campos;

			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null,"Error en el almacenamiento");
		}
	}

	//OBTENER VALORES MYSQL

	public static void getValues (String db, String nombre_tabla, int numCampos) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "SELECT * FROM " + nombre_tabla;
			Statement st = conexion.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {
				while(numCampos-- > 0) {
					// No tot serà string
					// Try catch amb getDouble???
					System.out.println(resultSet.getString(numCampos));
				}
			}
		}catch(SQLException e) {
			System.out.println(ex.getMessage());
		} 

	}
}



