import com.rollanddice.comunidice.model.Mensaje;
import com.rollanddice.comunidice.service.impl.MensajeServiceImpl;
import com.rollanddice.comunidice.service.spi.MensajeService;

public class MensajeServiceTest {

	public static void main(String[] args) {
		
		MensajeService s = new MensajeServiceImpl();
		Mensaje m = new Mensaje();
		
		m.setContenido("HOla amigo");
		m.setUsuarioEmisor(7);
		m.setUsuarioReceptor(6);
		m.setIdMensaje(1);
		try {
//			s.create(m);
//			s.delete(m);
//			s.findByEmisor(7);
//			s.findByReceptor(6);
			s.findById(3);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
