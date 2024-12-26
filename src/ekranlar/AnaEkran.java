package ekranlar;
import islem.*;

import java.util.Scanner;

public class AnaEkran {
    //Sisteme giriş ekranı, kullanıcıya üyelik ve giriş ekranı gösteriyor
    public void AnaEkran() {
        GirisVeKayit girisVeKayit = new GirisVeKayit();
        while (true) {
            System.out.println("\nOrman İşletme Depo Yönetim Bilgi Sistemi'ne Hoşgeldiniz! ");
            System.out.println("\n Giriş yapınız veya üye olunuz!");

            girisVeKayit.GirisVeKayit();
            String yetki = girisVeKayit.getYetki();
            int secim = girisVeKayit.getSecim();
            if (secim == 3) {
                break;
            } else {
                if (yetki != null) {
                    switch (yetki) {
                        case "admin":
                            adminEkrani();
                            break;
                        case "isci":
                            isciEkrani();
                            break;
                        case "musteri":
                            musteriEkrani();
                            break;
                    }
                } else {
                    System.out.println("Giriş sırasında bir hata oluştu. Yetki bilgisi alınamadı.");
                }
            }
        }
    }

    private void adminEkrani(){ //Yönetici ekranı arayüzünü kullanıcıya sunar. İşlemler için seçim ekranı oluşturur.
        System.out.println("Admin ekranına hoşgeldiniz");
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\nYapmak istediğiniz işlemi seçiniz:");
            System.out.println("1 - Sevk Girme \n2 - Ağaç Bilgilerini Görüntüleme\n3 - Ağaç Sayısı Sorgulama\n4 - Kullanıcı Yönetimi Ekranı\n5 - Ağaç Kullanım Alanı Sorgulama\n0 - Çıkış");
            System.out.print("Seçim: ");
            int secim = scanner.nextInt();

            if (secim == 0) {
                System.out.println("Sistemden çıkış yapılıyor...");
                break;
            }

            switch (secim){
                case 1:
                    SevkGirme sevk = new SevkGirme();
                    sevk.sevkAl();
                    break;
                case 2:
                    SevkGirme sevk2 = new SevkGirme();
                    sevk2.sevkYazdir();
                    break;
                case 3:
                    AgacSayaci agacSayi = new AgacSayaci();
                    agacSayi.AgacSayaci();
                    break;
                case 4:
                    KullaniciYonetimi yonetim = new KullaniciYonetimi();
                    yonetim.yonetimEkrani();
                    break;
                case 5:
                    Istifleme istifleme = new Istifleme();
                    System.out.println("Görmek istediğiniz türü seçiniz: \n 1:Çam 2:Meşe 3:Kayin 4:Ladin");
                    int tur = scanner.nextInt();
                    if (tur == 1){istifleme.tomrukOkuma("src/arsiv/cam.csv");}
                    else if (tur == 2){istifleme.tomrukOkuma("src/arsiv/mese.csv");}
                    else if (tur == 3){istifleme.tomrukOkuma("src/arsiv/kayin.csv");}
                    else if (tur == 4){istifleme.tomrukOkuma("src/arsiv/ladin.csv");}
                    else {
                        System.out.println("Hatalı Seçim Yaptınız");
                    }

                    break;

            }

        }
    }

    private void isciEkrani(){ //İşçi ekranı arayüzünü kullanıcıya sunar. İşlemler için seçim ekranı oluşturur.
        System.out.println("İşçi ekranına hoşgeldiniz");
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\nYapmak istediğiniz işlemi seçiniz:");
            System.out.println("1 - Sevk Girme \n2 - Ağaç Sayısı Sorgulama\n0 - Çıkış");
            System.out.print("Seçim: ");
            int secim = scanner.nextInt();

            if (secim == 0) {
                System.out.println("Sistemden çıkış yapılıyor...");
                break;
            }

            switch (secim){
                case 1:
                    SevkGirme sevk = new SevkGirme();
                    sevk.sevkAl();
                    break;
                case 2:
                    AgacSayaci agacSayi = new AgacSayaci();
                    agacSayi.AgacSayaci();
                    break;
            }

        }
    }

    private void musteriEkrani(){ //Müşteri ekranı arayüzünü kullanıcıya sunar. İşlemler için seçim ekranı oluşturur.
        System.out.println("Müşteri ekranına hoşgeldiniz");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\nYapmak istediğiniz işlemi seçiniz:");
            System.out.println("1 - Satın Alma \n2 - Odun İstif Sayısı Görüntüleme\n0 - Çıkış");
            System.out.print("Seçim: ");
            int secim = scanner.nextInt();

            if (secim == 0) {
                System.out.println("Sistemden çıkış yapılıyor...");
                break;
            }

            switch (secim){
                case 1:
                    SatisEkrani satis = new SatisEkrani();
                    satis.Satis();
                    satis.bilgiAlma();
                    break;
                case 2:
                    AgacSayaci agacSayi = new AgacSayaci();
                    agacSayi.AgacSayaci();
                    break;
            }

        }

    }
}
