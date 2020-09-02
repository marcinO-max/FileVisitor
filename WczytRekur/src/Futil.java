
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Futil {

    public static void processDir(String dirName, String resultFileName) throws IOException {
        Charset in = Charset.forName("Cp1250");
        Charset out = Charset.forName("UTF-8");
        List<Path> pathsOfDir = new ArrayList<Path>();
        Path dirPath = Paths.get(dirName);
        Path resPath = Paths.get(resultFileName);
        String end = ".txt";
        File zakonczmy = new File(resultFileName);


    Files.walkFileTree(dirPath, new FileVisitor<Path>() {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            return null;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            StringBuffer sb = new StringBuffer();
            if (file.getFileName().endsWith(end)) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String s1 = scanner.nextLine();
                    sb.append(s1);
                }
                try {
                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(zakonczmy, true)));
                    pw.println(sb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return null;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return null;
        }
    });




    }



    //       try(Stream<Path> subPath = Files.walk(dirPath)) {
//            List<String> result = subPath.map(x -> x.toString()).filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
//            result.forEach(System.out::print);
//        }catch(IOException e){
//            e.printStackTrace();
//        }




//       for(File f : directory.listFiles()){
//           if(!f.isDirectory()) {
//               try {
//                   Scanner s = new Scanner(f);
//                   BufferedWriter writer = new BufferedWriter(new FileWriter(result));
//                   while(s.hasNextLine()){
//                        writer.write(s.nextLine());
//                   }
//               } catch (FileNotFoundException e) {
//                   e.printStackTrace();
//               } catch (IOException e) {
//                   e.printStackTrace();
//               }
//           }
//       }
}
