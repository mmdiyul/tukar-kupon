import java.util.*;
import java.text.*;
class bigProject {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[] identitas;
		int jumlahKuponAwal = 0, jumlahKupon = 0, jumlahHadiah = 0, kodeHadiah = 0;
		char lagi = ' ';

		identitas = new String[4];

		String[] hadiah = {
			"STIKER",
			"GELANG",
			"MUG",
			"KAOS",
			"TAS",
			"JAKET"
		};

		int[] hargaHadiah = {
			5,
			15,
			40,
			75,
			130,
			175
		};

		do {

			System.out.println("+--------------------------------------------------------+");
			System.out.println("     SELAMAT DATANG DI SISTEM TUKAR KUPON INDOAPRIL v2    ");
			System.out.println("+--------------------------------------------------------+");
			System.out.println("+--------------------------------------------------------+");
			System.out.println("  MASUKKAN IDENTITAS ANDA :                               ");
			System.out.println("+--------------------------------------------------------+");

			inputIdentitas(identitas); // Memanggil fungsi inputIdentitas

			jumlahKupon = inputJumlahKupon(jumlahKupon, jumlahKuponAwal); // Memanggil fungsi inputJumlahKupon dan menyimpan hasil yang dikembalikan dari fungsi ke variabel jumlahKupon
			jumlahKuponAwal = jumlahKupon; // Menyimpan isi variabel jumlahKupon di jumlahKuponAwal

			lagi = cekJumlahKupon(jumlahKupon, lagi); // Memanggil fungsi cekJumlahKupon dan menyimpan hasil yang dikembalikan dari fungsi ke variabel lagi

		} while(jumlahKupon < 5 || jumlahKupon > 500 || lagi == 'y' && lagi == 'Y');		

		getListHadiah(hadiah, hargaHadiah); // Memanggil fungsi getListHadiah
		
		kodeHadiah = inputKodeHadiah(kodeHadiah, hadiah, hargaHadiah, jumlahKupon); // Memanggil fungsi inputKodeHadiah dan menyimpan hasil yang dikembalikan dari fungsi ke variabel kodeHadiah

		jumlahHadiah = inputJumlahHadiah(jumlahKupon, hargaHadiah, kodeHadiah, lagi, jumlahHadiah); // Memanggil fungsi inputJumlahHadiah dan menyimpan hasil yang dikembalikan dari fungsi ke variabel jumlahHadiah
		
		jumlahKupon = jumlahKuponAwal - (hargaHadiah[kodeHadiah] * jumlahHadiah); // Menghitung sisa kupon setelah transaksi

