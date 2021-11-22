package com.pe.fico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
	@Query("select count(l.nameProduct) from Product l where l.nameProduct=:name")
	public int buscarProducto(@Param("name") String nombre);
	
	@Query(value="SELECT i.name_institution, cp.name_category_product, count(p.id_institution) "
			+"from Product p join institution i on i.id_institution = p.id_institution "
			+"join category_product cp on cp.id_category_product = p.id_categoryproduct group by i.name_institution, cp.name_category_product",nativeQuery=true)
	public List<String[]>prodXcatXinst();
}
