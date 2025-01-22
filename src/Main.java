// Importowanie bibliotek potrzebnych do pracy z plikami oraz operacjami wejścia-wyjścia
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.Scanner;

public class Main { // Definiowanie głównej klasy programu

    public static void main(String[] args) { // Definiowanie metody main, która jest punktem wejścia programu

        try { // Blok try-catch obsługujący wyjątki związane z operacjami wejścia/wyjścia

            // Tworzenie obiektu File, który reprezentuje plik z listą studentów
            File listaStudentowPlik = new File("listaStudentow.txt");
            // Pobieranie listy przedmiotów z pliku
            LinkedList<String> przedmioty = getStrings();

            // Wyświetlenie ekranu powitalnego i opcji wyboru
            System.out.println("Witaj na stronie wirtualna uczelnia XYZ! \n"
                    + "wybierz nastepne menu: \n"
                    + "1.Logowanie 2.Rejestracja");

            // Tworzenie obiektu skaner do odczytu danych wejściowych od użytkownika
            Scanner ObiektSkaner = new Scanner(System.in);
            // Odczytanie wyboru użytkownika
            int wybor = ObiektSkaner.nextInt();

            // Przełącznik switch, który wybiera odpowiednią opcję w zależności od wyboru użytkownika
            switch (wybor) {
                case 1: // Jeśli użytkownik wybierze 1 (Logowanie)
                    boolean uzytkownikZalogowany = false; // Flaga kontrolująca proces logowania
                    while (!uzytkownikZalogowany) { // Pętla działa dopóki użytkownik nie zaloguje się poprawnie
                        // Prośba o wpisanie numeru studenta
                        System.out.println("Wpisz numer studenta:");
                        // Odczytanie numeru studenta od użytkownika
                        wybor = ObiektSkaner.nextInt();

                        // Tworzenie obiektu do odczytu pliku z listą studentów
                        Scanner czytaczPliku = new Scanner(listaStudentowPlik);
                        int obecnaLinijkaPliku = 0; // Zmienna śledząca numer bieżącej linii w pliku
                        String hasloStudenta = ""; // Zmienna przechowująca hasło studenta

                        // Pętla, która przeszukuje plik z listą studentów
                        while (czytaczPliku.hasNextLine()) {
                            // Odczytanie kolejnej linii z pliku
                            String obecnaLinia = czytaczPliku.nextLine();
                            obecnaLinijkaPliku++; // Inkrementacja numeru linii
                            System.out.println(obecnaLinijkaPliku); // Wyświetlanie numeru linii (do debugowania)
                            // Sprawdzanie, czy numer studenta wprowadzonego przez użytkownika odpowiada bieżącej linii
                            if (wybor == obecnaLinijkaPliku) {
                                hasloStudenta = obecnaLinia; // Jeśli tak, zapisujemy hasło tego studenta
                                break; // Kończymy przeszukiwanie pliku, gdy znajdziemy odpowiednią linię
                            }
                        }

                        // Prośba o wpisanie hasła
                        System.out.println("Wpisz swoje haslo:");
                        // Tworzenie obiektu do odczytu hasła od użytkownika
                        Scanner czytaczHasla = new Scanner(System.in);
                        // Odczytanie wpisanego hasła
                        String wpisaneHaslo = czytaczHasla.nextLine();
                        System.out.println(wpisaneHaslo); // Wyświetlenie wpisanego hasła (do debugowania)
                        // Porównanie wprowadzonego hasła z zapisanym hasłem studenta
                        if (hasloStudenta.equals(wpisaneHaslo)) {
                            uzytkownikZalogowany = true; // Ustawienie flagi, jeśli hasła się zgadzają
                        } else {
                            // Jeśli hasła się nie zgadzają, wyświetlenie komunikatu o błędzie
                            System.out.println("ZLE HASLO ZALOGUJ SIE PONOWNIE");
                        }
                    }
                    break; // Koniec case 1 (Logowanie)

                case 2: // Jeśli użytkownik wybierze 2 (Rejestracja)
                    // Pobranie numeru nowego studenta z pliku
                    int numerNowegoStudenta = getNumerNowegoStudenta(listaStudentowPlik);
                    // Wyświetlenie numeru studenta
                    System.out.println("twoj numer studenta to" + numerNowegoStudenta);
                    // Prośba o wpisanie hasła
                    System.out.println("wpisz swoje haslo:");
                    ObiektSkaner.nextLine(); // Czyszczenie bufora po poprzednim odczycie
                    // Odczytanie hasła od użytkownika
                    String wpisaneHaslo = ObiektSkaner.nextLine();
                    // Dodanie nowego hasła na końcu pliku (z nową linią)
                    wpisaneHaslo = "\n" + wpisaneHaslo;
                    // Zapisanie hasła do pliku z listą studentów
                    Files.write(Paths.get("listaStudentow.txt"), wpisaneHaslo.getBytes(), StandardOpenOption.APPEND);
                    // Tworzenie nowego pliku dla studenta
                    File obiektZarejestrowanyStudent = new File("studenci" + File.separator + numerNowegoStudenta + ".txt");
                    obiektZarejestrowanyStudent.createNewFile(); // Tworzenie nowego pliku studenta
                    break; // Koniec case 2 (Rejestracja)
            }
        } catch (FileNotFoundException e) { // Obsługa wyjątku, gdy plik nie zostanie znaleziony
            System.out.println("ERROR:FileNotFoundException");
        } catch (IOException e) { // Obsługa ogólnych wyjątków związanych z wejściem/wyjściem
            System.out.println("ERROR:IOException");
        }
    }

    // Metoda do pobrania listy przedmiotów z pliku
    private static LinkedList<String> getStrings() throws FileNotFoundException {
        LinkedList<String> przedmioty = new LinkedList<>();
        // Tworzenie obiektu do odczytu pliku z przedmiotami
        File listaPrzedmiotowPlik = new File("listaPrzedmiotow.txt");
        Scanner czytaczPlikuPrzedmioty = new Scanner(listaPrzedmiotowPlik);
        // Pętla przeszukująca plik i dodająca linie do listy przedmiotów
        while (czytaczPlikuPrzedmioty.hasNextLine()) {
            String obecnaLinia = czytaczPlikuPrzedmioty.nextLine();
            przedmioty.add(obecnaLinia); // Dodanie bieżącej linii do listy przedmiotów
        }
        return przedmioty; // Zwrócenie listy przedmiotów
    }

    // Metoda do uzyskania numeru nowego studenta na podstawie liczby wierszy w pliku
    private static int getNumerNowegoStudenta(File listaStudentowPlik) throws FileNotFoundException {
        Scanner czytaczPliku = new Scanner(listaStudentowPlik);
        int numerNowegoStudenta = 1; // Początkowy numer studenta
        // Pętla przeszukująca plik i inkrementująca numer studenta na podstawie liczby wierszy w pliku
        while (czytaczPliku.hasNextLine()) {
            czytaczPliku.nextLine(); // Odczytanie bieżącej linii
            numerNowegoStudenta++; // Inkrementacja numeru studenta
        }
        return numerNowegoStudenta; // Zwrócenie numeru nowego studenta
    }
}
