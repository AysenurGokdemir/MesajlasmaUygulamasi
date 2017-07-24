package example.com.msj;

/**
 * Created by selin on 7/8/17.
 */

public class Mesaj {

    String mesajText, gonderici,zaman;

    public Mesaj(){}


    public Mesaj(String mesajText, String gonderici, String zaman) {
        this.mesajText = mesajText;
        this.gonderici = gonderici;
        this.zaman = zaman;
    }

    public String getMesajText() {
        return mesajText;
    }

    public void setMesajText(String mesajText) {
        this.mesajText = mesajText;
    }

    public String getGonderici() {
        return gonderici;
    }

    public void setGonderici(String gonderici) {
        this.gonderici = gonderici;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }
}
