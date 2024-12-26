package islem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Istifleme implements TomrukIslem {

    // Depodaki tomrukları kullanım alanlarına göre listeler.
    @Override
    public void tomrukOkuma(String dosyaAdi){
        double[] sinirlar = {0, 10, 14, 21};
        String[] siniflar = {"0-10 yakmalık", "10-14 suntalık", "14-20 maden ocakları ", "21 ve üzeri tomruk"};

        int[] sinifSayilari = new int[siniflar.length];

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                double cap = Double.parseDouble(parts[1]) * 100; // Çap 100 ile çarpılıp metre cinsine çevrildi.


                int i;
                for (i = 0; i < sinirlar.length; i++) {
                    if (cap < sinirlar[i]) {
                        break;
                    }
                }
                String sinif = (i == sinirlar.length) ? siniflar[i - 1] : siniflar[i];


                sinifSayilari[i - 1]++;
            }


            for (int i = 0; i < siniflar.length; i++) {
                System.out.println(siniflar[i] + ": " + sinifSayilari[i] + " tane");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

