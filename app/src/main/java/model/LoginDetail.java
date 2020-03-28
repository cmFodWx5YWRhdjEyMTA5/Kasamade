package model;


import java.io.Serializable;

/**
 * Created by Dhanu on 03-08-2015.
 */
public class LoginDetail implements Serializable{
    // private variables
    int id;



    int reg_id;



    String orgnization_name;
    //@SerializedName("m_name")
    String mobile_no;
    String m_name;
    String firstname;
    String middlename;
    String lastname;

    String qualification;



    String qulification_status;
    String address;
    String home_email;
    String in_mmcop_from;
    String in_mmcop_to;
    String qualification_achieved;
    String marital_status;
    String spouse_name;
    String number_of_children;

    String hobbies;
    String achived_in_career;
    String designation;


    String field;
    String period_from;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    String flag;

    String image;
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    String identity;



    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

/*
    public String getQualification_status() {
        return qualification_status;
    }

    public void setQualification_status(String qualification_status) {
        this.qualification_status = qualification_status;
    }
*/
public String getQulification_status() {
    return qulification_status;
}

    public void setQulification_status(String qulification_status) {
        this.qulification_status = qulification_status;
    }

    public String getQulification_year() {
        return qulification_year;
    }

    public void setQulification_year(String qulification_year) {
        this.qulification_year = qulification_year;
    }

    String qulification_year;
/*
    public String getQualification_year() {
        return qualification_year;
    }

    public void setQualification_year(String qualification_year) {
        this.qualification_year = qualification_year;
    }*/

   // String qualification_year;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getHome_email() {
        return home_email;
    }

    public void setHome_email(String home_email) {
        this.home_email = home_email;
    }

    public String getOrgnization_name() {
        return orgnization_name;
    }

    public void setOrgnization_name(String orgnization_name) {
        this.orgnization_name = orgnization_name;
    }

    public String getIn_mmcop_from() {
        return in_mmcop_from;
    }

    public void setIn_mmcop_from(String in_mmcop_from) {
        this.in_mmcop_from = in_mmcop_from;
    }



    public String getIn_mmcop_to() {
        return in_mmcop_to;
    }

    public void setIn_mmcop_to(String in_mmcop_to) {
        this.in_mmcop_to = in_mmcop_to;
    }



    public String getQualification_achieved() {
        return qualification_achieved;
    }

    public void setQualification_achieved(String qualification_achieved) {
        this.qualification_achieved = qualification_achieved;
    }



    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }



    public String getSpouse_name() {
        return spouse_name;
    }

    public void setSpouse_name(String spouse_name) {
        this.spouse_name = spouse_name;
    }



    public String getNumber_of_children() {
        return number_of_children;
    }

    public void setNumber_of_children(String number_of_children) {
        this.number_of_children = number_of_children;
    }



    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }


    public String getAchived_in_career() {
        return achived_in_career;
    }

    public void setAchived_in_career(String achived_in_career) {
        this.achived_in_career = achived_in_career;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


/*
    public String getOrgnization_name() {
        return Orgnization_name;
    }

    public void setOrgnization_name(String Orgnization_name) {
        this.Orgnization_name = Orgnization_name;
    }*/


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getPeriod_from() {
        return period_from;
    }

    public void setPeriod_from(String period_from) {
        this.period_from = period_from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getReg_id() {
        return reg_id;
    }

    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }
    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {this.mobile_no = mobile_no;}

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    // Empty constructor
    public LoginDetail(int id, String mobile_no, String m_name, String firstname, String middlename, String lastname, String qulification_status, String qualification, String address, String home_email, String hobbies, String designation, String orgnization_name, String field, String period_from, String image, String flag ) {
        this.id = id;

        this.mobile_no=mobile_no;
        this.m_name = m_name;
        this.firstname=firstname;
        this.middlename=middlename;
        this.lastname=lastname;
        this.qulification_status=qulification_status;

        this.qualification=qualification;
        this.address=address;
        this.hobbies=hobbies;
        this.designation=designation;
        this.home_email=home_email;
        this.orgnization_name=orgnization_name;
        this.field=field;
        this.period_from=period_from;
        this.image=image;
        this.flag=flag;

    }
    public LoginDetail(int reg_id, String mobile_no) {
        this.reg_id = reg_id;
        this.mobile_no=mobile_no;

    }
    /*
    public MyProfileDetail(int id,String mobile_no, String m_name, String firstname, String middlename, String lastname, String qualification, String qualification_status, String address, String home_email,String in_mmcop_from,String in_mmcop_to,String qualification_achieved,String marital_status,String spouse_name, String number_of_children, String hobbies,String achived_in_career,String designation,String orgnization_name,String field, String period_from, String m_mob_one, String m_spouse, String m_children, String m_email, String m_voter_id, int m_voter_list_no, String m_mob_two, String m_image) {
        this.id = id;

        this.mobile_no=mobile_no;
        this.m_name = m_name;
        this.firstname=firstname;
        this.middlename=middlename;
        this.lastname=lastname;
        this.qualification = qualification;
        this.qualification_status = qualification_status;
        this.address = address;
        this.home_email = home_email;
        this.in_mmcop_from = in_mmcop_from;
        this.in_mmcop_to = in_mmcop_to;
        this.qualification_achieved = qualification_achieved;
        this.marital_status = marital_status;
        this.spouse_name = spouse_name;
        this.number_of_children = number_of_children;
        this.hobbies = hobbies;
        this.achived_in_career = achived_in_career;
        this.designation = designation;
        this.Orgnization_name = orgnization_name;
        this.field = field;
        this.period_from = period_from;

    }
*/
    public LoginDetail(String mobile_no, String m_name, String firstname, String middlename, String lastname, String m_birthdate, String m_edu, String m_father, String m_grandpa, String landmark_gav, String village_gav, String tal_gav, String dist_gav, String state_gav, String m_jatha, String landmark, String village, String tal, String dist, String state, String m_ocu, String m_mob_one, String m_spouse, String m_children, String m_email, String m_voter_id, int m_voter_list_no, String m_mob_two, String m_image) {
        this.mobile_no=mobile_no;
        this.m_name = m_name;
        this.firstname=firstname;
        this.middlename=middlename;
        this.lastname=lastname;
    }


}
