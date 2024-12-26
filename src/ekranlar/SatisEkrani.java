package ekranlar;

import agac.Agac;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SatisEkrani {
    public void SatisEkrani(){    }

    // Bireysel müşteri bilgilerini alır. Alınan biligileri dosyaya ekler.
    public static void musteriBilgileriniAl(String ad, String soyad, String tc) { // overloading
        System.out.println("Bireysel müşteri bilgileri alındı: " + ad + " " + soyad + " - TC: " + tc);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/arsiv/satinalma_bilgileri.csv", true))) {
            // CSV dosyasına bilgileri ekleyin
            writer.write(ad + "," + soyad + "," + tc);
            writer.newLine();
            System.out.println("Bilgiler başarıyla kaydedildi.");
        } catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        }
    }

    // Şirket müşterilerinin bilgilerini alır.Alınan biligileri dosyaya ekler.
    public static void musteriBilgileriniAl(String ad, String soyad, String tc, String sirketAdi) { // overloading
        System.out.println("Şirket müşterisi bilgileri alındı: " + ad + " " + soyad + " - TC: " + tc + " - Şirket Adı: " + sirketAdi);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/arsiv/satinalma_bilgileri.csv", true))) {
            // CSV dosyasına bilgileri ekleyin
            writer.write(ad + "," + soyad + "," + tc + "," + sirketAdi);
            writer.newLine();
            System.out.println("Bilgiler başarıyla kaydedildi.");
        } catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        }
    }

    // Şirket kullanıcılarının bilgilerini ve telefon numarasını. Alınan biligileri dosyaya ekler.
    public static void musteriBilgileriniAl(String ad, String soyad, String tc, String sirketAdi, String telefon) { // overloading
        System.out.println("Genel müşteri bilgileri alındı: " + ad + " " + soyad + " - TC: " + tc + " - Şirket Adı: " + sirketAdi + " - Telefon: " + telefon);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/arsiv/satinalma_bilgileri.csv", true))) {
            // CSV dosyasına bilgileri ekleyin
            writer.write(ad + "," + soyad + "," + tc + "," + sirketAdi + "," + telefon);
            writer.newLine();
            System.out.println("Bilgiler başarıyla kaydedildi.");
        } catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        }
    }

    public static void Satis() { //Satın almak istediği ağaç türü bilgisini müşteriden alır.
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Lütfen Satın Almak İstediğiniz Ağaç Türünü Seçiniz \n1:Çam 2:Meşe 3:Kayın 4:Ladin");
                System.out.print("Seçim: ");
                int selectedTur = scanner.nextInt();
                String dosya = null;
                switch (selectedTur) {
                    case 1:
                        dosya = "src/arsiv/cam.csv";
                        break;
                    case 2:
                        dosya = "src/arsiv/mese.csv";
                        break;
                    case 3:
                        dosya = "src/arsiv/kayin.csv";
                        break;
                    case 4:
                        dosya = "src/arsiv/ladin.csv";
                        break;
                    default:
                        System.out.println("Geçersiz seçim. Lütfen 1 ile 4 arasında bir sayı girin.");
                        scanner.nextLine();
                        continue;
                }

                SevkGirme sevk = new SevkGirme();
                List<Agac> agaclar = sevk.csvDosyasindanAgaclariOku(dosya);

                urunSecme(agaclar, dosya, selectedTur);

                System.out.print("Başka bir istif almak istiyor musunuz? (E/H): ");
                String devamEt = scanner.next().toLowerCase();

                if (!devamEt.equals("e")) {
                    System.out.println("Ağaç sorgulamasından çıkılıyor.");

                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Geçersiz giriş. Lütfen bir sayı girin.");
                scanner.nextLine();
            }
        }
    }

    public static void bilgiAlma() { // Müşteriden kişisel bilgilerini alır.
        boolean devam = true;
        while (devam) {
            try {
                System.out.println("\nLütfen Satın Alma Türünüzü Seçiniz \n1:Bireysel Müşteri 2:Kurumsal Müşteri");
                System.out.print("Seçim: ");
                Scanner scanner = new Scanner(System.in);
                int secim = scanner.nextInt();
                scanner.nextLine();
                switch (secim) {
                    case 1:
                        System.out.print("Adınız: ");
                        String ad = scanner.nextLine();

                        System.out.print("Soyadınız: ");
                        String soyad = scanner.nextLine();

                        System.out.print("TC Kimlik Numaranız: ");
                        String tc = scanner.nextLine();

                        SatisEkrani.musteriBilgileriniAl(ad, soyad, tc);
                        devam = false; // Döngüyü sonlandıran kod
                        break;
                    case 2:
                        System.out.print("Adınız: ");
                        ad = scanner.nextLine();

                        System.out.print("Soyadınız: ");
                        soyad = scanner.nextLine();

                        System.out.print("TC Kimlik Numaranız: ");
                        tc = scanner.nextLine();

                        System.out.print("Şirket Adı: ");
                        String sirketAdi = scanner.nextLine();

                        System.out.println("Telefon numaranızı giriniz (Yoksa 0 yazınız)");
                        String telefon = scanner.nextLine();
                        if (telefon.equals("0")) {
                            SatisEkrani.musteriBilgileriniAl(ad, soyad, tc, sirketAdi);
                        } else {
                            SatisEkrani.musteriBilgileriniAl(ad, soyad, tc, sirketAdi, telefon);
                        }
                        devam = false; // Döngüyü sonlandıran kod
                        break;
                    default:
                        System.out.println("Geçersiz seçim. Lütfen 1 ile 4 arasında bir sayı girin.");
                        break;

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Ağaç dosyalarındaki bilgiyi okuyarak müşteriye ağaç listesi çıkarır.
    public static void urunSecme(List<Agac> agaclar, String dosyaAdi, int tur) {
        Scanner scanner = new Scanner(System.in);

        if (!agaclar.isEmpty()) {
            System.out.println("Ağaç Listesi:");
            int i = 1;


            for (Agac agac : agaclar) {
                System.out.print(i++ + ". Ağaç: Tür: " + agac.getTur() + ", Çap: " + agac.getCap() + ", Kabuk Kalınlığı: " + agac.getKabuk() + ", Boy: " + agac.getBoy() + ", Hacim: " + agac.getHacim() + ", Adet:" + agac.getAdet() + ",Fiyat: " + agac.getFiyat() + "TL\n");
            }

            System.out.print("\n Satın almak istediğiniz ağacın numarasını girin: ");
            int secilenAgaclik = scanner.nextInt();

            if (secilenAgaclik > 0 && secilenAgaclik <= agaclar.size()) {
                Agac secilenAgac = agaclar.get(secilenAgaclik - 1);
                System.out.println("Satın Alınan Ağaç Bilgileri:");
                System.out.println("Tür: " + secilenAgac.getTur() + ", Çap: " + secilenAgac.getCap() +
                        ", Kabuk Kalınlığı: " + secilenAgac.getKabuk() + ", Boy: " + secilenAgac.getBoy() +
                        ", Hacim: " + secilenAgac.getHacim() + ", Adet: " + secilenAgac.getAdet() + ", Fiyat: " + secilenAgac.getFiyat() + " TL");

                // Satilanagaclar.csv dosyasına yazar.
                csvDosyasinaYaz("src/arsiv/satilanagaclar.csv", secilenAgac);

                // Satın alınan ağacın satırını CSV dosyasından siler.
                csvDosyasindanSil(dosyaAdi, secilenAgaclik);

                // Satın alınan ağacı listeden kaldırır.
                agaclar.remove(secilenAgaclik - 1);

                System.out.println("Satın alma işlemi tamamlandı.");

            } else {
                System.out.println("Geçersiz ağaç numarası. Lütfen geçerli bir numara girin.");
            }
        } else {
            System.out.println("Stokta ürün kalmadı.");
        }
    }

    public static void csvDosyasindanSil(String dosya, int satirNo) { // Seçilen ağaç istifini dosyadan silinmesini sağlar.
        Path dosyaYolu = Paths.get(dosya);
        List<String> lines;
        satirNo--;
        try {
            lines = Files.readAllLines(dosyaYolu);
            if(satirNo < 0 || satirNo >= lines.size()) {
                System.out.println("Geçersiz satır numarası.");
                return;
            }
            lines.remove(satirNo);
            Files.write(dosyaYolu, lines);
            bosSatirlariSil(String.valueOf(dosyaYolu));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void bosSatirlariSil(String dosyaAdi) { // Silinen istifin boş kalan satırını dosyadan siler.
        try {
            Path filePath = Paths.get(dosyaAdi);

            // Boş satırları silmek için dosyanın içeriğini oku ve boş satırları filtrele
            String updatedContent = Files.lines(filePath)
                    .filter(line -> !line.trim().isEmpty())
                    .collect(Collectors.joining(System.lineSeparator()));

            // Dosyayı temizle ve güncellenmiş içeriği yaz
            Files.write(filePath, updatedContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void csvDosyasinaYaz(String dosyaAdi, Agac agac) { // Satın alınan istifleri satılan ağaçlar dosayasına kaydeder.
        try (FileWriter writer = new FileWriter(dosyaAdi, true)) {
            writer.append(agac.getTur()).append(",");
            writer.append(String.valueOf(agac.getCap())).append(",");
            writer.append(String.valueOf(agac.getKabuk())).append(",");
            writer.append(String.valueOf(agac.getBoy())).append(",");
            writer.append(String.valueOf(agac.getHacim())).append(",");
            writer.append(String.valueOf(agac.getAdet())).append(",");
            writer.append(String.valueOf(agac.getFiyat())).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
