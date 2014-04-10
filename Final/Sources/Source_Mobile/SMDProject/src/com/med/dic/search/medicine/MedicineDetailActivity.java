package com.med.dic.search.medicine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.med.dic.MainActivity;
import com.med.dic.R;
import com.med.dic.SMDWebserviceClient;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.diary.using.MedicationDiaryActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;

public class MedicineDetailActivity extends Activity {

	public static final String MESSAGE = "com.med.dic.search.medicine.MedicineDetailActivity";
	private Intent intent;
	private TextView distribution1, distribution2, distribution3, nameTv, manufacturerTv, ingredientTv, indicationTv, contraindicationTv;
	private TextView dosingTv, typeOfPackageTv, warningTv, storageTv, interactionTv;
	private Button distribution1DetailBtn, distribution2DetailBtn, distribution3DetailBtn, distribution1Sell, distribution2Sell, distribution3Sell;
	private SMDWebserviceClient dbLoader;
	private ArrayList<Pharmacy> pharmacy;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.medicine_detail);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
        TextView tv = (TextView) findViewById(R.id.txtView);
        tv.setText("THÔNG TIN THUỐC");
        initUI();

        // Handle object return from another activity
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
        	Medicine medicine = (Medicine) extras.getSerializable(SearchMedicineActivity.MESSAGE);
        	nameTv.setText(medicine.getName());
        	manufacturerTv.setText(medicine.getManufacture());
        	ingredientTv.setText(medicine.getIngredient());
        	indicationTv.setText(medicine.getIndication());
        	contraindicationTv.setText(medicine.getContraindication());
        	dosingTv.setText(medicine.getDosingAndUse());
        	String typeOfPackage = "";
        	dbLoader = new SMDWebserviceClient();
        	if (isOnline()) {
	        	try {
	        		typeOfPackage = dbLoader.loadTypeOfPackageFromWS(medicine.getMedicineId());
	        	} catch (Exception ex) {
	        		ex.printStackTrace();
	    			showAlertServerConnection();
	        	}
        	} else {
        		showAlertInternetConnection();
        	}
        	typeOfPackageTv.setText(typeOfPackage);
        	if (!medicine.getWarning().equals("anyType{}")) {
        		warningTv.setText(medicine.getWarning());
        	}
        	if (!medicine.getStorage().equals("anyType{}")) {
        		storageTv.setText(medicine.getStorage());
        	}
        	if (!medicine.getInteraction().equals("anyType{}")) {
        		interactionTv.setText(medicine.getInteraction());
        	}
        	int medicineId = medicine.getMedicineId();
        	pharmacy = new ArrayList<Pharmacy>();
        	if (isOnline()) {
	        	try {
	        		pharmacy = dbLoader.loadPharmacyDataFromWS(medicineId);
	        	} catch (Exception ex) {
	        		ex.printStackTrace();
	    			showAlertServerConnection();
	        	}
        	} else {
        		showAlertInternetConnection();
        	}
        	// fix imagePath = picture link

        	Properties prop = new Properties();
    		try {
    			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("application.properties"));
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    		String HOST_NAME = prop.getProperty("smd.host.name");
    		String APP_NAME = prop.getProperty("smd.app.name");
    		String FOLDER_NAME = prop.getProperty("smd.folder.medicine.img");
    		String imgPath = HOST_NAME + APP_NAME + FOLDER_NAME + medicine.getImagePath();
        	AQuery aq = new AQuery(this);
            aq.id(R.id.medicineImage).image(imgPath, true, true, 120, 0);//50 = imageWidth
        }
        modifyDistribution(pharmacy);

        // Handle action click on reference link
        distribution1DetailBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent(MedicineDetailActivity.this, PharmacyDetailActivity.class);
				intent.putExtra(MESSAGE, pharmacy.get(0));
				startActivity(intent);
			}
		});

        distribution2DetailBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent(MedicineDetailActivity.this, PharmacyDetailActivity.class);
				intent.putExtra(MESSAGE,  pharmacy.get(1));
				startActivity(intent);
			}
		});

        distribution3DetailBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent(MedicineDetailActivity.this, PharmacyDetailActivity.class);
				intent.putExtra(MESSAGE,  pharmacy.get(2));
				startActivity(intent);
			}
		});

        distribution1Sell.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent(MedicineDetailActivity.this, MedicineSellingActivity.class);
				intent.putExtra(MESSAGE, pharmacy.get(0));
				startActivity(intent);
			}
		});

        distribution2Sell.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent(MedicineDetailActivity.this, MedicineSellingActivity.class);
				intent.putExtra(MESSAGE, pharmacy.get(1));
				startActivity(intent);
			}
		});

        distribution3Sell.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				intent = new Intent(MedicineDetailActivity.this, MedicineSellingActivity.class);
				intent.putExtra(MESSAGE, pharmacy.get(2));
				startActivity(intent);
			}
		});
	}

	/*
	 * go to find location function
	 */
	public void smToFindLocation(View view) {
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/*
	 * go to historical using function
	 */
	public void smToDiaryUsing(View view) {
		Intent _intent = new Intent(this, MedicationDiaryActivity.class);
		startActivity(_intent);
	}

	/*
	 * go to search medicine function
	 */
	public void mdToSearchMedicine(View view) {
		Intent _intent = new Intent(this, SearchMedicineActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to about us
	 */
	public void smToAboutUs(View view) {
		Intent _intent = new Intent(this, AboutUsActivity.class);
		startActivity(_intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * Get view
	 */
	public void initUI() {
		nameTv = (TextView) findViewById(R.id.name);
        manufacturerTv = (TextView) findViewById(R.id.manufacturerDetail);
        ingredientTv = (TextView) findViewById(R.id.ingredientDetail);
        indicationTv = (TextView) findViewById(R.id.indicationDetail);
        contraindicationTv = (TextView) findViewById(R.id.contraindicationDetail);
        dosingTv = (TextView) findViewById(R.id.dosingDetail);
        typeOfPackageTv = (TextView) findViewById(R.id.typeOfPackageDetail);
        warningTv = (TextView) findViewById(R.id.warningDetail);
        storageTv = (TextView) findViewById(R.id.storageDetail);
        interactionTv = (TextView) findViewById(R.id.interactionDetail);
        distribution1 = (TextView) findViewById(R.id.distribution1);
        distribution2 = (TextView) findViewById(R.id.distribution2);
        distribution3 = (TextView) findViewById(R.id.distribution3);
        distribution1DetailBtn = (Button) findViewById(R.id.distributionDetail1);
        distribution2DetailBtn = (Button) findViewById(R.id.distributionDetail2);
        distribution3DetailBtn = (Button) findViewById(R.id.distributionDetail3);
        distribution1Sell = (Button) findViewById(R.id.distributionSell1);
        distribution2Sell = (Button) findViewById(R.id.distributionSell2);
        distribution3Sell = (Button) findViewById(R.id.distributionSell3);
	}

	/*
	 * Modify number pharmacy distribution item
	 */
	public void modifyDistribution(ArrayList<Pharmacy> pharmacyDTO) {
		if (pharmacyDTO == null || pharmacyDTO.size() == 0) {
        	distribution1.setText("Không có");
        	distribution2.setVisibility(View.GONE);
        	distribution3.setVisibility(View.GONE);
        	distribution1DetailBtn.setVisibility(View.GONE);
        	distribution2DetailBtn.setVisibility(View.GONE);
        	distribution3DetailBtn.setVisibility(View.GONE);
        	distribution1Sell.setVisibility(View.GONE);
        	distribution2Sell.setVisibility(View.GONE);
        	distribution3Sell.setVisibility(View.GONE);
        } else if (pharmacyDTO.size() == 1) {
        	distribution1.setText(pharmacyDTO.get(0).getPharmacyName());
        	distribution2.setVisibility(View.GONE);
        	distribution3.setVisibility(View.GONE);
        	distribution2DetailBtn.setVisibility(View.GONE);
        	distribution3DetailBtn.setVisibility(View.GONE);
        	distribution2Sell.setVisibility(View.GONE);
        	distribution3Sell.setVisibility(View.GONE);
        } else if (pharmacyDTO.size() == 2) {
        	distribution1.setText(pharmacyDTO.get(0).getPharmacyName());
        	distribution2.setText(pharmacyDTO.get(1).getPharmacyName());
        	distribution3.setVisibility(View.GONE);
        	distribution3DetailBtn.setVisibility(View.GONE);
        	distribution3Sell.setVisibility(View.GONE);
        } else if (pharmacyDTO.size() == 3) {
        	distribution1.setText(pharmacyDTO.get(0).getPharmacyName());
        	distribution2.setText(pharmacyDTO.get(1).getPharmacyName());
        	distribution3.setText(pharmacyDTO.get(2).getPharmacyName());
        }
	}

	/* Check internet connection */
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/*
	 * Warning message when connection fail
	 */
	public void showAlertServerConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(MedicineDetailActivity.this);
		_builder.setTitle("Lỗi kết nối với máy chủ !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(MedicineDetailActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}

	/*
	 * Warning message when connection fail
	 */
	public void showAlertInternetConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(MedicineDetailActivity.this);
		_builder.setTitle("Lỗi kết nối mạng !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(MedicineDetailActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}
}