		getTransaksi(identitas,jumlahKuponAwal,hadiah,jumlahHadiah,jumlahKupon,kodeHadiah); // Memanggil fungsi getTransaksi

	}

	// Fungsi untuk input identitas
	static void inputIdentitas(String[] identitas) {		
    	Scanner sc = new Scanner(System.in);

		System.out.print("  NAMA LENGKAP  : ");
		identitas[0] = sc.nextLine();
		System.out.print("  NO. IDENTITAS : ");
		identitas[1] = sc.nextLine();
		System.out.print("  NO. HP        : ");
		identitas[2] = sc.nextLine();
		System.out.print("  ALAMAT        : ");
		identitas[3] = sc.nextLine();
    }

    // Fungsi untuk input jumlah kupon yang dimiliki
    static int inputJumlahKupon(int jumlahKupon, int jumlahKuponAwal) {
    	Scanner sc = new Scanner(System.in);
    	
		try {
			System.out.println("+--------------------------------------------------------+");
			System.out.print("  MASUKKAN JUMLAH KUPON YANG DIMILIKI : ");
			jumlahKupon = sc.nextInt();
			System.out.println("+--------------------------------------------------------+");
		} catch(InputMismatchException IME) {
			System.out.println("+--------------------------------------------------------+");
			System.out.println("  PROGRAM ERROR, JUMLAH KUPON HARUS BERUPA ANGKA ");
			System.out.println("  PROGRAM BERAKHIR! ");
			System.out.println("+--------------------------------------------------------+");
			System.exit(0);	
		}

		return jumlahKupon;
	}
	
	// Fungsi untuk cek jumlah kupon apakah jumlah kupon yang dimasukkan cukup untuk ditukarkan dengan hadiah atau tidak, jika tidak cukup maka program akan bertanya apakah ingin mengulangi atau tidak.
    static char cekJumlahKupon(int jumlahKupon, char lagi) {
    	Scanner sc = new Scanner(System.in);

		if (jumlahKupon < 5) {
			System.out.println("  KUPON ANDA BELUM MEMENUHI UNTUK MELANJUTKAN PROGRAM     ");
			System.out.println("+--------------------------------------------------------+");
			do {
				System.out.print("  ULANGI PROGRAM? (Y/n) "); lagi = sc.next().charAt(0);
				if (lagi != 'n' && lagi != 'N' && lagi != 'y' && lagi != 'Y') {
					System.out.println("+--------------------------------------------------------+");
					System.out.println("  KODE SALAH! (Y/n)                                       ");
					System.out.println("+--------------------------------------------------------+");
				}
			} while(lagi != 'n' && lagi != 'N' && lagi != 'y' && lagi != 'Y');
		}
		else if (jumlahKupon > 500) {
			System.out.println("  MOHON MAAF, KAMI HANYA MELAYANI PENUKARAN KUPON");
			System.out.println("  MAKSIMAL 500 KUPON");
			System.out.println("+--------------------------------------------------------+");
			do {
				System.out.print("  ULANGI PROGRAM? (Y/n) "); lagi = sc.next().charAt(0);
				if (lagi != 'n' && lagi != 'N' && lagi != 'y' && lagi != 'Y') {
					System.out.println("+--------------------------------------------------------+");
					System.out.println("  KODE SALAH! (Y/n)                                       ");
					System.out.println("+--------------------------------------------------------+");
				}
			} while(lagi != 'n' && lagi != 'N' && lagi != 'y' && lagi != 'Y');
		}
		else {
			System.out.println("  SELAMAT, ANDA DAPAT MENUKARKAN KUPON YANG ANDA MILIKI");
		}
		
		if (lagi == 'n' || lagi == 'N') {
			System.out.println("+--------------------------------------------------------+");
			System.out.println("  PROGRAM BERAKHIR. TERIMA KASIH                          ");
			System.out.println("+--------------------------------------------------------+");
			System.exit(0);
		}

		return lagi;
    }
    
    // Fungsi untuk menampilkan daftar hadiah yang tersedia
	static void getListHadiah(String[] hadiah, int[] hargaHadiah) {
    	System.out.println("+--------------------------------------------------------+");
		System.out.println("  TUKAR KUPON ANDA DENGAN :");

		for (int i = 0; i < hadiah.length; i++) {
			System.out.println("    ["+i+"] " + hadiah[i] + "\t("+hargaHadiah[i]+")");
		}
		
		System.out.println("+--------------------------------------------------------+");
    }

    // Fungsi untuk input kode hadiah yang dipilih
    static int inputKodeHadiah(int kodeHadiah, String[] hadiah, int[] hargaHadiah, int jumlahKupon) {
    	Scanner sc = new Scanner(System.in);

		do {
			do {
				try {
					System.out.print("  MASUKKAN KODE HADIAH : ");
					kodeHadiah = sc.nextInt();
				} catch(InputMismatchException IME) {
					System.out.println("+--------------------------------------------------------+");
					System.out.println("  PROGRAM ERROR, KODE HADIAH HARUS BERUPA ANGKA ");
					System.out.println("  PROGRAM BERAKHIR! ");
					System.out.println("+--------------------------------------------------------+");
					System.exit(0);	
				}
				
				System.out.println("+--------------------------------------------------------+");
				
				if (kodeHadiah <= 5 && kodeHadiah >= 0) {
				}
				else {
					System.out.println("  KODE TIDAK DITEMUKAN, ULANGI LAGI!                      ");
					System.out.println("+--------------------------------------------------------+");
				}
			} while (kodeHadiah > hadiah.length || kodeHadiah < 0);

			if (jumlahKupon/hargaHadiah[kodeHadiah] == 0) {
				System.out.println("  KUPON ANDA TIDAK MENCUKUPI. SILAHKAN ULANGI!            ");
				System.out.println("+--------------------------------------------------------+");
			}
		} while(jumlahKupon/hargaHadiah[kodeHadiah] == 0);

		return kodeHadiah;
    }
    
    // Fungsi untuk input jumlah hadiah yang akan diambil
	static int inputJumlahHadiah(int jumlahKupon, int[] hargaHadiah, int kodeHadiah, char lagi, int jumlahHadiah) {
    	Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("  HADIAH YANG BISA ANDA AMBIL MAKSIMAL     : " + jumlahKupon/hargaHadiah[kodeHadiah]);
			try {
				System.out.print("  MASUKKAN JUMLAH HADIAH YANG AKAN DIAMBIL : ");
				jumlahHadiah = sc.nextInt();
			} catch (InputMismatchException IME) {
				System.out.println("+--------------------------------------------------------+");
				System.out.println("  PROGRAM ERROR, JUMLAH HADIAH HARUS BERUPA ANGKA ");
				System.out.println("  PROGRAM BERAKHIR! ");
				System.out.println("+--------------------------------------------------------+");
				System.exit(0);				
			}

			if (jumlahHadiah > jumlahKupon/hargaHadiah[kodeHadiah]) {
				System.out.println("+--------------------------------------------------------+");
				System.out.println("  KUPON ANDA TIDAK MENCUKUPI. SILAHKAN ULANGI!            ");
				System.out.println("+--------------------------------------------------------+");
			}
			else if (jumlahHadiah == 0) {
				do {
					System.out.println("+--------------------------------------------------------+");
					System.out.println("  TIDAK BISA MENGAMBIL HADIAH 0! MINIMAL 1 HADIAH         ");
					System.out.println("+--------------------------------------------------------+");
					System.out.print("  MASIH INGIN MELANJUTKAN PENUKARAN KUPON? (Y/n) "); lagi = sc.next().charAt(0);
					System.out.println("+--------------------------------------------------------+");
				} while(lagi != 'n' && lagi != 'N' && lagi != 'y' && lagi != 'Y');
				
				if (lagi == 'n' || lagi == 'N') {
					System.out.println("+--------------------------------------------------------+");
					System.out.println("  PROGRAM BERAKHIR. TERIMA KASIH                          ");
					System.out.println("+--------------------------------------------------------+");
					System.exit(0);					
				}
			}
		} while (jumlahHadiah > jumlahKupon/hargaHadiah[kodeHadiah] || jumlahHadiah == 0);

		return jumlahHadiah;
    }

    // Fungsi untuk mendapatkan tanggal saat ini
	static String getTanggal() {  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  
        return dateFormat.format(date);  
    }

	// Fungsi untuk mendapatkan jam saat ini
    static String getJam() {  
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        Date date = new Date();  
        return dateFormat.format(date);  
    } 

    // Fungsi untuk menampilkan hasil transaksi penukaran kupon
	static void getTransaksi(String[] identitas, int jumlahKuponAwal, String[] hadiah, int jumlahHadiah, int jumlahKupon, int kodeHadiah) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+--------------------------------------------------------+");
		System.out.println("          HASIL TRANSAKSI TUKAR KUPON INDOAPRIL           ");
		System.out.println("+--------------------------------------------------------+");
		System.out.println("    TANGGAL : " + getTanggal());
		System.out.println("    JAM     : " + getJam());
		System.out.println("+--------------------------------------------------------+");
		System.out.println("    DATA CUSTOMER     : " );
		System.out.println("      NAMA            : " + identitas[0]);
		System.out.println("      NOMOR IDENTITAS : " + identitas[1]);
		System.out.println("      NOMOR HP        : " + identitas[2]);
		System.out.println("      ALAMAT          : " + identitas[3]);
		System.out.println("+--------------------------------------------------------+");
		System.out.println("    TRANSAKSI           : " );
		System.out.println("      JUMLAH KUPON      : " + jumlahKuponAwal);
		System.out.println("      HADIAH YG DIAMBIL : " + hadiah[kodeHadiah]);
		System.out.println("      JUMLAH HADIAH     : " + jumlahHadiah);
		System.out.println("      SISA KUPON        : " + jumlahKupon);
		System.out.println("+--------------------------------------------------------+");
		System.out.println("     SELAMAT ANDA MENDAPATKAN " + jumlahHadiah + " " + hadiah[kodeHadiah] + " ! ");
		System.out.println("+--------------------------------------------------------+");
		System.out.println("     TERIMA KASIH TELAH MENGGUNAKAN JASA INDOAPRIL        ");
		System.out.println("+--------------------------------------------------------+");
	}
}