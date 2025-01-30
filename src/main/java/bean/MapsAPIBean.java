package bean;

import java.util.List;

public class MapsAPIBean {

    private String fixedAddress;
    private List<String> addresses;

    public MapsAPIBean(String fixedAddress, List<String> addresses) {
        this.fixedAddress = fixedAddress;
        this.addresses = addresses;
    }

    public String getFixedAddress() {
        return fixedAddress;
    }

    public void setFixedAddress(String fixedAddress) {
        this.fixedAddress = fixedAddress;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }


}
