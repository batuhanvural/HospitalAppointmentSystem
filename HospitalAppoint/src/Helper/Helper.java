package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		
		UIManager.put("OptianPane.cancelButtonText", "İptal");
		UIManager.put("OptianPane.noButtonText", "Hayır");
		UIManager.put("OptianPane.okButtonText", "Tamam");
		UIManager.put("OptianPane.yesButtonText", "Evet");
		
	}
	
	public static void showMsg(String str) {
		
		String msg;
		optionPaneChangeButtonText();
		
		switch (str) {
		
		case "fill": 
			
			msg="Lütfen tüm alanları doldurunuz.";
			break;
			case "success":
				msg = "İşlem başarılı !";
				break;
			case "available":
				msg = "Daha önce oluşturulmuş";
				break;
			case "error":
				msg = "Hatalı işlem";
				break;
		
		default:
			msg = str;
		}
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "sure": 
			msg ="Bu işlemi yapmak istediğinize emin misiniz ?";
			break;
		default:
			msg = str;
		}
		int res = JOptionPane.showConfirmDialog(null, msg,"Dikkat !",JOptionPane.YES_NO_OPTION);
		if (res ==0) {
			return true;
			
		} else {
			return false;

		}
	}

}
