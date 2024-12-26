package agac;

public class AgacFactory {
    //sevk girme metodunda csv dosyasından okunan ağaçların değişkenlere atanması
    public static Agac createAgac(String tur, double cap, double kabuk, double boy, double hacim, int adet){
        switch (tur) {
            case "cam":
                return new CamAgac(cap, kabuk, boy, hacim, adet);
            case "mese":
                return new MeseAgac(cap, kabuk, boy, hacim, adet);
            case "kayin":
                return new KayinAgac(cap, kabuk, boy, hacim, adet);
            case "ladin":
                return new LadinAgac(cap, kabuk, boy, hacim, adet);
            default:
                throw new IllegalArgumentException("Geçersiz ağaç türü: " + tur);
        }
    }
}
