package com.example.finedu;

public class modal {
    String module_name,pdf;

    public modal() {
    }

    public modal(String module_name, String pdf, String pdfHindi) {
        this.module_name = module_name;
        this.pdf = pdf;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
