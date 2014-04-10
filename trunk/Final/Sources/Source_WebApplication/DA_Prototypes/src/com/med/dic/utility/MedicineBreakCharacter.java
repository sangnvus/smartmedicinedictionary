package com.med.dic.utility;

import com.med.dic.base.action.BaseAction;
import com.med.dic.model.Medicine;
import com.med.dic.validate.Validator;

public class MedicineBreakCharacter extends BaseAction{

	public static Medicine breakChar(Medicine medicine) {
		
		// INDICATIONS
		String indications = medicine.getIndications().replace("\r", "");
		String[] indicationSplit = indications.split("\n");
		for (String string : indicationSplit) {
			medicine.indicationList.add(string);
		}
		
		// CONTRAINDICATIONS
		String contraindications = medicine.getContraindications();
		if(Validator.nullOrBlank(contraindications)) {
			medicine.contraindicationList.add("Chưa có thông tin");
		} else {
			contraindications = medicine.getContraindications().replace("\r", "");
			String[] contraindicationSplit = contraindications.split("\n");
			for (String string : contraindicationSplit) {
				medicine.contraindicationList.add(string);
			}
		}
		
		// DOSING AND USE
		String dosingAndUse = medicine.getDosingAndUse().replace("\r", "");
		String[] dosingAndUseSplit = dosingAndUse.split("\n");
		for (String string : dosingAndUseSplit) {
			medicine.dosingAndUseList.add(string);
		}
		
		// 	INGREDIENTS
		String ingredients = medicine.getIngredients().replace("\r", "");
		String[] ingredientSplit = ingredients.split("\n");
		for (String string : ingredientSplit) {
			medicine.ingredientsList.add(string);
		}
		
		// STORAGE
		String storage = medicine.getStorage();
		if(Validator.nullOrBlank(storage)) {
			medicine.storageList.add("Chưa có thông tin");
		} else {
			storage = medicine.getStorage().replace("\r", "");
			String[] storageSplit = storage.split("\n");
			for (String string : storageSplit) {
				medicine.storageList.add(string);
			}
		}
		
		// INTERACTIONS
		String interaction = medicine.getInteraction();
		if(Validator.nullOrBlank(interaction)) {
			medicine.interactionList.add("Chưa có thông tin");
		} else {
			interaction = medicine.getInteraction().replace("\r", "");
			String[] interactionSplit = interaction.split("\n");
			for (String string : interactionSplit) {
				medicine.interactionList.add(string);
			}
		}
		
		// SIMILAR MEDICINE
		String similarMedicine = medicine.getSimilarMedicine();
		if(Validator.nullOrBlank(similarMedicine)) {
			medicine.similarMedicineList.add("Chưa có thông tin");
		} else {
			similarMedicine = medicine.getSimilarMedicine().replace("\r", "");
			String[] similarMedicineSplit = similarMedicine.split("\n");
			for (String string : similarMedicineSplit) {
				medicine.similarMedicineList.add(string);
			}
		}
		
		// WARNING
		String warning = medicine.getWarning();
		if(Validator.nullOrBlank(warning)) {
			medicine.warningList.add("Chưa có thông tin");
		} else {
			warning = medicine.getWarning().replace("\r", "");
			String[] warningSplit = warning.split("\n");
			for (String string : warningSplit) {
				medicine.warningList.add(string);
			}
		}
		
		return medicine;
	}
}
