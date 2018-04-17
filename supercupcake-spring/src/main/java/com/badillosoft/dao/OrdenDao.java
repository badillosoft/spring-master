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
	
	//@Query(value="SELECT B.* FROM orden_cupcakes A INNER JOIN cupcake B ON A.cupcakes_id=B.id WHERE A.orden_id=:id", nativeQuery=true)
	public List<Cupcake> obtenerCupcakes(@Param("id") Long idOrden, @Param("precioMayor") Double precioMayor);
	
	@Query(value="SELECT * FROM orden WHERE cliente_id=:id", nativeQuery=true)
	public List<Orden> obtenerOrdenes(@Param("id") Long idCliente);
	
	//@Modifying
	@Query(value = "SELECT SUM(B.precio) FROM orden_cupcakes A INNER JOIN cupcake B ON A.cupcakes_id=B.id WHERE A.orden_id=:id", nativeQuery=true)
	public Double calcularTotal(@Param("id") Long idOrden);
	
}

/*
@SqlResultSetMapping(name="cupcakes", entities= {
			@EntityResult(entityClass=Cupcake.class, fields= {
					@FieldResult(name="id", column="id"),
					@FieldResult(name="tipo", column="tipo"),
					@FieldResult(name="precio", column="precio")})
			}, columns= { @ColumnResult(name="id") })
	@NamedNativeQuery(name="obtenerOrdenes", query="SELECT B.* FROM orden_cupcakes A INNER JOIN cupcake B ON A.cupcakes_id=B.id WHERE A.orden_id=:id", resultSetMapping="cupcakes")
	*/
