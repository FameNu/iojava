import practice.PracticeLast;
import practice.ResultCity;
import practice.ResultRunningNumber;
import readwrite01.*;

public class Main {
    private final static String dirPath = "file/";
    public static void main(String[] args) {
//        morning();
//        afternoon();
        practice();
    }

    public static void morning() {
        String fileBasic = dirPath + "note.txt";
        IOStream ioStream = new IOStream();
//        ioStream.readFile(fileBasic);
//        ioStream.readFileByToken(fileBasic);

        String BigFile = dirPath + "big.txt";
        ioStream.readBigFile(BigFile);
    }

    public static void afternoon() {
        String fileName = "raf.txt";
        String filePath = dirPath + fileName;
        FileAccess fileAccess = new FileAccess();
        fileAccess.randAccessFile(filePath);
        fileAccess.readRaf(filePath);
    }

    public static void practice() {
        PracticeLast practiceLast = new PracticeLast();
//        practiceLast.readCSV();
        ResultCity resultCity = practiceLast.search("Tokyo");
        if (resultCity != null) {
            System.out.println("find on latitude = " + resultCity.getLatitude());
            System.out.println("find on longitude = " + resultCity.getLongitude());
            System.out.println("and population = " + resultCity.getPopulation());
        } else {
            System.out.println("City not found.");
        }

        long findPopulation = practiceLast.findPopulation("Japan");
        System.out.println("Population of Japan = " + findPopulation);

        ResultRunningNumber findRunningNumber = practiceLast.searchRunningNumber(1392685764);
        if (findRunningNumber != null) {
            System.out.println("find on city = " + findRunningNumber.getCity());
            System.out.println("find on country = " + findRunningNumber.getCountry());
            System.out.println("and population = " + findRunningNumber.getPopulation());
        } else {
            System.out.println("Running Number not found.");
        }
    }
}
