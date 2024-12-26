package ekranlar;

import agac.Agac;
import agac.AgacFactory;
import islem.HacimHesap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SevkGirme {

    public static void sevkAl() {
        // Kullanıcıdan yeni ağaç bilgilerini alır
        Agac yeniAgac = kullanicidanAgacBilgisiAl();

        // Yeni ağacı ağaç türüne göre CSV dosyasına ekler
        yeniAgac.saveToCsv(getCsvDosyaAdi(yeniAgac.getTur()));
    }

    public static void sevkYazdir() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Kullanıcıya ağaç türü seçtirir
                System.out.println("Bilgilerini görüntülemek isteyeceğiniz ağaç türünü seçiniz: \n1:Çam 2:Meşe 3:Kayın 4:Ladin");
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
                        continue; // Yanlış giriş durumunda tekrar döngü başına gitmesini sağlar
                }

                // CSV dosyasından seçilen ağaç türündeki ağaç verilerini okur
                List<Agac> agaclar = csvDosyasindanAgaclariOku(dosya);
                System.out.println("Ağaç Listesi:");
                int i = 1;
                for (Agac agac : agaclar) {
                    System.out.println(i++ + ". Ağaç: Tür: " + agac.getTur() + ", Çap: " + agac.getCap() + ", Kabuk Kalınlığı: " + agac.getKabuk() + ", Boy: " + agac.getBoy() + ", Hacim: " + agac.getHacim() + ", Adet:" + agac.getAdet());
                }

                // Kullanıcıdan çıkmak isteyip istemediğini sorar
                System.out.print("Başka bir ağaç türü sorgulamak istiyor musunuz? (E/H): ");
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

    public static void csvDosyasinaAgacEkle(Agac agac, String dosyaAdi) {
        // ağaç türünün CSV dosyasına ağaçları ekler
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi, true))) {
            if (new FileReader(dosyaAdi).read() != -1) {
                writer.newLine();
            }
            writer.write(agac.toCsvString());
            System.out.println("CSV dosyasına ağaç verisi ekleme tamamlandı.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Agac> csvDosyasindanAgaclariOku(String dosya) {
        List<Agac> agaclar = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String line;

            // CSV dosyasından ağaç verilerini okur
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    String tur = fields[0];
                    double cap = Double.parseDouble(fields[1]);
                    double kabuk = Double.parseDouble(fields[2]);
                    double boy = Double.parseDouble(fields[3]);
                    double hacim = Double.parseDouble(fields[4]);
                    int adet = Integer.parseInt(fields[5]);
                    agaclar.add(AgacFactory.createAgac(tur, cap, kabuk, boy, hacim, adet));
                } else {
                    System.out.println("Geçersiz veri satırı: " + line);
                }
            }

            System.out.println("CSV dosyasından ağaç verileri okuma tamamlandı.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return agaclar;
    }

    private static String turkceKarakterleriIngilizceyeCevir(String tur) {//ağaç eklerken girilen türkçe karakterleri düzeltir
        tur = tur.replace("ç", "c");
        tur = tur.replace("ı", "i");
        tur = tur.replace("ş", "s");
        return tur;
    }

    private static Agac kullanicidanAgacBilgisiAl() { // kullanıcıdan ağaç bilgisi girişini sağlar
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nYeni Ağaç Bilgilerini Girin:");

        String tur;
        while (true) {
            System.out.print("Tür (çam, meşe, kayın, ladin): ");
            tur = turkceKarakterleriIngilizceyeCevir(scanner.next().toLowerCase());
            if (tur.equals("cam") || tur.equals("mese") || tur.equals("kayin") || tur.equals("ladin")) {
                break;
            } else {
                System.out.println("Hatalı tür girişi. Lütfen tekrar deneyin.");
            }
        }

        System.out.print("Çap (cm): ");
        double cap_cm = scanner.nextDouble();
        double cap = cap_cm / 100;

        System.out.print("Kabuk kalınlığı (cm): ");
        double kabuk_cm = scanner.nextDouble();
        double kabuk = kabuk_cm / 100;

        System.out.print("Boy: ");
        double boy = scanner.nextDouble();

        System.out.print("Adet: ");
        int adet = scanner.nextInt();


        // HacimHesap sınıfında hacmini hesaplatır
        HacimHesap hesap = new HacimHesap();
        double hacim;
        if (kabuk_cm == 0){
            hacim = hesap.HacimHesap(cap, boy, adet);
        }else {
            hacim = hesap.HacimHesap(cap, kabuk, boy, adet);
        }


        return AgacFactory.createAgac(tur, cap, kabuk, boy, hacim, adet);
    }

    private static String getCsvDosyaAdi(String tur) { // csv dosyasına yazdırırken ağaç türüne göre dosya adının seçilmesi
        return "src/arsiv/" + tur + ".csv";
    }
}
