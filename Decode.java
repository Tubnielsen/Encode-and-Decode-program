/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.Arrays;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 /**
  *
  * @author MikkelPosselt
  */
 public class Decode {

     /**
      * @param args the command line arguments
      */
     public static void main(String[] args) throws FileNotFoundException, IOException {
         encodedFileReader(new File(args[0]), new File(args[1])); //Her kalder vi funktionen encodedFileReader, med de to filer som gives i terminalen.
     }

     public static void encodedFileReader(File file, File out) throws FileNotFoundException{
         int[] occurences = new int[256]; //Først laver vi et array til at holde de gemte occurence værdier.
         InputStream is = new FileInputStream(file);
         OutputStream os = new FileOutputStream(out);
         BitInputStream bis = new BitInputStream(is);
         int num;
         int counter = 0;
         try {
             while (counter < 256){ //Her laver vi et loop, der læser hvor mange gange hvert Bit-Værdi opstod i den originale tekst.
                 num = bis.readInt();
                 occurences[counter] = num;
                 counter++;
             }
             DictBinTree dbt = Encode.huffman(occurences).data; //Her laver vi et træ, ud fra huffmans funktionen inde fra Encode filen.
             Node currentNode = dbt.root;
           	int bit;
             while ((bit = bis.readBit()) != -1){ //Til sidst laver vi så et whil loop der læser bits
                 if (bit == 0){
                     currentNode = currentNode.getLeftChild(); //Hvis den bit er lig 0, så bevæges der til venstre i træet.
                 } else{
                     currentNode = currentNode.getRightChild(); //Hvis den er 1, bevæges der til højre.
                 }
                 if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null){ //Hvis loopet kommer ned til et leaf i træet
                     os.write(currentNode.getBitValue()); //Så skal den skrive det blads, bitværdi i den decodede fil
                     currentNode = dbt.root; //Og sætte den nuværende node til at være root.
                 }
             }
         } catch (IOException ex) {}
     }
 }
