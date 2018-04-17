package com.badillosoft.dto;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@NamedNativeQueries({
	@NamedNativeQuery(name = "Orden.obtenerCupcakes",
	query="SELECT B.* FROM orden_cupcakes A INNER JOIN cupcake B ON A.cupcakes_id=B.id WHERE A.orden_id=:id AND B.precio > :precioMayor",
	resultClass=Cupcake.class)/*,
	@NamedNativeQuery(name = "Orden.obtenerCupcakes",
	query="SELECT B.* FROM orden_cupcakes A INNER JOIN cupcake B ON A.cupcakes_id=B.id WHERE A.orden_id=:id AND B.precio > :precioMayor",
	resultClass=Cupcake.class)*/
})
public class Query {

}
