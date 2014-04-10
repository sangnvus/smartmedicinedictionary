package net.roseindia.employee.action;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.med.dic.dao.MedicineDAO;
import com.med.dic.model.Medicine;
import com.mysql.jdbc.PreparedStatement;
import com.opensymphony.xwork2.ActionSupport;

public class AutoCompleteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<String> autoString = new ArrayList<>();
	public String greeting;
	private ResultSet resultSet = null;
	List<Medicine> medList = new ArrayList<>();
	MedicineDAO medicineDAO;
	
	public String autoComplete() {
//		autoString.add("Xuan");
//		autoString.add("Thi");
//		autoString.add("Le");
//		autoString.add("Pham");
//		autoString.add("Ha");
//		autoString.add("Hai");
//		autoString.add("Huy");
//		autoString.add("Khoi");
//		autoString.add("Minh");
//		autoString.add("Tu");
//		autoString.add("Ngo");
//		autoString.add("Thu");
		//greeting = "Hello Ajax";
		resultSet = resultSet("SELECT MEDICINE_NAME FROM MEDICINES", 0, null);
		if (resultSet != null) {
			try {
				while(resultSet.next()) {
					String districtName = resultSet.getString("MEDICINE_NAME");
					autoString.add(districtName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//medList = medicineDAO.medList();
		//for (Medicine medicine : medList) {
			//String medName = medicine.getMedName();
			//autoString.add(medName);
		//}
		
		return SUCCESS;
	}
	
	public ResultSet resultSet(String query, int paramNum, String[] values) {
		ResultSet resultSet = null;
		try {
			Properties prop = new Properties();
			InputStream in = getClass().getResourceAsStream("database.properties");
			prop.load(in);
			String driver = prop.getProperty("jdbc.driverClassName");
			String url = prop.getProperty("jdbc.url");
			String userName = prop.getProperty("jdbc.username");
			String password = prop.getProperty("jdbc.password");
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, userName,
					password);
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
			if (!("".equals(values)) || !(values == null)){
				for (int i = 1; i <= paramNum; i++) {
					statement.setInt(i, Integer.parseInt(values[i-1]));
				}
			}
			resultSet = statement.executeQuery();
		} catch (Exception e) {
			return null;
		}
		return resultSet;
	}
	/**
	 * @return the greeting
	 */
	public String getGreeting() {
		return greeting;
	}
	/**
	 * @param greeting the greeting to set
	 */
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	/**
	 * @return the autoString
	 */
	public List<String> getAutoString() {
		return autoString;
	}
	/**
	 * @param autoString the autoString to set
	 */
	public void setAutoString(List<String> autoString) {
		this.autoString = autoString;
	}

	/**
	 * @return the medList
	 */
	public List<Medicine> getMedList() {
		return medList;
	}

	/**
	 * @param medList the medList to set
	 */
	public void setMedList(List<Medicine> medList) {
		this.medList = medList;
	}

	/**
	 * @return the medicineDAO
	 */
	public MedicineDAO getMedicineDAO() {
		return medicineDAO;
	}

	/**
	 * @param medicineDAO the medicineDAO to set
	 */
	public void setMedicineDAO(MedicineDAO medicineDAO) {
		this.medicineDAO = medicineDAO;
	}
}
