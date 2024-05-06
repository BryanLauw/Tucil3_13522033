# Tugas Kecil 3 IF2211 Strategi Algoritma
> by Bryan Cornelius Lauwrence

## Daftar Konten
* [Informasi Umum](#informasi-umum)
* [Deskripsi Singkat](#deskripsi-singkat)
* [Kebutuhan](#kebutuhan)
* [Setup dan Penggunaan](#setup-dan-penggunaan)
* [Kreator](#kreator)

## Informasi Umum
Penyelesaian Permainan _Word Ladder_ Menggunakan Algoritma _UCS_, _Greedy Best First Search_, dan A*

## Deskripsi Singkat
_Word Ladder_ adalah permainan yang mengharuskan pemain untuk berpindah dari satu kata ke kata lainnya dengan<br>
mengganti huruf satu per satu. Program akan mencari jalur perubahan huruf untuk mencapai kata tujuan sambil<br>
meminimalkan jumlah huruf yang diubah. Program memanfaatkan 3 jenis algoritma, yaitu _UCS_, _Greedy Best First Search_, dan A*.<br>
Perhitungan _greedy_ merupakan berapa kali huruf diubah dan perhitungan _heuristic_ merupakan jumlah huruf pada kata<br>
yang dicek yang berbeda dengan huruf pada kata tujuan<br>
Contoh permainan dapat dilihat pada lama [ini](#https://wordwormdormdork.com/#/game/313).

## Kebutuhan
1. Java 22

## Setup dan Penggunaan
1. Install Java versi terbaru pada link [berikut](https://www.oracle.com/id/java/technologies/downloads/).
2. Clone repository ini dengan perintah
```bash
git clone https://github.com/BryanLauw/Tucil3_13522033.git
```
3. Jika ingin meng-_compile_ program jalankan perintah sesuai sistem operasi. Pada windows, klik file `build.bat`. Sedangkan pada Linux, jalankan `make build` pada terminal atau klik file `build.sh`
4. Untuk menjalankan program, klik file `run.bat` pada Windows atau jalankan `make run` atau klik file `run.sh` pada Linux.
5. Jika tampilan tidak muncul, cobalah geser sedikit _frame_ yang muncul.
6. Masukkan input sesuai keinginan.
7. Klik tombol `FIND` untuk mencari solusi.
8. Kata-kata yang digunakan merupakan kata-kata dalam bahasa Inggris.
9. Kembali ke langkah 6 jika ingin melanjutkan pencarian kata.
10. Tutup program setelah selesai menggunakan.

## Struktur File
Direktori tugas ini memiliki struktur file sebagai berikut:
```
.
├── README.md
├── bin
│   ├── Dictionary.class
│   ├── Main$1.class
│   ├── Main.class
│   ├── Node.class
│   ├── Path.class
│   ├── PrioQueueNode.class
│   ├── Process.class
│   └── dictionary.txt
├── build.bat
├── build.sh
├── doc
│   └── Tucil3_13522033.pdf
├── makefile
├── run.bat
├── run.sh
├── src
│   ├── Dictionary.java
│   ├── Main.java
│   ├── Node.java
│   ├── Path.java
│   ├── PrioQueueNode.java
│   └── Process.java
└── test
    ├── test-1.png
    ├── test-2.png
    ├── test-3.png
    ├── test-4.png
    ├── test-5.png
    ├── test-6.png
    └── testCase.txt
```

## Kreator
| NIM | Nama |
|-----|------|
| 13522033 | Bryan Cornelius Lauwrence |