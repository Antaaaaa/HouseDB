public class House {
    private int houseId;
    private String district;
    private String address;
    private float area;
    private int roomNumber;
    private long price;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "House{" +
                "district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", room_number=" + roomNumber +
                ", price=" + price +
                '}';
    }

    public House(String district, String address, float area, int roomNumber, long price) {
        this.district = district;
        this.address = address;
        this.area = area;
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public House(int houseId, String district, String address, float area, int roomNumber, long price) {
        this.houseId = houseId;
        this.district = district;
        this.address = address;
        this.area = area;
        this.roomNumber = roomNumber;
        this.price = price;
    }
}
