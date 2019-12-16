

package com.cibertec.app.models.dao;

import java.util.List;
import com.cibertec.app.models.entity.RecorridoRuta;

public interface IRecorridoRutaDao {
	
	public List<RecorridoRuta> listarRecorrido();

	public void registarRecorrido(RecorridoRuta ruta);

	public RecorridoRuta editarRecorrido(Long id);
	
	public void eliminarRecorrido(Long id);
	
	public RecorridoRuta buscarRelacion(Long idRuta);


}
