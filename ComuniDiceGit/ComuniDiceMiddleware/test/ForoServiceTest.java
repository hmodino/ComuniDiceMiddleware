import com.rollanddice.comunidice.model.Criteria;
import com.rollanddice.comunidice.model.Foro;
import com.rollanddice.comunidice.service.impl.ForoServiceImpl;
import com.rollanddice.comunidice.service.spi.ForoService;

public class ForoServiceTest {
	public static void main(String args[]) {
		
		Foro f = new Foro();
		ForoService s = new ForoServiceImpl();
		Criteria c = new Criteria();
		
		c.setIdCategoria(1);
		c.setNombre("nombre foro");
		f.setCategoria(1);
		f.setIdUsuario(7);
		f.setNombre("nombre foro");
		f.setIdForo(2);
		try {
//			s.create(f);
//			s.delete(f);
//			s.findById(3);
			s.findByCriteria(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
