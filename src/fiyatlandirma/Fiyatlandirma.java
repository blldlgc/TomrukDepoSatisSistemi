package fiyatlandirma;

abstract class Fiyatlandirma {
    private double birimFiyat;
    private double hacim;

    public Fiyatlandirma(double birimFİyat, double hacim) {
        this.birimFiyat = birimFİyat;
        this.hacim = hacim;
    }
    public double istifFiyatiniHesapla() {// istif fiyatını döndürür
        return birimFiyat * hacim;
    }
}
