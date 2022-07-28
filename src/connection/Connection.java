package connection;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



//INSERTAR DATOS EN TABLAS MYSQL

public static void insertData (String db, String nombre_tabla,) {
	try {
		String Querydb = "USE"+db+";";
		Statement stdb= conexion.createStatement();
		stdb.executeUpdate(Querydb);
		
		String Query= "INSERT INTO" +table_name + "(campo1, campo2, campo3, campo4)" VALUE ("
				+"\""+campo1+"\","
				+"\""+campo2+"\","
				+"\""+campo3+"\","
				+"\""+campo4+"\" );
				
		Statement st = conexion.createStatement();
		st.executeUpdate(Query);
		
		System.out.println("Datos almacenados correctamente");
	} catch (SQLException ex) {
		System.out.println(ex.getMessage());
		JOptionPane.showMessageDialog(null,"Error en el almacenamiento");
	}
}

//OBTENER VALORES MYSQL

public static void getValues (String db, String nombre_tabla) {
	try {
		String Querydb = "USE"+db+";";
		Statement stdb = conexion.createStatement();
		stdb.executeUpdate(Querydb);
		
		String Query = "SELECT * FROM" + nombre_tabla;
		Statement st = conexion.createStatement();
		java.sql.ResultSet resultSet;
		resultSet = st.executeQuery(Query);
		
		while (resultSet.next()) {
			System.out.println("ID:" + resultSet.getString("ID") + ""
					+ "campo1:" + resultSet.getString("Campo1") + ""
					+ "campo2:" + resultSet.getString("Campo2") + ""
					+ "campo3:" + resultSet.getString("Campo3") + ""
					+ "campo4:" + resultSet.getString("Campo4") + ""};
		
	}
}
}



