package com.example.myapplication;

public class Currency {
        private String name;
        private String nominal;
        private String numCode;
        private String charCode;
        private String value;
        int Image;

    public Currency(String name, String charCode, String value, int Image) {
        this.name = name;
        this.charCode = charCode;
        this.value = value;
        this.Image = Image;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumCode() {
            return numCode;
        }

        public void setNumCode(String numCode) {
            this.numCode = numCode;
        }

        public String getCharCode() {
            return charCode;
        }

        public void setCharCode(String charCode) {
            this.charCode = charCode;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getNominal() {
            return nominal;
        }
        public void setNominal(String nominal) {
            this.nominal = nominal;
        }

        public String toString(){
            return name + "\n" + nominal + "\n" + numCode + "\n" + charCode + "\n" + value + "\n";
        }
}
