import com.rollanddice.comunidice.service.impl.MailServiceImpl;
import com.rollanddice.comunidice.service.spi.MailService;

public class VentaServiceTest {

	public static void main(String[] args) {

		MailService s = new MailServiceImpl();
		try {
			s.emailService("", "", "lkgjñroiegjr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
