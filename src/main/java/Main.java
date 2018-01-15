
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static Scanner in = new Scanner(System.in);
    private static List<Film> films;


    private static final String QUERIES =   "\n1 - Is there any film by Year\n" +                           //anymatch
                                            "2 - Show n films by Year\n" +                                  //limit
                                            "3 - Show n films by Year sorted by name\n" +                   //sorted
                                            "4 - Show all runtimes\n" +                                     //distinct
                                            "5 - Print every word that is used in film names by year\n" +   //flatmap
                                            "6 - Show n film names\n" +                                     //map
                                            "7 - Longest film of Year\n" +                                  //Optional
                                            "0 - Exit\n";

    private static final Map<Integer, Action> actions = Map.of(
            1, Main::useAnyMatch,
            2, Main::useFilter,
            3, Main::useSorted,
            4, Main::useDistinct,
            5, Main::useFlatMap,
            6, Main::useMap,
            7, Main::useOptional
    );

    public static void main(String args[]) throws IOException {

        films = initList();

        System.out.println(QUERIES);
        int choice = in.nextInt();
        while(choice != 0){
            actions.get(choice).execute();
            System.out.println(QUERIES);
            choice = in.nextInt();
        }

        LocalDate date = LocalDate.parse(in.nextLine());

        System.out.println(films.stream().anyMatch(film->film.getDate().equals(date)));
    }

    private static void useAnyMatch(){
        System.out.print("Year: ");
        int year = in.nextInt();
        System.out.println(films.stream().anyMatch(film -> film.getDate().getYear() == year));
    }

    private static void useFilter(){
        System.out.print("Year: ");
        int year = in.nextInt();
        System.out.print("How many?\n");
        int n = in.nextInt();
        System.out.println(films.stream().filter(film -> film.getDate().getYear() == year).limit(n).collect(Collectors.toList()));
    }

    private static void useSorted(){
        System.out.print("Year: ");
        int year = in.nextInt();
        System.out.println(films.stream().filter(film -> film.getDate().getYear() == year).sorted(Comparator.comparing(Film::getName)).collect(Collectors.toList()));
    }

    private static void useDistinct(){
        System.out.println(films.stream().map(Film::getRuntime).distinct().map(duration-> duration.toHours() + ":" + duration.toMinutesPart()).collect(Collectors.toList()));
    }

    private static void useFlatMap(){
        System.out.print("Year: ");
        int year = in.nextInt();
        System.out.println(films.stream().filter(film->film.getDate().getYear() == year).flatMap(film-> Stream.of(film.getName().split(" "))).collect(Collectors.toList()));
    }

    private static void useMap(){
        System.out.print("How many?\n");
        int n = in.nextInt();
        System.out.println(films.stream().limit(n).map(Film::getName).collect(Collectors.toList()));
    }

    private static void useOptional(){
        System.out.print("Year :");
        int year = in.nextInt();
        Optional<Film> optionalFilm = films.stream().filter(film -> film.getDate().getYear() == year).max(Comparator.comparing(Film::getRuntime));
        if(optionalFilm.isPresent())
        optionalFilm.ifPresent(System.out::println);
    }






    private static List<Film> initList() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/data.csv"));
        String[] parts;
        String line;
        List<Film> films = new LinkedList<>();

        while((line = in.readLine())!= null){
            parts = line.split(",");
            films.add(new Film(parts[0], parts[1], Integer.parseInt(parts[2])));
        }
        return films;
    }



}
