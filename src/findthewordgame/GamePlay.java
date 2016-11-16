/** 
 * @author: frcodefever
 */
package findthewordgame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Collections;


public class GamePlay extends ControlCorpusList{
    
    private String userName;
    private int levelGame;//epipedo duskolias paixnidiou
    private int triesNr; //prospathies ana guro
    
    //metablitis gia eisodo dedomenwn apo ton xristi
    public Scanner input = new Scanner(System.in);
    public int guessWordChoice = 0;//epilogi leksis apo ta proteinomena pou parousiazontai ston xristi
    public int recordsonCorpusleftover = 0;//bpithitiki metabliti pou deixnei poses eggrafes exoun apomeinei
    
    //constuctor
    public GamePlay()
    {
        
        
        System.out.print("ΚΑΛΩΣΗΡΘΑΤΕ ΣΤΟ ΠΑΙΧΝΙΔΙ <<ΒΡΕΣ ΤΙΣ ΛΕΞΕΙΣ>>\n");
        System.out.print("Εισάγεται το ονομά σας: ");
        userName= input.next();
        
        do //elegxos ean o xristis dinei ta epilegmena epipeda duskolias
        {
            //elegxos egkurotitas akairaiou arithmou
            try
            {
                input = new Scanner(System.in);
                System.out.print("Επιλέξτε επίπεδο δυσκολιας (1-Ευκολο, 2-Δύσκολο): ");
                levelGame = input.nextInt();
            }catch(InputMismatchException e)
            {
                System.out.println("Παρακαλούμε να δώσετε έγκυρους ακέραιους αριθμούς");
                levelGame=0;
            }
        }while(levelGame != 1 && levelGame != 2 );
        
        //arxikopoihsh prospatheiwn tou xristi ana paixnidi
        triesNr = 5;
    }
    
    public int returnlevelGame(){return levelGame;}
    public int returnTriesNr(){return triesNr;}
    
    public GamePlay(String p1,int p2,int p3)
    {userName=p1;levelGame=p2;triesNr=p3;}
    
    //Methodos pou rithmizei tin roh tou paixnidiou xrisimopoiwntas tous ulopoihmenouns methodous tis klasis GamePlay
    public void play()
    {
        AddCorpusToList();
        if (GetList().isEmpty() == true)
        {
            System.out.println("To corpus δεν περιέχει καμιά εγγραφή. Παρακαλώ ενημερώστε την λίστα με τις εγγραφές των λέξεων.");
            System.exit(0);
        }
        ShuffleList();
        //PrintList();
        Rounds();
        //System.out.println(GetList().size());
        
    }
    
