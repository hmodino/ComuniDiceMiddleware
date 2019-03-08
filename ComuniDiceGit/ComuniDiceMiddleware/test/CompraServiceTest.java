import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.model.Compra;
import com.rollanddice.comunidice.model.LineaCompra;
import com.rollanddice.comunidice.model.Producto;
import com.rollanddice.comunidice.service.impl.CompraServiceImpl;
import com.rollanddice.comunidice.service.spi.CompraService;

public class CompraServiceTest {
	
	public static void main(String[] args) {
		
		CompraService compra = new CompraServiceImpl();
		Compra c = new Compra();
		LineaCompra lc = new LineaCompra();
		Producto p = new Producto();
		List<LineaCompra> lcs = new ArrayList<LineaCompra>();
		
		p.setPrecio(30.0);
		p.setIdProducto(30);
		lc.setProducto(p);
		lc.setCantidad(2);
		lc.setDescuento(0.0);
		lcs.add(lc);
		c.setIdUsuario(7);
		c.setModoPago(1);
		c.setGastosEnvio(2.5);
		c.setIdCompra(8);
		
		
		try {
			compra.create(c, lcs);
//			compra.delete(c, lcs);
//			compra.findById(20);
//			compra.findByUsuario(7);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
