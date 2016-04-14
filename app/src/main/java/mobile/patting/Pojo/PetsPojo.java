package mobile.patting.Pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by goodworklabs on 30/03/2016.
 */
public class PetsPojo {
    @SerializedName("id")
    int id;
    @SerializedName("user_id")
    int userId;
    @SerializedName("pet_type")
    private String petType;
    @SerializedName("category")
    private String petCate;
    @SerializedName("name")
    private String petNameTitle;
    @SerializedName("short_description")
    private String petDesc;
    @SerializedName("description")
    private String petBrfDesc;
    @SerializedName("pet_price")
    private String petPrice;
    @SerializedName("breed")
    private String petBreed;
    @SerializedName("size")
    private String petSize;
    @SerializedName("gender")
    private String petGender;
    @SerializedName("age")
    private String petAge;
    @SerializedName("location")
    private String location;
    @SerializedName("state")
    private String state;
    @SerializedName("city")
    private String city;
    @SerializedName("photos")
    private String photos;
    @SerializedName("url")
    private String detailedUrl;

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDetailedUrl() {
        return detailedUrl;
    }

    public void setDetailedUrl(String detailedUrl) {
        this.detailedUrl = detailedUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetCate() {
        return petCate;
    }

    public void setPetCate(String petCate) {
        this.petCate = petCate;
    }

    public String getPetDesc() {
        return petDesc;
    }

    public void setPetDesc(String petDesc) {
        this.petDesc = petDesc;
    }

    public String getPetNameTitle() {
        return petNameTitle;
    }

    public void setPetNameTitle(String petNameTitle) {
        this.petNameTitle = petNameTitle;
    }

    public String getPetBrfDesc() {
        return petBrfDesc;
    }

    public void setPetBrfDesc(String petBrfDesc) {
        this.petBrfDesc = petBrfDesc;
    }

    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

    public String getPetPrice() {
        return petPrice;
    }

    public void setPetPrice(String petPrice) {
        this.petPrice = petPrice;
    }
    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
