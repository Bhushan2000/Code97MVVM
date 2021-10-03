package com.example.code97mvvm.ui.users.createUser;

public class CreateResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String registerDate;
    private String updatedDate;

    public CreateResponse(String id, String firstName, String lastName, String email, String registerDate, String updatedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registerDate = registerDate;
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    //    {
//        "id": "61570338c844712e1faca8f6",
//            "firstName": "Bh",
//            "lastName": "bh",
//            "email": "bhbh@gmail.com",
//            "registerDate": "2021-10-01T12:46:48.840Z",
//            "updatedDate": "2021-10-01T12:46:48.840Z"
//    }


}