    //Methodos opou emfanizei to periballon kai tin allilepidrasi me ton xristi gia kathe guro paixnidiou
    public void Rounds()
    {
        //Anaktoume tin lista me tis lekseies k tis perigrafes tous
        ArrayList list = GetList();
        
        //Gyroi paixnidiou ana 3 lekseis
        int rounds=0;
        
        int div =  list.size()/3;
        if (list.size()%3 == 0)
        {
            rounds = div;
        }
        else
        {
            rounds = div + 1;
        }
        
        //Pinakas 2 theswewn opou tha periexei tis lekseis stin 1i thesi
        // kai tis perigrafes twn antistoixwn leksewn stin 2i thesi
        String[] word_description = new String[2];
        
        int triesRound=0; //prospatheies ana gyro
        int rightGuessWords=0; //arithmos leksewn pou manteuontai swsta
        int r = 0;//metritis gyrwn
        int c = 0;//metablitis tupwsis leksewn
        int l = 0;//metablitis listwn
        //temp metablites gia na kataxwrithoun oi lekseis pou manteuontai apo ton xristi 
        String guess1,guess2,guess3 = "";
        //Lista me anakatemena ta grammata twn emfanizomenwn leksewnn pou manteuei o xristis
        ArrayList guessLetterList = new ArrayList();
        String guessWord="";
        ArrayList guessWordChoiceList = new ArrayList();
        
        //O kathe guros tha emfanizei 3 lekseis, opou prwta emfanizetai i perigrafi kai
        //stin sunexeia i leksi pou einai na brethei
        for( r=0; r<rounds; r++ )
        {
            System.out.println("\n-------------------------- "+(r+1)+"ος Γύρος Παιχνιδιού --------------------------\n");
            
            guessWordChoiceList.removeAll(guessWordChoiceList);
            triesRound = returnTriesNr();
            rightGuessWords = 0;
            
            if(list.size()>=3)
            {
                //split: opou uparxei char "|" xwrizei to string se epimerous tmimata
                //Stin periptwsi mas tha xwrisei to string kathe fora se 2 tmimata opou kataxwrountai stin 1i k 2i thesi tou pinaka word_description
                word_description = list.get(0).toString().split("\\|",-1);
                System.out.println("1."+word_description[1]);
                guess1 = word_description[0].toUpperCase();
                printGuessWord(guess1,c);
                 
                 
                word_description = list.get(1).toString().split("\\|",-1);
                System.out.println("2."+word_description[1]);
                guess2 = word_description[0].toUpperCase();
                printGuessWord(guess2,c);
                 
                word_description = list.get(2).toString().split("\\|",-1);
                System.out.println("3."+word_description[1]);
                guess3 = word_description[0].toUpperCase();
                printGuessWord(guess3,c);
                
                //prosthesi twn grammatwn stin list guessLetterList
                if(returnlevelGame() == 2)
                {
                    for(c=0;c<guess1.length();c++) guessLetterList.add(guess1.charAt(c));
                    for(c=0;c<guess2.length();c++) guessLetterList.add(guess2.charAt(c));
                    for(c=0;c<guess3.length();c++) guessLetterList.add(guess3.charAt(c));
                }
                else
                {
                    for(c=1;c<guess1.length()-1;c++) guessLetterList.add(guess1.charAt(c));
                    for(c=1;c<guess2.length()-1;c++) guessLetterList.add(guess2.charAt(c));
                    for(c=1;c<guess3.length()-1;c++) guessLetterList.add(guess3.charAt(c));
                }
                
                //Anakateusi leksewn kai tupwsi tous
                Collections.shuffle(guessLetterList);
                
                while(triesRound>0 && rightGuessWords!=3)
                {
                    System.out. println("Υπολειπόμενα Γράμματα: "+guessLetterList);
                    if(InsertGuessWordCheck(guessWord,guess1,guess2,guess3)== 1)
                    {
                        if(guessWordChoiceList.contains(guessWordChoice))
                        {
                            System.out. println("Έχετε βρεί την επιλογή :"+guessWordChoice+", επιλέξτε διαφορετική.");
                        }
                        else
                        {
                            System.out. println("Μπράβο. Μαντέψατε σωστά!!!");
                            rightGuessWords++;
                            guessWordChoiceList.add(guessWordChoice);//prostheti tin epilogi wsste na anagnwristei ti exei epileksei o xristis
                            //allagi logw afairesis grammatwn stin guessLetterList
                            guessLetterList = RemoveLetters(guessLetterList,c,l,guess1,guess2,guess3);
                        }
                    }
                    else
                    {   //periptwsi opou o xristis exei brei tin leksi swsta kai tin ksanaeisagei lathos ston idio guro
                        if(guessWordChoiceList.contains(guessWordChoice)) 
                        {
                            System.out. println("Έχετε βρεί την επιλογή :"+guessWordChoice+", επιλέξτε διαφορετική.");
                        }
                        else
                        {
                            System.out. println("Λάθος. Έχετε ακόμα: "+(triesRound-1)+" προσπάθειες.");
                            triesRound--;
                        }
                    }
                }
                
                if(rightGuessWords==3) System.out. println("\nΟλοκληρώσατε επιτυχώς τον γύρο "+(r+1)+" με σωστές απαντήσεις, κάνοντας: "+ (returnTriesNr()-triesRound)+" λαθη.");
                if(triesRound == 0) System.out. println("\nΔεν ολοκληρώσατε τον γύρο "+(r+1)+ ". Οι προσπάθειές σας τελείωσαν.");
                
                //diagrafi twn 3wn stoixeiwn apo tin lista kathe fora apo tin i=0 thesi tis
                RemoveElementFromList(0);
                RemoveElementFromList(0);
                RemoveElementFromList(0);
            }
            else //se periptwsi pou apomenoun duo i mia kataxwriseis sto corpus twn leksewn me tis perigrafes tous
            {
                recordsonCorpusleftover = 2;
                if(list.size()==2) //periptwsi 2 kataxwrisewn sto corpus
                {
                    word_description = list.get(0).toString().split("\\|",-1);
                    System.out.println("1."+word_description[1]);
                    guess1 = word_description[0].toUpperCase();
                    printGuessWord(guess1,c);
                     
                    word_description = list.get(1).toString().split("\\|",-1);
                    System.out.println("2."+word_description[1]);
                    guess2 = word_description[0].toUpperCase();
                    printGuessWord(guess2,c);

                    //prosthesi twn grammatwn stin list guessLetterList
                    if(returnlevelGame() == 2)
                    {
                        for(c=0;c<guess1.length();c++) guessLetterList.add(guess1.charAt(c));
                        for(c=0;c<guess2.length();c++) guessLetterList.add(guess2.charAt(c));
                    }
                    else
                    {
                        for(c=1;c<guess1.length()-1;c++) guessLetterList.add(guess1.charAt(c));
                        for(c=1;c<guess2.length()-1;c++) guessLetterList.add(guess2.charAt(c));
                    }

                    //Anakateusi leksewn kai tupwsi tous
                    Collections.shuffle(guessLetterList);

                    while(triesRound>0 && rightGuessWords!=2)
                    {
                        System.out. println("Υπολειπόμενα Γράμματα: "+guessLetterList);
                        if(InsertGuessWordCheck(guessWord,guess1,guess2,"")== 1)
                        {
                            if(guessWordChoiceList.contains(guessWordChoice))
                            {
                                System.out. println("Έχετε βρεί την επιλογή :"+guessWordChoice+", επιλέξτε διαφορετική.");
                            }
                            else
                            {
                                System.out. println("Μπράβο. Μαντέψατε σωστά!!!");
                                rightGuessWords++;
                                guessWordChoiceList.add(guessWordChoice);//prostheti tin epilogi wsste na anagnwristei ti exei epileksei o xristis
                                //allagi logw afairesis grammatwn stin guessLetterList
                                guessLetterList = RemoveLetters(guessLetterList,c,l,guess1,guess2,"");
                            }
                        }
                        else
                        {   //periptwsi opou o xristis exei brei tin leksi swsta kai tin ksanaeisagei lathos ston idio guro
                            if(guessWordChoiceList.contains(guessWordChoice)) 
                            {
                                System.out. println("Έχετε βρεί την επιλογή :"+guessWordChoice+", επιλέξτε διαφορετική.");
                            }
                            else
                            {
                                System.out. println("Λάθος. Έχετε ακόμα: "+(triesRound-1)+" προσπάθειες.");
                                triesRound--;
                            }
                        }
                    }                    
                    
                    if(rightGuessWords==2) System.out. println("\nΟλοκληρώσατε επιτυχώς τον γύρο "+(r+1)+" με σωστές απαντήσεις, κάνοντας: "+ (returnTriesNr()-triesRound)+" λαθη.");
                    if(triesRound == 0) System.out. println("\nΔεν ολοκληρώσατε τον γύρο "+(r+1)+ ". Οι προσπάθειές σας τελείωσαν.");
                    
                    //diagrafi twn 2wn stoixeiwn apo tin lista kathe fora apo tin i=0 thesi tis
                    RemoveElementFromList(0);
                    RemoveElementFromList(0);
                     
                }
                else //periptwsi mias kataxwrisis sto corpus list.size()==1
                {
                    recordsonCorpusleftover = 1;
                    
                    word_description = list.get(0).toString().split("\\|",-1);
                    System.out.println("1."+word_description[1]);
                    guess1 = word_description[0].toUpperCase();
                    printGuessWord(guess1,c);

                    //prosthesi twn grammatwn stin list guessLetterList
                    if(returnlevelGame() == 2)
                    {
                        for(c=0;c<guess1.length();c++) guessLetterList.add(guess1.charAt(c));
                    }
                    else
                    {
                        for(c=1;c<guess1.length()-1;c++) guessLetterList.add(guess1.charAt(c));
                    }

                    //Anakateusi leksewn kai tupwsi tous
                    Collections.shuffle(guessLetterList);

                    while(triesRound>0 && rightGuessWords!=1)
                    {
                        System.out. println("Υπολειπόμενα Γράμματα: "+guessLetterList);
                        if(InsertGuessWordCheck(guessWord,guess1,"","")== 1)
                        {
                            if(guessWordChoiceList.contains(guessWordChoice))
                            {
                                System.out. println("Έχετε βρεί την επιλογή :"+guessWordChoice+", επιλέξτε διαφορετική.");
                            }
                            else
                            {
                                System.out. println("Μπράβο. Μαντέψατε σωστά!!!");
                                rightGuessWords++;
                                guessWordChoiceList.add(guessWordChoice);//prostheti tin epilogi wsste na anagnwristei ti exei epileksei o xristis
                                //allagi logw afairesis grammatwn stin guessLetterList
                                guessLetterList = RemoveLetters(guessLetterList,c,l,guess1,"","");
                            }
                        }
                        else
                        {   //periptwsi opou o xristis exei brei tin leksi swsta kai tin ksanaeisagei lathos ston idio guro
                            if(guessWordChoiceList.contains(guessWordChoice)) 
                            {
                                System.out. println("Έχετε βρεί την επιλογή :"+guessWordChoice+", επιλέξτε διαφορετική.");
                            }
                            else
                            {
                                System.out. println("Λάθος. Έχετε ακόμα: "+(triesRound-1)+" προσπάθειες.");
                                triesRound--;
                            }
                        }
                    }                        
                    
                    if(rightGuessWords==1) System.out. println("\nΟλοκληρώσατε επιτυχώς τον γύρο "+(r+1)+" με σωστές απαντήσεις, κάνοντας: "+ (returnTriesNr()-triesRound)+" λαθη.");
                    if(triesRound == 0) System.out. println("\nΔεν ολοκληρώσατε τον γύρο "+(r+1)+ ". Οι προσπάθειές σας τελείωσαν.");
                    
                    //doagrafi 1ou stoixeiou listas
                    RemoveElementFromList(0);
                }
            }
            
            //Erwtisi ean o xristis thelei na sunexisei ston epomeno guro
            String contin="";
            do
            {
                System.out.print("\nΘέλετε να συνεχίσετε στον επόμενο γυρο με τις επόμενες λέξεις?(y/n)");
                contin = input.next();
            }while(contin.compareTo("y") != 0 && contin.compareTo("n") != 0);
            if(contin.compareTo("n") == 0 ) break;
        }
        
    }
    
