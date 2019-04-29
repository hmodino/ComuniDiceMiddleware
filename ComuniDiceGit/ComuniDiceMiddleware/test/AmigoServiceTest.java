import java.util.ArrayList;
import java.util.List;

import com.rollanddice.comunidice.model.Usuario;
import com.rollanddice.comunidice.service.impl.AmigoServiceImpl;
import com.rollanddice.comunidice.service.spi.AmigoService;

public class AmigoServiceTest {
	
	public static void main(String args[]) {
		
		AmigoService s = new AmigoServiceImpl();

		try {
//			s.create(7, 6);
//			s.findAmigos(6);
			Usuario u = s.findByEmailAmigo("ESTELA.quijada@GMAIL.COM", 6);
			System.out.println(u);
//			s.findByNombreAmigo("HModino", 7);
//			s.delete(7, 6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
