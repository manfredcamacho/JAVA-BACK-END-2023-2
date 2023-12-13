package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Speaker;

public class MySQLSpeakerRepository implements SpeakerRepository {

	public void save(Speaker speaker) {
		String sql = "insert into orador (nombre,apellido,mail,tema,fecha_alta) values(?,?,?,?,?)";
		
		try(Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement statement  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, speaker.getName());
			statement.setString(2, speaker.getLastName());
			statement.setString(3, speaker.getEmail());
			statement.setString(4, speaker.getTopic());
			statement.setDate(5, Date.valueOf(speaker.getCreationDate()));
			statement.executeUpdate();
			ResultSet res = statement.getGeneratedKeys();

			if (res.next())
				speaker.setId(res.getLong(1));

		}catch (Exception e) {
			throw new IllegalArgumentException("Failed to register the speaker.",e);
		}
	}

	public Speaker getById(Long id) {
		String sql = "select nombre, apellido, tema, mail, fecha_alta from orador where id = ?";
		
		Speaker speaker = null;
		try(Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet res = statement.executeQuery();//SELECT

			if(res.next()) {
				String name = res.getString(1);
				String lastName = res.getString(2);
				String topic = res.getString(3);
				String email = res.getString(4);
				Date creationDate = res.getDate(5);

				speaker = new Speaker(id,name, lastName, email, topic, creationDate.toLocalDate());
			}
		}catch (Exception e) {
			throw new IllegalArgumentException("Failed to retrieve the speaker.", e);
		}
		return speaker;
	}

	@Override
	public void update(Speaker speaker) {
		String sql = "update orador set nombre=?, apellido=?, tema=? where id = ?";

		try(Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, speaker.getName());
			statement.setString(2, speaker.getLastName());
			statement.setString(3, speaker.getTopic());
			statement.setLong(4, speaker.getId());
			statement.executeUpdate();
		}catch(Exception e) {
			throw new IllegalArgumentException("Failed to update the speaker.", e);
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from orador where id = ?";
				
		try(Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		}catch(Exception e) {
			throw new IllegalArgumentException("Failed to delete the speaker.", e);
		}
	}

	@Override
	public List<Speaker> findAll() {
		String sql = "select id, nombre, apellido, tema, mail, fecha_alta from orador";
		
		List<Speaker> speakers = new ArrayList<>();
		try(Connection conn = ConnectionManager.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet res = statement.executeQuery();

			while(res.next()) {
				Long id = res.getLong(1);
				String name = res.getString(2);
				String lastName = res.getString(3);
				String topic = res.getString(4);
				String email = res.getString(5);
				Date creationDate = res.getDate(6);
				
				Speaker speaker = new Speaker(id,name, lastName, email, topic, creationDate.toLocalDate());
				speakers.add(speaker);
			}
		}catch (Exception e) {
			throw new IllegalArgumentException("Failed to retrieve the speakers.", e);
		}
		
		return speakers;
	}
}