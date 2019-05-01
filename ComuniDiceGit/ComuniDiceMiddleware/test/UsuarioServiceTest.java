import com.rollanddice.comunidice.model.Direccion;
import com.rollanddice.comunidice.model.Usuario;
import com.rollanddice.comunidice.service.impl.UsuarioServiceImpl;
import com.rollanddice.comunidice.service.spi.UsuarioService;

public class UsuarioServiceTest {

	public static void main(String[] args) {
		
		UsuarioService s = new UsuarioServiceImpl();
		Usuario u = new Usuario();
		Direccion d = null;
		
		u.setIdUsuario(6);
		u.setContrasenha("1234");
//		u.setEmail("hector.modino.otero@gmail.com");
//		u.setContrasenha("1234");
//		u.setNombre("Estela");
//		u.setApellido1("Quijada");
//		u.setApellido2("Viña");
//		u.setTelefono("468794132");
//		u.setNombreUsuario("b");
//		u.setNombreUsuario("fmwlf");
		
		try {
//			s.findByEmail(u.getEmail());
//			s.findById(u.getIdUsuario());
//			s.findByNombre(u.getNombreUsuario());
//			s.signUp(u);
//			s.logIn("hector.modino.otero@gmail.com", "1234");
//			s.signUp(u, d);
			s.editar(u, d);
//			Properties systemProperties = System.getProperties();
//			String key = null;
//			for(Enumeration keys = systemProperties.keys(); keys.hasMoreElements();) {
//				key = (String) keys.nextElement();
//				System.out.println(key+"="+System.getProperty(key));
//			}
//			s.editar(u);
//			s.eliminar(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
