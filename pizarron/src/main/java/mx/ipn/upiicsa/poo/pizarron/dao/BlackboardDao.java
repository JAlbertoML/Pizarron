package mx.ipn.upiicsa.poo.pizarron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ipn.upiicsa.poo.pizarron.model.Diagram;

public class BlackboardDao {

	private List<Diagram> diagramList;

	public List<Diagram> getDiagrams() {
		diagramList = new ArrayList<Diagram>();
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from diagrama");
			preparedStatement.execute();
			ResultSet result = preparedStatement.getResultSet();
			while (result.next()) {
				Integer idDiagram = result.getInt("id_diagrama");
				String name = result.getString("tx_nombre");
				String description = result.getString("tx_descripcion");
				Date creationDate = new Date(result.getDate("fh_creacion").getTime());
				Date updatedDate = new Date(result.getDate("fh_modificacion").getTime());
				// String json = result.getString("tx_json");
				Diagram diagram = new Diagram(idDiagram, name, description, creationDate, updatedDate);
				diagramList.add(diagram);
			}
		} catch (SQLException e) {
			System.out.println("--> Error en la consulta de diagrama");
			e.printStackTrace();
		}
		return diagramList;
	}

	public Boolean saveDiagram(Diagram diagram) {
		Boolean exito = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into diagrama (tx_nombre, tx_descripcion, fh_creacion, fh_modificacion, tx_json) values(?, ?, ?, ?, ?)");
			preparedStatement.setString(1, diagram.getName());
			preparedStatement.setString(2, diagram.getDescription());
			preparedStatement.setDate(3, new java.sql.Date(diagram.getCreationDate().getTime()));
			preparedStatement.setDate(4, new java.sql.Date(diagram.getCreationDate().getTime()));
			preparedStatement.setString(5, diagram.getJson());
			if (preparedStatement.execute()) {
				exito = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exito;
	}

	public Boolean removeDiagram(Diagram diagram) {
		Boolean exito = false;
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from diagrama where id_diagrama = ?");
			preparedStatement.setInt(1, diagram.getId());
			if (preparedStatement.execute()) {
				exito = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exito;
	}
}
