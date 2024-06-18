package readwrite01;

import java.io.*;
import java.util.Objects;

public class IOStream {
    public void readFile(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader fileReader = new BufferedReader(file);
            String content = "";

            while ((content = fileReader.readLine()) != null) {
                if (content.contains("ID")){
                    System.out.println(content);
                    System.out.println(content.indexOf("ID is "));
                    // find id after text "ID is "
                    String id = (content.split("ID is ")[1]).split(" ")[0];
                    System.out.println(id);

                    String[] words = content.split(" ");
                    for (String word : words) {
                        if (Integer.parseInt(word) > 0) {
                            System.out.println("word = " + word);
                        }
                    }
                }
            }

//            fileReader.lines().forEach(System.out::println);

            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("ex = " + ex);
        } catch (IOException ioException) {
            System.out.println("ioException = " + ioException);
        }
    }

    public void readFileByToken(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader fileReader = new BufferedReader(file);

            StreamTokenizer token = new StreamTokenizer(fileReader);

            int type = token.nextToken();
//            System.out.println("type = " + type);
//            System.out.println(token.sval);
            for (int i = 0; i < 10; ) {
                if (token.ttype == StreamTokenizer.TT_WORD) {
                    System.out.println("token.sval = " + token.sval);
                    i++;
                } else if (token.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println("token.nval = " + token.nval);
                    i++;
                }
                token.nextToken();
            }

            while (token.ttype != StreamTokenizer.TT_EOF) {
                if (token.ttype == StreamTokenizer.TT_WORD && token.sval.contains("ID")) {
                    while (token.ttype != StreamTokenizer.TT_NUMBER) {
                        token.nextToken();
                    }
                    System.out.printf("ID: %.0f\n", token.nval);
                    break;
                }
                token.nextToken();
            }

            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("ex = " + ex);
        } catch (IOException ioException) {
            System.out.println("ioException = " + ioException);
        }
    }

    public void readBigFile(String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader fileReader = new BufferedReader(file);

            int count = 0;
            String content = "";
            String containName = "Sherlock Holmes";
            while ((content = fileReader.readLine()) != null) {
                if (content.toLowerCase().contains(containName.toLowerCase())) {
                    String[] words = content.split(" ");
                    for (int i = 0; i < words.length; i++) {
                        if (i + 1 != words.length &&
                                (words[i].toLowerCase() + " " + words[i + 1].toLowerCase()).contains(containName.toLowerCase())) {
                            System.out.println("getSherlock = " + words[i] + " " + words[i + 1]);
                            count++;
                        }
                    }
                }
                if (content.toLowerCase().contains("KK".toLowerCase())) {
                    System.out.println("content = " + content);
                }
            }
            System.out.println("count \"Sherlock Holmes\" = " + count);

            String myName = "Phuwamet Panjachalermrat ID: 65130500066";
            String result = "There are " + count + " \"" + containName + "\" in the file.\n";
            String fileContent = myName + "\n" + result;
            writeResult("lab2.txt", fileContent);

            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("ex = " + ex);
        } catch (IOException ioException) {
            System.out.println("ioException = " + ioException);
        }
    }

    private void writeResult(String fileName, String content) {
        try {
            String filePath = "file/" + fileName;
            FileWriter file = new FileWriter(filePath);
            BufferedWriter fileWriter = new BufferedWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println("ioException = " + ioException);
        }
    }
}
