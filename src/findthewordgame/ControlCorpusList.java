/** 
 * @author: frcodefever
 */
package findthewordgame;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

/*
Klasi i opoia diaxeirizetai tin sullogi corpus, 
sto paradeigma mas to corpus einai to arxeio Words+Description.txt, opou periexontai
oi lekseis kai oi orismoi tous.
*/
public class ControlCorpusList {
    
    //lista pou periexei ws dedomena tis lekseis kai tis perigrafes tous
    private ArrayList listOfWords_Description;//the List
    
    
    //constructor
    public ControlCorpusList(){
    listOfWords_Description = new ArrayList();
    }
    
    //Methodos pou prosthetei stin lista listOfWords_Description ta dedomena pou antlei apo to corpus (sullogi sto .txt arxeio)
    public void AddCorpusToList(){
        
        try
        {
            //Antloume to path pou douleuei to project
            String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            //Entopismos arxeiou
            FileInputStream file = new FileInputStream(currentPath+"\\src\\findthewordgame\\Words+Description.txt");
            //Xrisimopoieitai InputStreamReader gia encoding wste na anagnwristoun ellinikoi xaraktires (Windows-1253)
            InputStreamReader inStr = new InputStreamReader(file,"Windows-1253");
            //Prosbasi sto arxeio (.txt) gia diabasma 
            BufferedReader in = new BufferedReader( inStr );
            
            //diabazei oli tin trexousa grammi tou arxeiou .txt mexri na entopistei xaraktiras "\n" (enter)
            //ki kataxwrisi stin temp metabliti str
            String str=in.readLine();
            listOfWords_Description.add(str);
            
            
            if(str == null)//se periptwsi pou to corpus einai teleiw adeios xwris kamia kataxwrisi
            {
                listOfWords_Description.remove(0);
            }
            
            while ((str = in.readLine()) != null) {
                //System.out.println(str);
                listOfWords_Description.add(str);
            }
            file.close();
            inStr.close();
            in.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Methodos prosthesis stoixeiwn se mia sigkekrimeni thesi stin listOfWords_Description
    public void AddElementToList(int position, String p1){
        listOfWords_Description.add(position,p1);
    }
    
    //Methodos afairesis stoixeiwn apo mia sigkekrimeni thesi stin listOfWords_Description 
    public void RemoveElementFromList(int position){
        listOfWords_Description.remove(position);
    }
    
    //Methodos epistrofis tis listas me to corpus .txt
    public ArrayList GetList(){
        return listOfWords_Description;
    }
    //Anakateuei tin listOfWords_Description xrisimopoiwntas tin sunartisi random
    public void ShuffleList(){
        Collections.shuffle(listOfWords_Description);
    }
    
    public void PrintList(){
        System.out.println("\n============================================");
        System.out.println("=== Tupwsi stoixeiwn Listas: listOfWords ===\n");
        int i;
        for(i=0;i<listOfWords_Description.size();i++){
            System.out.println(listOfWords_Description.get(i));
        }
        System.out.println("\n====== Telos Tupwsis =======");
        System.out.println("============================");
    }
}
