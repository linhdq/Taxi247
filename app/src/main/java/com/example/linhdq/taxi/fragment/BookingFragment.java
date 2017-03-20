package com.example.linhdq.taxi.fragment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.adapter.CarTypePagerAdapter;
import com.example.linhdq.taxi.interf.OnItemSelectedRecyclerView;
import com.example.linhdq.taxi.model.CarTypeModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by LinhDQ on 12/25/16.
 */

public class BookingFragment extends Fragment implements View.OnClickListener, OnItemSelectedRecyclerView, OnMapReadyCallback, GoogleMap.OnCameraIdleListener, GoogleMap.OnMyLocationButtonClickListener {
    //view
    private TextView listTab[];
    private ViewPager viewPagerCarType;
    private LinearLayout layoutCarType;
    //
    private Context context;
    //map
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    //
    private int currentPageIndex;
    private boolean isShowPrice;
    //adapter
    private CarTypePagerAdapter carTypePagerAdapter;
    //
    private CarTypeModel listCarCity[];
    private CarTypeModel listCarAirPort[];
    private CarTypeModel listCarTCCar[];
    //fragment
    private ChildCarTypeFragment listFragment[];
    //aniamtion
    private ObjectAnimator transLayoutBottomDown;
    private ObjectAnimator transLayoutBottomUp;

    //Location
    private Marker marker;
    private MarkerOptions markerOptions;
    private LatLng currentPosition;
    private String locationInformation;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        init(view);
        addListener();
        initCarType();
        configViewPager();
        initMap();

        return view;
    }

    private void init(View view) {
        //view
        listTab = new TextView[3];
        listTab[0] = (TextView) view.findViewById(R.id.tab_city);
        listTab[1] = (TextView) view.findViewById(R.id.tab_airport);
        listTab[2] = (TextView) view.findViewById(R.id.tab_tccar);
        viewPagerCarType = (ViewPager) view.findViewById(R.id.car_type_viewpager);
        layoutCarType = (LinearLayout) view.findViewById(R.id.layout_car_type);
        //
        context = view.getContext();
        //
        currentPageIndex = 0;
        isShowPrice = false;
    }

    private void addListener() {
        for (TextView tab : listTab) {
            tab.setOnClickListener(this);
        }
        viewPagerCarType.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        layoutCarType.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //animation
                transLayoutBottomDown =
                        ObjectAnimator.ofFloat(layoutCarType, View.TRANSLATION_Y, layoutCarType.getHeight() / 2);
                transLayoutBottomUp =
                        ObjectAnimator.ofFloat(layoutCarType, View.TRANSLATION_Y, 0);
                if (!isShowPrice) {
                    transLayoutBottomDown.start();
                }
            }
        });
    }

    private void initCarType() {
        //car in city
        listCarCity = new CarTypeModel[4];
        listCarCity[0] = new CarTypeModel(R.drawable.car_default, getString(R.string.all));
        listCarCity[1] = new CarTypeModel(R.drawable.car3, getString(R.string._4_seats_small));
        listCarCity[2] = new CarTypeModel(R.drawable.car1, getString(R.string._5_seats));
        listCarCity[3] = new CarTypeModel(R.drawable.car2, getString(R.string._7_seats));
        //car in airport
        listCarAirPort = new CarTypeModel[3];
        listCarAirPort[0] = new CarTypeModel(R.drawable.car_default, getString(R.string.any_airport));
        listCarAirPort[1] = new CarTypeModel(R.drawable.car1, getString(R.string._4_seats_airport));
        listCarAirPort[2] = new CarTypeModel(R.drawable.car2, getString(R.string._7_seats_airport));
        //car TCCar
        listCarTCCar = new CarTypeModel[3];
        listCarTCCar[0] = new CarTypeModel(R.drawable.car_default, getString(R.string.any));
        listCarTCCar[1] = new CarTypeModel(R.drawable.car1, getString(R.string._4_seats));
        listCarTCCar[2] = new CarTypeModel(R.drawable.car2, getString(R.string._7_seats));
    }

    private void configViewPager() {
        listFragment = new ChildCarTypeFragment[3];
        listFragment[0] = new ChildCarTypeFragment();
        listFragment[1] = new ChildCarTypeFragment();
        listFragment[2] = new ChildCarTypeFragment();
        listFragment[0].setList(listCarCity);
        listFragment[1].setList(listCarAirPort);
        listFragment[2].setList(listCarTCCar);
        for (ChildCarTypeFragment f : listFragment) {
            f.setOnItemSelectedRecyclerView(this);
        }
        //
        carTypePagerAdapter = new CarTypePagerAdapter(getChildFragmentManager(), listFragment);
        viewPagerCarType.setAdapter(carTypePagerAdapter);
    }

    private void initMap() {
        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.google_map, mapFragment);
        fragmentTransaction.commit();
    }

    private void changeTabStatus(int newIndex) {
        listTab[currentPageIndex].setBackgroundColor(getResources().getColor(R.color.white));
        listTab[currentPageIndex].setTextColor(getResources().getColor(R.color.teal_600));
        currentPageIndex = newIndex;
        listTab[currentPageIndex].setBackgroundColor(getResources().getColor(R.color.orange_800));
        listTab[currentPageIndex].setTextColor(getResources().getColor(R.color.white));
        viewPagerCarType.setCurrentItem(currentPageIndex);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_city:
                changeTabStatus(0);
                break;
            case R.id.tab_airport:
                changeTabStatus(1);
                break;
            case R.id.tab_tccar:
                changeTabStatus(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void OnItemSelected(boolean isUp) {
        if (isUp && !isShowPrice) {
            transLayoutBottomUp.start();
            isShowPrice = true;
        } else if (!isUp) {
            transLayoutBottomDown.start();
            isShowPrice = false;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnCameraIdleListener(this);
        googleMap.setOnMyLocationButtonClickListener(this);
//        currentPosition = googleMap.getCameraPosition().target;
//        locationInformation = getLocationInfomation(context, currentPosition.latitude, currentPosition.longitude);
//        markerOptions = new MarkerOptions().position(currentPosition).title(locationInformation);
//        marker = googleMap.addMarker(markerOptions);
//        marker.showInfoWindow();
    }

    private String getLocationInfomation(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            String add = null;
            if (addresses.size() > 0) {
                Address obj = addresses.get(0);

                add = obj.getAddressLine(0);
            }
            return add;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onCameraIdle() {
//        Log.d("fucked", "fuck");
//        if (marker != null) {
//            marker.remove();
//            currentPosition = googleMap.getCameraPosition().target;
//            locationInformation = getLocationInfomation(context, currentPosition.latitude, currentPosition.longitude);
//            markerOptions.position(currentPosition).title(locationInformation);
//            marker = googleMap.addMarker(markerOptions);
//            marker.showInfoWindow();
//        }
    }

    private static void displayPromptForEnablingGPS(final Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "Do you want open GPS setting?";

        builder.setMessage(message)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                activity.startActivity(new Intent(action));
                                d.dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                d.cancel();
                            }
                        });
        builder.create().show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        if (!((LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE)).isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            displayPromptForEnablingGPS(getActivity());
            return true;
        } else {
            return false;
        }
    }

}
