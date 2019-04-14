package com.mawwad.jobfinder.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import com.mawwad.jobfinder.BuildConfig;
import com.mawwad.jobfinder.Data.DataLoader;
import com.mawwad.jobfinder.R;
import com.mawwad.jobfinder.model.Filter;
import com.mawwad.jobfinder.model.Provider;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.ganfra.materialspinner.MaterialSpinner;

public class MainActivity extends AppCompatActivity {



    private List<Provider> providerList;

    @BindView(R.id.find_btn)
    Button findButton ;
    @BindView(R.id.loadingPanel)
    LinearLayout loadingView;
    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView availablePositionAutoComplete;
    @BindView(R.id.spinner)
    MaterialSpinner providerSpinner ;
    private Filter filter = new Filter() ;

    @OnClick(R.id.find_btn)
    public void onFindButtonClicked(){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.FILTER_KEY,filter);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAutoCompletePlaces();
        initPositionsSelector();
        initProvidersSelector();
    }

    private void initProvidersSelector() {
        ArrayAdapter<Provider> adapter1 = new ArrayAdapter<Provider>(this, android.R.layout.simple_spinner_item, DataLoader.getInstance().providerList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        providerSpinner.setAdapter(adapter1);
        providerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position<0){
                    filter.setProvider(null);
                    return;
                }
                Provider provider = adapter1.getItem(position);
                filter.setProvider(provider);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initPositionsSelector() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_singlechoice, DataLoader.getInstance().availablePosition);
        //Getting the instance of AutoCompleteTextView
        availablePositionAutoComplete.setThreshold(1);//will start working from first character
        availablePositionAutoComplete.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        availablePositionAutoComplete.setTextColor(Color.RED);
        availablePositionAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = adapter.getItem(position);
                filter.setPosition(s);
            }
        });
    }

    private void initAutoCompletePlaces() {
        String apiKey = BuildConfig.ApiKey;

        Places.initialize(getApplicationContext(), apiKey);
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        assert autocompleteFragment != null;
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG));
        autocompleteFragment.setHint(getResources().getString(R.string.search_by_city));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                filter.setLat(String.valueOf(place.getLatLng().latitude));
                filter.setLng(String.valueOf(place.getLatLng().longitude));
                filter.setLatLng(String.valueOf(place.getLatLng()));
            }

            @Override
            public void onError(Status status) {
            }
        });
    }


}
