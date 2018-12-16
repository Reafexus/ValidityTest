public class LineData {
    private int id;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String address1;
    private String address2;
    private String zip;
    private String city;
    private String stateLong;
    private String state;
    private String phone;

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public String getCompany(){
        return company;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress1(){
        return address1;
    }
    public String getAddress2(){
        return address2;
    }
    public String getZip(){
        return zip;
    }
    public String getCity(){
        return city;
    }
    public String getStateLong(){
        return stateLong;
    }
    public String getState(){
        return state;
    }
    public String getPhone(){
        return phone;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setFirstName(String firstName ){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setCompany(String company){
        this.company = company;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setAddress1(String address1){
        this.address1 = address1;
    }
    public void setAddress2(String address2){
        this.address2 = address2;
    }
    public void setZip(String zip){
        this.zip = zip;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setStateLong(String stateLong){
        this.stateLong = stateLong;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
}