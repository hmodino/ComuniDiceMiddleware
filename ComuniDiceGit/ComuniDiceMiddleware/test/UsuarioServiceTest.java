import com.rollanddice.comunidice.model.Usuario;
import com.rollanddice.comunidice.service.impl.UsuarioServiceImpl;
import com.rollanddice.comunidice.service.spi.UsuarioService;

public class UsuarioServiceTest {

	public static void main(String[] args) {
		
		UsuarioService s = new UsuarioServiceImpl();
		Usuario u = new Usuario();
		
		u.setIdUsuario(7);
		u.setEmail("kcdls�ol");
		u.setContrasenha("1234");
		u.setNombre("Estela");
		u.setApellido1("Quijada");
		u.setApellido2("Vi�a");
		u.setTelefono("468794132");
		u.setNombreUsuario("b");
		
		try {
//			s.findByEmail(u.getEmail());
//			s.findById(u.getIdUsuario());
//			s.findByNombre(u.getNombreUsuario());
			s.signUp(u);
//			s.logIn("Estela.QUIjada@gmail.com", "1234");
//			s.editar(u);
//			s.eliminar(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}