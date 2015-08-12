using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DictionaryEditor
{
  public class Headword
  {
    private String headword;
    private String POS;
    private String pronunciation;
    private String semantics;
    private String socialUsage;
    private String crossReferences;
    private List<Definition> definitions;
    private List<Illustration> illustrations;

    public Headword()
    {
    }

    public string getHeadword
    {
      get { return headword; }
      set { headword = value; }
    }
    public String PartOfSpeech
    {
      get { return POS; }
      set { POS = value; }
    }
    public String Pronunciation
    {
      get { return pronunciation; }
      set { pronunciation = value; }
    }



  }
  class Definition
  {
    private String headword;
    private String POS;
    private String definition;
    private String shortDefinition;
    private int headwordID;
    private int defNumber;
    private List<Illustration> illustrations;

    public Definition()
    {
    }
  }
  class Illustration
  {
    private String illustration;
    private String translation;
    private String headword;
    private int headwordID;
    private int defNumber;
    private int illustrationNumber = 0;
    private int illustrationID;

    public Illustration()
    {
    }
  }
}
