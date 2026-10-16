import java.util.Scanner;

class Menu {
    String nama;
    double harga;
    String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

public class Kasir {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Array Input Menu Restoran (4 Makanan, 4 Minuman)
        Menu[] daftarMenu = new Menu[8];
        
        daftarMenu[0] = new Menu("Nasi Padang", 25000, "Makanan");
        daftarMenu[1] = new Menu("Mie Ayam Spesial", 18000, "Makanan");
        daftarMenu[2] = new Menu("Ayam Bakar Madu", 22000, "Makanan");
        daftarMenu[3] = new Menu("Sate Ayam Madura", 20000, "Makanan");
        
        daftarMenu[4] = new Menu("Es Teh Manis", 5000, "Minuman");
        daftarMenu[5] = new Menu("Es Jeruk Peras", 7000, "Minuman");
        daftarMenu[6] = new Menu("Jus Alpukat", 12000, "Minuman");
        daftarMenu[7] = new Menu("Kopi Hitam", 8000, "Minuman");

        tampilkanMenu(daftarMenu);

        // Array untuk menyimpan maksimal 4 pesanan
        int[] indeksPesanan = {-1, -1, -1, -1};
        int[] jumlahPesanan = {0, 0, 0, 0};

        // 2. Menerima Pesanan
        terimaPesanan(scanner, daftarMenu, indeksPesanan, jumlahPesanan);

        // 3 & 4. Menghitung Total dan Mencetak Struk
        prosesDanCetakStruk(daftarMenu, indeksPesanan, jumlahPesanan);

        scanner.close();
    }

    // METHOD: Menampilkan Menu
    public static void tampilkanMenu(Menu[] daftarMenu) {
        System.out.println("========================================");
        System.out.println("       DAFTAR MENU RESTORAN KITA        ");
        System.out.println("========================================");
        System.out.println("KATEGORI: MAKANAN");
        System.out.println("- " + daftarMenu[0].nama + " \t(Rp " + daftarMenu[0].harga + ")");
        System.out.println("- " + daftarMenu[1].nama + " \t(Rp " + daftarMenu[1].harga + ")");
        System.out.println("- " + daftarMenu[2].nama + " \t(Rp " + daftarMenu[2].harga + ")");
        System.out.println("- " + daftarMenu[3].nama + " \t(Rp " + daftarMenu[3].harga + ")");
        
        System.out.println("\nKATEGORI: MINUMAN");
        System.out.println("- " + daftarMenu[4].nama + " \t(Rp " + daftarMenu[4].harga + ")");
        System.out.println("- " + daftarMenu[5].nama + " \t(Rp " + daftarMenu[5].harga + ")");
        System.out.println("- " + daftarMenu[6].nama + " \t(Rp " + daftarMenu[6].harga + ")");
        System.out.println("- " + daftarMenu[7].nama + " \t\t(Rp " + daftarMenu[7].harga + ")");
        System.out.println("========================================");
    }

    // METHOD BANTUAN: Mencari indeks menu berdasarkan nama (Tanpa Loop)
    public static int cariIndeksMenu(Menu[] daftarMenu, String namaDicari) {
        if (namaDicari.equalsIgnoreCase(daftarMenu[0].nama)) return 0;
        if (namaDicari.equalsIgnoreCase(daftarMenu[1].nama)) return 1;
        if (namaDicari.equalsIgnoreCase(daftarMenu[2].nama)) return 2;
        if (namaDicari.equalsIgnoreCase(daftarMenu[3].nama)) return 3;
        if (namaDicari.equalsIgnoreCase(daftarMenu[4].nama)) return 4;
        if (namaDicari.equalsIgnoreCase(daftarMenu[5].nama)) return 5;
        if (namaDicari.equalsIgnoreCase(daftarMenu[6].nama)) return 6;
        if (namaDicari.equalsIgnoreCase(daftarMenu[7].nama)) return 7;
        return -1; // Jika tidak ditemukan
    }

    // METHOD BANTUAN: Memproses 1 baris input string menjadi data pesanan
    public static void prosesSatuPesanan(Menu[] daftarMenu, String input, int[] indeksPesanan, int[] jumlahPesanan, int slot) {
        if (!input.equalsIgnoreCase("selesai") && input.contains("=")) {
            String[] parts = input.split("=");
            String namaMenu = parts[0].trim();
            int qty = Integer.parseInt(parts[1].trim());
            
            int indeks = cariIndeksMenu(daftarMenu, namaMenu);
            if (indeks != -1) {
                indeksPesanan[slot] = indeks;
                jumlahPesanan[slot] = qty;
            } else {
                System.out.println("-> Menu tidak ditemukan, pesanan ini dilewati.");
            }
        }
    }

