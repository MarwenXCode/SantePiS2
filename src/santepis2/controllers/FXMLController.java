/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();

    @FXML
    protected GoogleMapView mapView;

    @FXML
    protected TextField fromTextField;

    @FXML
    protected TextField toTextField;

    @FXML
    private void toTextFieldAction(ActionEvent event) {
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.WALKING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
        request.setOpt(true);
        
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        options.center(new LatLong(36.8189700, 10.1657900))
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(true)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }

}