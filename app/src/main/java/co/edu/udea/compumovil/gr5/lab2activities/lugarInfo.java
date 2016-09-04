package co.edu.udea.compumovil.gr5.lab2activities;

/**
 * Created by Lina on 03/09/2016.
 */
public class lugarInfo {
    int _id;
    String _name;
    String _description;
    String _info_general;
    double _temp, _lat,_long,_rate;

    public double get_rate() {
        return _rate;
    }

    public void set_rate(double _rate) {
        this._rate = _rate;
    }

    public lugarInfo() {
    }

    public lugarInfo(int _id, String _name, String _description, String _info_general, double _temp, double _lat, double _long, double _rate, int _image) {
        this._id = _id;
        this._name = _name;
        this._description = _description;
        this._info_general = _info_general;
        this._temp = _temp;
        this._lat = _lat;
        this._long = _long;
        this._rate = _rate;
        this._image = _image;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_info_general() {
        return _info_general;
    }

    public void set_info_general(String _info_general) {
        this._info_general = _info_general;
    }

    public double get_temp() {
        return _temp;
    }

    public void set_temp(double _temp) {
        this._temp = _temp;
    }

    public double get_lat() {
        return _lat;
    }

    public void set_lat(double _lat) {
        this._lat = _lat;
    }

    public double get_long() {
        return _long;
    }

    public void set_long(double _long) {
        this._long = _long;
    }

    public int get_image() {
        return _image;
    }

    public void set_image(int _image) {
        this._image = _image;
    }

    int _image;
}
