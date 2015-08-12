using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DictionaryEditor
{
  public class Headword
  {
    private String myHeadword;
    private String partOfSpeech;
    private String pronunciation;
    private String semantics;
    private String socialUsage;
    private String crossReferences;
    private List<Definition> definitions;
    private List<Illustration> illustrations;

    public Headword() 
    {
      definitions = new List<Definition>();
      illustrations = new List<Illustration>();
    }
    public Headword(String headword)
    {
      this.myHeadword = headword;
      definitions = new List<Definition>();
      illustrations = new List<Illustration>();
    }

    // Public getter/setter methods.
    public string HeadwordProp
    {
      get { return myHeadword; }
      set { myHeadword = value; }
    }
    public String PartOfSpeech
    {
      get { return partOfSpeech; }
      set { partOfSpeech = value; }
    }
    public String Pronunciation
    {
      get { return pronunciation; }
      set { pronunciation = value; }
    }
    public String Semantics
    {
      get { return semantics; }
      set { semantics = value; }
    }
    public String SocialUsage
    {
      get { return socialUsage; }
      set { socialUsage = value; }
    }
    public String CrossReferences
    {
      get { return crossReferences; }
      set { crossReferences = value; }
    }
    public List<Definition> Definitions
    {
      get { return definitions; }
      set { definitions = value; }
    }
    public List<Illustration> Illustrations
    {
      get { return illustrations; }
      set { illustrations = value; }
    }
  }

  public class Definition
  {
    private String defHeadword;
    private String partOfSpeech;
    private String definition;
    private String shortDefinition;
    private int headwordID;
    private int defNumber;
    private List<Illustration> illustrations;

    public Definition(int number) 
    {
      defNumber = number;
      illustrations = new List<Illustration>();
    }

    // Public getter/setter properties.
    public String HeadwordProp
    {
      get { return defHeadword; }
      set { defHeadword = value; }
    }
    public String PartOfSpeech
    {
      get { return this.partOfSpeech; }
      set { this.partOfSpeech = value; }
    }
    public String DefinitionProp
    {
      get { return definition; }
      set { definition = value; }
    }
    public String ShortDefinition
    {
        get { return shortDefinition; }
        set { shortDefinition = value; }
    }
    public int HeadwordID
    {
      get { return headwordID; }
      set 
      { 
        headwordID = value;
        foreach (Illustration i in illustrations)
          i.HeadwordID = value;
      }
    }
    public int DefNumber
    {
      get { return defNumber; }
      set
      {
        defNumber = value;
        foreach (Illustration i in illustrations)
          i.DefinitionNumber = value;
      }
    }  
    public List<Illustration> Illustrations
    {
        get { return illustrations; }
        set { illustrations = value; }
    }

  }

  public class Illustration
  {
    private String headword;
    private String illustrationDescription;
    private String translation;
    private int defNumber;
    private int headwordID;
    private int illustrationNumber;
    private int illustrationID;

    public Illustration(int num)
    {
        illustrationNumber = num;
    }

    public String IllDescription
    {
        get { return illustrationDescription; }
        set { illustrationDescription = value; }
    }
    public String Translation
    {
      get { return translation; }
      set { translation = value; }
    }
    public String Headword
    {
      get { return headword; }
      set { headword = value; }
    }
    public int HeadwordID
    {
      get { return headwordID; }
      set { headwordID = value; }
    }
    public int DefinitionNumber
    {
      get { return defNumber; }
      set { defNumber = value; }
    }
    public int IllustrationNumber
    {
      get { return illustrationNumber; }
      set { illustrationNumber = value; }
    }
    public int IllustrationID
    {
      get { return illustrationID; }
      set { illustrationID = value; }
    }
  }
}
