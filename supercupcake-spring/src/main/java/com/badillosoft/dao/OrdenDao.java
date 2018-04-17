package com.badillosoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.badillosoft.dto.Cupcake;
import com.badillosoft.dto.Orden;

@Transactional
public interface OrdenDao extends CrudRepository<Orden, Long> {
	
	/*@Query(value="SELECT B FROM orden_cupcakes A INNER JOIN cupcake B ON A.cupcakes_id=B.id WHERE A.orden_id=:id", nativeQuery=true)
	public List<Cupcake> obtenerCupcakes(@Param("id") Long idOrden);
	
	@Query(value="SELECT * FROM orden WHERE cliente_id=:id", nativeQuery=true)
	public List<Orden> obtenerOrdenes(@Param("id") Long idCliente);*/
	
	@Query(value = "SELECT SUM(B.precio) FROM orden_cupcakes A INNER JOIN cupcake B ON A.cupcakes_id=B.id WHERE A.orden_id=:id", nativeQuery=true)
	public Double calcularTotal(@Param("id") Long idOrden);
	
}
