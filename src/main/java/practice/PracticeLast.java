package practice;

import java.io.*;

public class PracticeLast {
    private final String dirPath = "file/";
    private final String fileCSV = dirPath + "worldcities.csv";
    private final String filePractice = dirPath + "practice.dat";

    private final int sizeCity = 50;
    private final int sizeCountry = 50;

    // define the pointer for each field
    private final int pointerId = 0;
    private final int pointerCity = pointerId + 4;
    private final int pointerLatitude = pointerCity + sizeCity;
    private final int pointerLongitude = pointerLatitude + 8;
    private final int pointerCountry = pointerLongitude + 8;
    private final int pointerPopulation = pointerCountry + sizeCountry;
    private final int pointerNewLine = pointerPopulation + 4;
    public void readCSV() {
        try {
            FileReader file = new FileReader(fileCSV);
            BufferedReader fileReader = new BufferedReader(file);
            String content = fileReader.readLine();
            String[] columns = content.split(",");
            CityIndex cityIndex = new CityIndex();
            for (int i = 0; i < columns.length; i++) {
                switch (columns[i]) {
                    case "city" -> cityIndex.setCity(i);
                    case "lat" -> cityIndex.setLatitude(i);
                    case "lng" -> cityIndex.setLongitude(i);
                    case "country" -> cityIndex.setCountry(i);
                    case "population" -> cityIndex.setPopulation(i);
                    case "id" -> cityIndex.setId(i);
                }
            }

            RandomAccessFile fileOutput = new RandomAccessFile(filePractice, "rw");

            while ((content = fileReader.readLine()) != null) {
                String[] data = content.split(",");

                int id = Integer.parseInt(data[cityIndex.getId()]);

                String city = data[cityIndex.getCity()];

                double latitude = Double.parseDouble(data[cityIndex.getLatitude()]);

                double longitude = Double.parseDouble(data[cityIndex.getLongitude()]);

                String country = data[cityIndex.getCountry()];

                int population = 0;
                if (!data[cityIndex.getPopulation()].isEmpty()) {
                    population = Integer.parseInt(data[cityIndex.getPopulation()]);
                }

                writeFile(fileOutput, id, city, sizeCity, latitude, longitude, country, sizeCountry, population);
                System.out.println("city = " + city);
            }

            fileOutput.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile(RandomAccessFile raf, int id, String city, int sizeCity, double latitude, double longitude, String country, int sizeCountry, int population) {
        try {
            raf.writeInt(id);

            byte[] cityInBytes = getOverOfString(city, sizeCity).getBytes();
            raf.write(cityInBytes, 0, sizeCity);

            raf.writeDouble(latitude);

            raf.writeDouble(longitude);

            byte[] countryInBytes = getOverOfString(country, sizeCountry).getBytes();
            raf.write(countryInBytes, 0, sizeCountry);

            raf.writeInt(population);

            raf.writeUTF("\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getOverOfString(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length);
        }
        for (int i = str.length(); i < length; i++) {
            str += " ";
        }
        return str;
    }

    public ResultCity search(String searchCityName) {
        try {
            RandomAccessFile raf = new RandomAccessFile(filePractice, "r");

            long lineCount = findLineFromCityName(raf, searchCityName);

            if (lineCount == -1) return null;
            raf.seek((lineCount * pointerNewLine) + pointerLatitude);
            double getLatitude = raf.readDouble();
            double getLongitude = raf.readDouble();

            raf.seek((lineCount * pointerNewLine) + pointerPopulation);
            int getPopulation = raf.readInt();

            return (new ResultCity(getLatitude, getLongitude, getPopulation));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long findLineFromCityName(RandomAccessFile raf, String searchCityName) throws IOException {
        long countLine = 0;
        while (raf.getFilePointer() < raf.length()) {
            raf.seek((countLine * pointerNewLine) + pointerCity);
            byte[] cityInBytes = new byte[sizeCity];
            raf.read(cityInBytes);
            String city = (new String(cityInBytes)).trim();
            if (city.equals(searchCityName)) {
                return countLine;
            }
            countLine++;
        }
        return -1;
    }

    public long findPopulation(String country) {
        try {
            RandomAccessFile raf = new RandomAccessFile(filePractice, "r");
            long totalPopulation = 0;
            long lineCount = 0;
            while (raf.getFilePointer() < raf.length()) {

                raf.seek(lineCount * pointerNewLine + pointerCountry);

                byte[] countryInBytes = new byte[sizeCountry];
                raf.read(countryInBytes, 0, sizeCountry);

                String getCountry = (new String(countryInBytes)).trim();

                if (getCountry.equals(country)) {
                    raf.seek(lineCount * pointerNewLine + pointerPopulation);
                    int getPopulation = raf.readInt();
                    totalPopulation += getPopulation;
                }
                lineCount++;
            }
            raf.close();
            return totalPopulation;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultRunningNumber searchRunningNumber(int cityNumber) {
        try {
            RandomAccessFile raf = new RandomAccessFile(filePractice, "r");

            long lineCount = findLineFromCityNumber(raf, cityNumber);

            if (lineCount == -1) return null;

            raf.seek((lineCount * pointerNewLine) + pointerCity);
            byte[] cityInBytes = new byte[sizeCity];
            raf.read(cityInBytes);
            String getCity = (new String(cityInBytes)).trim();

            raf.seek((lineCount * pointerNewLine) + pointerCountry);
            byte[] countryInBytes = new byte[sizeCountry];
            raf.read(countryInBytes);
            String getCountry = (new String(countryInBytes)).trim();

            raf.seek((lineCount * pointerNewLine) + pointerPopulation);
            int getPopulation = raf.readInt();

            return (new ResultRunningNumber(getCity, getCountry, getPopulation));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long findLineFromCityNumber(RandomAccessFile raf, int cityNumber) {
        try {
            long countLine = 0;
            while (raf.getFilePointer() < raf.length()) {
                raf.seek((countLine * pointerNewLine) + pointerId);
                int id = raf.readInt();
                if (id == cityNumber) {
                    System.out.println("id = " + id);
                    return countLine;
                }
                countLine++;
            }
            return -1;
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return -1;
        }
    }
}
