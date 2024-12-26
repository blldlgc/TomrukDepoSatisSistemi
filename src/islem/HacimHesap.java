package islem;

public class HacimHesap {
    //Kabuksuz tomruk hacmi hesaplar.
    public double HacimHesap(double cap , double boy, double adet){
        double r = cap / 2;
        double hacim = adet * r * Math.PI * r * boy;
        return hacim;
    }
    
    //Kabuklu tomruk hacmi hesaplar.
    public double HacimHesap(double cap , double kabuk , double boy, double adet){
        double r = cap / 2;
        r = r - kabuk;
        double hacim = adet * Math.PI * r * r *boy;
        return hacim;
    }
}
