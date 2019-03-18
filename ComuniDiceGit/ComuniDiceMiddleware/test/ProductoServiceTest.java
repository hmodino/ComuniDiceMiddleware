import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Juego;
import com.rollanddice.comunidice.model.Producto;
import com.rollanddice.comunidice.service.impl.ProductoServiceImpl;
import com.rollanddice.comunidice.service.spi.ProductoService;

public class ProductoServiceTest {

	public static void main(String[] args) {

		ProductoService s = new ProductoServiceImpl();
		Producto p = new Producto();
		Juego j = new Juego();
		Criteria c = new Criteria();
		
		c.setIdCategoria(1);
		c.setPrecioDesde(10.0);
		c.setPrecioHasta(12.0);
		c.setTipoVendedor(0);
		p.setIdCategoria(1);
		p.setDescripcion("Descripcion del producto");
		p.setNombre("Nombre");
		p.setPrecio(10.2);
		p.setStock(5);
//		j.setIdCategoria(1);
//		j.setDescripcion("Descripcion del juego");
//		j.setNombre("Nombre");
//		j.setPrecio(10.2);
//		j.setStock(5);
//		j.setAnhoPublicacion(2018);
//		j.setFormato(1);
//		j.setTipoVendedor(0);
//		j.setPaginas(400);
//		j.setTipoTapa(1);
		
		try {
//			s.create(p, "es_ES");
			
			for(int i = 0; i<3;i++) {
				s.findById(30, "es_ES");
			}
//			s.createJuego(j);
//			s.findJuegoById(58);
//			s.findByCriteria(c, "es_ES");
//			s.findJuegoByCriteria(c);
		}catch(Exception e) {
			e.printStackTrace();	
		}

	}

}
