/**
 * Assignemt Part: 3
 * SpellCheck.cs
 *
 * Description: This program implements a GUI for a dictionary editor program,
 *  it links to SpellChecker.dll to implement the background work of the dictionary.
 *  See DictionaryForm.cs for the most recent changes. 
 * 
 * Name: Nicholas Nagrodski
 * ZID: Z140294
 * Course: CSCI473 - .NET
 * Prof: Zerwekh
 * Due Date: 2011/03/07
 * Version: 3.0.0.0
 *
 */
using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;
using System.Threading;

namespace SpellChecker
{
  //  This class reads in a file of "dictionary words" and uses them
  // as the basis of our DictionaryEditor Program.
  public class SpellCheck
  {
    protected List<string> dictionaryWords; // Field to hold our entire dictionary.
    public List<WordEventArgs> dictWordInfoLine; // Field to hold the ...  
    private System.Random rnd;

    public delegate void WordEventArgsEventHandler(Object o, WordEventArgs wea);
    public event WordEventArgsEventHandler WordEventArgsEvent;

    // Default constructor for SpellCheck.
    public SpellCheck()
    {
      dictionaryWords = new List<string>();
      dictWordInfoLine = new List<WordEventArgs>();
      rnd = new Random();

      readWordList();         // 
      readWordDefinitions();  // Populates the List<WordEventArgs> dictWordInfoLine.

      TimerCallback tcb = new TimerCallback(sendWordEventArgs);
      Timer timer = new Timer(tcb, null, 0, 10000); // Create a timer with a 10s interval.

  }  // End public SpellCheck()   


  protected virtual void OnWordEventArgsEvent(WordEventArgs wea)
  {
    if (WordEventArgsEvent != null)
      WordEventArgsEvent(this, wea);
  }

  public void sendWordEventArgs(Object o)
  {
    OnWordEventArgsEvent(dictWordInfoLine[rnd.Next(0, dictWordInfoLine.Count)]);
  }

  //  Returns a descriptive string on whether the passed in string
  // was found in the dictionary.
  public string CheckSpelling(string str)
        {
            //Block of code to test our custom exception 
            //handeling class shown below.
            double result;

            if (str.Equals("aaa"))
                throw new SpellException("Word was \"aaa\"");
            if (str.Equals("bbb")) //If the word is bbb we create a division by zero exception.
                // Perform a divide by zero.  Compiler can't catch this.
                result = 100 / int.Parse("0");
            if (str.Equals("ccc")) //If the word is ccc 
            {
                try
                {
                    result = 100 / int.Parse("0");  // Perform a division by zero.
                }
                catch (DivideByZeroException dbze)
                {
                    throw new SpellException(this.ToString(), "Word was \"ccc\"", dbze);
                }
            }

            //Otherwise we can check the spelling of the word.
            if (dictionaryWords.Contains(str))
            {
                return "Word \""
                       + str
                       + "\" is spelled correctly.\nFull Version Name: "
                       + Assembly.GetExecutingAssembly().GetName().FullName
                       + "\nVersion Number: "
                       + Assembly.GetExecutingAssembly().GetName().Version;
            }
            else
            {
                return "Incorrect Spelling!";
            }
        }
    
  public void readWordList()
        {
            StreamReader sr = null;
            try
            {
                // Create an instance of StreamReader to read from a file.
                sr = new StreamReader("WordList.txt");

                // Read and store lines from the file until the EOF is reached.
                string inputLine;
                while ((inputLine = sr.ReadLine()) != null)
                    dictionaryWords.Add(inputLine.ToLower());
            }
            catch (Exception e)
            {
                // Let the user know what went wrong.
                Console.WriteLine("The file could not be read:");
                Console.WriteLine(e.Message);
            }
            finally
            {
                // Dispose of the object if we acquired resources.
                if (sr != null)
                    sr.Dispose();
            }
        }


  // This function parses in information from "WordDefinitions.txt" in the following format:
  // headword*pos1*pronunciation*semantic fields*social usage*
  // cross references*definition number*pos2*definition*short definition*
  public void readWordDefinitions()
        {
            char [] tokensMain = {'*'};
            char [] tokensSub = { ',' };
            string [] inputLine;
            string inputLineWhole;
            StreamReader sr;
            
            using (sr = new StreamReader("WordDefinitions.txt") )
            {
                while ((inputLineWhole = sr.ReadLine()) != null)
                {
                    // Container to read in our data.
                    WordEventArgs wea = new WordEventArgs(); 
                    
                    // Tokenize the line and read in each part of data.
                    inputLine = inputLineWhole.Split(tokensMain); 

                    wea.Headword = inputLine[0];
                    wea.Pos1 = inputLine[1];
                    wea.Pronunciation = inputLine[2];

                    wea.SemainticFields = inputLine[3].Split(tokensSub).ToList();
                    wea.SocialUsage = inputLine[4].Split(tokensSub).ToList();
                    wea.CrossReferences = inputLine[5].Split(tokensSub).ToList();

                    wea.DefinitionNumber = inputLine[6];
                    wea.Pos2 = inputLine[7];
                    wea.Definition = inputLine[8];
                    wea.ShortDefinition = inputLine[9];

                    dictWordInfoLine.Add(wea);  // Add the item, WordEventArgs to the list.
                }
            }
        } // End public void getHeadWordInfo()
  }  // End class SpellCheck


  // Class that defines custom exception for SpellCheck.
  public class SpellException : ApplicationException
  {
    protected string message;
    protected string sender;

    // Three MSDN recomended constructors.
    public SpellException() : base() { }
    public SpellException(string s)
      : base(s)
    {
      message = s;
    }
    public SpellException(string s, Exception inner)
      : base(s, inner)
    {
      message = s;
    }
    public SpellException(string sender, string s, Exception inner)
      : base(s, inner)
    {
      message = s;
      this.sender = sender;
    }

    //property to allow set/get of the "sender" string.
    public string Sender
    {
      get { return sender; }
      set { sender = value; }
    }
  } // End class SpellException


  public class WordEventArgs : EventArgs
  {
    private string headword;
    private string pos1;       
    private string pronunciation;
    private List<string> semanticFields;
    private List<string> socialUsage;
    private List<string> crossReferences;
    private string definitionNumber;
    private string pos2;
    private string definition;
    private string shortDefinition;

    // Constructor
    public WordEventArgs() : base() { }

    // public getter/setter methods.
    public string Headword 
    {
      get { return headword; }
      set { headword = value; }
    }
    public string Pos1
    {
      get { return pos1; }
      set { pos1 = value; }
    }
    public string Pronunciation
    {
      get { return pronunciation; }
      set { pronunciation = value; }
    }
    public List<string> SemainticFields
    {
      get { return semanticFields; }
      set { semanticFields = value; }
    }
    public List<string> SocialUsage
    {
      get { return socialUsage; }
      set { socialUsage = value; }
    }
    public List<string> CrossReferences
    {
      get { return crossReferences; }
      set { crossReferences = value; }
    }
    public string DefinitionNumber
    {
      get { return definitionNumber; }
      set { definitionNumber = value; }
    }
    public string Pos2
    {
      get { return pos2; }
      set { pos2 = value; }
    }
    public string Definition
    {
      get { return definition; }
      set { definition = value; }
    }
    public string ShortDefinition
    {
      get { return shortDefinition; }
      set { shortDefinition = value; }
    }  
  } // End class WordEventArgs
} // End namespace SpellChecker