    // METHOD: Menerima Input Pesanan (Maks 4, Tanpa Loop, Format String)
    public static void terimaPesanan(Scanner scanner, Menu[] daftarMenu, int[] indeksPesanan, int[] jumlahPesanan) {
        System.out.println("\nSilakan pesan maksimal 4 menu.");
        System.out.println("Format: Nama Menu = Jumlah (Contoh: Nasi Padang = 2)");
        System.out.println("Ketik 'selesai' jika pesanan sudah cukup.\n");

        // Slot Pesanan 1
        System.out.print("Pesanan 1: ");
        String in1 = scanner.nextLine();
        if (!in1.equalsIgnoreCase("selesai")) {
            prosesSatuPesanan(daftarMenu, in1, indeksPesanan, jumlahPesanan, 0);

            // Slot Pesanan 2
            System.out.print("Pesanan 2: ");
            String in2 = scanner.nextLine();
            if (!in2.equalsIgnoreCase("selesai")) {
                prosesSatuPesanan(daftarMenu, in2, indeksPesanan, jumlahPesanan, 1);

                // Slot Pesanan 3
                System.out.print("Pesanan 3: ");
                String in3 = scanner.nextLine();
                if (!in3.equalsIgnoreCase("selesai")) {
                    prosesSatuPesanan(daftarMenu, in3, indeksPesanan, jumlahPesanan, 2);

                    // Slot Pesanan 4
                    System.out.print("Pesanan 4: ");
                    String in4 = scanner.nextLine();
                    if (!in4.equalsIgnoreCase("selesai")) {
                        prosesSatuPesanan(daftarMenu, in4, indeksPesanan, jumlahPesanan, 3);
                    }
                }
            }
        }
    }

    // METHOD: Menghitung Biaya dan Mencetak Struk
    public static void prosesDanCetakStruk(Menu[] daftarMenu, int[] indeksPesanan, int[] jumlahPesanan) {
        double subtotal = 0;
        String minumanPromo = "Es Teh Manis"; // Menyimpan nama minuman yang dapat Beli 1 Gratis 1

        System.out.println("\n========================================");
        System.out.println("             STRUK PEMESANAN            ");
        System.out.println("========================================");

        // Cek dan cetak pesanan 1
        if (indeksPesanan[0] != -1) {
            Menu m = daftarMenu[indeksPesanan[0]];
            double totalItem = m.harga * jumlahPesanan[0];
            subtotal += totalItem;
            System.out.println(m.nama + " \t(" + jumlahPesanan[0] + " x " + (int)m.harga + ") \t= Rp " + (int)totalItem);
            if (m.kategori.equals("Minuman")) minumanPromo = m.nama;
        }
        // Cek dan cetak pesanan 2
        if (indeksPesanan[1] != -1) {
            Menu m = daftarMenu[indeksPesanan[1]];
            double totalItem = m.harga * jumlahPesanan[1];
            subtotal += totalItem;
            System.out.println(m.nama + " \t(" + jumlahPesanan[1] + " x " + (int)m.harga + ") \t= Rp " + (int)totalItem);
            if (m.kategori.equals("Minuman")) minumanPromo = m.nama;
        }
        // Cek dan cetak pesanan 3
        if (indeksPesanan[2] != -1) {
            Menu m = daftarMenu[indeksPesanan[2]];
            double totalItem = m.harga * jumlahPesanan[2];
            subtotal += totalItem;
            System.out.println(m.nama + " \t(" + jumlahPesanan[2] + " x " + (int)m.harga + ") \t= Rp " + (int)totalItem);
            if (m.kategori.equals("Minuman")) minumanPromo = m.nama;
        }
        // Cek dan cetak pesanan 4
        if (indeksPesanan[3] != -1) {
            Menu m = daftarMenu[indeksPesanan[3]];
            double totalItem = m.harga * jumlahPesanan[3];
            subtotal += totalItem;
            System.out.println(m.nama + " \t(" + jumlahPesanan[3] + " x " + (int)m.harga + ") \t= Rp " + (int)totalItem);
            if (m.kategori.equals("Minuman")) minumanPromo = m.nama;
        }

        if (subtotal == 0) {
            System.out.println("Tidak ada pesanan yang valid.");
            return;
        }

        // --- MENGHITUNG PROMO DAN BIAYA ---
        double diskon = 0;
        
        // Aturan: Diskon 10% jika pesanan melebihi 100.000
        if (subtotal > 100000) {
            diskon = subtotal * 0.10;
        }

        double totalSetelahDiskon = subtotal - diskon;
        double pajak = totalSetelahDiskon * 0.10; // Pajak 10%
        double biayaPelayanan = 20000;
        double totalAkhir = totalSetelahDiskon + pajak + biayaPelayanan;

        // --- MENCETAK RINCIAN BIAYA ---
        System.out.println("----------------------------------------");
        System.out.println("Subtotal \t\t\t= Rp " + (int)subtotal);
        
        // Cetak Promo Beli 1 Gratis 1 (Jika > 50.000 dan ada minuman)
        if (subtotal > 50000 && !minumanPromo.equals("")) {
            System.out.println("** PROMO: Beli 1 Gratis 1 (" + minumanPromo + ") **");
        }

        // Cetak Diskon
        if (diskon > 0) {
            System.out.println("Diskon 10% (> Rp 100.000) \t= -Rp " + (int)diskon);
        }

        System.out.println("Pajak 10% \t\t\t= Rp " + (int)pajak);
        System.out.println("Biaya Pelayanan \t\t= Rp " + (int)biayaPelayanan);
        System.out.println("========================================");
        System.out.println("TOTAL KESELURUHAN \t\t= Rp " + (int)totalAkhir);
        System.out.println("========================================");
    }
}