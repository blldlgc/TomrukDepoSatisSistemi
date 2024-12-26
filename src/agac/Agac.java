package agac;

import ekranlar.SevkGirme;
import fiyatlandirma.*;

import java.util.Locale;

public class Agac {
    private String tur;
    private double cap;
    private double kabuk;
    private double boy;
    private double hacim;
    private int adet;
    private double fiyat;

    public Agac(String tur, double cap, double kabuk, double boy, double hacim, int adet) {
        this.tur = tur;
        this.cap = cap;
        this.kabuk = kabuk;
        this.boy = boy;
        this.hacim = hacim;
        this.adet = adet;
    }

    // ağaç verileriyle ilgili get metodlarının tanımlanması
    public String getTur() {
        return tur;
    }

    public double getCap() {
        return cap;
    }

    public double getKabuk() {
        return kabuk;
    }

    public double getBoy() {
        return boy;
    }

    public double getHacim() {
        return hacim;
    }

    public int getAdet() {
        return adet;
    }

    public double getFiyat(){ //ağaç istif fiyatlarının hesaplanması
        String tur = getTur();
        switch (tur){
            case "cam": Cam cam = new Cam(getHacim());
                fiyat = cam.istifFiyatiniHesapla();
                break;
            case "mese": Mese mese = new Mese(getHacim());
                fiyat = mese.istifFiyatiniHesapla();
                break;
            case "kayin": Kayin kayin = new Kayin(getHacim());
                fiyat = kayin.istifFiyatiniHesapla();
                break;
            case "ladin": Ladin ladin = new Ladin(getHacim());
                fiyat = ladin.istifFiyatiniHesapla();
                break;
        }
        double yuvarlanmisFiyat = Math.round(fiyat * 100.0) / 100.0; // fiyat bilgisini virgülden sonraki 2 basamağı siler
        return yuvarlanmisFiyat;
    }

    public String toCsvString() {
        return String.format(Locale.US, "%s,%.2f,%.2f,%.2f,%.4f,%d", tur, cap, kabuk, boy, hacim, adet);
    }

    public void saveToCsv(String fileName) {
        SevkGirme sevk = new SevkGirme();

        sevk.csvDosyasinaAgacEkle(this, fileName);
    }
}
