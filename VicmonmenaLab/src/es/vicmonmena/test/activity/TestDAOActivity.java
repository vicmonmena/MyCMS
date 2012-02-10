package es.vicmonmena.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import es.vicmonmena.test.controller.Controller;
import es.vicmonmena.utils.ui.GenericAdapter;
import es.vicmonmena.vicmonmenalab.R;

public class TestDAOActivity extends Activity {

	private final String TAG = "TestDAOActivity";
	
	private LinearLayout formulario;
	private LinearLayout listado;
	
	private Button showForm;
	private Button showList;
	
	private ListView lv;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona_layout);
        
        formulario = (LinearLayout)findViewById(R.id.formularioLayout);
        listado = (LinearLayout)findViewById(R.id.listadoLayout);
        showForm = (Button)findViewById(R.id.showForm);
        showList = (Button)findViewById(R.id.showList);
        
    	lv = (ListView)findViewById(R.id.listado);
    }
    
    /**
     * @param v - botón con id 'showList' 
     * */
    public void verListado(View v) {
    	Log.i(TAG, "Has seleccionado ver Listado");
    	
    	showForm.setEnabled(true);
    	v.setEnabled(false);
    	
    	formulario.setVisibility(View.INVISIBLE);
    	listado.setVisibility(View.VISIBLE);   
    	    	
    }
    
    /**
     * @param v - botón con id 'showForm' 
     * */
    public void verFormulario(View v) {
    	Log.i(TAG, "Has seleccionado ver Formulario");
    	showList.setEnabled(true);
    	v.setEnabled(false);
    	
    	formulario.setVisibility(View.VISIBLE);
    	listado.setVisibility(View.INVISIBLE);
    }
    
    public void nuevaPersona(View view) {
    	EditText nombreTxt = (EditText)findViewById(R.id.nombreTxt);
    	EditText apellidosTxt = (EditText)findViewById(R.id.apellidosTxt);
    	EditText dniTxt = (EditText)findViewById(R.id.dniTxt);
    	EditText fechaTxt = (EditText)findViewById(R.id.fechaNacimientoTxt);
    	EditText direccionTxt = (EditText)findViewById(R.id.direccionTxt);
    	EditText emailTxt = (EditText)findViewById(R.id.emailTxt);
    	
    	String text = "Has introducido: " + nombreTxt.getText() + " " + apellidosTxt.getText() + "con DNI: " + dniTxt.getText() + ", Email: " +
    			emailTxt.getText() + ", Dirección:" + direccionTxt.getText() + " y Nacido el: ";
    	
    	Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
    	toast.show();
    	
    	try {
		
    		Controller.getInstance(this).nuevaPersona(
    			nombreTxt.getText().toString(),
    			apellidosTxt.getText().toString(), 
    			dniTxt.getText().toString(),
    			fechaTxt.getText().toString(),
    			emailTxt.getText().toString(),
    			direccionTxt.getText().toString());
    		
    		Log.i(TAG, "Enhorabuena!! " + nombreTxt.getText() + " ya es una persona más en la lista.");
		} catch (Exception e) {
			Log.e(TAG, "No se puede añadir a " + nombreTxt.getText(),e);
		}    	
    	
    	refreshList();
    	
    }
    
    private void refreshList() {
    	try {
    		GenericAdapter gadapter = new GenericAdapter(
    			this,
				android.R.layout.simple_list_item_1,     				
				Controller.getInstance(this).listarPersonas());
    		
    		lv.setAdapter(gadapter);
		} catch (Exception e) {
			Log.e(TAG, "No se puede cargar la lista de personas",e);
		}
    } 
}