    //methodos tupwsis leksis pou prepei na mantepsei o xristis
    //pername tin metabliti c ws parametro gia na min ksanadilwnetai kathe fora pou kaloume thn methodo
    public void printGuessWord(String guess, int c)
    {
         //System.out.print(guess+guess.length()+"|");
         System.out.print("Λέξη :");
         
         if(returnlevelGame() == 1)
         {
             for( c = 0; c < guess.length(); c++)
             {
                 if(c == 0) System.out.print(guess.charAt(c)+" ");
                 else if(c == (guess.length() - 1)) System.out.print(guess.charAt(c)+" ");
                 else System.out.print("_ ");
             }
         }
         else //levelGame == 2
         {
             for( c = 0; c < guess.length(); c++)
             {
                 System.out.print("_ ");
             }
         }
         System.out.println();
    }
    
    //Methodos opou o xristis eisagei leksi kai elegxetai an manteuei swsta. Epistrefei 1 otan i leksi pou manteuetai einai swsti, alliws 0.
    //guessWordChoice,guessWord pernountai sa parametroi gia na min ksanadilwnontai polles fores gia kathe klisi tis methodou
    public int InsertGuessWordCheck(String guessWord,String guess1,String guess2,String guess3)
    {
        do
        {
            try
            {
                input = new Scanner(System.in);
                System.out.print("\nΕπιλέξτε λέξη που θέλετε να μαντέψετε :");
                guessWordChoice = input.nextInt();
                
                if(recordsonCorpusleftover == 2 && guessWordChoice >= 3)
                {
                    do
                    {
                        System.out.println("Στο corpus υπάρχουν 2 εγγραφές, παρακαλώ επιλέξτε 1 ή 2 : ");
                        guessWordChoice = input.nextInt();
                    }while(guessWordChoice !=1 && guessWordChoice !=2);
                }
                
                if(recordsonCorpusleftover == 1 && guessWordChoice >= 2)
                {
                    do
                    {
                        System.out.println("Στο corpus υπάρχει μία εγγραφή, παρακαλώ επιλέξτε 1 : ");
                        guessWordChoice = input.nextInt();
                    }while(guessWordChoice !=1 );
                }
                
            }catch(InputMismatchException e)
            {
                System.out.println("Παρακαλούμε να δώσετε έγκυρους ακέραιους αριθμούς");
                guessWordChoice=0;
            }
        }while(guessWordChoice !=1 && guessWordChoice !=2 && guessWordChoice !=3);
        //while(guessWordChoice<=0 || guessWordChoice>3);
        
        
        System.out.print("Εισάγετε την λέξη :");
        guessWord = input.next();
        guessWord = guessWord.toUpperCase();
        
        if(guessWordChoice == 1)
        {
            if (guessWord.compareTo(guess1) == 0) return 1;
            else return 0;
        }
        else if(guessWordChoice == 2)
        {
            if (guessWord.compareTo(guess2) == 0) return 1;
            else return 0;
        }
        else if(guessWordChoice == 3)
        {
            if (guessWord.compareTo(guess3) == 0) return 1;
            else return 0;
        }
        
        return 0;
    }
    
