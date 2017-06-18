package com.example.arieshu.firebasedatabasetest;

class Profile {

    private String addr;
    private String name;
    private String phone;

    public Profile() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Profile(String addr, String name, String phone) {
        this.addr = addr;
        this.name = name;
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
