package com.med.dic.webservice;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.med.dic.model.Pharmacy;

public class WebService {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:8080/MedicineDictionaryWebApp/smdservice?wsdl");

	        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://webservice.dic.med.com/", "SMDWebserviceImplService");

	        Service service = Service.create(url, qname);

	        SMDWebserviceInterface itf = service.getPort(SMDWebserviceInterface.class);

//	        ArrayList<Medicine> list = itf.getMedicinesByPharmacyId(2);
//	        for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i).getMedId() + " - " + list.get(i).getMedName());
//			}

//	        ArrayList<Pharmacy> list = itf.getListPharmacyLocation(21.02935175, 105.79175234);
//	        for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i).getPharmacyId() + " - " + list.get(i).getPharmacyName());
//			}

	        ArrayList<Pharmacy> list = itf.getPharmacyByMedicineId(55);
	        for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getPharmacyId() + " - " + list.get(i).getPharmacyName());
			}

//	        ArrayList<String> list = itf.getPackageName();
//	        for (int i = 0; i < list.size(); i++) {
//				System.out.println(i+1 + " - " + list.get(i).toString());
//			}

//	        String pack = itf.getTypeOfPackage(40);
//	        System.out.println(pack);
	}
}
