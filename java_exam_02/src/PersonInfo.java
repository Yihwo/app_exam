public class PersonInfo {
    String name;
    String gender;
    String address;
    String school;
    String company;
    String phone_number;
    boolean phone_number_is_cellphone;
    boolean isStudent;


    PersonInfo(String aName, String aGender, String aAddress, String aCompany,
               String aSchool, String aPhone_number,boolean isCellPhone, boolean isStudent){
        name = aName;
        gender = aGender;
        address = aAddress;

        if (isStudent) {
            school = aSchool;
            company = "";
        }
        else{
            school = "";
            company = aCompany;
        }
        phone_number = aPhone_number;
        phone_number_is_cellphone = isCellPhone;
    }

}
