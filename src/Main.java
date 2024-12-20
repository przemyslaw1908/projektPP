//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.LinkedList;

public class Main { // deklaruje klase main

    public static void main(String[] args) { // deklaruje metode main


        File listaStudentowPlik = new File("listaStudentow.txt");// deklaruje zmienna zawierajaca plik listastudentow
        LinkedList<String> przedmioty = new LinkedList<String>();
        File listaPrzedmiotowPlik = new File("listaPrzedmiotow.txt");
        Scanner czytaczPlikuPrzedmioty = new Scanner(listaPrzedmiotowPlik); //tworzy skaner czytaczPliku skanujacy polik
        while (czytaczPlikuPrzedmioty.hasNextLine()) { // tworzy petle while, ktora dziala dopoki plik ma nastepna linijke
            przedmioty.add(czytaczPlikuPrzedmioty.nextLine());
        }
System.out.println(przedmioty);



        System.out.println("Witaj na stronie wirtualna uczelnia XYZ! \n" //wyswietla ekran powitalny
                + "wybierz nastepne menu: \n" //wyswietla kolejna linijke ekranu powitalnego
                + "1.Logowanie 2.Rejestracja"); //mowi o mozliwosciach wyboru z menu
        Scanner ObiektSkaner = new Scanner(System.in); // tworzy obiekt skaner o nazwie ObiektSkaner skanujacy to co wpisze uzytkownik
        int wybor = ObiektSkaner.nextInt(); //deklaruje zmienna w ktorej znajduje sie to co napisze uzytkownik, a dokladniej wybor kolejnego menu po menu powitalnym (logowanie lub rejestracja)
        switch (wybor) { // deklaruje przelacznik switch, ktory w zaleznosci od poprzedniego wyboru uzytkownika, wlaczy nastepne menu
            case 1://wybor pierwszy (Logowanie)
                try { // instrukcja try, która wyboera blok kodu i szuka w nim błędu
                    Boolean uzytkownikZalogowany = new Boolean(false);
                    while (!uzytkownikZalogowany) {
                        System.out.println("Wpisz numer studenta:");//wyswietla na ekranie prosbe o numer studenta
                        wybor = ObiektSkaner.nextInt(); // definiuje zmienna wybor jako nastepna linijke wpisana przez uzytkownika, w tym przypadku jest to numer studenta czyli tak na prawde index pola w ktorym znajduje sie haslo
                        Scanner czytaczPliku = new Scanner(listaStudentowPlik); //deklaruje skaner czytacz pliku, który ma na celu odczytanie zawartosci pliku
                        int obecnaLinijkaPliku = 0; //deklaruje zmienna obecnaLinijkaPliku, w ktorej znajduje sie numer obecnej linijki pliku
                        String hasloStudenta = new String("");
                        while (czytaczPliku.hasNextLine()) { //zaczyna instrukcje while ktora dziala dopoki plik ma nastepna zapelniona linijke
                            String obecnaLinia = czytaczPliku.nextLine(); //definiuje obiekt String, w którym znajduje sie obecna linijka
                            obecnaLinijkaPliku++; //inkrementuje zmienna obecnaLinijkaPliku
                            System.out.println(obecnaLinijkaPliku);
                            if (wybor == obecnaLinijkaPliku) { // sprawdza czy wpisany przez uzytkownika numer studenta jest taki sam jak obecna linijka
                                hasloStudenta = obecnaLinia; // zapisuje haslo studenta ktory probuje sie zalogowac
                                break; //konczy petle while, jako iz znalezlismy poszukiwana linijke
                            }

                        }

                        System.out.println("Wpisz swoje haslo:");
                        Scanner czytaczHasla = new Scanner(System.in);
                        String wpisaneHaslo = czytaczHasla.nextLine();
                        System.out.println(wpisaneHaslo);
                        if (hasloStudenta.equals(wpisaneHaslo)) {
                            uzytkownikZalogowany = true;
                        } else {System.out.println("ZLE HASLO ZALOGUJ SIE PONOWNIE");}

                    }

                } catch (
                        FileNotFoundException e) { //instrukcja ktora, przy bledzie FileNotFoundException wykonuje nastepna linijke
                    System.out.println("ERRORERROR"); //wyswietlanie informacji o bledzie uzytkownikowi
                }
                break; //zakancza case 1
            case 2: //drugi przypadek instruckji switch, (rejestracja)
                try { // instrukcja try, która wyboera blok kodu i szuka w nim błędu
                    int numerNowegoStudenta = getNumerNowegoStudenta(listaStudentowPlik);
                    System.out.println("twoj numer studenta to" + numerNowegoStudenta); // wyswietla na ekranie numer studenta
                    System.out.println("wpisz swoje haslo:"); //prosi o wpisanie hasla
                    ObiektSkaner.nextLine();
                    String wpisaneHaslo = ObiektSkaner.nextLine(); //definiuje obiekt wpisaneHaslo jako nastepna linijka wpisana przez uzytkownika
                    wpisaneHaslo = "\n" + wpisaneHaslo; //ta linijka sprawia ze wpisane haslo bedzie dodane na koncu pliku plus kolejna linijka
                    Files.write(Paths.get("listaStudentow.txt"), wpisaneHaslo.getBytes(), StandardOpenOption.APPEND); // zapisuje do pliku wpisane haslo
                    File obiektZarejestrowanyStudent = new File("studenci" + File.separator + numerNowegoStudenta + ".txt");
                    obiektZarejestrowanyStudent.createNewFile();

                    break;
                } catch (FileNotFoundException e) { // instrukcja ktora przy bledzie  FileNotFoundException wykonuje nastepna linijkke
                    System.out.println("ERRORERROR:FileNotFoundException"); // wyswietla blad
                } catch (IOException e) { //instruckaj ktora przy bledzie IOException wykonuje nastepna linijke
                    System.out.println("ERRORERROR:IOException"); // wyswietla blad

                }
        }

    }

    private static int getNumerNowegoStudenta(File listaStudentowPlik) throws FileNotFoundException {
        Scanner czytaczPliku = new Scanner(listaStudentowPlik); //tworzy skaner czytaczPliku skanujacy polik
        int numerNowegoStudenta = 1; // deklaruje zmienna numerNowegoStudenta
        while (czytaczPliku.hasNextLine()) { // tworzy petle while, ktora dziala dopoki plik ma nastepna linijke
            String obecnaLiniaa = new String();
            obecnaLiniaa = czytaczPliku.nextLine();
            numerNowegoStudenta++; //inkrementuje zmienna obecna linijka pliku
        }
        return numerNowegoStudenta;
    }

    }








