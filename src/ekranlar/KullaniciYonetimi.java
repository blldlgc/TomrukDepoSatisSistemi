package ekranlar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Kullanıcı yönetim ekranı
public class KullaniciYonetimi {
    private List<Kullanici> kullanicilar;
    private String dosyaAdi = "src/arsiv/kullanicilar.csv";

    public KullaniciYonetimi() { // Kullanıları diziye aktarma
        this.kullanicilar = new ArrayList<>();
        kullanicilariYukle();
    }

    private void kullanicilariYukle() { // kullanıcıları csv dosyasından okuyup virgüle göre parçalayan kod
        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = okuyucu.readLine()) != null) {
                String[] parcalar = satir.split(",");
                if (parcalar.length == 3) {
                    String ad = parcalar[0];
                    String sifre = parcalar[1];
                    String erisimSeviyesi = parcalar[2];
                    kullanicilar.add(new Kullanici(ad, sifre, erisimSeviyesi));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void kullaniciEkle(String ad, String sifre, String erisimSeviyesi) { // yeni kullanıcı ekleme
        Kullanici yeniKullanici = new Kullanici(ad, sifre, erisimSeviyesi);
        kullanicilar.add(yeniKullanici);
        kullanicilariKaydet();
    }

    public void kullaniciSil(String ad) { // kullanıcı silme
        boolean kullaniciBulundu = kullanicilar.removeIf(kullanici -> kullanici.getAd().equals(ad));
        kullanicilariKaydet();

        if (kullaniciBulundu) {
            System.out.println(ad + " adlı kullanıcı silindi.");
        } else {
            System.out.println("Böyle bir kullanıcı bulunmamaktadır. Lütfen geçerli bir kullanıcı adı giriniz.");
        }
    }

    private void kullanicilariKaydet() { // yapılan değişiklikleri kaydeden kod
        try (BufferedWriter yazici = new BufferedWriter(new FileWriter(dosyaAdi))) {
            for (Kullanici kullanici : kullanicilar) {
                yazici.write(kullanici.toString());
                yazici.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Kullanici> getKullanicilar() {
        return kullanicilar;
    }

    public class YetkiSeviyesiAl { // kullanıcının yetki seviyesini atayan kod (iç içe class)
        public static void main(String[] args) {
            String yetki = yetkiSeviyesiAl();
            System.out.println("Seçilen yetki: " + yetki);
        }

        public static String yetkiSeviyesiAl() {
            Scanner scanner = new Scanner(System.in);
            String yetki = null;
            boolean gecerliSecim = false;

            while (!gecerliSecim) {
                System.out.print("Yetki seviyesini giriniz: \n 1:Admin 2:İşçi 3: Müşteri\n");

                int secim2 = scanner.nextInt();

                switch (secim2) {
                    case 1:
                        yetki = "admin";
                        gecerliSecim = true;
                        break;
                    case 2:
                        yetki = "isci";
                        gecerliSecim = true;
                        break;
                    case 3:
                        yetki = "musteri";
                        gecerliSecim = true;
                        break;
                    default:
                        System.out.println("Geçersiz seçim. Lütfen 1, 2 veya 3 giriniz.");
                        break;
                }
            }
            return yetki;
        }
    }

    public void yonetimEkrani() { // kullanıcı yönetim ekranı
        System.out.println("\nKullanıcı Yönetim Ekranı");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nYapmak istediğiniz işlemi seçiniz:");
            System.out.println("1:Kullanıcıları Görüntüle 2:Kullanıcı Ekle 3:Kullanıcı Sil 0:İptal");
            System.out.print("Seçim: ");
            int secim = scanner.nextInt();

            if (secim == 0) {
                System.out.println("Sistemden çıkış yapılıyor...");
                break;
            }

            switch (secim) {
                case 1: // kullanıcıları görüntüleme
                    List<Kullanici> kullaniciListesi = getKullanicilar();
                    for (Kullanici kullanici : kullaniciListesi) {
                        System.out.println(kullanici.getAd() + ", " + kullanici.getSifre() + ", " + kullanici.getErisimSeviyesi());
                    }
                    break;
                case 2: // kullanıcı ekleme
                    System.out.print("Yeni kullanıcı adınızı girin: ");
                    String yeniKullaniciAdi = scanner.next();

                    System.out.print("Yeni şifrenizi girin: ");
                    String yeniSifre = scanner.next();

                    String yetki = YetkiSeviyesiAl.yetkiSeviyesiAl();

                    kullaniciEkle(yeniKullaniciAdi, yeniSifre, yetki);
                    break;
                case 3: // kullanıcı silme
                    System.out.print("Silmek istediğiniz kullanıcının kullanıcı adını girin: ");
                    String kullaniciAdi = scanner.next();
                    kullaniciSil(kullaniciAdi);
                    break;
            }


        }
    }
}
