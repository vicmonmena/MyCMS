package es.vicmonmena.utils.ui;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;

/**
 * Clase genérica que permite cargar elementos en ListViews
 * Y añadir un filtrado de estos mediante un campo de texto.
 * <br/>
 * <br/>
 * <b>Creado el: 08/02/2012
 * @author Vicente Montaño Mena
 * <br/>
 * Twitter: @vicmonmena
 * <br/>
 * GTalk: vicmonmena@gmail.com
 * */
public class GenericAdapter extends ArrayAdapter {	

	/**
	 * Contiene el listado de elementos inicial cargados en el adapter
	 * */
	private ArrayList elementos;
	
	/**
	 * Contiene el listado de elementos filtrados en el adapter
	 * */
	private ArrayList filtered;	
	private Context context;
	private GenericFilter filter;
	
	/**
	 * @param context 
	 * @param textViewResourceId - identificador del View donde se va a cargar el adapter
	 * @param elementos - listado de elementos que se van a cargar en el adapter
	 * */
	public GenericAdapter(Context context, int textViewResourceId, ArrayList elementos) {		
		super(context, textViewResourceId, elementos);
		
		this.context = context;
		this.elementos = elementos;
		this.filtered = elementos;
		this.filter = new GenericFilter();
	}
	
	@Override
	public int getCount() {
		int size = 0;
		if(filtered != null)
			size = filtered.size();
		return size;
	}
	
	@Override
	public Object getItem(int position) {			
		Object item = null;
		if(filtered != null) {
			item = filtered.get(position);
		}
		return item;
	}	
		
	@Override
	public Filter getFilter() {
		return filter;
	}
	
	/**
	 * Clase que permite realizar el filtrado de los elementos cargados en este Adapter
	 * */
	private class GenericFilter extends Filter {
		
		public GenericFilter() {}
			
		@Override
		protected void publishResults(CharSequence paramCharSequence,
				FilterResults paramFilterResults) {
			filtered = (ArrayList)paramFilterResults.values;
			
			notifyDataSetChanged();
		}
		
		@Override
		protected FilterResults performFiltering(CharSequence paramCharSequence) {
			
			FilterResults result = new FilterResults();				
			
			if(elementos == null){				
					result.values = elementos;
					result.count = elementos.size();				
			}
			else if(TextUtils.isEmpty(paramCharSequence)) {				
					result.values = elementos;
					result.count = elementos.size();				
			} else {							
				
				ArrayList coincidencias = new ArrayList();
				if(elementos != null && elementos.size() > 0) {					
					int size = elementos.size();
					for (int i =0; i < size; i++) {
						
						String elemento = elementos.get(i).toString();
						// Ralentiza el la bÃºsqueda, tarda mucho en encontrar los resultados
//						String insensitiveElem = Util.quitarAcentos(elementos.get(i).getNombre());
						
						if(elemento.toLowerCase().contains(paramCharSequence.toString())) {
							coincidencias.add(elementos.get(i));
						}
					}
				}					
				result.values = coincidencias;
				result.count = coincidencias.size();				
			}				
			
			return result;
		};
	}

}
