package com.med.dic.search.medicine;

import java.io.Serializable;

public class Medicine implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int medicineId;
	private String name;
	private String manufacture;
	private String ingredient;
	private String indication;
	private String contraindication;
	private String typeOfPackage;
	private String warning;
	private String dosingAndUse;
	private String storage;
	private String imagePath;
	private String interaction;

	//Display icon for list search
//	private int image;

	public Medicine(int medicineId, String name, String manufacture, String ingredient,
					String indication, String contraindication, String typeOfPackage, String warning,
					String dosingAndUse, String storage, String imagePath, String interaction) {
		this.medicineId = medicineId;
		this.name = name;
		this.manufacture = manufacture;
		this.ingredient = ingredient;
		this.indication = indication;
		this.contraindication = contraindication;
		this.typeOfPackage = typeOfPackage;
		this.warning = warning;
		this.dosingAndUse = dosingAndUse;
		this.storage = storage;
		this.imagePath = imagePath;
		this.interaction = interaction;
	}

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getContraindication() {
		return contraindication;
	}

	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}

	public String getTypeOfPackage() {
		return typeOfPackage;
	}

	public void setTypeOfPackage(String typeOfPackage) {
		this.typeOfPackage = typeOfPackage;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public String getDosingAndUse() {
		return dosingAndUse;
	}

	public void setDosingAndUse(String dosingAndUse) {
		this.dosingAndUse = dosingAndUse;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

}
