import com.rollanddice.comunidice.model.Comentario;
import com.rollanddice.comunidice.service.impl.ComentarioServiceImpl;
import com.rollanddice.comunidice.service.spi.ComentarioService;

public class ComentarioServiceTest {

	public static void main(String args[]) {

		ComentarioService s = new ComentarioServiceImpl();
		Comentario c = new Comentario();
		c.setContenido("AFFWEfelkveowihfew");
		c.setUsuario(7);
//		c.setIdComentario(2);
		try {
//			s.create(c, 30, null);
			s.findByProductoOForo(30, null);
//			s.findById(2);
//			s.findByUsuarioProductoOForo(7, 30, null);
//			s.findByUsuarioTipo(7, true);
//			s.delete(c);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