    //diagrafi guessWordChoice xaraktirwn apo tin lista guessLetterList
    //wste na emfanizontai ta upoleipomena grammata
    public ArrayList RemoveLetters(ArrayList guessLetterList,int c,int l,String guess1,String guess2,String guess3)
    {
        if(returnlevelGame()==2)
        {
            if(guessWordChoice == 1)
            {
                for(c=0;c<guess1.length();c++)
                    for(l=0;l<guessLetterList.size();l++)
                    {
                        if(guess1.charAt(c) == guessLetterList.get(l).toString().charAt(0))
                        {
                            guessLetterList.remove(l);
                            break;
                        }
                    }
            }
            else if(guessWordChoice == 2)
            {
                for(c=0;c<guess2.length();c++)
                    for(l=0;l<guessLetterList.size();l++)
                    {
                        if(guess2.charAt(c) == guessLetterList.get(l).toString().charAt(0))
                        {
                            guessLetterList.remove(l);
                            break;
                        }
                    }

            }
            else if(guessWordChoice == 3)
            {
                for(c=0;c<guess3.length();c++)
                    for(l=0;l<guessLetterList.size();l++)
                    {
                        if(guess3.charAt(c) == guessLetterList.get(l).toString().charAt(0))
                        {
                            guessLetterList.remove(l);
                            break;
                        }
                    }
            }
        }
        else //levelGame = 1
        {
            if(guessWordChoice == 1)
            {
                for(c=1;c<guess1.length()-1;c++)
                    for(l=0;l<guessLetterList.size();l++)
                    {
                        if(guess1.charAt(c) == guessLetterList.get(l).toString().charAt(0))
                        {
                            guessLetterList.remove(l);
                            break;
                        }
                    }
            }
            else if(guessWordChoice == 2)
            {
                for(c=1;c<guess2.length()-1;c++)
                    for(l=0;l<guessLetterList.size();l++)
                    {
                        if(guess2.charAt(c) == guessLetterList.get(l).toString().charAt(0))
                        {
                            guessLetterList.remove(l);
                            break;
                        }
                    }

            }
            else if(guessWordChoice == 3)
            {
                for(c=1;c<guess3.length()-1;c++)
                    for(l=0;l<guessLetterList.size();l++)
                    {
                        if(guess3.charAt(c) == guessLetterList.get(l).toString().charAt(0))
                        {
                            guessLetterList.remove(l);
                            break;
                        }
                    }
            }
        }
        return guessLetterList;
    }
    
}
