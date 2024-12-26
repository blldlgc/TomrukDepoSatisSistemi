package ekranlar;

public class Kullanici {
    private String ad;
    private String sifre;
    private String erisimSeviyesi;

    public Kullanici(String ad, String sifre, String erisimSeviyesi) { // verileri çağırmaya yarayan kod
        this.ad = ad;
        this.sifre = sifre;
        this.erisimSeviyesi = erisimSeviyesi;
    }

    // kullanıcı girişi için gerekli set ve get metodları
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getErisimSeviyesi() {
        return erisimSeviyesi;
    }

    public void setErisimSeviyesi(String erisimSeviyesi) {
        this.erisimSeviyesi = erisimSeviyesi;
    }

    @Override
    public String toString() {
        return ad + "," + sifre + "," + erisimSeviyesi;
    }
}
