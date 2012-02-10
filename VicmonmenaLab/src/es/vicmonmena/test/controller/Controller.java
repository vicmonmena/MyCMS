package es.vicmonmena.test.controller;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import es.vicmonmena.test.dto.PersonaDTO;
import es.vicmonmena.utils.model.dao.GenericDAO;

public class Controller {
	private final String TAG = "Controller";
	private static Controller instance;
	private GenericDAO gdao;
	private Context context;
	
	private Controller(Context context) {
		this.context = context;
		String sql = "CREATE TABLE persona (" +
				"_id INTEGER PRIMARY KEY," +
				"nombre VARCHAR(255)," +
				"apellidos VARCHAR(255)," +
				"dni VARCHAR(9)," +
				"fechaNacimiento VARCHAR(10)," +
				"email VARCHAR(255)," +
				"direccion VARCHAR(255)" +
				");";
				
		gdao = GenericDAO.getInstance(context, "testdb", sql, "persona", 1);
	}
	
	public static synchronized Controller getInstance(Context context) {
		if (instance == null) {
			instance = new Controller(context);
		}
		return instance;
	}		
	
	public ArrayList<PersonaDTO> listarPersonas() throws Exception {
		ArrayList<PersonaDTO> personas = null;
		
		String columns[] = new String[]{"_id","nombre","apellidos"};
		
		Cursor c = gdao.get("persona", columns, null, null, null);
		
		if(c != null) {
			personas = new ArrayList<PersonaDTO>(c.getCount());
			while(c.moveToNext()) {
				
				PersonaDTO dto = new PersonaDTO(
					c.getInt(c.getColumnIndex("apellidos")),
					c.getString(c.getColumnIndex("nombre")) + " " + c.getString(c.getColumnIndex("apellidos")));
				personas.add(dto);
			}
		}
		
		return personas;
	}
	
	public void nuevaPersona(String nombre, String apellidos, String dni, 
			String fechaNacimiento, String email, String direccion)  throws Exception {		
		
		ContentValues values = new ContentValues(6);
		values.put("nombre", nombre);
		values.put("apellidos", apellidos);
		values.put("dni", dni);		
		values.put("fechaNacimiento", fechaNacimiento);
		values.put("email", email);
		values.put("direccion", direccion);			
		
		if(gdao.insert("persona", values) < 0) {
			throw new Exception();
		}
	}
}
