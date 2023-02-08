package com.example.finedu;

public class bookModal {
    String image,name,pdf;

    public bookModal() {
    }

    public bookModal(String image, String name, String pdf) {
        this.image = image;
        this.name = name;
        this.pdf = pdf;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
