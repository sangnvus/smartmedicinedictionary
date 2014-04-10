package com.med.dic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.med.dic.search.medicine.Medicine;
import com.med.dic.search.medicine.MedicineSellingActivity;
import com.med.dic.search.medicine.Pharmacy;

public class SMDWebserviceClient {

	public SMDWebserviceClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 *
	 */
	public ArrayList<Medicine> loadMedicinesDataFromWS(String inputTxt) throws Exception {
		ArrayList<Medicine> listMedicine = new ArrayList<Medicine>();
		Properties prop = new Properties();
		try {
			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("ws_config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String NAME_SPACE = prop.getProperty("ws.nameSpace");
		String METHOD_NAME = prop.getProperty("ws.load.medicine.data.methodName");
		String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
		String URL = prop.getProperty("ws.url");

		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		PropertyInfo propInfo=new PropertyInfo();
		propInfo.name="medName";
		propInfo.type=PropertyInfo.STRING_CLASS;
		request.addProperty("medName", inputTxt);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject response = (SoapObject) envelope.bodyIn;
			int count = response.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject obj = (SoapObject) response.getProperty(i);
				int medicineId = Integer.parseInt(obj.getProperty("medId").toString());
				String name = obj.getProperty("medName").toString();
				String manufacture = obj.getProperty("manufacturer").toString();
				String ingredient = obj.getProperty("ingredients").toString();
				String indication = obj.getProperty("indications").toString();
				String contraindication = obj.getProperty("contraindications").toString();
				String dosingAndUse = obj.getProperty("dosingAndUse").toString();
				String typeOfPackage = obj.getProperty("typeOfPackageName").toString();
				String warning = obj.getProperty("warning").toString();
				String storage = obj.getProperty("storage").toString();
				String imgagePath = obj.getProperty("imgPath").toString();
				String interaction = obj.getProperty("interaction").toString();
				listMedicine.add(new Medicine(medicineId, name, manufacture,
						ingredient, indication, contraindication,
						typeOfPackage, warning, dosingAndUse, storage,
						imgagePath, interaction));
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (XmlPullParserException e) {
			throw new Exception(e.getMessage());
		}
		return listMedicine;
	}

	/*
	 *
	 */
	public ArrayList<Pharmacy> loadPharmacyDataFromWS(int medicineId) throws Exception {
		ArrayList<Pharmacy> listPharmacy = new ArrayList<Pharmacy>();
		Properties prop = new Properties();
		try {
			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("ws_config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String NAME_SPACE = prop.getProperty("ws.nameSpace");
		String METHOD_NAME = prop.getProperty("ws.load.pharmacy.data.by.medicineId.methodName");
		String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
		String URL = prop.getProperty("ws.url");

		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		request.addProperty("medId", medicineId);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject response = (SoapObject) envelope.bodyIn;
			int count = response.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject obj = (SoapObject) response.getProperty(i);
				int pharmacyId = Integer.parseInt(obj.getProperty("pharmacyId").toString());
				String pharmacyName = obj.getProperty("pharmacyName").toString();
				String address = obj.getProperty("address").toString();
				String businessLicense = obj.getProperty("businessLicenseNo").toString();
				String homePhone = obj.getProperty("homePhone").toString();
				String pharmaceutical = obj.getProperty("pharCompany").toString();
				String gppNo = obj.getProperty("gppNo").toString();
				String imagePath = obj.getProperty("imgPath").toString();
				String notes = obj.getProperty("notes").toString();
				double latitude = Double.parseDouble(obj.getProperty("lat").toString());
				double longitude = Double.parseDouble(obj.getProperty("lon").toString());
				listPharmacy.add(new Pharmacy(pharmacyId, pharmacyName, homePhone, businessLicense, pharmaceutical, gppNo, imagePath, address, notes, latitude, longitude));
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (XmlPullParserException e) {
			throw new Exception(e.getMessage());
		}
		return listPharmacy;
	}

	/*
	 *
	 */
	public Pharmacy loadPharmacyDataByNameAndAddressFromWS(String name, String addrs) throws Exception {
		Pharmacy pharmacy = new Pharmacy();
		Properties prop = new Properties();
		try {
			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("ws_config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String NAME_SPACE = prop.getProperty("ws.nameSpace");
		String METHOD_NAME = prop.getProperty("ws.load.pharmacy.data.by.name.and.address.methodName");
		String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
		String URL = prop.getProperty("ws.url");

		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		request.addProperty("pharmName", name);
		request.addProperty("address", addrs);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject response = (SoapObject) envelope.bodyIn;
			int count = response.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject obj = (SoapObject) response.getProperty(i);
				int pharmacyId = Integer.parseInt(obj.getProperty("pharmacyId").toString());
				String pharmacyName = obj.getProperty("pharmacyName").toString();
				String address = obj.getProperty("address").toString();
				String businessLicense = obj.getProperty("businessLicenseNo").toString();
				String homePhone = obj.getProperty("homePhone").toString();
				String pharmaceutical = obj.getProperty("pharCompany").toString();
				String gppNo = obj.getProperty("gppNo").toString();
				String imagePath = obj.getProperty("imgPath").toString();
				String notes = obj.getProperty("notes").toString();
				double latitude = Double.parseDouble(obj.getProperty("lat").toString());
				double longitude = Double.parseDouble(obj.getProperty("lon").toString());
				pharmacy = new Pharmacy(pharmacyId, pharmacyName, homePhone, businessLicense, pharmaceutical, gppNo, imagePath, address, notes, latitude, longitude);
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (XmlPullParserException e) {
			throw new Exception(e.getMessage());
		}
		return pharmacy;
	}

	/*
	 *
	 */
	public ArrayList<Pharmacy> loadListPharmacyLocationFromWs(double lat, double lon) throws Exception {
		ArrayList<Pharmacy> listPharmacy = new ArrayList<Pharmacy>();
		Properties prop = new Properties();
		try {
			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("ws_config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String NAME_SPACE = prop.getProperty("ws.nameSpace");
		String METHOD_NAME = prop.getProperty("ws.load.list.pharmacy.location.data.methodName");
		String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
		String URL = prop.getProperty("ws.url");

		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		request.addProperty("lat", lat);
		request.addProperty("lon", lon);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		MarshalDouble md = new MarshalDouble();
		md.register(envelope);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject response = (SoapObject) envelope.bodyIn;
			int count = response.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject obj = (SoapObject) response.getProperty(i);
				int pharmacyId = Integer.parseInt(obj.getProperty("pharmacyId").toString());
				String pharmacyName = obj.getProperty("pharmacyName").toString();
				String address = obj.getProperty("address").toString();
				String imagePath = obj.getProperty("imgPath").toString();
				double latitude = Double.parseDouble(obj.getProperty("lat").toString());
				double longitude = Double.parseDouble(obj.getProperty("lon").toString());
				listPharmacy.add(new Pharmacy(pharmacyId, pharmacyName, null, null, null, null, imagePath, address, null, latitude, longitude));
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (XmlPullParserException e) {
			throw new Exception(e.getMessage());
		}
		return listPharmacy;
	}

	/*
	 *
	 */
	public ArrayList<String> loadPackageNameFromWS() throws Exception {
		ArrayList<String> myList = new ArrayList<String>();
		myList.add(MedicineSellingActivity.DEFAULT_PACKAGE_TYPE);
		Properties prop = new Properties();
		try {
			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("ws_config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String NAME_SPACE = prop.getProperty("ws.nameSpace");
		String METHOD_NAME = prop.getProperty("ws.load.medicine.package.name.methodName");
		String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
		String URL = prop.getProperty("ws.url");

		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject response =  (SoapObject) envelope.bodyIn;
			int count = response.getPropertyCount();
			for (int i = 0; i < count; i++) {;
				String packageName = response.getProperty(i).toString();
				myList.add(packageName);
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (XmlPullParserException e) {
			throw new Exception(e.getMessage());
		}
		return myList;
	}

	/*
	 *
	 */
	public String loadTypeOfPackageFromWS(int medId) throws Exception {
		String typeOfPackage = "";
		Properties prop = new Properties();
		try {
			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("ws_config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String NAME_SPACE = prop.getProperty("ws.nameSpace");
		String METHOD_NAME = prop.getProperty("ws.load.medicine.type.of.package.methodName");
		String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
		String URL = prop.getProperty("ws.url");

		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		request.addProperty("medId", medId);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive response =  (SoapPrimitive) envelope.getResponse();
			typeOfPackage = response.toString();
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (XmlPullParserException e) {
			throw new Exception(e.getMessage());
		}
		return typeOfPackage;
	}

	/*
	 *
	 */
	public ArrayList<Medicine> loadMedicinesInPharmacyFromWS(int pharmacyId) throws Exception {
		ArrayList<Medicine> listMedicine = new ArrayList<Medicine>();
		Properties prop = new Properties();
		try {
			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("ws_config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String NAME_SPACE = prop.getProperty("ws.nameSpace");
		String METHOD_NAME = prop.getProperty("ws.load.medicine.in.pharmacy.data.methodName");
		String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
		String URL = prop.getProperty("ws.url");

		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		request.addProperty("pharId", pharmacyId);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject response = (SoapObject) envelope.bodyIn;
			int count = response.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject obj = (SoapObject) response.getProperty(i);
				int medicineId = Integer.parseInt(obj.getProperty("medId").toString());
				String name = obj.getProperty("medName").toString();
				String manufacture = obj.getProperty("manufacturer").toString();
				String ingredient = obj.getProperty("ingredients").toString();
				String indication = obj.getProperty("indications").toString();
				String contraindication = obj.getProperty("contraindications").toString();
				String dosingAndUse = obj.getProperty("dosingAndUse").toString();
				String typeOfPackage = obj.getProperty("typeOfPackageName").toString();
				String warning = obj.getProperty("warning").toString();
				String storage = obj.getProperty("storage").toString();
				String imgagePath = obj.getProperty("imgPath").toString();
				String interaction = obj.getProperty("interaction").toString();
				listMedicine.add(new Medicine(medicineId, name, manufacture,
						ingredient, indication, contraindication,
						typeOfPackage, warning, dosingAndUse, storage,
						imgagePath, interaction));
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (XmlPullParserException e) {
			throw new Exception(e.getMessage());
		}
		return listMedicine;
	}

	/*
	 *
	 */
	public String test() throws Exception {
		String resp = "";
		Properties prop = new Properties();
		try {
			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("ws_config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String NAME_SPACE = prop.getProperty("ws.nameSpace");
		String METHOD_NAME = prop.getProperty("ws.test.methodName");
		String SOAP_ACTION = NAME_SPACE + METHOD_NAME;
		String URL = prop.getProperty("ws.url");

		SoapObject request = new SoapObject(NAME_SPACE, METHOD_NAME);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapPrimitive respone = (SoapPrimitive) envelope.getResponse();
			resp = respone.toString();
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} catch (XmlPullParserException e) {
			throw new Exception(e.getMessage());
		}
		return resp;
	}
}
