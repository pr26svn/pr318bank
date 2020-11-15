package space.dorzhu.test;

public class Course {
    private String numCode;
    private  String charCode;
    private String nominal;
    private String name;
    private String value;
    private Double sell;

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

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSell() {
        double a = Double.parseDouble(value);
        double b = 0.1;
        double c = (a+(a*b));
        return (int) c;
    }

    public String result(){
        return name + " - " + name + " - " + nominal + " - " + value;
    }
}