import com.rollanddice.comunidice.service.impl.AmigoServiceImpl;
import com.rollanddice.comunidice.service.spi.AmigoService;

public class AmigoServiceTest {
	
	public static void main(String args[]) {
		
		AmigoService s = new AmigoServiceImpl();
		
		try {
//			s.create(7, 6);
			s.findAmigos(7);
//			s.findByEmailAmigo("ESTELA.quijada@GMAIL.COM", 6);
//			s.findByNombreAmigo("HModino", 7);
//			s.delete(7, 6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
